package Dec_07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class Dec_07 {

    public List<Integer> positions = new ArrayList<>();
    public Map<Integer, Long> targetPositions = new HashMap<>();

    public static void main(String[] args) {
        new Dec_07();
    }

    public Dec_07() {
        readFile();
        System.out.println(findBestPosition());
    }

    public long calculateDistance(int from, int target) {
        int min = Math.min(from, target);
        int max = Math.max(from, target);
        long distance = max - min;
        long exponent = 0;
        for (int i = 1; i < distance; i++) {
            exponent += i;
        }
        return distance + exponent;
    }

    public long findBestPosition() {
        Set<Integer> distinctValues = new HashSet<>(positions);
        for (int target = 1; target < Collections.max(positions); target++) {
            int finalTarget = target;
            AtomicLong fuel = new AtomicLong();
            distinctValues.forEach(position -> {
                long amount = positions.stream().filter(p -> p.equals(position)).toList().size();
                fuel.addAndGet(calculateDistance(position, finalTarget) * amount);

            });
            targetPositions.put(finalTarget, fuel.get());
        }

        targetPositions.forEach((e, v) -> System.out.println(e + ": " + v));

        long bestTarget = Integer.MAX_VALUE;
        for (Integer key : targetPositions.keySet()) {
            if (targetPositions.get(key) < bestTarget) {
                bestTarget = targetPositions.get(key);
            }
        }
        return bestTarget;
    }

    public void readFile() {
        String readLine;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/Dec_07/puzzleInput07.txt"))) {
            while ((readLine = bufferedReader.readLine()) != null) {
                Arrays.stream(readLine.split(",")).forEach(s -> positions.add(Integer.parseInt(s)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
