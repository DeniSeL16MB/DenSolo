public class Телевизор {
    private String марка;
    private int диагональ;
    private boolean включен;
    private int громкость;

    public Телевизор() {
        this.марка = "Не указано";
        this.диагональ = 32;
        this.включен = false;
        this.громкость = 10;
    }

    public Телевизор(String марка, int диагональ, boolean включен, int громкость) {
        this.марка = марка;
        this.диагональ = диагональ;
        this.включен = включен;
        this.громкость = громкость;
    }

    public String getМарка() { return марка; }
    public void setМарка(String марка) { this.марка = марка; }

    public int getДиагональ() { return диагональ; }
    public void setДиагональ(int диагональ) { this.диагональ = диагональ; }

    public boolean isВключен() { return включен; }
    public void setВключен(boolean включен) { this.включен = включен; }

    public int getГромкость() { return громкость; }
    public void setГромкость(int громкость) {
        if (громкость < 0) this.громкость = 0;
        else if (громкость > 100) this.громкость = 100;
        else this.громкость = громкость;
    }

    public void включить() {
        включен = true;
        System.out.println("Телевизор включен");
    }

    public void выключить() {
        включен = false;
        System.out.println("Телевизор выключен");
    }

    public void увеличитьГромкость() {
        if (громкость < 100) {
            громкость++;
            System.out.println("Громкость увеличена до " + громкость);
        } else {
            System.out.println("Громкость уже максимальна");
        }
    }

    public void уменьшитьГромкость() {
        if (громкость > 0) {
            громкость--;
            System.out.println("Громкость уменьшена до " + громкость);
        } else {
            System.out.println("Громкость уже минимальна");
        }
    }

    @Override
    public String toString() {
        return "Телевизор{" +
                "марка='" + марка + '\'' +
                ", диагональ=" + диагональ +
                ", включен=" + включен +
                ", громкость=" + громкость +
                '}';
    }
}