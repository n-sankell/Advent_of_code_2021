package Dec_08;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Dec_08 {

    List<SevenSegment> sevenSegments = new ArrayList<>();

    public static void main(String[] args) {
        new Dec_08();
    }

    public Dec_08() {
        readFile();
        printAll();
        System.out.println(sevenSegments.stream().mapToInt(i -> i.decodeOutput()).sum());
    }

    public int countDigits(int digit) {
        int count = 0;
        for (SevenSegment segment : sevenSegments) {
            for (String entry : segment.output) {
                if (entry.length() == digit) {
                    count++;
                }
            }
        }
        return count;
    }

    public void printAll() {
        for (SevenSegment segment : sevenSegments) {
            segment.printSegment();
            System.out.println();
        }
    }

    public void readFile() {
        String readLine;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/Dec_08/puzzleInput08.txt"))) {
            while ((readLine = bufferedReader.readLine()) != null) {
                sevenSegments.add(new SevenSegment(readLine.replace(" | ", "_").split("_")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
