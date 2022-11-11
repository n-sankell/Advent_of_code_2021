package Dec_04;

public class BingoBoard {

    public Number[][] board = new Number[5][5];
    public boolean addMe = true;
    public Number lastNumber;

    public void printBoard() {
        for (Number[] row : board) {
            for (Number number : row) {
                if (Integer.toString(number.value).length() == 1) {
                    System.out.print(" " + number.value + " ");
                } else {
                    System.out.print(number.value + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public int calculateScore() {
        int score = 0;
        for (Number[] row : board) {
            for (Number number : row) {
                if (!number.drawn) {
                    score += number.value;
                    System.out.println(score);
                }
            }
        }
        return score * lastNumber.value;
    }

    public void setLastNumber(Number lastNumber) {
        System.out.println("WINNING NUMBER: " + lastNumber.value);
        this.lastNumber = lastNumber;
    }

    public boolean isWinner() {
        for (int i = 0; i < 5; i++) {
            if (board[i][0].drawn && board[i][1].drawn && board[i][2].drawn && board[i][3].drawn && board[i][4].drawn) {
                return true;
            }
            if (board[0][i].drawn && board[1][i].drawn && board[2][i].drawn && board[3][i].drawn && board[4][i].drawn) {
                return true;
            }
        }
        return false;
    }

    public void addRow(String row, int rowNumber) {
        StringBuilder number = new StringBuilder();
        int counter = 0;
        number.append(row.charAt(0));
        for (int i = 1; i < row.length(); i++) {
            if (i % 3 == 0) {
                String result = number.toString();
                board[rowNumber][counter] = new Number();
                board[rowNumber][counter].value = Integer.parseInt(result.trim());
                number = new StringBuilder();
                counter++;
            }
            number.append(row.charAt(i));
            if (i == row.length()-1) {
                String result = number.toString();
                board[rowNumber][counter] = new Number();
                board[rowNumber][counter].value = Integer.parseInt(result.trim());
            }
        }
    }

}
