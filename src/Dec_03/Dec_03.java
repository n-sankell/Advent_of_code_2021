package Dec_03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Dec_03 {

    private List<String> binaryInput = new ArrayList<>();
    private List<String> oxygenRatings = new ArrayList<>();
    private List<String> co2Ratings = new ArrayList<>();
    private StringBuilder binaryGammaRate = new StringBuilder("");
    private StringBuilder binaryEpsilonRate = new StringBuilder("");
    private int decimalGammaRate = 0;
    private int decimalEpsilonRate = 0;
    private int decimalOxygenRating = 0;
    private int decimalCo2Rating = 0;
    private int diagnostics;
    private int consumption;

    public static void main(String[] args) {
        new Dec_03();
    }

    public Dec_03() {
        readFile();
        getRatesFromInput();
        calculateConsumption();
        findOxygenRating();
        findCO2Rating();
        calculateRatings();
        System.out.println(oxygenRatings);
        System.out.println(co2Ratings);
        System.out.println(diagnostics);
    }

    public void readFile() {
        String readLine;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/Dec_03/puzzleInput03.txt"));) {
            while ((readLine = bufferedReader.readLine()) != null) {
                binaryInput.add(readLine);
                oxygenRatings.add(readLine);
                co2Ratings.add(readLine);
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

    public void findOxygenRating() {
        for (int i = 0; i < 12; i++) {
            int countedZeroes = 0;
            int countedOnes = 0;
            for (String binary : oxygenRatings) {
                if (binary.charAt(i) == '0') {
                    countedZeroes++;
                } else if (binary.charAt(i) == '1') {
                    countedOnes++;
                }
            }
            if (oxygenRatings.size() > 1) {
                if (countedOnes < countedZeroes) {
                    int finalI2 = i;
                    oxygenRatings.removeIf(binary -> binary.charAt(finalI2) == '1');
                } else if (countedOnes == countedZeroes) {
                    int finalI1 = i;
                    oxygenRatings.removeIf(binary -> binary.charAt(finalI1) == '0');
                } else {
                    int finalI = i;
                    oxygenRatings.removeIf(binary -> binary.charAt(finalI) == '0');
                }
            }
        }
    }

    public void findCO2Rating() {
        for (int i = 0; i < 12; i++) {
            int countedZeroes = 0;
            int countedOnes = 0;
            for (String binary : co2Ratings) {
                if (binary.charAt(i) == '0') {
                    countedZeroes++;
                } else if (binary.charAt(i) == '1') {
                    countedOnes++;
                }
            }
            if (co2Ratings.size() > 1) {
                if (countedOnes < countedZeroes) {
                    int finalI1 = i;
                    co2Ratings.removeIf(binary -> binary.charAt(finalI1) == '0');
                } else if (countedOnes == countedZeroes) {
                    int finalI = i;
                    co2Ratings.removeIf(binary -> binary.charAt(finalI) == '1');
                } else {
                    int finalI2 = i;
                    co2Ratings.removeIf(binary -> binary.charAt(finalI2) == '1');
                }
            }
        }
    }

    public void calculateRatings() {
        decimalOxygenRating = Integer.parseInt(oxygenRatings.get(0),2);
        decimalCo2Rating = Integer.parseInt(co2Ratings.get(0),2);
        diagnostics = decimalOxygenRating * decimalCo2Rating;
    }

    public void calculateConsumption() {
        decimalGammaRate = Integer.parseInt(String.valueOf(binaryGammaRate),2);
        decimalEpsilonRate = Integer.parseInt(String.valueOf(binaryEpsilonRate),2);
        consumption = decimalGammaRate * decimalEpsilonRate;
    }

}
