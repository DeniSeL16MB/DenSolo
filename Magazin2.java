import java.util.ArrayList;
import java.util.List;
import java.util.MissingFormatWidthException;
import java.util.Scanner;
import java.time.LocalDate;

class Product {
    private String name;
    private double cost;

    public Product(String name, double cost) {
        setName(name);
        setCost(cost);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым");
        }
        if (name.trim().length() < 3) {
            throw new IllegalArgumentException("Название продукта должно быть не короче 3 символов");
        }
        if (name.trim().matches("^\\d+$")) {
            throw new IllegalArgumentException("Название не должно содержать только цифры");
        }
        this.name = name.trim();
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        if (cost <= 0) {
            throw new IllegalArgumentException("Стоимость продукта должна быть больше нуля");
        }
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (Double.compare(product.cost, cost) != 0) return false;
        return name.equals(product.name);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

class DiscountProduct extends Product {
    private double discount;
    private LocalDate discountEndDate;

    public DiscountProduct(String name, double cost, double discount, LocalDate discountEndDate) {
        super(name, cost);
        if (discount <= 0) {
            throw new IllegalArgumentException("Размер скидки должен быть больше нуля");
        }
        if (discount >= getCost()) {
            throw new IllegalArgumentException("Скидка не должна быть равна или превышать стоимость");
        }
        this.discount = discount;
        this.discountEndDate = discountEndDate;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        if (discount <= 0) {
            throw new IllegalArgumentException("Размер скидки должен быть больше нуля");
        }
        if (discount >= getCost()) {
            throw new IllegalArgumentException("Скидка не должна быть равна или превышать стоимость");
        }
        this.discount = discount;
    }

    public LocalDate getDiscountEndDate() {
        return discountEndDate;
    }

    public void setDiscountEndDate(LocalDate discountEndDate) {
        this.discountEndDate = discountEndDate;
    }

    public double getCurrentCost() {
        LocalDate today = LocalDate.now();
        if (today.isBefore(discountEndDate)) {
            return getCost() - discount;
        } else {
            return getCost();
        }
    }

    @Override
    public String toString() {
        return "DiscountProduct{" +
                "name='" + getName() + '\'' +
                ", originalCost=" + getCost() +
                ", discount=" + discount +
                ", discountEndDate=" + discountEndDate +
                '}';
    }

    @Override
    public double getCost() {
        return getCurrentCost();
    }

    @Override
    public void setCost(double cost) {
        if (cost <= 0) {
            throw new IllegalArgumentException("Стоимость продукта должна быть больше нуля");
        }
        super.setCost(cost);
    }
}

class Person {
    private String name;
    private double money;
    private List<Product> packageProducts;

    public Person(String name, double money) {
        setName(name);
        setMoney(money);
        this.packageProducts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (name.trim().length() < 3) {
            throw new IllegalArgumentException("Имя не может быть короче 3 символов");
        }
        this.name = name.trim();
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Деньги не могут быть отрицательными");
        }
        this.money = money;
    }

    public List<Product> getPackageProducts() {
        return packageProducts;
    }

