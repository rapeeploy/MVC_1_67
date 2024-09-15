package controller;

import model.BlackTeam;
import model.BrownTeam;
import model.WhiteTeam;
import view.View;

import java.util.Random;

public class Controller {
    private BlackTeam blackTeam;
    private BrownTeam brownTeam;
    private WhiteTeam whiteTeam;
    private View view;
    private Random random;
    private int cowTurn;  // Keep track of which cow and team should roll next

    public Controller(View view) {
        this.view = view;
        this.blackTeam = new BlackTeam();
        this.brownTeam = new BrownTeam();
        this.whiteTeam = new WhiteTeam();
        this.random = new Random();
        this.cowTurn = 0;  // Start with the first cow of the white team
        this.view.setController(this); // Set controller for the view
    }

    //TODO methid สำหรับรับส่งค่าไปคำนวน
      
    public void updateView() {
        int whiteScore = whiteTeam.getSum();
        int blackScore = blackTeam.getSum();
        int brownScore = brownTeam.getSum();
        // view.updateScoreTable(whiteScore, blackScore, brownScore);
    }

    public String determineWinner() {
        int blackScore = blackTeam.getSum();
        int brownScore = brownTeam.getSum();
        int whiteScore = whiteTeam.getSum();
        if (blackScore > brownScore && blackScore > whiteScore) {
            return "Black Team wins!";
        } else if (brownScore > blackScore && brownScore > whiteScore) {
            return "Brown Team wins!";
        } else if (whiteScore > blackScore && whiteScore > brownScore) {
            return "White Team wins!";
        } else {
            return "It's a tie!";
        }
    }
}
