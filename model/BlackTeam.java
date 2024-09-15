package model;

public class BlackTeam {
    Cow white_no1 = new Cow();
    Cow white_no2 = new Cow();
    Cow white_no3 = new Cow();

    // private int[][] score = new int[3][2];

    private int[][] overall_score = new int[10][3];
    
    int times;
    private int sum;
    private int thisRound;
    private int round_when_Update;
    private int nRound;

    public void setScore(int p) {
        this.overall_score[thisRound][times] = p;
        this.overall_score[thisRound][2] = this.overall_score[thisRound][0] + this.overall_score[thisRound][1];
    }

    public int getSum() {
        for (int i = 0; i < 10; i++) {
                sum += overall_score[i][2];
        }
        return sum;
    }

    public void setSum(int sum) {
        
    }

    public void setError_report() {
        this.white_no1.setError_report(10);
        this.white_no2.setError_report(10);
        this.white_no3.setError_report(10);
    }

    public void calculatePoint(boolean isError,int remainingPins, int knockedDown) {
        //*lied */
        if (isError) {
//TODO
            if (times == 0) {
                //* strike */
                if (remainingPins - knockedDown == 0) {
                    // setScore(10, c.getNumber(), 0);
                    // exit
                    setScore(10);
                    times = -1; // exit
                    round_when_Update = thisRound + 6;
                    nRound = 2;
                //* open */
                } else {
                    setScore(0);
                    times = -1;
                }
                
            } else {
                //* spare */
                if (remainingPins - knockedDown == 0) {
                    times = 0;
                    setScore(0);
                    times = 1;
                    setScore(10);
                    times = -1;
                    round_when_Update = thisRound + 3;
                    nRound = 1;
                } else {
                    //* ล้มไม่หมด */
                    setScore(0);
                    times = -1;
                }
            }

        //*not lied */
        } else {
            //* strike */
            if (times == 0) {
                if (remainingPins - knockedDown == 0) {
                    setScore(10);
                    times = -1;
                    round_when_Update = thisRound + 6;
                    nRound = 2;

                //* open */
                } else {
                    setScore(knockedDown);
                    times++;
                }
            } else {
                //* spare */
                if (remainingPins - knockedDown == 0) {
                    times = 0;
                    setScore(0);
                    times = 1;
                    setScore(10);
                    round_when_Update = thisRound + 3;
                    nRound = 1;
                    times = -1;
                //* open */
                } else {
                    setScore(knockedDown);
                    times = -1;
                }
            }
        }
    }

    public void updateScore() {
        if (thisRound == round_when_Update ) {
            if (nRound == 1) {
                // เพิ่มคะแนนสำหรับ Cow Spare
                overall_score[thisRound - 3][2] += overall_score[thisRound][2];
            } else if (nRound == 2) {
                // เพิ่มคะแนนสำหรับ Cow Strike
                overall_score[thisRound - 6][2] += overall_score[thisRound - 3][2] + overall_score[thisRound - 6][2];
            }
        }
    }
}
