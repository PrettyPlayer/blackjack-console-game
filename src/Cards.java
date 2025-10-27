class Cards{
    final String[] cardsSuitArray = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    String[] cardsArray = new String[52];

    Cards() {
        fillCards();
    }
    public void fillCards(){
        for (int i = 0; i < this.cardsArray.length; i++) {
            if (i % 4 == 0){this.cardsArray[i] = "♣"+this.cardsSuitArray[i/4]+"♣";}
            else if (i % 4 == 1){this.cardsArray[i] = "♠"+this.cardsSuitArray[i/4]+"♠";}
            else if (i % 4 == 2){this.cardsArray[i] = "♥"+this.cardsSuitArray[i/4]+"♥";}
            else {this.cardsArray[i] = "♦"+this.cardsSuitArray[i/4]+"♦";}
            //System.out.println(this.cardsArray[i]);
        }
    }

}
