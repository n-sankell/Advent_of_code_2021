package Dec_05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dec_05 {

    public List<Integer> X_S = new ArrayList<>();
    public List<Integer> X_E = new ArrayList<>();
    public List<Integer> Y_S = new ArrayList<>();
    public List<Integer> Y_E = new ArrayList<>();
    public int[][] intGrid;
    int maxX;
    int maxY;
    int minX;
    int minY;

    public static void main(String[] args) {
        new Dec_05();
    }

    public Dec_05() {
        readFile();
        findHighestAndLowest();
        countCoordinates();
        countIntersections();
    }

    public void readFile() {
        String readLine;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/Dec_05/puzzleInput05.txt"))) {
            while ((readLine = bufferedReader.readLine()) != null) {
                String[] middleSplit = readLine.split(" -> ");
                String[] startCoordinates = middleSplit[0].split(",");
                String[] endCoordinates = middleSplit[1].split(",");
                X_S.add(Integer.parseInt(startCoordinates[0]));
                X_E.add(Integer.parseInt(endCoordinates[0]));
                Y_S.add(Integer.parseInt(startCoordinates[1]));
                Y_E.add(Integer.parseInt(endCoordinates[1]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void findHighestAndLowest() {
        minX = Math.min(Collections.min(X_S), Collections.min(X_E));
        minY = Math.min(Collections.min(Y_S), Collections.min(Y_E));
        maxX = Math.max(Collections.max(X_S), Collections.max(X_E));
        maxY = Math.max(Collections.max(Y_S), Collections.max(Y_E));
    }

    public void countCoordinates() {
        intGrid = new int[1000][1000];
        for (int i = 0; i < 500; i++) {
            if (X_S.get(i).equals(X_E.get(i))) {
                int minYa = Math.min(Y_S.get(i), Y_E.get(i));
                int maxYa = Math.max(Y_S.get(i), Y_E.get(i));
                for (int j = minYa; j <= maxYa; j++) {
                    intGrid[X_S.get(i)][j] += 1;
                }
            } else if (Y_S.get(i).equals(Y_E.get(i))) {
                int minXb = Math.min(X_S.get(i), X_E.get(i));
                int maxXb = Math.max(X_S.get(i), X_E.get(i));
                for (int j = minXb; j <= maxXb; j++) {
                    intGrid[j][Y_S.get(i)] += 1;
                }
            } else {

                int indexX;
                if (X_S.get(i) < X_E.get(i)) {
                    indexX = 1;
                } else {
                    indexX = -1;
                }
                int indexY;
                if (Y_S.get(i) < Y_E.get(i)) {
                    indexY = 1;
                } else {
                    indexY = -1;
                }

                int range = Math.abs(X_S.get(i) - X_E.get(i));
                for (int j = 0; j <= range; j++) {
                    System.out.println(j);
                    intGrid[X_S.get(i) + indexX * j][Y_S.get(i) + indexY * j] += 1;
                }
            }
        }
    }

    public void countIntersections() {
        int count = 0;
        for (int[] ints : intGrid) {
            for (int i : ints) {
                if (i > 1) {
                    count++;
                }
                if (i == 0) {
                    System.out.print(".");
                } else {
                    System.out.print(i);
                }
            }
            System.out.println();
        }
        System.out.println(count);
    }

}
