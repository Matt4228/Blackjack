import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Deck{
    private Card[] deck = new Card[52];
    

    public Deck() {
        deck[0] = new Card(0, 1, "Ace of Hearts", true);
        deck[1] = new Card(1, 2, "Two of Hearts", false);
        deck[2] = new Card(2, 3, "Three of Hearts", false);
        deck[3] = new Card(3, 4, "Four of Hearts", false);
        deck[4] = new Card(4, 5, "Five of Hearts", false);
        deck[5] = new Card(5, 6, "Six of Hearts", false);
        deck[6] = new Card(6, 7, "Seven of Hearts", false);
        deck[7] = new Card(7, 8, "Eight of Hearts", false);
        deck[8] = new Card(8, 9, "Nine of Hearts", false);
        deck[9] = new Card(9, 10, "Ten of Hearts", false);
        deck[10] = new Card(10, 10, "Jack of Hearts", false);
        deck[11] = new Card(11, 10, "Queen of Hearts", false);
        deck[12] = new Card(12, 10, "King of Hearts", false);

        deck[13] = new Card(13, 1, "Ace of Diamonds", true);
        deck[14] = new Card(14, 2, "Two of Diamonds", false);
        deck[15] = new Card(15, 3, "Three of Diamonds", false);
        deck[16] = new Card(16, 4, "Four of Diamonds", false);
        deck[17] = new Card(17, 5, "Five of Diamonds", false);
        deck[18] = new Card(18, 6, "Six of Diamonds", false);
        deck[19] = new Card(19, 7, "Seven of Diamonds", false);
        deck[20] = new Card(20, 8, "Eight of Diamonds", false);
        deck[21] = new Card(21, 9, "Nine of Diamonds", false);
        deck[22] = new Card(22, 10, "Ten of Diamonds", false);
        deck[23] = new Card(23, 10, "Jack of Diamonds", false);
        deck[24] = new Card(24, 10, "Queen of Diamonds", false);
        deck[25] = new Card(25, 10, "King of Diamonds", false);

        deck[26] = new Card(26, 1, "Ace of Clubs", true);
        deck[27] = new Card(27, 2, "Two of Clubs", false);
        deck[28] = new Card(28, 3, "Three of Clubs", false);
        deck[29] = new Card(29, 4, "Four of Clubs", false);
        deck[30] = new Card(30, 5, "Five of Clubs", false);
        deck[31] = new Card(31, 6, "Six of Clubs", false);
        deck[32] = new Card(32, 7, "Seven of Clubs", false);
        deck[33] = new Card(33, 8, "Eight of Clubs", false);
        deck[34] = new Card(34, 9, "Nine of Clubs", false);
        deck[35] = new Card(35, 10, "Ten of Clubs", false);
        deck[36] = new Card(36, 10, "Jack of Clubs", false);
        deck[37] = new Card(37, 10, "Queen of Clubs", false);
        deck[38] = new Card(38, 10, "King of Clubs", false);

        deck[39] = new Card(39, 1, "Ace of Spades", true);
        deck[40] = new Card(40, 2, "Two of Spades", false);
        deck[41] = new Card(41, 3, "Three of Spades", false);
        deck[42] = new Card(42, 4, "Four of Spades", false);
        deck[43] = new Card(43, 5, "Five of Spades", false);
        deck[44] = new Card(44, 6, "Six of Spades", false);
        deck[45] = new Card(45, 7, "Seven of Spades", false);
        deck[46] = new Card(46, 8, "Eight of Spades", false);
        deck[47] = new Card(47, 9, "Nine of Spades", false);
        deck[48] = new Card(48, 10, "Ten of Spades", false);
        deck[49] = new Card(49, 10, "Jack of Spades", false);
        deck[50] = new Card(50, 10, "Queen of Spades", false);
        deck[51] = new Card(51, 10, "King of Spades", false);

    }

    public void shuffle() {
        Card[] shuffledDeck = new Card[52];
        List<Integer> indexes = new ArrayList<Integer>();
        int shuffled = 0;
        Random rng = new Random();
        while(shuffled < 51) {
            int index = rng.nextInt(51);
            if (!indexes.contains(index)) {
                indexes.add(index);
                shuffledDeck[index] = deck[shuffled];
                shuffled++;
                System.out.println(shuffled);
            }
        }
        System.out.print("bing");
        deck = shuffledDeck;
        System.out.print("bang");
    }

    public String toString() {
        String str = "";

        for (Card card : deck) {
            str += card.getCardTitle() + "\n";
        }

        return str;
    }
}