import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Deck{
    public int[] indexes = new int[52];
    private int i = 0;
    

    public Deck() {
        shuffle();
    }

    public void shuffle() {
        Random rng = new Random();
        List<Integer> shuffledList = new ArrayList<Integer>();
        int shuffled = 0;
        while(shuffled < 52) {
            int index = rng.nextInt(52);
            if(!shuffledList.contains(index)){
                indexes[shuffled] = index;
                shuffledList.add(index);
                shuffled++;
            }
        }
    }

    public int getNextCard() {
        i++;
        return indexes[i-1];
    }

    public String toStringInOrder() {
        String str = "";

        for (int i = 0; i < 52; i++) {
            Card card = new Card(i);
            str += card.getCardTitle() + "\n";
        }

        return str;
    }

    public String toString() {
        String str = "";

        for (int index: indexes) {
            Card card = new Card(index);
            str += card.getCardTitle() + "\n";
        }

        return str;
    }

    public void reset() {
        i = 0;
        shuffle();
    }
}