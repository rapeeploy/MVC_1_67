package view;
import javax.swing.*;

import controller.Controller;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class View extends JFrame {
    private JLabel cowLabel;
    private JLabel pinLabel;
    private JButton rollButton;
    public JTextArea resultArea;

    private int currentCow = 0; // เริ่มจากวัวตัวแรก (index 0)
    private int currentThrow = 0; // เก็บจำนวนครั้งที่วัวโยน (0 หรือ 1)
    private int totalCows = 9; // วัวทั้งหมด 9 ตัว (3 ทีม ๆ ละ 3 ตัว)
    public int remainingPins; // จำนวนพินที่เหลือสำหรับวัวปัจจุบัน
    public int knockedDown;
    private int[] pins = new int[totalCows]; // พินที่เหลือของวัวแต่ละตัว
    private Random random = new Random();
    private Controller controller;

    public View() {
        setTitle("Bowling Game Simulation");
        setSize(700, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ตั้งค่าพินเริ่มต้นให้แต่ละวัว
        for (int i = 0; i < totalCows; i++) {
            pins[i] = 10; // พินเริ่มต้น 10 พินสำหรับวัวทุกตัว
        }

        // ส่วนแสดงข้อมูลวัวปัจจุบันและพินที่เหลืออยู่
        cowLabel = new JLabel();
        cowLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(cowLabel, BorderLayout.NORTH);

        pinLabel = new JLabel("Pins Remaining: 10");
        pinLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(pinLabel, BorderLayout.CENTER);

        // ปุ่มโยนลูกโบว์ลิ่ง
        rollButton = new JButton("Roll the ball");
        rollButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rollBall();
            }
        });
        add(rollButton, BorderLayout.SOUTH);

        // พื้นที่แสดงผล
        resultArea = new JTextArea();
        add(new JScrollPane(resultArea), BorderLayout.EAST);

        updateCowLabel(); // อัปเดตวัวที่กำลังโยน
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }
    // ฟังก์ชันสุ่มจำนวนพินที่ล้ม
    private void rollBall() {
        int remainingPins = pins[currentCow]; // จำนวนพินที่เหลือสำหรับวัวปัจจุบัน
        int knockedDown = random.nextInt(remainingPins + 1); // สุ่มจำนวนพินที่ล้ม
        pins[currentCow] -= knockedDown; // หักพินที่ล้มออกจากพินที่เหลือ

        resultArea.append("Cow No. " + (currentCow + 1) + " knocked down " + knockedDown + " pins.\n");
        //TODO controller.ส่งค่า knocked down remainingPins
        pinLabel.setText("Pins Remaining: " + pins[currentCow]);

        // ตรวจสอบว่าครบสองครั้งหรือยัง
        currentThrow++;
        if (currentThrow == 2) {
            // ถ้าโยนครบ 2 ครั้งแล้ว ให้เปลี่ยนไปวัวตัวถัดไป
            currentCow++;
            currentThrow = 0; // รีเซ็ตจำนวนการโยน
        }

        // วนกลับไปที่วัวตัวแรกเมื่อครบ 9 ตัว
        if (currentCow >= totalCows) {
            currentCow = 0;
            //TODO ประกาศผู้ชนะ resultArea.append(controller.determineWinner());
            resultArea.append("--- New Round ---\n");
            // resultArea.append();
            // รีเซ็ตพินสำหรับรอบใหม่ (ถ้าต้องการ)
            for (int i = 0; i < totalCows; i++) {
                pins[i] = 10; // รีเซ็ตพินทุกตัวให้เป็น 10
            }
        }

        updateCowLabel(); // อัปเดตวัวปัจจุบัน
    }

    // อัปเดตข้อความแสดงวัวปัจจุบัน
    private void updateCowLabel() {
        String cowColor;
        int cowNumber = (currentCow % 3) + 1; // คำนวณลำดับของวัวแต่ละตัว (1-3)

        if (currentCow < 3) {
            cowColor = "White";
        } else if (currentCow < 6) {
            cowColor = "Black";
        } else {
            cowColor = "Brown";
        }

        cowLabel.setText("Cow No. " + cowNumber + ": " + cowColor + " (Throw " + (currentThrow + 1) + ")");
    }


}
