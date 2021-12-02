import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Dec_01 {

    List<Integer> countIncreases = new ArrayList<>();

    public static void main(String[] args) {
        new Dec_01();
    }

    public Dec_01() {
        countIncreases = readFile();
        int numberOfIncreases = countNumbers(countIncreases);
        System.out.println(numberOfIncreases);
        int numberOfBlocks = countBlocks(countIncreases);
        System.out.println(numberOfBlocks);
    }

    public List<Integer> readFile() {
        String readLine;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/puzzleInput01.txt"));) {
            while ((readLine = bufferedReader.readLine()) != null) {
                countIncreases.add(Integer.parseInt(readLine));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countIncreases;
    }

    public int countNumbers(List<Integer> countIncreases) {
        int count = 0;
        int lastNumber = 0;
        for (Integer number : countIncreases) {
            if (number > lastNumber && lastNumber != 0) {
                count++;
            }
            lastNumber = number;
        }
        return count;
    }

    public int countBlocks(List<Integer> countIncreases) {
        int count = 0;
        int blockA;
        int blockB;
        for (int i = 0; i < countIncreases.size()-3; i++) {
            blockA = countIncreases.get(i) + countIncreases.get(i+1) + countIncreases.get(i+2);
            blockB = countIncreases.get(i+1) + countIncreases.get(i+2) + countIncreases.get(i+3);
            if (blockA < blockB) {
                count++;
            }
        }
        return count;
    }

}
