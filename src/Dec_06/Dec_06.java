package Dec_06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dec_06 {

    public final List<Integer> allFish = new ArrayList<>();
    public final long[] fishStates = new long[9];

    public static void main(String[] args) {
        new Dec_06();
    }

    public Dec_06() {
        readFile();
        System.out.println(allFish.size());
        fillArray();
        printArray();
        cycleDays(256);
        printArray();
        System.out.println(allFish.size());
    }

    public void cycleDays(int days) {
        for (int day = 0; day < days; day++) {
            long slot0 = fishStates[0];
            System.arraycopy(fishStates, 1, fishStates, 0, fishStates.length - 1);
            fishStates[6] += slot0;
            fishStates[8] = slot0;

            printArray();
        }
    }

    public void fillArray() {
        for (int i = 0; i < 9; i++) {
            int finalI = i;
            fishStates[i] = allFish.stream().filter(f -> f == finalI).toList().size();
        }
    }

    public void printArray() {
        long total = 0;
        for (int i = 0; i < 9; i++) {
            System.out.println("DAY: " + i + " NUMBER OF FISH: " + fishStates[i]);
            total += fishStates[i];
        }
        System.out.println("TOTAL: " + total);
        System.out.println();
    }

    public void breedFish() {
        for (int day = 0; day < 80; day++) {
            int count = 0;
            for (int i = 0; i < allFish.size(); i++) {
                if (allFish.get(i) == 0) {
                    count++;
                }
                allFish.set(i, allFish.get(i) -1);
            }
            for (int i = 0; i < allFish.size(); i++) {
                if (allFish.get(i) == -1) {
                    allFish.set(i, 6);
                }
            }
            for (int j = 0; j < count; j++) {
                allFish.add(8);
            }
        }
    }

    public void readFile() {
        String readLine;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/Dec_06/puzzleInput06.txt"))) {
            while ((readLine = bufferedReader.readLine()) != null) {
                Arrays.stream(readLine.split(",")).forEach(s -> allFish.add(Integer.parseInt(s)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
