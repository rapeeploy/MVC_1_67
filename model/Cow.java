package model;

import java.util.Random;

public class Cow {
    private int number;
    private int error_report;

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setError_report(int error_report) {
        this.error_report = error_report;
    }

    public int getError_report() {
        return error_report;
    }

    public boolean is_right_report() {
        Random rn = new Random();
        int answer = rn.nextInt(100) + 1;

        return answer <= error_report ? true : false;
    }
}
