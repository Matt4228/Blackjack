


public class App {
    public static void main(String[] args) throws Exception {
        Deck deck = new Deck();
        System.out.println(deck.toString());
        System.out.println("\n--------------------\n");
        deck.shuffle();
        System.out.println(deck.toString());
    }
}
