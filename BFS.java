import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;

public class BFS {
    public List<int[][]> solve(Sudoku puzzle) {
        Queue<Sudoku> queue = new LinkedList<>();
        List<int[][]> solutions = new ArrayList<>();
        queue.add(puzzle);

        while (!queue.isEmpty()) {
            Sudoku current = queue.poll();

            if (current.isSolved()) {
                solutions.add(current.getGrid());
                break; // Stop after finding the first solution
            }

            for (int[][] nextState : current.generateNextStates()) {
                queue.add(new Sudoku(nextState));
            }
        }
        return solutions;
    }
}
