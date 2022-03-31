import java.util.ArrayList;
import java.util.Scanner;


public class App {
    public static void main(String[] args) throws Exception {
        Deck deck = new Deck();
        Person dealer = new Person();
        int num_players = 1;
        ArrayList<Person> players = new ArrayList<Person>();
        for(int i = 0; i < num_players; i++) {
            Person player = new Person();
            players.add(player);
        }

        Scanner keys = new Scanner(System.in);

        System.out.println("How many players?");
        num_players = keys.nextInt();
        System.out.flush();
        dealer.addToHand(deck.indexes[0]);
        players.get(0).addToHand(deck.indexes[1]);
        dealer.addToHand(deck.indexes[2]);
        players.get(0).addToHand(deck.indexes[3]);
        System.out.println("OK lets get started! The dealer is showing a " + dealer.getFaceUp().getCardTitle());
        System.out.println("You have:");
        ArrayList<Card>hand = players.get(0).getHand();
        for(Card card : hand) {
            System.out.println(card.getCardTitle());
        }
        System.out.println("hit or stay?");
        String input = keys.nextLine();
        

        
    }
}
