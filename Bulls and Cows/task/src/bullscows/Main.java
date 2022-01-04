package bullscows;

import java.util.*;

class Code {
    protected final List<String> code = new ArrayList<>();

    public Code(String secretCode) {
        this.code.addAll(Arrays.asList(secretCode.split("")));
    }

    public List<String> getCode() {
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
        for (String character: game.getCode()) {
            if (guess.getCode().contains(character)) {
                if (Objects.equals(character, guess.getCode().get(game.getCode().indexOf(character)))) {
                    game.addBull();
                } else {
                    game.addCow();
                }
            }
        }
    }

    public void clear() {
        this.cows = 0;
        this.bulls = 0;
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
    }

    public static String generateCode(int codeLength, int symbols) {
        String possibleSymbols = "0123456789abcdefghijklmnopqrstuvwxyz";
        StringBuilder secretCode = new StringBuilder();

        while (secretCode.length() < codeLength) {
            Random random = new Random();
            int digit = random.nextInt(symbols);

            if (!secretCode.toString().contains(String.valueOf(possibleSymbols.charAt(digit)))) {
                secretCode.append(possibleSymbols.charAt(digit));
            }
        }

        System.out.print("The secret is prepared: ");
        for (int i = 0; i < codeLength; i++) {
            System.out.print("*");
        }
        if (symbols > 10) {
            System.out.printf(" (0-9, a-%c).\n",possibleSymbols.charAt(symbols - 1) );
        } else if (symbols == 10){
            System.out.print(" (0-9, a).\n");
        } else {
            System.out.print(" (0-9).\n");
        }

        return secretCode.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, enter the secret code's length: ");
        int codeLength;

        try {
            codeLength = scanner.nextInt();
        } catch (InputMismatchException error) {
            System.out.print("error:Not a valid number");
            return;
        }
        if (codeLength > 36) {
            System.out.println("error: Can't generate a secret number with a length of 37.");
            return;
        }

        System.out.println("Input the number of possible symbols in the code: ");
        int symbols = scanner.nextInt();

        if (symbols > 36){
            System.out.print("error: max number of possible symbols in the code is 36");
            return;
        }
        if (symbols > codeLength) {
            System.out.printf("Error: error it's not possible to generate a code with a length of %d with %d unique symbols", codeLength, symbols);
            System.exit(0);
            return;
        }

        Game game = new Game(Game.generateCode(codeLength, symbols));
        System.out.println("Okay, let's start a game!");
        Code guess;

        int turn = 1;
        do {
            game.clear();
            System.out.printf("Turn %d:\n", turn);
            guess = new Code(scanner.nextLine());
            Game.Grader(game,guess);
            game.printResults();
            turn++;

        } while (game.getBulls() != codeLength);


    }
}
