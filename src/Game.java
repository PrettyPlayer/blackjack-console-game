import java.util.Scanner;

class Game {
    int money;

    Game() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Сколько денег вы имеете?");
        this.setMoney(scanner.nextInt());
    }
    public void startGame(){
        Cards cards = new Cards();
    }
    public void setMoney(int startMoney) {
        this.money = startMoney;
    }
    public int getMoney(){
        return this.money;
    }
}
