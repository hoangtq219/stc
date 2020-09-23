package training.infixtopostfix;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author : hoangtq
 * @since : 23:16 23/09/2020, Wed
 **/
public class EvaluateString {

    public static void main(String[] args) {
        EvaluateString main = new EvaluateString();
        Scanner input = new Scanner(System.in);
        for (int testCase = 1; testCase <= 10; testCase++) {
            String sizeIgnore = input.nextLine();
            String expression = input.nextLine();

            Queue<String> tokens = main.infixToPostfix(expression);
            long result = 0;
            if (tokens.size() > 0) {
                result = main.evaluatePostfix(tokens);
            }
            System.out.printf("#%d %d\n", testCase, result);
        }
    }

    public Queue<String> infixToPostfix(String expression) {
        // loại bỏ các ký tự space
        expression = expression.replaceAll(" ", "");

        Stack<Character> operators = new Stack<>();
        Queue<String> tokens = new LinkedList<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);

            if (!Character.isDigit(c)) {
                if (sb.length() > 0) {
                    tokens.add(sb.toString());
                }
                sb = new StringBuilder();
            }

            // Nếu ký tự đang xét là toán hạng
            if (Character.isLetterOrDigit(c)) {
                sb.append(c);
                if (i == expression.length() - 1) {
                    if (sb.length() > 0) {
                        tokens.add(sb.toString());
                        sb = new StringBuilder();
                    }
                }
            }
            // Nếu ký tự đang xét là '(' thêm nó vào stack operators
            else if (c == '(') {
                operators.push(c);
            }

            //  Nếu ký tự đang xét là ')' lấy từ stack ra
            // cho đến khi gặp ký tự  '('
            else if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    tokens.add(operators.pop() + "");
                }
                if (!operators.isEmpty() && operators.peek() != '(') {
                    new LinkedList<>();
                } else {
                    operators.pop();
                }
            } else {
                while (!operators.isEmpty() && precedence(c) <= precedence(operators.peek())) {
                    if (operators.peek() == '(') {
                        new LinkedList<>();
                    }
                    String operation = operators.pop() + "";
                    tokens.add(operation);
                }
                operators.push(c);
            }
        }

        // lấy operations từ stack
        while (!operators.isEmpty()) {
            if (operators.peek() == '(') {
                new LinkedList<>();
            }
            String operation = operators.pop() + "";
            tokens.add(operation);
        }
        return tokens;
    }

    public int precedence(char operation) {
        switch (operation) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;
            default:
                System.out.println("Operation is not supported");
                break;
        }
        return -1;
    }

    public long evaluatePostfix(Queue<String> tokens) {
        Stack<Long> stack = new Stack<>();
        String operations = "+-*/";
        for (String c : tokens) {

            if (!operations.contains(c)) {
                stack.push(Long.parseLong(c));
            } else {
                long numb1 = stack.pop();
                long numb2 = stack.pop();

                switch (c) {
                    case "+":
                        stack.push(numb2 + numb1);
                        break;

                    case "-":
                        stack.push(numb2 - numb1);
                        break;

                    case "/":
                        stack.push(numb2 / numb1);
                        break;

                    case "*":
                        stack.push(numb2 * numb1);
                        break;
                    default:
                        System.out.println("Operation is not supported");
                        break;
                }
            }
        }
        return stack.pop();
    }
}
