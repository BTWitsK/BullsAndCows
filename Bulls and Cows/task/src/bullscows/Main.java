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

    public int getCows() {
        return this.cows;
    }

    public static void Grader(Game game, Code guess) {

        for (Integer num : game.getCode()) {
            if (guess.getCode().contains(num)) {
                if (game.getCode().indexOf(num) == guess.getCode().indexOf(num)) {
                    game.addBull();
                } else {
                    game.addCow();
                }
            }
        }
    }

    public void printResults() {
        if (this.bulls == 0 && this.cows == 0) {
            System.out.println("Grade: None.");
        } else if (this.bulls == 0) {
            System.out.printf("Grade: %d cow(s)\n", getCows());
        } else if (this.cows == 0) {
            System.out.printf("Grade: %d bull(s)\n", getBulls());
        } else if (this.bulls == size()) {
            System.out.printf("Grade: %d bull(s)\n", getBulls());
            System.out.println("Congratulations! You guessed the secret code.");
        } else {
            System.out.printf("Grade: %d bull(s) and %d cow(s).\n", getBulls(), getCows());
        }
        //bulls = 0;
        //cows = 0;
    }

    public static String generateCode(int input) {
        StringBuilder secretCode = new StringBuilder();

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
        System.out.println("Please, enter the secret code's length: ");

        int input = scanner.nextInt();
        if (input > 10) {
            System.out.println("Error: Can't generate a secret number with a length of 11.");
            return;
        }

        System.out.println("Okay, let's start a game!");
        Game game = new Game(Game.generateCode(input));
        Code guess;

        int turn = 1;
        do {
            System.out.printf("Turn %d:\n", turn);
            guess = new Code(String.valueOf(scanner.nextInt()));
            Game.Grader(game,guess);
            game.printResults();
            turn++;

        } while (game.getBulls() != input);


    }
}
