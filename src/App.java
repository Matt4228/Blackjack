import java.util.ArrayList;
import java.util.Scanner;


public class App {
    public static void main(String[] args) throws Exception {
        //local game variables
        Scanner keys = new Scanner(System.in);
        Deck deck = new Deck();
        Person dealer = new Person();
        ArrayList<Person> players = new ArrayList<Person>();
        boolean anyPlaying = true;

        //intitialize players
        System.out.println("How many players?");
        int num_players = keys.nextInt();
        for(int i = 0; i < num_players; i++) {
            Person player = new Person();
            players.add(player);
        }

        //set up
        dealer.addToHand(deck.getNextCard());
        players.get(0).addToHand(deck.getNextCard());
        dealer.addToHand(deck.getNextCard());
        players.get(0).addToHand(deck.getNextCard());
        System.out.println("OK lets get started! The dealer is showing a " + dealer.getFaceUp().getCardTitle());

        String disc = keys.nextLine();

        //play loop
        while(anyPlaying) {
            printPlayerSB(players.get(0));
            System.out.println("hit or stay?");
            String input = keys.nextLine();
            if(input.equals("stay")) {
                anyPlaying = false;
            }
            else {
                players.get(0).addToHand(deck.getNextCard());
            }


        }

        //Who won?

        
        
        
        
        

        keys.close();
    }


    public static void printPlayerSB(Person player) {
        System.out.println("You have:");
            ArrayList<Card>hand = player.getHand();
            for(Card card : hand) {
                System.out.println(card.getCardTitle());
            }
            System.out.println("Value: " + player.getHandVal());
    }
}
