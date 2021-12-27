package bullscows;

import java.util.*;

class Code {
    private final List<Integer> code = new ArrayList<>();

    public Code(String secretCode) {
        for (String num : secretCode.split("")) {
            this.code.add(Integer.valueOf(num));
        }

    }

    public List<Integer> getCode() {
        return this.code;
    }

    public int getFirstDigit() {
        return code.get(0);
    }

    public int getSecondDigit() {
        return code.get(1);
    }

    public int getThirdDigit() {
        return code.get(2);
    }

    public int getFourthDigit() {
        return code.get(3);
    }

   /* public int size() {
        return code.size();
    } */
}

class Game{
    private int bulls;
    private int cows;
    private final Code secret;

    public Game(Code secretCode) {
        this.bulls = 0;
        this.cows = 0;
        this.secret = secretCode;
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

    public Code getSecret() {
        return secret;
    }

    public void Grader(Game game, Code guess) {

        if (game.getSecret().getFirstDigit() == guess.getFirstDigit()) {
            game.addBull();
        }

        if (game.getSecret().getSecondDigit() == guess.getSecondDigit()) {
            game.addBull();
        }

        if (game.getSecret().getThirdDigit() == guess.getThirdDigit()) {
            game.addBull();
        }

        if (game.getSecret().getFourthDigit() == guess.getFourthDigit()) {
            game.addBull();
        }




    }

    public void printResults() {
        if (bulls == 0 && cows == 0) {
            System.out.println("Grade: None. The secret code is 2474");
        } else if (bulls == 0) {
            System.out.printf("Grade: %d cow(s). The secret code is 2474 \n", cows);
        } else if (cows == 0) {
            System.out.printf("Grade: %d bull(s). The secret code is 2474 \n", bulls);
        } else {
            System.out.printf("Grade: %d bull(s) and %d cow(s). The secret code is 2474 \n", bulls, cows);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> secret = new ArrayList<>();
        Game.secretCode.


        ArrayList<Integer> input = new ArrayList<>();







    }



}
