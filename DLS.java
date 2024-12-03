public class DLS {
    private int maxDepth;

    public DLS(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public boolean solve(Sudoku puzzle, int depth) {
        if (depth > maxDepth) return false;

        if (puzzle.isSolved()) {
            return true; // Puzzle is solved
        }

        for (int[][] nextState : puzzle.generateNextStates()) {
            Sudoku nextPuzzle = new Sudoku(nextState);
            if (solve(nextPuzzle, depth + 1)) {
                // Copy solved grid back into the original puzzle
                int[][] solvedGrid = nextPuzzle.getGrid();
                for (int i = 0; i < puzzle.getGrid().length; i++) {
                    System.arraycopy(solvedGrid[i], 0, puzzle.getGrid()[i], 0, solvedGrid[i].length);
                }
                return true;
            }
        }
        return false;
    }
}
