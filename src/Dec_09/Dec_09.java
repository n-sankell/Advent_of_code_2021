package Dec_09;

import Dec_08.SevenSegment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Dec_09 {

    public List<String> heightMaps = new ArrayList<>();

    public static void main(String[] args) {
        new Dec_09();
    }

    public Dec_09() {
        readFile();
        print();
    }

    public void print() {
        heightMaps.forEach(System.out::println);
    }

    public void readFile() {
        String readLine;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/Dec_09/puzzleInput09.txt"))) {
            while ((readLine = bufferedReader.readLine()) != null) {
                heightMaps.add(readLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
