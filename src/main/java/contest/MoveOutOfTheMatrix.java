package contest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author : hoangtq
 * @since : 22:45 31/01/2021, Sun
 **/
public class MoveOutOfTheMatrix {

    private int n;
    private int result = -1;
    private List<Point> pointList = new ArrayList<>();
    private int startRow;
    private int startCol;

    public static void main(String[] args) {
        MoveOutOfTheMatrix main = new MoveOutOfTheMatrix();
        Scanner scanner = new Scanner(System.in);
        main.initAndSolve(scanner); }

    private void initAndSolve(Scanner scanner) {
        Point[][] matrix = readInput(scanner);
        solve(matrix, n, startRow, startCol);
        System.out.println("Result = " + result);
        getPath();
    }

    private void getPath() {
        if (result == -1) {
            System.out.println("Path not found!!!");
            return;
        }

        for (Point point : pointList) {
            String nextPointStr = String.format("Matrix[%d][%d] = %d"
                    , point.getRow() + 1
                    , point.getCol() + 1
                    , point.getValue()
            );
            System.out.println(nextPointStr);
        }

    }

    private void solve(Point[][] matrix, int n, int startRow, int startCol) {
        // reset
        result = -1;
        this.n = n;
        Point[] points = new Point[8];
        points[0] = matrix[startRow][startCol];
        // process
        backtrack(matrix, points, 0);
    }

    private void backtrack(Point[][] matrix, Point[] points, int steps) {
        Point currentPoint = points[steps];

        if (canMoveOutOfTheMatrix(currentPoint)) {
            int total = sumOfThePointPassed(points, steps);
            if (result != -1 && total < result) {
                result = total;
                pointResult(points, steps);
            } else if (result == -1) {
                result = total;
                pointResult(points, steps);
            }
            return;
        }

        if (steps < 7 && canMoveInMatrix(points[steps])) {

            // left
            if (checkMoveLeftRow(currentPoint.getCol(), steps)) {
                points[steps + 1] = matrix[currentPoint.getRow()][currentPoint.getCol() - currentPoint.getValue()];
                backtrack(matrix, points, steps + 1);
            }

            // right
            if (checkMoveRightRow(currentPoint.getCol(), currentPoint.getValue())) {
                points[steps + 1] = matrix[currentPoint.getRow()][currentPoint.getCol() + currentPoint.getValue()];
                backtrack(matrix, points, steps + 1);
            }

            // down
            if (checkMoveDownColumn(currentPoint.getRow(), currentPoint.getValue())) {
                points[steps + 1] = matrix[currentPoint.getRow() - currentPoint.getValue()][currentPoint.getCol()];
                backtrack(matrix, points, steps + 1);
            }

            // up
            if (checkMoveUpColumn(currentPoint.getRow(), currentPoint.getValue())) {
                points[steps + 1] = matrix[currentPoint.getRow() + currentPoint.getValue()][currentPoint.getCol()];
                backtrack(matrix, points, steps + 1);
            }
        }
    }

    private int sumOfThePointPassed(Point[] points, int steps) {
        int sumOfThePointsPassed = 0;
        for (int index = 0; index <= steps; index++) {
            sumOfThePointsPassed += points[index].value;
        }
        return sumOfThePointsPassed;
    }

    private void pointResult(Point[] points, int steps) {
        pointList = new ArrayList<>();
        for (int index = 0; index <= steps; index++) {
            pointList.add(points[index]);
        }
    }

    private boolean canMoveOutOfTheMatrix(Point point) {
        // move out of the matrix
        if (checkMoveLeftOutOfTheMatrix(point)
                || checkMoveRightOutOfTheMatrix(point)
                || checkMoveDownOutOfTheMatrix(point)
                || checkMoveUpOutOfTheMatrix(point)) {
            return true;
        }

        return false;
    }

    private boolean canMoveInMatrix(Point point) {
        // move in the matrix
        if (checkMoveLeftRow(point.getRow(), point.getValue())
                || checkMoveRightRow(point.getRow(), point.getValue())
                || checkMoveUpColumn(point.getCol(), point.getValue())
                || checkMoveDownColumn(point.getCol(), point.getValue())) {
            return true;
        }

        return false;
    }

    private boolean checkMoveLeftOutOfTheMatrix(Point point) {
        if (point.row + 1 == point.value) return true;
        return false;
    }

    private boolean checkMoveRightOutOfTheMatrix(Point point) {
        if (point.row + point.value == n) return true;
        return false;
    }

    private boolean checkMoveUpOutOfTheMatrix(Point point) {
        if (point.col + 1 == point.value) return true;
        return false;
    }

    private boolean checkMoveDownOutOfTheMatrix(Point point) {
        if (point.col + point.value == n) return true;
        return false;
    }

    private boolean checkMoveLeftRow(int col, int numbStep) {
        if (col - numbStep >= 0) return true;
        return false;
    }

    private boolean checkMoveRightRow(int col, int numbStep) {
        if (col + numbStep < n) return true;
        return false;
    }

    private boolean checkMoveUpColumn(int row, int numbStep) {
        if (row + numbStep < n) return true;
        return false;
    }

    private boolean checkMoveDownColumn(int row, int numbStep) {
        if (row - numbStep >= 0) return true;
        return false;
    }

    private Point[][] readInput(Scanner scanner) {
        // the first line of each test case contains three integer n, startColumn, startRow
        String[] firstLine = readLine(scanner);
        n = Integer.parseInt(firstLine[0]);
        // startCol, startRow subtract 1 because matrix index start row, column at 0
        startCol = Integer.parseInt(firstLine[1]) - 1;
        startRow = Integer.parseInt(firstLine[2]) - 1;

        // the n lines contain n integers each
        Point[][] matrix = new Point[n][n];
        for (int row = 0; row < n; row++) {
            String[] columns = readLine(scanner);
            for (int column = 0; column < n; column++) {
                int value = Integer.parseInt(columns[column]);
                matrix[row][column] = new Point(row, column, value);
            }
        }
        return matrix;
    }

    private String[] readLine(Scanner scanner) {
        String line = scanner.nextLine();
        return line.split("\\s++");
    }

    static class Point {
        private int row;
        private int col;
        private int value;

        public Point(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }

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

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return row == point.row &&
                    col == point.col &&
                    value == point.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col, value);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "row=" + row +
                    ", col=" + col +
                    ", value=" + value +
                    '}';
        }
    }

}
