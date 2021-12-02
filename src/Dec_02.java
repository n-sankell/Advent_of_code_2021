import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Dec_02 {

    List<String> fromFile = new ArrayList<>();

    public Dec_02() {
        readFile();
        System.out.println(calculateCoordinates());
    }

    public static void main(String[] args) {
        new Dec_02();
    }

    public void readFile() {
        String readLine;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/puzzleInput02.txt"));) {
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

}


