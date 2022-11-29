package Dec_09;

import java.util.ArrayList;
import java.util.List;

public class NumVertex {

    public int value;
    public boolean visited = false;
    public List<NumVertex> neighbours = new ArrayList<>();

    public NumVertex(int value) {
        this.value = value;
    }

    public NumVertex addNeighbour(NumVertex neighbour) {
        neighbours.add(neighbour);
        return this;
    }

}
