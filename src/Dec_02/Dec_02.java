package Dec_02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Dec_02 {

    public final List<String> fromFile = new ArrayList<>();

    public static void main(String[] args) {
        new Dec_02();
    }

    public Dec_02() {
        readFile();
        System.out.println(calculateCoordinates());
        System.out.println(calculateCoordinatesAim());
    }

    public void readFile() {
        String readLine;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/Dec_02/puzzleInput02.txt"))) {
            while ((readLine = bufferedReader.readLine()) != null) {
                fromFile.add(readLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int calculateCoordinates() {
        int position = 0;
        int depth = 0;
        for (String coordinate : fromFile) {
            if (coordinate.contains("forward")) {
                    position += Integer.parseInt(coordinate.substring(8));
            } else if (coordinate.contains("up")) {
                depth -= Integer.parseInt(coordinate.substring(3));
            } else if (coordinate.contains("down")) {
                depth += Integer.parseInt(coordinate.substring(5));
            }
        }
        return position * depth;
    }

    public int calculateCoordinatesAim() {
        int aim = 0;
        int position = 0;
        int depth = 0;
        for (String coordinate : fromFile) {
            if (coordinate.contains("forward")) {
                if (aim == 0) {
                    position += Integer.parseInt(coordinate.substring(8));
                } else {
                    position += Integer.parseInt(coordinate.substring(8));
                    depth += Integer.parseInt(coordinate.substring(8)) * aim;
                }
            } else if (coordinate.contains("up")) {
                aim -= Integer.parseInt(coordinate.substring(3));
            } else if (coordinate.contains("down")) {
                aim += Integer.parseInt(coordinate.substring(5));
            }
        }
        return position * depth;
    }

}


