import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int hour = LocalDateTime.now().getHour();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        scanner.close();

        if (hour >= 5 && hour < 12) {
            System.out.print("Good Morning");
        } else if (hour >= 12 && hour < 18) {
            System.out.print("Good Afternoon");
        } else if (hour >= 18 && hour < 24) {
            System.out.print("Good Evening");
        } else { // часы с 0 до 4 включительно
            System.out.print("Good Night");
        }

        System.out.println(", " + name);
    }
}