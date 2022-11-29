package Dec_09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Dec_09 {

    public List<String> heightMaps = new ArrayList<>();
    public int height;
    public int with;
    public int[][] heightIntMap;
    public NumVertex[][] numVertexes;
    public List<Basin> basins = new ArrayList<>();

    public static void main(String[] args) {
        new Dec_09();
    }

    public Dec_09() {
        readFile();
        mapToIntArray();
        combineVertexes();
        findLowPoints();
        findLargestBasins();
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

    public void mapToIntArray() {
        height = heightMaps.size();
        with = heightMaps.get(0).length();
        heightIntMap = new int[100][100];
        numVertexes = new NumVertex[100][100];

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                heightIntMap[i][j] = 9;
                numVertexes[i][j] = new NumVertex(heightIntMap[i][j]);
            }
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                heightIntMap[i][j] = Integer.parseInt(String.valueOf(heightMaps.get(i).charAt(j)));
                numVertexes[i][j] = new NumVertex(heightIntMap[i][j]);
            }
        }
    }

    public void combineVertexes() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (i == 0 && j == 0) {
                    numVertexes[i][j].addNeighbour(numVertexes[i][j+1]).addNeighbour(numVertexes[i+1][j]);
                } else if (i == 0 && j == 99) {
                    numVertexes[i][j].addNeighbour(numVertexes[i][j-1]).addNeighbour(numVertexes[i+1][j]);
                } else if (i == 99 && j == 0) {
                    numVertexes[i][j].addNeighbour(numVertexes[i][j+1]).addNeighbour(numVertexes[i-1][j]);
                } else if (i == 99 && j == 99) {
                    numVertexes[i][j].addNeighbour(numVertexes[i][j-1]).addNeighbour(numVertexes[i-1][j]);
                } else if (i == 0) {
                    numVertexes[i][j].addNeighbour(numVertexes[i][j-1]).addNeighbour(numVertexes[i][j+1]).addNeighbour(numVertexes[i+1][j]);
                } else if (j == 0) {
                    numVertexes[i][j].addNeighbour(numVertexes[i][j+1]).addNeighbour(numVertexes[i+1][j]).addNeighbour(numVertexes[i-1][j]);
                } else if (i == 99) {
                    numVertexes[i][j].addNeighbour(numVertexes[i][j-1]).addNeighbour(numVertexes[i][j+1]).addNeighbour(numVertexes[i-1][j]);
                } else if (j == 99) {
                    numVertexes[i][j].addNeighbour(numVertexes[i][j-1]).addNeighbour(numVertexes[i+1][j]).addNeighbour(numVertexes[i-1][j]);
                } else {
                    numVertexes[i][j].addNeighbour(numVertexes[i][j+1]).addNeighbour(numVertexes[i][j-1]).addNeighbour(numVertexes[i+1][j]).addNeighbour(numVertexes[i-1][j]);
                }
            }
        }
    }

    public void findLowPoints() {
        String white = "\u001B[0m";
        String mark = "\u001B[31m";
        String gray = "\u001B[37m";
        int counter = 0;
        int riskLevelsSum = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (i == 0 && j == 0) {
                    if (heightIntMap[i][j] < heightIntMap[i][j+1] && heightIntMap[i][j] < heightIntMap[i+1][j]) {
                        System.out.print(mark + heightIntMap[i][j]);
                        basins.add(new Basin(numVertexes[i][j]));
                        counter++;
                        riskLevelsSum += heightIntMap[i][j] + 1;
                    } else {
                        if (heightIntMap[i][j] == 9) {
                            System.out.print(white + heightIntMap[i][j]);
                        } else {
                            System.out.print(gray + heightIntMap[i][j]);
                        }
                    }
                } else if (i == 0 && j == 99) {
                    if (heightIntMap[i][j] < heightIntMap[i][j-1] && heightIntMap[i][j] < heightIntMap[i+1][j]) {
                        System.out.print(mark + heightIntMap[i][j]);
                        basins.add(new Basin(numVertexes[i][j]));
                        counter++;
                        riskLevelsSum += heightIntMap[i][j] + 1;
                    } else {
                        if (heightIntMap[i][j] == 9) {
                            System.out.print(white + heightIntMap[i][j]);
                        } else {
                            System.out.print(gray + heightIntMap[i][j]);
                        }
                    }
                } else if (i == 99 && j == 0) {
                    if (heightIntMap[i][j] < heightIntMap[i][j+1] && heightIntMap[i][j] < heightIntMap[i-1][j]) {
                        System.out.print(mark + heightIntMap[i][j]);
                        basins.add(new Basin(numVertexes[i][j]));
                        counter++;
                        riskLevelsSum += heightIntMap[i][j] + 1;
                    } else {
                        if (heightIntMap[i][j] == 9) {
                            System.out.print(white + heightIntMap[i][j]);
                        } else {
                            System.out.print(gray + heightIntMap[i][j]);
                        }
                    }
                } else if (i == 99 && j == 99) {
                    if (heightIntMap[i][j] < heightIntMap[i][j-1] && heightIntMap[i][j] < heightIntMap[i-1][j]) {
                        System.out.print(mark + heightIntMap[i][j]);
                        basins.add(new Basin(numVertexes[i][j]));
                        counter++;
                        riskLevelsSum += heightIntMap[i][j] + 1;
                    } else {
                        if (heightIntMap[i][j] == 9) {
                            System.out.print(white + heightIntMap[i][j]);
                        } else {
                            System.out.print(gray + heightIntMap[i][j]);
                        }
                    }
                } else if (i == 0) {
                    if (heightIntMap[i][j] < heightIntMap[i][j-1] && heightIntMap[i][j] < heightIntMap[i][j+1] && heightIntMap[i][j] < heightIntMap[i+1][j]) {
                        System.out.print(mark + heightIntMap[i][j]);
                        basins.add(new Basin(numVertexes[i][j]));
                        counter++;
                        riskLevelsSum += heightIntMap[i][j] + 1;
                    } else {
                        if (heightIntMap[i][j] == 9) {
                            System.out.print(white + heightIntMap[i][j]);
                        } else {
                            System.out.print(gray + heightIntMap[i][j]);
                        }
                    }
                } else if (j == 0) {
                    if (heightIntMap[i][j] < heightIntMap[i][j+1] && heightIntMap[i][j] < heightIntMap[i+1][j] && heightIntMap[i][j] < heightIntMap[i-1][j]) {
                        System.out.print(mark + heightIntMap[i][j]);
                        basins.add(new Basin(numVertexes[i][j]));
                        counter++;
                        riskLevelsSum += heightIntMap[i][j] + 1;
                    } else {
                        if (heightIntMap[i][j] == 9) {
                            System.out.print(white + heightIntMap[i][j]);
                        } else {
                            System.out.print(gray + heightIntMap[i][j]);
                        }
                    }
                } else if (i == 99) {
                    if (heightIntMap[i][j] < heightIntMap[i][j-1] && heightIntMap[i][j] < heightIntMap[i][j+1] && heightIntMap[i][j] < heightIntMap[i-1][j]) {
                        System.out.print(mark + heightIntMap[i][j]);
                        basins.add(new Basin(numVertexes[i][j]));
                        counter++;
                        riskLevelsSum += heightIntMap[i][j] + 1;
                    } else {
                        if (heightIntMap[i][j] == 9) {
                            System.out.print(white + heightIntMap[i][j]);
                        } else {
                            System.out.print(gray + heightIntMap[i][j]);
                        }
                    }
                } else if (j == 99) {
                    if (heightIntMap[i][j] < heightIntMap[i][j-1] && heightIntMap[i][j] < heightIntMap[i+1][j] && heightIntMap[i][j] < heightIntMap[i-1][j]) {
                        System.out.print(mark + heightIntMap[i][j]);
                        basins.add(new Basin(numVertexes[i][j]));
                        counter++;
                        riskLevelsSum += heightIntMap[i][j] + 1;
                    } else {
                        if (heightIntMap[i][j] == 9) {
                            System.out.print(white + heightIntMap[i][j]);
                        } else {
                            System.out.print(gray + heightIntMap[i][j]);
                        }
                    }
                } else {
                    if (heightIntMap[i][j] < heightIntMap[i][j-1] && heightIntMap[i][j] < heightIntMap[i][j+1] &&
                            heightIntMap[i][j] < heightIntMap[i+1][j] && heightIntMap[i][j] < heightIntMap[i-1][j]) {
                        System.out.print(mark + heightIntMap[i][j]);
                        basins.add(new Basin(numVertexes[i][j]));
                        counter++;
                        riskLevelsSum += heightIntMap[i][j] + 1;
                    } else {
                        if (heightIntMap[i][j] == 9) {
                            System.out.print(white + heightIntMap[i][j]);
                        } else {
                            System.out.print(gray + heightIntMap[i][j]);
                        }
                    }
                }
            }
            System.out.println();
        }
        System.out.println(white + "COUNTED LOW-POINTS: " + counter);
        System.out.println(white + "TOTAL RISK-LEVEL: " + riskLevelsSum);
    }

    public void findLargestBasins() {
        List<Integer> sizes = basins.stream().map(b -> b.vertexes.size()).sorted().toList();
        System.out.println(sizes.get(sizes.size()-1) * sizes.get(sizes.size()-2) * sizes.get(sizes.size()-3));
    }

}
