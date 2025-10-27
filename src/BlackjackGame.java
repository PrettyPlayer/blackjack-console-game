import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class BlackjackGame {
    static boolean play1 = true;
    static boolean play2 = true;
    static int currentBet;
    static int currentMoney;
    static int randomNumber;
    static int yourScore;
    static int botScore;
    static String[][] cardsArray = new String[52][2];
    static final String[] cardsSuitArray = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    static Random rand = new Random();
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<String> deckListPlayer = new ArrayList<>();
    static ArrayList<String> deckListBot = new ArrayList<>();

    public static void start() {
        setMoney();
        play1 = true;
        do {
            refreshCurrentGame();
            refreshDeck();
            setBet();
            takeCard("bot", 1);
            takeCard("player", 1);
            playerPlay();
            botPlay();
            getMoney();
            System.out.println("Сыграем еще?");
            play1 = scanner.nextInt()==1;
        } while (play1);
    }





    public static void setBet(){
        do {
            System.out.println("Введите ставку");
            currentBet = scanner.nextInt();
            if (currentBet>currentMoney){
                System.out.println("Не хватает денег");
            }
        } while (currentBet>currentMoney);
    }

    public static void getMoney() {
        System.out.println("У вас " + currentMoney + " рублей");
    }

    public static void setMoney() {
        System.out.println("Введите стартовое количество денег");
        currentMoney = scanner.nextInt();
    }

    public static void playerPlay() {
        play2 = true;
        do {
            takeCard("player", 1);
            while (deckListBot.size()==1 && botScore>yourScore)
            {
                takeCard("player", 1);
            }
            showDeck(deckListBot, "bot");
            showDeck(deckListPlayer, "player");
            if(checkResult()){
                break;
            }
            play2 = scanner.nextInt() == 1;
        } while (play2);
        play2 = false;
    }

    public static void botPlay() {
        while (botScore<=yourScore && yourScore<21) {
            takeCard("bot", 1);
            showDeck(deckListBot, "bot");
            showDeck(deckListPlayer, "player");
            if(checkResult()){
                break;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void fillDeck() {
        for (int i = 0; i < cardsArray.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (j % 2 == 0) {
                    if (i % 4 == 0) {
                        cardsArray[i][j] = "♣" + cardsSuitArray[i / 4] + "♣";
                    } else if (i % 4 == 1) {
                        cardsArray[i][j] = "♠" + cardsSuitArray[i / 4] + "♠";
                    } else if (i % 4 == 2) {
                        cardsArray[i][j] = "♥" + cardsSuitArray[i / 4] + "♥";
                    } else {
                        cardsArray[i][j] = "♦" + cardsSuitArray[i / 4] + "♦";
                    }
                    //System.out.println(cardsArray[i][j]);
                } else {
                    cardsArray[i][j] = String.valueOf(i / 4 + 1);
                    //System.out.println(cardsArray[i][j]);
                }
            }
        }
    }

    public static void takeCard(String who, Integer countCards){
        for (countCards=countCards; countCards>0; countCards--) {
            randomNumber = rand.nextInt(cardsArray.length);
            if (who == "bot") {
                botScore += Integer.parseInt(cardsArray[randomNumber][1]);
                deckListBot.add(cardsArray[randomNumber][0]);
                //System.out.println("Противнику выпала карта " + cardsArray[randomNumber][0] + " | " + cardsArray[randomNumber][1] + " | " + botScore);
                //System.out.println("Карты противника: " + showDeck(deckListBot));
            } else if (who == "player") {
                yourScore += Integer.parseInt(cardsArray[randomNumber][1]);
                deckListPlayer.add(cardsArray[randomNumber][0]);
                //System.out.println("Вам выпала карта " + cardsArray[randomNumber][0] + " | " + cardsArray[randomNumber][1] + " | " + yourScore);
                //System.out.println("Ваши карты: " + showDeck(deckListPlayer));
            }
        }
    }
    public static void refreshDeck(){
        deckListPlayer.clear();
        deckListBot.clear();
        fillDeck();
    }
    public static void refreshCurrentGame(){
        yourScore = 0;
        botScore = 0;
    }
    public static void showDeck(ArrayList<String> list, String owner){
        StringBuilder deckBuilder = new StringBuilder();
        for (String card : list) {
            deckBuilder.append(card).append(" ");
        }
        if (owner == "bot"){
            System.out.println("Карты противника: " + deckBuilder.toString().trim() + " | " + botScore);
        } else {
            System.out.println("Ваши карты: " + deckBuilder.toString().trim() + " | " + yourScore);
        }
    }
    public static boolean checkResult() {
        if (yourScore > 21 || botScore == 21 || (botScore>yourScore && botScore<22)) {
            System.out.println("Вы проиграли");
            currentMoney -= currentBet;
            return true;
        } else if (yourScore == 21 || botScore > 21) {
            System.out.println("Вы выиграли " + currentBet + " рублей");
            currentMoney += currentBet;
            return true;
        } else if (play2) {
            System.out.println("Взять еще карту?");
            return false;
        } else {
            return false;
        }
    }
}
