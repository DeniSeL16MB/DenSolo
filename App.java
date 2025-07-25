import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        Телевизор tv1 = new Телевизор();
        Телевизор tv2 = new Телевизор("Samsung", 55, false, 20);

        System.out.println("Введите марку телевизора:");
        String маркаВвода = scanner.nextLine();

        System.out.println("Введите диагональ (в дюймах):");
        int диагональВвода = scanner.nextInt();

        System.out.println("Телевизор включен? (true/false):");
        boolean включенВвод = scanner.nextBoolean();

        System.out.println("Введите громкость (0-100):");
        int громкостьВвод = scanner.nextInt();

        Телевизор tv3 = new Телевизор(маркаВвода, диагональВвода, включенВвод, громкостьВвод);

        String[] марки = {"LG", "TCL", "Vityaz", "Sharp", "Toshiba"};
        String случайнаяМарка = марки[random.nextInt(марки.length)];
        int случайнаяДиагональ = 20 + random.nextInt(41);
        boolean случайныйВкл = random.nextBoolean();
        int случайнаяГромкость = random.nextInt(101);

        Телевизор tv4 = new Телевизор(случайнаяМарка, случайнаяДиагональ, случайныйВкл, случайнаяГромкость);

        System.out.println("Объекты Телевизор:");
        System.out.println(tv1);
        System.out.println(tv2);
        System.out.println(tv3);
        System.out.println(tv4);

        if (!tv2.isВключен()) {
            tv2.включить();
        }
        tv2.увеличитьГромкость();
        System.out.println("Статус tv2: " + tv2);

        scanner.close();
    }
}