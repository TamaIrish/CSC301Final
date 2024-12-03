import java.util.List;
import java.util.ArrayList;

public class SudokuSolver {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\dauri\\IdeaProjects\\Sudoku\\src\\medium.txt"; // Specify the file path
        System.out.println("Reading puzzle from: " + filePath);

        Sudoku puzzle = SudokuFileReader.readPuzzle(filePath);

        if (puzzle == null) {
            System.out.println("Failed to load puzzle. Please check the file and try again.");
            return;
        }

        System.out.println("Puzzle loaded successfully!");
        System.out.println("Initial Puzzle:");
        printPuzzle(puzzle.getGrid());

        // Solve using BFS
        BFS bfsSolver = new BFS();
        List<int[][]> bfsSolutions = bfsSolver.solve(puzzle);
        System.out.println("BFS Solved: " + (bfsSolutions.size() > 0));
        if (bfsSolutions.size() > 0) {
            System.out.println("BFS Solution:");
            printPuzzle(bfsSolutions.get(0)); // Print the first solution found
        }

        // Solve using DLS with a maximum depth of 100
        DLS dlsSolver = new DLS(100);
        boolean dlsSolved = dlsSolver.solve(puzzle, 0);
        System.out.println("DLS Solved: " + dlsSolved);
        if (dlsSolved) {
            System.out.println("DLS Solution:");
            printPuzzle(puzzle.getGrid());
        }
    }

    private static void printPuzzle(int[][] grid) {
        for (int[] row : grid) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}