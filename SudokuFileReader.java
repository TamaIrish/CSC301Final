import java.io.File;
import java.util.Scanner;

public class SudokuFileReader {
    public static Sudoku readPuzzle(String fileName) {
        try {
            File file = new File(fileName); // File is in the current directory
            Scanner scanner = new Scanner(file);

            int size = scanner.nextInt(); // Read the size of the Sudoku grid
            int[][] grid = new int[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (scanner.hasNextInt()) {
                        grid[i][j] = scanner.nextInt();
                    } else {
                        throw new IllegalArgumentException("Invalid file format in " + fileName);
                    }
                }
            }
            scanner.close();
            return new Sudoku(grid); // Return a single Sudoku puzzle
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Return null if an error occurs
    }
}