    public boolean buyProduct(Product product) {
        double price = product.getCost();
        if (price <= this.money) {
            this.money -= price;
            packageProducts.add(product);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Покупатель: ").append(name).append(", Остаток денег: ").append(money).append(", Продукты: ");
        if (packageProducts.isEmpty()) {
            sb.append("ничего не куплено");
        } else {
            for (Product p : packageProducts) {
                sb.append(p.getName()).append(" ");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (Double.compare(person.money, money) != 0) return false;
        if (!name.equals(person.name)) return false;
        return packageProducts.equals(person.packageProducts);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        temp = Double.doubleToLongBits(money);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + packageProducts.hashCode();
        return result;
    }
}

public class Magazin2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Person> buyers = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        System.out.println("Введите данные покупателей. Для завершения введите 'end'.");
        while (true) {
            System.out.print("Введите имя покупателя: ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("end")) {
                break;
            }
            try {
                if (name.trim().isEmpty()) {
                    System.out.println("Имя не может быть пустым");
                    continue;
                }
                if (name.trim().length() < 3) {
                    System.out.println("Имя не может быть короче 3 символов");
                    continue;
                }
                System.out.print("Введите сумму денег: ");
                String moneyInput = scanner.nextLine();
                double money;
                try {
                    money = Double.parseDouble(moneyInput);
                } catch (NumberFormatException e) {
                    System.out.println("Деньги не могут быть отрицательными");
                    continue;
                }
                if (money < 0) {
                    System.out.println("Деньги не могут быть отрицательными");
                    continue;
                }
                Person person = new Person(name, money);
                buyers.add(person);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Введите продукты. Для завершения введите 'END'.");
        while (true) {
            System.out.print("Введите название продукта: ");
            String prodName = scanner.nextLine();
            if (prodName.equalsIgnoreCase("END")) {
                break;
            }
            if (prodName.trim().isEmpty()) {
                System.out.println("Название продукта не может быть пустым");
                continue;
            }

            System.out.print("Введите стоимость продукта: ");
            String costInput = scanner.nextLine();
            double cost;
            try {
                cost = Double.parseDouble(costInput);
            } catch (NumberFormatException e) {
                System.out.println("Стоимость должна быть числом");
                continue;
            }
            if (cost <= 0) {
                System.out.println("Стоимость не может быть отрицательной или нулевой");
                continue;
            }

            try {
System.out.print("Это скидочный продукт? (da/net): ");
String isDiscount = scanner.nextLine();

Product product;

if (isDiscount.equalsIgnoreCase("da")) {
    System.out.print("Введите величину скидки: ");
    String discountStr = scanner.nextLine();
    double discount;
    try {
        discount = Double.parseDouble(discountStr);
        if (discount <= 0) {
            System.out.println("Величина скидки должна быть больше нуля");
            continue;
        }
    } catch (NumberFormatException e) {
        System.out.println("Некорректный формат скидки");
        continue;
    }

    System.out.print("Введите срок действия скидки в днях: ");
    String daysStr = scanner.nextLine();
    int days;
    try {
        days = Integer.parseInt(daysStr);
        if (days <= 0) {
            System.out.println("Срок действия должен быть больше 0");
            continue;
        }
    } catch (NumberFormatException e) {
        System.out.println("Некорректный формат срока");
        continue;
    }
MissingFormatWidthException
    LocalDate endDate = LocalDate.now().plusDays(days);
    try {
        product = new DiscountProduct(prodName, cost, discount, endDate);
    } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
        continue;
    }
} else {
    try {
        product = new Product(prodName, cost);
    } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
        continue;
    }
}
products.add(product);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        for (Person buyer : buyers) {
            System.out.println("Покупки для " + buyer.getName() + ":");
            while (true) {
                System.out.println("Введите название продукта для покупки или 'end' для завершения:");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("end")) {
                    break;
                }
                Product productToBuy = null;
                for (Product p : products) {
                    if (p.getName().equalsIgnoreCase(input.trim())) {
                        productToBuy = p;
                        break;
                    }
                }
                if (productToBuy == null) {
                    System.out.println("Нет такого продукта в списке");
                    continue;
                }
                if (buyer.buyProduct(productToBuy)) {
                    System.out.println(buyer.getName() + " купил " + productToBuy.getName());
                } else {
                    System.out.println(buyer.getName() + " не может позволить себе " + productToBuy.getName());
                }
            }
        }

        for (Person buyer : buyers) {
            if (buyer.getPackageProducts().isEmpty()) {
                System.out.println(buyer.getName() + " Ничего не куплено");
            } else {
                System.out.println(buyer.getName() + " купил:");
                for (Product p : buyer.getPackageProducts()) {
                    System.out.println("- " + p.getName() + " за " + p.getCost());
                }
            }
        }

        scanner.close();
    }
}