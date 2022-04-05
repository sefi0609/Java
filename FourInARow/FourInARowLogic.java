package com.example.fourinarow;

public class FourInARowLogic {
    private static int[] place; //place[0] == button1, place[1] == button2 ...
                                //how many circles there are in a column

    public static String turn;//variable to show which player is playing now
    public static String[][] board;//2d array for the board of the game
//constructor for the class, reset all the variables
    public FourInARowLogic(){
        place = new int[7];
        board = new String[6][7];
        turn = "CYAN";
        for(int i = 0; i < 6; i++) {
            place[i] = 0;
            for(int j = 0; j < 7; j++)
                board[i][j] = "0";
        }
    }
//check for the next free row at column
    public static int checkFreeBox(int column){
        if(place[column] == 6)
            return -1;
        else
            return place[column];
    }
    //switch the turn of the players
    public static void switchTurn(){
        if(turn == "CYAN")
            turn = "RED";
        else
            turn = "CYAN";
    }
//return 1 if there is a winner else return 0
    public static int checkForWinner(){
        if(board[0][0].equals(board[0][1]) && board[0][1].equals(board[0][2]) && board[0][2].equals(board[0][3]) && board[0][0] != "0")
            return 1;
        else if(board[0][1].equals(board[0][2]) && board[0][2].equals(board[0][3]) && board[0][3].equals(board[0][4]) && board[0][1] != "0")
            return 1;
        else if(board[0][2].equals(board[0][3]) && board[0][3].equals(board[0][4]) && board[0][4].equals(board[0][5]) && board[0][2] != "0")
            return 1;
        else if(board[0][3].equals(board[0][4]) && board[0][4].equals(board[0][5]) && board[0][5].equals(board[0][6]) && board[0][3] != "0")
            return 1;
        else if(board[1][0].equals(board[1][1]) && board[1][1].equals(board[1][2]) && board[1][2].equals(board[1][3]) && board[1][0] != "0")
            return 1;
        else if(board[1][1].equals(board[1][2]) && board[1][2].equals(board[1][3]) && board[1][3].equals(board[1][4]) && board[1][1] != "0")
            return 1;
        else if(board[1][2].equals(board[1][3]) && board[1][3].equals(board[1][4]) && board[1][4].equals(board[1][5]) && board[1][2] != "0")
            return 1;
        else if(board[1][3].equals(board[1][4]) && board[1][4].equals(board[1][5]) && board[1][5].equals(board[1][6]) && board[1][3] != "0")
            return 1;
        else if(board[2][0].equals(board[2][1]) && board[2][1].equals(board[2][2]) && board[2][2].equals(board[2][3]) && board[2][0] != "0")
            return 1;
        else if(board[2][1].equals(board[2][2]) && board[2][2].equals(board[2][3]) && board[2][3].equals(board[2][4]) && board[2][1] != "0")
            return 1;
        else if(board[2][2].equals(board[2][3]) && board[2][3].equals(board[2][4]) && board[2][4].equals(board[2][5]) && board[2][2] != "0")
            return 1;
        else if(board[2][3].equals(board[2][4]) && board[2][4].equals(board[2][5]) && board[2][5].equals(board[2][6]) && board[2][3] != "0")
            return 1;
        else if(board[3][0].equals(board[3][1]) && board[3][1].equals(board[3][2]) && board[3][2].equals(board[3][3]) && board[3][0] != "0")
            return 1;
        else if(board[3][1].equals(board[3][2]) && board[3][2].equals(board[3][3]) && board[3][3].equals(board[3][4]) && board[3][1] != "0")
            return 1;
        else if(board[3][2].equals(board[3][3]) && board[3][3].equals(board[3][4]) && board[3][4].equals(board[3][5]) && board[3][2] != "0")
            return 1;
        else if(board[3][3].equals(board[3][4]) && board[3][4].equals(board[3][5]) && board[3][5].equals(board[3][6]) && board[3][3] != "0")
            return 1;
        else if(board[4][0].equals(board[4][1]) && board[4][1].equals(board[4][2]) && board[4][2].equals(board[4][3]) && board[4][0] != "0")
            return 1;
        else if(board[4][1].equals(board[4][2]) && board[4][2].equals(board[4][3]) && board[4][3].equals(board[4][4]) && board[4][1] != "0")
            return 1;
        else if(board[4][2].equals(board[4][3]) && board[4][3].equals(board[4][4]) && board[4][4].equals(board[4][5]) && board[4][2] != "0")
            return 1;
        else if(board[4][3].equals(board[4][4]) && board[4][4].equals(board[4][5]) && board[4][5].equals(board[4][6]) && board[4][3] != "0")
            return 1;
        else if(board[5][0].equals(board[5][1]) && board[5][1].equals(board[5][2]) && board[5][2].equals(board[5][3]) && board[5][0] != "0")
            return 1;
        else if(board[5][1].equals(board[5][2]) && board[5][2].equals(board[5][3]) && board[5][3].equals(board[5][4]) && board[5][1] != "0")
            return 1;
        else if(board[5][2].equals(board[5][3]) && board[5][3].equals(board[5][4]) && board[5][4].equals(board[5][5]) && board[5][2] != "0")
            return 1;
        else if(board[5][3].equals(board[5][4]) && board[5][4].equals(board[5][5]) && board[5][5].equals(board[5][6]) && board[5][3] != "0")
            return 1;
        else if(board[0][0].equals(board[1][0]) && board[1][0].equals(board[2][0]) && board[2][0].equals(board[3][0]) && board[0][0] != "0")
            return 1;
        else if(board[1][0].equals(board[2][0]) && board[2][0].equals(board[3][0]) && board[3][0].equals(board[4][0]) && board[1][0] != "0")
            return 1;
        else if(board[2][0].equals(board[3][0]) && board[3][0].equals(board[4][0]) && board[4][0].equals(board[5][0]) && board[2][0] != "0")
            return 1;
        else if(board[0][1].equals(board[1][1]) && board[1][1].equals(board[2][1]) && board[2][1].equals(board[3][1]) && board[0][1] != "0")
            return 1;
        else if(board[1][1].equals(board[2][1]) && board[2][1].equals(board[3][1]) && board[3][1].equals(board[4][1]) && board[1][1] != "0")
            return 1;
        else if(board[2][1].equals(board[3][1]) && board[3][1].equals(board[4][1]) && board[4][1].equals(board[5][1]) && board[2][1] != "0")
            return 1;
        else if(board[0][2].equals(board[1][2]) && board[1][2].equals(board[2][2]) && board[2][2].equals(board[3][2]) && board[0][2] != "0")
            return 1;
        else if(board[1][2].equals(board[2][2]) && board[2][2].equals(board[3][2]) && board[3][2].equals(board[4][2]) && board[1][2] != "0")
            return 1;
        else if(board[2][2].equals(board[3][2]) && board[3][2].equals(board[4][2]) && board[4][2].equals(board[5][2]) && board[2][2] != "0")
            return 1;
        else if(board[0][3].equals(board[1][3]) && board[1][3].equals(board[2][3]) && board[2][3].equals(board[3][3]) && board[0][3] != "0")
            return 1;
        else if(board[1][3].equals(board[2][3]) && board[2][3].equals(board[3][3]) && board[3][3].equals(board[4][3]) && board[1][3] != "0")
            return 1;
        else if(board[2][3].equals(board[3][3]) && board[3][3].equals(board[4][3]) && board[4][3].equals(board[5][3]) && board[2][3] != "0")
            return 1;
        else if(board[0][4].equals(board[1][4]) && board[1][4].equals(board[2][4]) && board[2][4].equals(board[3][4]) && board[0][4] != "0")
            return 1;
        else if(board[1][4].equals(board[2][4]) && board[2][4].equals(board[3][4]) && board[3][4].equals(board[4][4]) && board[1][4] != "0")
            return 1;
        else if(board[2][4].equals(board[3][4]) && board[3][4].equals(board[4][4]) && board[4][4].equals(board[5][4]) && board[2][4] != "0")
            return 1;
        else if(board[0][5].equals(board[1][5]) && board[1][5].equals(board[2][5]) && board[2][5].equals(board[3][5]) && board[0][5] != "0")
            return 1;
        else if(board[1][5].equals(board[2][5]) && board[2][5].equals(board[3][5]) && board[3][5].equals(board[4][5]) && board[1][5] != "0")
            return 1;
        else if(board[2][5].equals(board[3][5]) && board[3][5].equals(board[4][5]) && board[4][5].equals(board[5][5]) && board[2][5] != "0")
            return 1;
        else if(board[0][6].equals(board[1][6]) && board[1][6].equals(board[2][6]) && board[2][6].equals(board[3][6]) && board[0][6] != "0")
            return 1;
        else if(board[1][6].equals(board[2][6]) && board[2][6].equals(board[3][6]) && board[3][6].equals(board[4][6]) && board[1][6] != "0")
            return 1;
        else if(board[2][6].equals(board[3][6]) && board[3][6].equals(board[4][6]) && board[4][6].equals(board[5][6]) && board[2][6] != "0")
            return 1;
        else if(board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && board[2][2].equals(board[3][3]) && board[0][0] != "0")
            return 1;
        else if(board[0][1].equals(board[1][2]) && board[1][2].equals(board[2][3]) && board[2][3].equals(board[3][4]) && board[0][1] != "0")
            return 1;
        else if(board[0][2].equals(board[1][3]) && board[1][3].equals(board[2][4]) && board[2][4].equals(board[3][5]) && board[0][2] != "0")
            return 1;
        else if(board[0][3].equals(board[1][4]) && board[1][4].equals(board[2][5]) && board[2][5].equals(board[3][6]) && board[0][3] != "0")
            return 1;
        else if(board[1][0].equals(board[2][1]) && board[2][1].equals(board[3][2]) && board[3][2].equals(board[4][3]) && board[1][0] != "0")
            return 1;
        else if(board[1][1].equals(board[2][2]) && board[2][2].equals(board[3][3]) && board[3][3].equals(board[4][4]) && board[1][1] != "0")
            return 1;
        else if(board[1][2].equals(board[2][3]) && board[2][3].equals(board[3][4]) && board[3][4].equals(board[4][5]) && board[1][2] != "0")
            return 1;
        else if(board[1][3].equals(board[2][4]) && board[2][4].equals(board[3][5]) && board[3][5].equals(board[4][6]) && board[1][3] != "0")
            return 1;
        else if(board[0][6].equals(board[1][5]) && board[1][5].equals(board[2][4]) && board[2][4].equals(board[3][3]) && board[0][6] != "0")
            return 1;
        else if(board[0][5].equals(board[1][4]) && board[1][4].equals(board[2][3]) && board[2][3].equals(board[3][2]) && board[0][5] != "0")
            return 1;
        else if(board[0][4].equals(board[1][3]) && board[1][3].equals(board[2][2]) && board[2][2].equals(board[1][3]) && board[0][4] != "0")
            return 1;
        else if(board[0][3].equals(board[1][2]) && board[1][2].equals(board[2][1]) && board[2][1].equals(board[3][0]) && board[0][3] != "0")
            return 1;
        else if(board[1][6].equals(board[2][5]) && board[2][5].equals(board[3][4]) && board[3][4].equals(board[4][3]) && board[1][6] != "0")
            return 1;
        else if(board[1][5].equals(board[2][4]) && board[2][4].equals(board[3][3]) && board[3][3].equals(board[4][2]) && board[1][5] != "0")
            return 1;
        else if(board[1][4].equals(board[2][3]) && board[2][3].equals(board[3][2]) && board[3][2].equals(board[4][1]) && board[1][4] != "0")
            return 1;
        else if(board[1][3].equals(board[2][2]) && board[2][2].equals(board[3][1]) && board[3][1].equals(board[4][0]) && board[1][3] != "0")
            return 1;
        else if(board[2][6].equals(board[3][5]) && board[3][5].equals(board[4][4]) && board[4][4].equals(board[5][3]) && board[2][6] != "0")
            return 1;
        else if(board[2][5].equals(board[3][4]) && board[3][4].equals(board[4][3]) && board[4][3].equals(board[4][3]) && board[2][5] != "0")
            return 1;
        else if(board[2][4].equals(board[3][3]) && board[3][3].equals(board[4][2]) && board[4][2].equals(board[5][1]) && board[2][4] != "0")
            return 1;
        else if(board[2][3].equals(board[3][2]) && board[3][2].equals(board[4][1]) && board[4][1].equals(board[5][0]) && board[2][3] != "0")
            return 1;
        else if(board[2][0].equals(board[3][1]) && board[3][1].equals(board[4][2]) && board[4][2].equals(board[5][3]) && board[2][0] != "0")
            return 1;
        else if(board[2][1].equals(board[3][2]) && board[3][2].equals(board[4][3]) && board[4][3].equals(board[5][4]) && board[2][1] != "0")
            return 1;
        else if(board[2][2].equals(board[3][3]) && board[3][3].equals(board[4][4]) && board[4][4].equals(board[5][5]) && board[2][2] != "0")
            return 1;
        else if(board[2][3].equals(board[3][4]) && board[3][4].equals(board[4][5]) && board[4][5].equals(board[5][6]) && board[2][3] != "0")
            return 1;
        return 0;
    }
//calculate where to draw a circle and see if there is a winner
    public static double[] calculateBox(int boxNum, int buttonNum,double hInterval,double vInterval){
        //adjusting the location of the boxes
        double box1 = vInterval+236;
        double box2 = vInterval+178;
        double box3 = vInterval+120;
        double box4 = vInterval+62;
        double box5 = vInterval+3;
        double box6 = vInterval-55;

        double button1 = hInterval/4;
        double button2 = hInterval+20;
        double button3 = hInterval+105;
        double button4 = hInterval+190;
        double button5 = hInterval+275;
        double button6 = hInterval+360;
        double button7 = hInterval+445;
        //
        switch (buttonNum){//go to the button the user clicked on
            //if button number 1 is clicked
            case 1:
                place[0] += 1;
                //if box number 1 is empty
                if(boxNum == 1){
                    board[5][0] = turn;//adjusting the board for the checkForWinner function
                    return new double[] {button1,box1,checkForWinner()};//[0] = x, [1] = y, [2] indicates if there is a winner
                }
                //if box number 2 is empty...
                else if(boxNum == 2){
                    board[4][0] = turn;
                    return new double[] {button1,box2,checkForWinner()};
                }
                else if(boxNum == 3){
                    board[3][0] = turn;
                    return new double[] {button1,box3,checkForWinner()};
                }
                else if(boxNum == 4){
                    board[2][0] = turn;
                    return new double[] {button1,box4,checkForWinner()};
                }
                else if(boxNum == 5){
                    board[1][0] = turn;
                    return new double[] {button1,box5,checkForWinner()};
                }
                else if(boxNum == 6){
                    board[0][0] = turn;
                    return new double[] {button1,box6,checkForWinner()};
                }
                //no need for break because of the return
            //if button number 2 is clicked...
            case 2:
                place[1] += 1;
                //if box number 1 is empty
                if(boxNum == 1){
                    board[5][1] = turn;
                    return new double[] {button2,box1,checkForWinner()};
                }
                //if box number 2 is empty...
                else if(boxNum == 2){
                    board[4][1] = turn;
                    return new double[] {button2,box2,checkForWinner()};
                }
                else if(boxNum == 3){
                    board[3][1] = turn;
                    return new double[] {button2,box3,checkForWinner()};
                }
                else if(boxNum == 4){
                    board[2][1] = turn;
                    return new double[] {button2,box4,checkForWinner()};
                }
                else if(boxNum == 5){
                    board[1][1] = turn;
                    return new double[] {button2,box5,checkForWinner()};
                }
                else if(boxNum == 6){
                    board[0][1] = turn;
                    return new double[] {button2,box6,checkForWinner()};
                }
                //no need for break because of the return
            case 3:
                place[2] += 1;
                if(boxNum == 1){
                    board[5][2] = turn;
                    return new double[] {button3,box1,checkForWinner()};
                }
                else if(boxNum == 2){
                    board[4][2] = turn;
                    return new double[] {button3,box2,checkForWinner()};
                }
                else if(boxNum == 3) {
                    board[3][2] = turn;
                    return new double[]{button3, box3,checkForWinner()};
                }
                 else if(boxNum == 4){
                    board[2][2] = turn;
                    return new double[] {button3,box4,checkForWinner()};
                }
                else if(boxNum == 5){
                    board[1][2] = turn;
                    return new double[] {button3,box5,checkForWinner()};
                }
                else if(boxNum == 6){
                    board[0][2] = turn;
                    return new double[] {button3,box6,checkForWinner()};
                }
                //no need for break because of the return
            case 4:
                place[3] += 1;
                if(boxNum == 1){
                    board[5][3] = turn;
                    return new double[] {button4,box1,checkForWinner()};
                }
                else if(boxNum == 2){
                    board[4][3] = turn;
                    return new double[] {button4,box2,checkForWinner()};
                }
                else if(boxNum == 3) {
                    board[3][3] = turn;
                    return new double[]{button4, box3,checkForWinner()};
                }
                else if(boxNum == 4){
                    board[2][3] = turn;
                    return new double[] {button4,box4,checkForWinner()};
                }
                else if(boxNum == 5){
                    board[1][3] = turn;
                    return new double[] {button4,box5,checkForWinner()};
                }
                else if(boxNum == 6){
                    board[0][3] = turn;
                    return new double[] {button4,box6,checkForWinner()};
                }
                //no need for break because of the return
            case 5:
                place[4] += 1;
                if(boxNum == 1){
                    board[5][4] = turn;
                    return new double[] {button5,box1,checkForWinner()};
                }
                else if(boxNum == 2){
                    board[4][4] = turn;
                    return new double[] {button5,box2,checkForWinner()};
                }
                else if(boxNum == 3) {
                    board[3][4] = turn;
                    return new double[]{button5, box3,checkForWinner()};
                }
                else if(boxNum == 4){
                    board[2][4] = turn;
                    return new double[] {button5,box4,checkForWinner()};
                }
                else if(boxNum == 5){
                    board[1][4] = turn;
                    return new double[] {button5,box5,checkForWinner()};
                }
                else if(boxNum == 6) {
                    board[0][4] = turn;
                    return new double[]{button5, box6,checkForWinner()};
                }
                //no need for break because of the return
            case 6:
                place[5] += 1;
                if(boxNum == 1){
                    board[5][5] = turn;
                    return new double[] {button6,box1,checkForWinner()};
                }
                else if(boxNum == 2){
                    board[4][5] = turn;
                    return new double[] {button6,box2,checkForWinner()};
                }
                else if(boxNum == 3) {
                    board[3][5] = turn;
                    return new double[]{button6, box3,checkForWinner()};
                }
                else if(boxNum == 4){
                    board[2][5] = turn;
                    return new double[] {button6,box4,checkForWinner()};
                }
                else if(boxNum == 5){
                    board[1][5] = turn;
                    return new double[] {button6,box5,checkForWinner()};
                }
                else if(boxNum == 6){
                    board[0][5] = turn;
                    return new double[] {button6,box6,checkForWinner()};
                }
                //no need for break because of the return
            case 7:
                place[6] += 1;
                if(boxNum == 1){
                    board[5][6] = turn;
                    return new double[] {button7,box1,checkForWinner()};
                }
                else if(boxNum == 2){
                    board[4][6] = turn;
                    return new double[] {button7,box2,checkForWinner()};
                }
                else if(boxNum == 3) {
                    board[3][6] = turn;
                    return new double[]{button7, box3,checkForWinner()};
                }
                else if(boxNum == 4){
                    board[2][6] = turn;
                    return new double[] {button7,box4,checkForWinner()};
                }
                else if(boxNum == 5){
                    board[1][6] = turn;
                    return new double[] {button7,box5,checkForWinner()};
                }
                else if(boxNum == 6) {
                    board[0][6] = turn;
                    return new double[]{button7, box6,checkForWinner()};
                }
                //no need for break because of the return
        }
        return new double[] {0,0};//must return something outside the switch
    }
}
