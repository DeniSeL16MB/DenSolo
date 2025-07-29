import java.util.Scanner;

public class Klava {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        
        if (input.length() != 1 || !Character.isLowerCase(input.charAt(0))) {
            System.out.println("Введите одну строчную букву английского алфавита.");
            return;
        }
        
        char[] keyboard = {
            'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p',
            'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l',
            'z', 'x', 'c', 'v', 'b', 'n', 'm'
        };
        
        char inputChar = input.charAt(0);
        int index = -1;
        for (int i = 0; i < keyboard.length; i++) {
            if (keyboard[i] == inputChar) {
                index = i;
                break;
            }
        }
        
        if (index == -1) {
            System.out.println("Введенная буква не найдена на клавиатуре.");
            return;
        }
        
        int leftIndex = (index - 1 + keyboard.length) % keyboard.length;
        System.out.println(keyboard[leftIndex]);
    }
}
