import java.util.Random;

public class Igra {
    public static void main(String[] args) {
        Random rand = new Random();


        int vasyaChoice = rand.nextInt(3);
        int petyaChoice = rand.nextInt(3);

        String[] options = {"Камень", "Ножницы", "Бумага"};

        System.out.println("Вася выбрал: " + options[vasyaChoice]);
        System.out.println("Петя выбрал: " + options[petyaChoice]);

        if (vasyaChoice == petyaChoice) {
            System.out.println("Ничья!");
        } else if ((vasyaChoice == 0 && petyaChoice == 1) ||
                   (vasyaChoice == 1 && petyaChoice == 2) ||
                   (vasyaChoice == 2 && petyaChoice == 0)) {
            System.out.println("Вася выиграл!");
        } else {
            System.out.println("Петя выиграл!");
        }
    }
}