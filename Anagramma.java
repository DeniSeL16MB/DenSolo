import java.util.Arrays;
import java.util.Scanner;

public class Anagramma {

    public static boolean isAnagram(String s, String t) {
        s = s.replaceAll("\\s", "").toLowerCase();
        t = t.replaceAll("\\s", "").toLowerCase();

        if (s.length() != t.length()) {
            return false;
        }

        char[] arrS = s.toCharArray();
        char[] arrT = t.toCharArray();

        Arrays.sort(arrS);
        Arrays.sort(arrT);

        return Arrays.equals(arrS, arrT);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите первую строку:");
        String s = scanner.nextLine();

        System.out.println("Введите вторую строку:");
        String t = scanner.nextLine();

        System.out.println(isAnagram(s, t));
    }
}
