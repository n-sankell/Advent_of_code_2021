package Dec_01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dec_01 {

    private static final List<Integer> allNumbers = new ArrayList<>();


    public static void main(String[] args) {
        readFileIntoList("src/Dec_01/puzzleInput01.txt");
        amountOfIncrements();
        amountOfGroupIncrements();
    }

    /**Little method to quickly get the sum of a group of depths
     * @param index Which index we're going from in allNumbers
     * @return Returns sum of the group
     */
    public static int getGroupSum(int index){
        return allNumbers.get(index) + allNumbers.get(index + 1) + allNumbers.get(index + 2);
    }


    /**Method to read the whole input file into a single list for easier management
     * @param filepath Path of the file we read the input from
     */
    public static void readFileIntoList(String filepath){

        try(BufferedReader fileIn = new BufferedReader(new FileReader(filepath))){
            String line = fileIn.readLine();

            while (line != null){
                int numLine = Integer.parseInt(line);
                allNumbers.add(numLine);
                line = fileIn.readLine();
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }


    /**Method to count the amount of times one number is higher than the last one in the list
     * Takes one index, compares it to the next one, reassigns and repeats for the whole list
     */
    public static void amountOfIncrements(){
        int amountOfIncrements = 0;
        int currentDepth = allNumbers.get(0);

        for (int i = 1; i < allNumbers.size(); i++) {
            int tempDepth = allNumbers.get(i);

            if(tempDepth > currentDepth){
                amountOfIncrements++;
            }

            currentDepth = tempDepth;
        }
        System.out.println(amountOfIncrements);
    }


    /**Method to count increments between groups of three depths
     * Gets a group based on index, takes the next group starting at index + 1, compares, repeats
     */
    public static void amountOfGroupIncrements(){
        int amountOfGroupIncrements = 0;

        for (int i = 0; i < allNumbers.size() - 3; i++) {
            int currentDepthSum = getGroupSum(i);
            int nextDepthSum = getGroupSum(i + 1);

            if(nextDepthSum > currentDepthSum){
                amountOfGroupIncrements++;
            }
        }
        System.out.println(amountOfGroupIncrements);
    }
}
