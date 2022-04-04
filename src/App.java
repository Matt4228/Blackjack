import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;

public class App {
    public static void main(String[] args) throws Exception {
        //local game variables
        Scanner keys = new Scanner(System.in);
        Deck deck = new Deck();
        Person dealer = new Person();
        Person player = new Person();
        boolean anyPlaying = true;

        
        
        SwingUtilities.invokeLater(() -> {
            var initialText = "Welcome to Blackjack";
            var area = new JTextArea(initialText, 8, 50);

            //buttons
            var playButton = new JButton("Play");
            var playAgainButton = new JButton("Play Again");
            var hitButton = new JButton("Hit");
            var stayButton = new JButton("Stay");


            //start window
            var buttonPanel = new JPanel();
            buttonPanel.add(playButton);

            //play button action -> initate game
            playButton.addActionListener(e -> {
                area.setText(start(dealer, player, deck));
                buttonPanel.remove(playButton);

                boolean blackjack = bj(player);
                
                if(!blackjack){
                    buttonPanel.add(hitButton);
                    buttonPanel.add(stayButton);
                }
                else {
                    buttonPanel.add(playAgainButton);
                }
                
                buttonPanel.updateUI();
            });

            //update = area.setText();

            playAgainButton.addActionListener(e -> {
                dealer.reset();
                player.reset();
                deck.reset();

                area.setText(start(dealer, player, deck));
                buttonPanel.remove(playButton);

                boolean blackjack = bj(player);
                
                if(!blackjack){
                    buttonPanel.add(hitButton);
                    buttonPanel.add(stayButton);
                }
                else {
                    buttonPanel.add(playAgainButton);
                }
                
                buttonPanel.updateUI();
            });

            hitButton.addActionListener(e -> {
                hit(player, deck);
                area.setText(playerSB(dealer, player));

                boolean blackjack = bj(player);
                boolean valid = eval(player);

                if (!valid || blackjack) {
                    buttonPanel.remove(hitButton);
                    buttonPanel.remove(stayButton);
                    buttonPanel.add(playAgainButton);
                }
                
            });

            stayButton.addActionListener(e -> {
                
            });
            
            
            




            var frame = new JFrame("Blackjack");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new JScrollPane(area), BorderLayout.CENTER);
            frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
            frame.pack();
            frame.setVisible(true);

        });
    }

    public static boolean compare(Person player, Person dealer) {
        
        return false;
    }

    public static boolean bj(Person player) {
        if(player.getHandVal() == 21) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean eval(Person player) {
        int num_aces = 0;
        for (Card card : player.getHand()) {
            if(card.isAce()) {
                num_aces++;
            }
        }

        int value = player.getHandVal();

        while(value > 21) {
            if(num_aces > 0) {
                value -= 10;
                num_aces--;
            }
            else {
                return false;
            }
        }
        if (value <= 21) {
            return true;
        }

        return false;
        

    }

    public static void hit(Person player, Deck deck) {
        player.addToHand(deck.getNextCard());
    }

    public static String start(Person dealer, Person player, Deck deck) {
        String result = "";

        player.addToHand(deck.getNextCard());
        dealer.addToHand(deck.getNextCard());
        player.addToHand(deck.getNextCard());
        dealer.addToHand(deck.getNextCard());

        
        result += playerSB(dealer, player);


        return result;
    }


    public static String playerSB(Person dealer, Person player) {
        String result = "";

        boolean blackjack = bj(player);
        boolean valid = eval(player);

        result += "The cards have been dealt and the dealer is showing a " + dealer.getFaceUp().getCardTitle();
        result += "\n\n";
        result += "You have: ";
        for(Card card : player.getHand()) {
            result += "\n" + card.getCardTitle();
        }

        if(blackjack) {
            result += "\nYou got Blackjack, you win!";
        }
        else if (!valid) {
            result += "\nOh no! You busted! Better luck next time";
        }
        else{
            result += "\nValue: " + player.getHandVal();
        }
        

        return result;
    }
}
