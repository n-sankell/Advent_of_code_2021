package Dec_09;

import java.util.*;

public class Basin {

    public List<NumVertex> vertexes = new ArrayList<>();
    public NumVertex lowPoint;

    public Basin(NumVertex lowPoint) {
        this.lowPoint = lowPoint;
        fillVertexList();
    }

    public void fillVertexList() {
        Queue<NumVertex> queue = new LinkedList<>();
        lowPoint.visited = true;
        queue.add(lowPoint);
        while (!queue.isEmpty()) {
            NumVertex current = queue.poll();
            vertexes.add(current);
            for (NumVertex neighbour : current.neighbours) {
                if (!neighbour.visited) {
                    neighbour.visited = true;
                    if (neighbour.value < 9) {
                        queue.add(neighbour);
                    }
                }
            }
        }
    }

}
