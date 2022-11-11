package Dec_08;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Pattern {

    public Map<Integer, String> segmentsMap = new HashMap<>();

    public void analyzePattern(String[] input) {
        for (String s : input) {
            if (s.length() == 2) {
                segmentsMap.put(1, sortString(s));
            } else if (s.length() == 3) {
                segmentsMap.put(7, sortString(s));
            } else if (s.length() == 4) {
                segmentsMap.put(4, sortString(s));
            } else if (s.length() == 7) {
                segmentsMap.put(8, sortString(s));
            }
        }

        for (String s : input) {
            int counter = 0;
            if (s.length() == 6) {
                for (char ch : segmentsMap.get(1).toCharArray()) {
                    for (char c : s.toCharArray()) {
                        if (ch == c) {
                            counter++;
                        }
                    }
                }
                if (counter == 1) {
                    segmentsMap.put(6, sortString(s));
                } else {
                    int secondCounter = 0;
                    for (char c : segmentsMap.get(4).toCharArray()) {
                        for (char ch : s.toCharArray()) {
                            if (ch == c) {
                                secondCounter++;
                            }
                        }
                    }
                    if (secondCounter == 4) {
                        segmentsMap.put(9, sortString(s));
                    } else {
                        segmentsMap.put(0, sortString(s));
                    }
                }
            }
        }

        for (String s : input) {
            int counter = 0;
            if (s.length() == 5) {
                for (char c : segmentsMap.get(6).toCharArray()) {
                    for (char ch : s.toCharArray()) {
                        if (c == ch) {
                            counter++;
                        }
                    }
                }
                if (counter == 5) {
                    segmentsMap.put(5, sortString(s));
                } else {
                    int secondCounter = 0;
                    for (char c : segmentsMap.get(1).toCharArray()) {
                        for (char ch : s.toCharArray()) {
                            if (c == ch) {
                                secondCounter++;
                            }
                        }
                    }
                    if (secondCounter == 1) {
                        segmentsMap.put(2, sortString(s));
                    } else {
                        segmentsMap.put(3, sortString(s));
                    }
                }
            }
        }
    }

    public String sortString(String s) {
        char[] charArray = s.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    public void printMap() {
        segmentsMap.forEach((integer, s) -> System.out.println(integer + ": " + s));
    }

}
