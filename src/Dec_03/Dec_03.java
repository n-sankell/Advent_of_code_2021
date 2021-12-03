package Dec_03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Dec_03 {

    private List<String> binaryInput = new ArrayList<>();
    private StringBuilder binaryGammaRate = new StringBuilder("");
    private StringBuilder binaryEpsilonRate = new StringBuilder("");
    private int decimalGammaRate = 0;
    private int decimalEpsilonRate = 0;
    private int consumption;

    public static void main(String[] args) {
        new Dec_03();
    }

    public Dec_03() {
        readFile();
        getRatesFromInput();
        calculateConsumption();
        System.out.println(binaryGammaRate);
        System.out.println(binaryEpsilonRate);
        System.out.println(decimalGammaRate);
        System.out.println(decimalEpsilonRate);
        System.out.println(consumption);
    }

    public void readFile() {
        String readLine;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/Dec_03/puzzleInput03.txt"));) {
            while ((readLine = bufferedReader.readLine()) != null) {
                binaryInput.add(readLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getRatesFromInput() {
        for (int i = 0; i < 12; i++) {
            int countedZeroes = 0;
            int countedOnes = 0;
            for (String binary : binaryInput) {
                if (binary.charAt(i) == '0') {
                    countedZeroes++;
                } else if (binary.charAt(i) == '1') {
                    countedOnes++;
                }
            }
            if (countedOnes < countedZeroes) {
                binaryGammaRate.append("0");
                binaryEpsilonRate.append("1");
            } else {
                binaryGammaRate.append("1");
                binaryEpsilonRate.append("0");
            }
        }
    }

    public void calculateConsumption() {
        decimalGammaRate = Integer.parseInt(String.valueOf(binaryGammaRate),2);
        decimalEpsilonRate = Integer.parseInt(String.valueOf(binaryEpsilonRate),2);
        consumption = decimalGammaRate * decimalEpsilonRate;
    }

}
