package bullscows;

import java.util.*;

class Code {
    protected final List<Integer> code = new ArrayList<>();

    public Code(String secretCode) {
        for (String num : secretCode.split("")) {
            this.code.add(Integer.valueOf(num));
        }
    }

    public List<Integer> getCode() {
        return this.code;
    }

    public int getDigit (int i) {
        return code.get(i);
    }

    public int size() {
        return code.size();
    }

    public String printString() {
        StringBuilder answer = new StringBuilder();
        code.forEach(answer::append);

        return answer.toString();
    }
}

class Game extends Code {
    private int bulls;
    private int cows;

    public Game(String secretCode) {
        super(secretCode);
        this.bulls = 0;
        this.cows = 0;
    }

    public void addBull() {
        this.bulls++;
    }

    public int getBulls() {
        return this.bulls;
    }

    public void addCow() {
        this.cows++;
    }

    public void removeCow() {
        this.cows--;
    }

    public int getCows() {
        return this.cows;
    }

    /* public Code getSecret() {
        return secret;
    }*/

    public static void Grader(Game game, Code guess) {

        for (Integer num : guess.getCode()) {
            if (game.getCode().contains(num)) {
                game.addCow();
            }
        }

        for (int i = 0; i < game.size(); i++) {
            if (game.getDigit(i) == guess.getDigit(i)) {
                game.addBull();
                game.removeCow();
            }

        }

    }

    public void printResults() {
        if (this.bulls == 0 && this.cows == 0) {
            System.out.printf("Grade: None. The secret code is %s\n", printString());
        } else if (this.bulls == 0) {
            System.out.printf("Grade: %d cow(s). The secret code is %s\n", cows, printString());
        } else if (this.cows == 0) {
            System.out.printf("Grade: %d bull(s). The secret code is %s\n", bulls, printString());
        } else {
            System.out.printf("Grade: %d bull(s) and %d cow(s). The secret code is %s\n", bulls, cows, printString());
        }
    }

    public static String generateCode(int input) {
        StringBuilder secretCode = new StringBuilder("");

        while (secretCode.length() < input) {
            long pseudoRandomNumber = System.nanoTime();
            List<String> random  = List.of(String.valueOf(pseudoRandomNumber).split(""));

            for (String digit : random) {
                if (!Arrays.asList(secretCode.toString().split("")).contains(digit)) {
                    secretCode.append(digit);
                    break;
                }
            }

            if (secretCode.charAt(0) == '0') {
                secretCode.deleteCharAt(0);
            }
        }

        return secretCode.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Code guess = new Code(scanner.nextLine());

        int input = scanner.nextInt();
        if (input > 10) {
            System.out.println("Error: Can't generate a secret number with a length of 11.");
        } else {
            Game game = new Game(Game.generateCode(input));
            System.out.printf("The random secret number is %s", game.printString());
        }


        //Game.Grader(game, guess);

        //game.printResults();

    }
}
