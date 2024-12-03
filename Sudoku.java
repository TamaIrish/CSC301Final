import java.util.List;
import java.util.ArrayList;

public class Sudoku {
    private int[][] grid;
    private int size;

    public Sudoku(int[][] grid) {
        this.grid = grid;
        this.size = grid.length;
    }

    public boolean isSolved() {
        return validate(grid);
    }

    public List<int[][]> generateNextStates() {
        return nextStates(grid);
    }

    public int[][] getGrid() {
        return grid;
    }

    private boolean validate(int[][] grid) {
        // Check all cells for validity
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0 || !isValid(grid, i, j, grid[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    private List<int[][]> nextStates(int[][] grid) {
        List<int[][]> states = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) { // Empty cell found
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(grid, i, j, num)) {
                            int[][] newGrid = copyGrid(grid);
                            newGrid[i][j] = num;
                            states.add(newGrid);
                        }
                    }
                    return states; // Return after generating states for the first empty cell
                }
            }
        }
        return states; // No empty cells, return empty list
    }

    private boolean isValid(int[][] grid, int row, int col, int num) {
        // Check row
        for (int i = 0; i < grid.length; i++) {
            if (grid[row][i] == num && i != col) return false;
        }
        // Check column
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][col] == num && i != row) return false;
        }
        // Check 3x3 subgrid
        int subgridRow = (row / 3) * 3;
        int subgridCol = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int currentRow = subgridRow + i;
                int currentCol = subgridCol + j;
                if (grid[currentRow][currentCol] == num && (currentRow != row || currentCol != col)) return false;
            }
        }
        return true;
    }

    private int[][] copyGrid(int[][] grid) {
        int[][] newGrid = new int[grid.length][grid.length];
        for (int i = 0; i < grid.length; i++) {
            System.arraycopy(grid[i], 0, newGrid[i], 0, grid[i].length);
        }
        return newGrid;
    }
}
