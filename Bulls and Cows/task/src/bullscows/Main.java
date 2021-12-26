package bullscows;

import java.util.*;

class Game{
    static int bulls = 0;
    static int cows = 0;
    static ArrayList<String> code = new ArrayList<>();


    public void Grader(List guess) {






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


        List<Integer> input = new ArrayList<>();

        for (String num : String.valueOf(scanner.nextInt()).split("")) {
            input.add(Integer.valueOf(num));
        }






    }



}
