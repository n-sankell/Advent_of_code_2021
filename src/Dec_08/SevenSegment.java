package Dec_08;

import java.util.Arrays;
import java.util.Map;

public class SevenSegment {

    public String[] input;
    public String[] output;
    public Pattern pattern;

    public SevenSegment(String[] inputArray) {
        fillArrays(inputArray);
    }

    private void fillArrays(String[] inputArray) {
        input = inputArray[0].split(" ");
        output = inputArray[1].trim().split(" ");
        pattern = new Pattern();
        pattern.analyzePattern(input);
        pattern.printMap();
    }

    public void printSegment() {
        System.out.println("INPUT: ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String inputString : input) {
            stringBuilder.append(inputString).append("-");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        System.out.println(stringBuilder);

        System.out.println("OUTPUT: ");
        stringBuilder = new StringBuilder();
        for (String outputString : output) {
            stringBuilder.append(outputString).append("-");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        System.out.println(stringBuilder);
    }

    public int decodeOutput() {
        StringBuilder outputBuilder = new StringBuilder();
        for (String outputString : output) {
            outputBuilder.append(decodeString(outputString));
        }
        return Integer.parseInt(String.valueOf(outputBuilder));
    }

    private String decodeString(String s) {
        return getKey(pattern.sortString(s));
    }

    public String getKey(String value) {
        for (Map.Entry<Integer, String> entry : pattern.segmentsMap.entrySet()) {
            if (entry.getValue().equals(value)) {
                return String.valueOf(entry.getKey());
            }
        }
        return null;
    }

}
