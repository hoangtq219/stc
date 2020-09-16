package stc.prob3;

import java.util.*;

/**
 * @author : hoangtq
 * @since : 21:44 15/09/2020, Tue
 **/
public class Sol {

    private static final int BLOCK_POINT = 0;
    private static final int POINT = 1;
    private static final int FINAL_POINT = 2;

    private AbstractPoint[][] mazePoint;
    private int[][] maze;
    private Point finalPoint;
    private int numRows = 100;
    private int numCols = 100;

    public static void main(String[] args) {
        Sol solution = new Sol();
        solution.run();
    }

    public void run() {
        Scanner input = new Scanner(System.in);

        for (int testCase = 1; testCase <= 10; testCase++) {
            // Rank test case
            String testIgnore = input.nextLine();

            // mặc định một ma trận có 100x100 ô
            maze = new int[100][100];

            // đọc từng hàng của bộ test
            for (int row = 0; row < 100; row++) {
                String line = input.nextLine();
                String[] arr = line.split("\\s++");
                for (int col = 0; col < arr.length; col++) {
                    maze[row][col] = Integer.parseInt(arr[col]);
                }
            }

            System.out.printf("#%d %d\n", testCase, director());
        }
    }

    public int director() {
        // reset maze Point
        initMazePoint(maze);

        Point point = finalPoint;

        // startPoint = finishPoint
        if (point.getRow() == 0) {
            return point.getCol();
        }

        // set startPoint is visited
        mazePoint[point.getRow()][point.getCol()] = new BlockPoint(point.getRow(), point.getCol());

        while (canMove(point)) {
//            System.out.println(point.toString());

            if (canMoveLeft(point)) {
                point = moveLeft(point);
            } else if (canMoveRight(point)) {
                point = moveRight(point);
            } else if (canMoveUp(point)) {
                point = moveUp(point);
            }

            // set Point is visited (BlockPoint)
            mazePoint[point.getRow()][point.getCol()] = new BlockPoint(point.getRow(), point.getCol());

            if (point.getRow() == 0) {
                return point.getCol();
            }
        }

        // trả về -1 nếu không tìm được đường đi
        // nhưng theo cách mô tả thì bài toán kiểu gì cũng có đường đi là col (numCols-1)
        return -1;
    }

    // check is point - can visit (not BlockPoint)
    public boolean isPoint(int row, int col) {
        return !mazePoint[row][col].isBlockPoint();
    }

    private boolean canMove(Point point) {
        if (canMoveLeft(point) || canMoveUp(point) || canMoveRight(point)) {
            return true;
        }
        return false;
    }

    private boolean canMoveRight(Point point) {
        if (point.getCol() < numCols - 1 && isPoint(point.getRow(), point.getCol() + 1)) {
            return true;
        }
        return false;
    }

    private Point moveRight(Point point) {
        return (Point) mazePoint[point.getRow()][point.getCol() + 1];
    }

    private boolean canMoveLeft(Point point) {
        if (point.getCol() > 0 && isPoint(point.getRow(), point.getCol() - 1)) {
            return true;
        }
        return false;
    }

    private Point moveLeft(Point point) {
        return (Point) mazePoint[point.getRow()][point.getCol() - 1];
    }

    private boolean canMoveDown(Point point) {
        if (point.getRow() < numRows - 1 && isPoint(point.getRow() + 1, point.getCol())) {
            return true;
        }
        return false;
    }

    private Point moveDown(Point point) {
        return (Point) mazePoint[point.getRow() + 1][point.getCol()];
    }

    private boolean canMoveUp(Point point) {
        if (point.getRow() > 0 && isPoint(point.getRow() - 1, point.getCol())) {
            return true;
        }
        return false;
    }

    private Point moveUp(Point point) {
        return (Point) mazePoint[point.getRow() - 1][point.getCol()];
    }

    public void initMazePoint(int[][] maze) {
        mazePoint = new AbstractPoint[numRows][numCols];

        for (int row = 0; row < numRows; ++row) {
            for (int col = 0; col < numCols; ++col) {
                switch (maze[row][col]) {
                    case POINT:
                        mazePoint[row][col] = new Point(row, col);
                        break;
                    case BLOCK_POINT:
                        mazePoint[row][col] = new BlockPoint(row, col);
                        break;
                    case FINAL_POINT:
                        finalPoint = new Point(row, col);
                        mazePoint[row][col] = finalPoint;
                        break;
                    default:
                        System.out.printf("Not found: maze[%d][%d] = %d", row, col, maze[row][col]);
                }
            }
        }
    }

    static class Point extends AbstractPoint {

        public Point() {
        }

        public Point(int row, int col) {
            super(row, col);
        }

        @Override
        public boolean isBlockPoint() {
            return false;
        }
    }

    static abstract class AbstractPoint {
        private int row;
        private int col;

        public AbstractPoint() {
        }

        public AbstractPoint(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public abstract boolean isBlockPoint();

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AbstractPoint that = (AbstractPoint) o;
            return row == that.row &&
                    col == that.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }

        @Override
        public String toString() {
            return "AbstractPoint{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }

    static class BlockPoint extends AbstractPoint {

        public BlockPoint() {
        }

        public BlockPoint(int row, int col) {
            super(row, col);
        }

        @Override
        public boolean isBlockPoint() {
            return true;
        }
    }
}
