package Dec_10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Dec_10 {

    public List<String> code = new ArrayList<>();
    public Set<String> incompleteLines = new HashSet<>();

    public static void main(String[] args) {
        new Dec_10();
    }

    public Dec_10() {
        readFile();
        firstPart();
        autocomplete();
    }

    public void firstPart() {
        long score = 0;
        for (String row : code) {
            Queue<Character> queue = new LinkedList<>();
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < row.length(); i++) {
                queue.add(row.charAt(i));
            }
            while (!queue.isEmpty()) {
                Character next = queue.poll();
                switch (next) {
                    case '[', '<', '{', '(' -> stack.add(next);
                    case ']', '>', '}', ')' -> {
                        if (!stack.isEmpty()) {
                            char prev = stack.pop();
                            if (getScore(prev, next) > 0) {
                                score += getScore(prev, next);
                                queue.clear();
                                incompleteLines.remove(row);
                            }
                        }
                    }
                }
            }
        }
        System.out.println(score);
    }

    public void autocomplete() {
        List<Long> scores = new ArrayList<>();
        for (String row : incompleteLines) {
            Stack<Character> opening = new Stack<>();
            for (int i = 0; i < row.length(); i++) {
                switch (row.charAt(i)) {
                    case '[', '<', '{', '(' -> opening.add(row.charAt(i));
                    case ']', '>', '}', ')' -> opening.pop();
                }
            }

            List<Character> reversed = new ArrayList<>(opening.stream().toList());
            Collections.reverse(reversed);
            scores.add(calculateScore(reversed));
        }

        List<Long> sorted = scores.stream().sorted().toList();
        System.out.println(sorted.get(sorted.size()/2));

    }

    public long calculateScore(List<Character> remaining) {
        long score = 0;
        for (Character character : remaining) {
            score = score * 5 + getValue(character);
        }
        return score;
    }

    public int getValue(char c) {
        int score = 0;
        switch (c) {
            case '(' -> score = 1;
            case '[' -> score = 2;
            case '{' -> score = 3;
            case '<' -> score = 4;
        }
        return score;
    }

    public long getScore(char prev, char next) {
        int score = 0;
        if (!isMatch(prev, next)) {
            switch (next) {
                case ')' -> score = 3;
                case ']' -> score = 57;
                case '}' -> score = 1197;
                case '>' -> score = 25137;
            }
        }
        return score;
    }

    public boolean isMatch(char prev, char next) {
        boolean match = false;
        switch (prev) {
            case '{' -> match = next == '}';
            case '(' -> match = next == ')';
            case '[' -> match = next == ']';
            case '<' -> match = next == '>';
        }
        return match;
    }

    public void readFile() {
        String readLine;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/Dec_10/puzzleInput10.txt"))) {
            while ((readLine = bufferedReader.readLine()) != null) {
                code.add(readLine);
                incompleteLines.add(readLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
