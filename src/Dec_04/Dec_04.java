package Dec_04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Dec_04 {

    private final List<String[]> drawNumbers = new ArrayList<>();
    private final List<String[]> bingoBoards = new ArrayList<>();
    private final List<Integer> numbersToDraw = new ArrayList<>();
    private final List<BingoBoard> bingoBoardList = new ArrayList<>();

    public static void main(String[] args) {
        new Dec_04();
    }

    public Dec_04() {
        readFile();
        addNumbersToList();
        createBingoBoards();
        printBingoBoards();
        BingoBoard winner = drawNumbersB();
        System.out.println("THE LAST WINNER IS: ");
        winner.printBoard();
        System.out.println("WINNING NUMBER: " + winner.lastNumber.value);
        System.out.println("SCORE: " + winner.calculateScore());
    }

    public BingoBoard drawNumbers() {
        for (Integer number : numbersToDraw) {
            for (BingoBoard bingoBoard : bingoBoardList) {
                for (Number[] numbers : bingoBoard.board) {
                    for (Number bingoNumber : numbers) {
                        if (bingoNumber.value == number) {
                            bingoNumber.drawn = true;
                        }
                        if (bingoBoard.isWinner()) {
                            bingoBoard.setLastNumber(bingoNumber);
                            return bingoBoard;
                        }
                    }
                }
            }
        }
        return null;
    }

    public BingoBoard drawNumbersB() {
        List<BingoBoard> winners = new ArrayList<>();
            for (Integer number : numbersToDraw) {
                for (BingoBoard bingoBoard : bingoBoardList) {
                    for (Number[] numbers : bingoBoard.board) {
                        for (Number bingoNumber : numbers) {
                            if (bingoNumber.value == number) {
                                bingoNumber.drawn = true;
                            }
                            if (bingoBoard.isWinner()) {
                                if (bingoBoard.addMe) {
                                    bingoBoard.addMe = false;
                                    bingoBoard.setLastNumber(bingoNumber);
                                    winners.add(bingoBoard);
                                }
                            }
                            if (bingoBoardList.stream().allMatch(board -> board.isWinner())) {
                                return winners.get(winners.size()-1);
                            }
                        }
                    }
                }
            }
        return null;
    }

    public void printBingoBoards() {
        for (BingoBoard board : bingoBoardList) {
            board.printBoard();
        }
    }

    public void addNumbersToList() {
        for (String[] numbers : drawNumbers) {
            for (String number: numbers) {
                numbersToDraw.add(Integer.parseInt(number));
            }
        }
    }

    public void createBingoBoards() {
        BingoBoard bingoBoard = null;
        int counter = 0;
        for (String[] bingo : bingoBoards) {
            for (String number : bingo) {
                if (number.isEmpty()) {
                    counter = 0;
                }
                if (counter == 0) {
                    bingoBoard = new BingoBoard();
                } else {
                    bingoBoard.addRow(number, counter -1);
                }
                if (counter == 5) {
                    bingoBoardList.add(bingoBoard);
                }
                counter++;
            }
        }
    }

    public void readFile() {
        String readLine;
        int changeList = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/Dec_04/puzzleInput04.txt"))) {
            while ((readLine = bufferedReader.readLine()) != null) {
                if (readLine.equals("")) {
                    changeList = 1;
                }
                if (changeList == 0) {
                    drawNumbers.add(readLine.split(","));
                } else {
                    String[] bingoBoard = readLine.split("\n");
                    bingoBoards.add(bingoBoard);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
