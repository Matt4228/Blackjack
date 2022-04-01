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
            var hitButton = new JButton("Hit");
            var stayButton = new JButton("Stay");

            var buttonPanel = new JPanel();
            buttonPanel.add(playButton);

            playButton.addActionListener(e -> {
                area.setText(start(dealer, player, deck));
                buttonPanel.remove(playButton);
                buttonPanel.add(hitButton);
                buttonPanel.add(stayButton);
                buttonPanel.updateUI();
            });

            hitButton.addActionListener(e -> {
                hit(player, deck);
                area.setText(playerSB(dealer, player));
            });

            stayButton.addActionListener(e -> {
                //eval(area, player);
            });
            
            
            




            var frame = new JFrame("Blackjack");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new JScrollPane(area), BorderLayout.CENTER);
            frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
            frame.pack();
            frame.setVisible(true);

        });
    }

    public static void eval(Person player) {

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

        result += "The cards have been dealt and the dealer is showing a " + dealer.getFaceUp().getCardTitle();
        result += "\n\n";
        result += "You have: ";
        for(Card card : player.getHand()) {
            result += "\n" + card.getCardTitle();
        }
        result += "\nValue: " + player.getHandVal();

        return result;
    }
}
