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
        char[] tempString = new char[4];
        for (int i = 0; i < tempString.length; i++) {
            tempString[i] = Character.forDigit(code.get(i), 10);
        }

        return new String(tempString);
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
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Code guess = new Code(scanner.nextLine());
        Game game = new Game("2474");

        Game.Grader(game, guess);

        game.printResults();

    }
}
