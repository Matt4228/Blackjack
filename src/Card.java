public class Card {
    private int cardNum;
    private int cardVal;
    private String cardTitle;
    private String cardImg;
    private boolean ace;

    public Card() {
        cardNum = 0;
        cardVal = 0;
        cardTitle = "Joker";
        cardImg = null;
        ace = false;
    }

    public Card(int num, int val, String title, boolean ace) {
        cardNum = num;
        cardVal = val;
        cardTitle = title;
        cardImg = "null";
        this.ace = ace;
    }

    public Card(int num, int val, String title, String url, boolean ace) {
        cardNum = num;
        cardVal = val;
        cardTitle = title;
        cardImg = url;
        this.ace = ace;
    }

    public int getCardNum() {
        return cardNum;
    }

    public int getCardVal() {
        return cardVal;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public String getCardImg() {
        return cardImg;
    }

    public boolean isAce() {
        return ace;
    }

    public void setCardNum(int num) {
        cardNum = num;
    }

    public void setCardVal(int val) {
        cardVal = val;
    }

    public void setCardTitle(String title) {
        cardTitle = title;
    }

    public void setCardImg(String url) {
        cardImg = url;
    }

    public void setAce(boolean ace) {
        this.ace = ace;
    }
}