import java.util.ArrayList;


public class Person {
    private ArrayList<Card> hand = new ArrayList<Card>();

    public Person() {

    }

    public void addToHand(int i) {
        Card card = new Card(i);
        hand.add(card);
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public Card getFaceUp() {
        return hand.get(1);
    }

    public Card getFaceDown() {
        return hand.get(0);
    }

    public int getHandVal() {
        int total = 0;

        for(Card card : hand) {
            total += card.getCardVal();
        }

        return total;
    }
}


