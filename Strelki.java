import java.util.Scanner;

public class Strelki {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sequence = scanner.nextLine();

        int count = 0;
        int i = 0;
        int n = sequence.length();

        while (i < n) {
            if (i + 4 < n) {
                String fiveChars = sequence.substring(i, i + 5);
                if (fiveChars.equals(">>-->") || fiveChars.equals("<--<<")) {
                    count++;
                    i += 5;
                    continue;
                }
            }
            i++;
        }

        System.out.println(count);
    }
}
