package bullscows;

import java.util.*;

class Code {
    protected final List<String> code = new ArrayList<>();

    public Code(String secretCode) {
        /* since "code" is a List object ".addAll" copies the list created by splitting "secretCode"
        which is a string split into a list with ".asList" instead of iterating through it */
        this.code.addAll(Arrays.asList(secretCode.split("")));
    }

    public List<String> getCode() {
        return this.code;
    }

    public int size() {
        return code.size();
    }
}

class Game extends Code {
    /* instead of using the same class with a different constructor (since the secret code doesn't need
    bull or cows) I created a different class. In retrospect maybe just needed one class and use compareTo
    to check when the game is won */
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

    public static void Grader(Game game, Code guess) {
        //iterates through every character in the secret code
        for (String character: game.getCode()) {
            //checks to see if the first character of the secretcode is included in the guess
            if (guess.getCode().contains(character)) {
                /*.indexOf finds the FIRST occurrence so if the guess has more than one occurrence of the same digit?
                that's why we're getting the index of the secret code digit, then checking to see if the index of
                the guess contains the same digit in the secret code */
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
            System.out.printf("Grade: %d cow(s)\n", this.cows);
        } else if (this.cows == 0) {
            System.out.printf("Grade: %d bull(s)\n", this.bulls);
        } else if (this.bulls == size()) {
            System.out.printf("Grade: %d bull(s)\n", this.bulls);
            System.out.println("Congratulations! You guessed the secret code.");
        } else {
            System.out.printf("Grade: %d bull(s) and %d cow(s).\n", this.bulls, this.cows);
        }
    }

    public static String generateCode(int codeLength, int symbols) {
        String possibleSymbols = "0123456789abcdefghijklmnopqrstuvwxyz";
        StringBuilder secretCode = new StringBuilder();

        if (codeLength > 36 || codeLength < 1) {
            System.out.println("error: Can't generate a secret number with " +
                    "a length greater than 36 or less than 1.");
            System.exit(0);
        }

        if (symbols > 36){
            System.out.print("Error: max number of possible symbols in the code is 36");
            System.exit(0);
        }

        if (symbols < codeLength) {
            System.out.printf("Error: it's not possible to generate a code" +
                    "with a length of %d with %d unique symbols", codeLength, symbols);
            System.exit(0);
        }

        while (secretCode.length() < codeLength) {
            Random random = new Random();
            int digit = random.nextInt(symbols);

            /* since String is an array of characters we can check it by index using .charAt, but that returns
            a character, so to compare it to secretCode which is a string builder we cast both as strings instead
            of iterating and checking it that way to make sure only unique characters are added */
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
            System.out.print("Error:Not a valid number");
            return;
        }

        System.out.println("Input the number of possible symbols in the code: ");
        int symbols;

        try {
            symbols = scanner.nextInt();
        } catch (InputMismatchException error) {
            System.out.print("Error:Not a valid number");
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
