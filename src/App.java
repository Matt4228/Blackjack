import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;

public class App {
    public static void main(String[] args) throws Exception {
        //game elements
        Deck deck = new Deck();
        Person dealer = new Person();
        Person player = new Person();

        
        SwingUtilities.invokeLater(() -> {
            var initialText = "Welcome to Blackjack";
            var area = new JTextArea(initialText, 20, 50);

            //buttons
            var playButton = new JButton("Play");
            var playAgainButton = new JButton("Play Again");
            var hitButton = new JButton("Hit");
            var stayButton = new JButton("Stay");

            //add button panel
            var buttonPanel = new JPanel();
            buttonPanel.add(playButton);

            //play button action -> initate game
            playButton.addActionListener(e -> {
                //update text area and evaluate hand
                boolean bj = startGame(area, deck, dealer, player);
                
                //update buttonPanel
                buttonPanel.remove(playButton);
                System.out.println("Bang");

                if(bj) {
                    buttonPanel.add(playAgainButton);
                }
                else {
                    buttonPanel.add(hitButton);
                    buttonPanel.add(stayButton);
                }

                buttonPanel.updateUI();
            });

            //play again
            playAgainButton.addActionListener(e -> {
                //update text area and evaulate hand
                boolean bj = startGame(area, deck, dealer, player);
                
                //update buttonPanel
                buttonPanel.remove(playAgainButton);

                if(bj) {
                    buttonPanel.add(playAgainButton);
                }
                else {
                    buttonPanel.add(hitButton);
                    buttonPanel.add(stayButton);
                }

                buttonPanel.updateUI();
            });

            //hit
            hitButton.addActionListener(e -> {
                //update text area and evaluate hand
                boolean busted = hit(area, deck, dealer, player);

                if(busted) {
                    buttonPanel.remove(hitButton);
                    buttonPanel.remove(stayButton);
                    buttonPanel.add(playAgainButton);
                    buttonPanel.updateUI();
                }
            });

            //stay
            stayButton.addActionListener(e -> {
                endGame(area, deck, dealer, player);
                
                buttonPanel.remove(hitButton);
                buttonPanel.remove(stayButton);
                buttonPanel.add(playAgainButton);
                buttonPanel.updateUI();
            });
            

            var frame = new JFrame("Blackjack");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new JScrollPane(area), BorderLayout.CENTER);
            frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
            frame.pack();
            frame.setVisible(true);

        });
    }

    public static boolean startGame(JTextArea area, Deck deck, Person dealer, Person player) {
        deck.reset();
        dealer.reset();
        player.reset();
        
        player.addToHand(deck.getNextCard());
        dealer.addToHand(deck.getNextCard());
        player.addToHand(deck.getNextCard());
        dealer.addToHand(deck.getNextCard());

        String result = sb(false, deck, dealer, player);

        if(player.getHandVal() == 21) {
            result += "\nBackjack! You win!";
            area.setText(result);
            return true;
        }
        else {
            area.setText(result);
            return false;
        }
    }

    public static boolean hit(JTextArea area, Deck deck, Person dealer, Person player) {
        player.addToHand(deck.getNextCard());
        String result = sb(false, deck, dealer, player);

        if(player.getHandVal() > 21) {
            result += "\nOh no! You busted. Better luck next time!";
            area.setText(result);
            return true;
        }
        else {
            area.setText(result);
            return false;
        }

    }

    public static void endGame(JTextArea area, Deck deck, Person dealer, Person player) {
        while(dealer.getHandVal() < 17 && dealer.getHandVal() < player.getHandVal()) {
            dealer.addToHand(deck.getNextCard());
        }
        
        String result = sb(true, deck, dealer, player);

        if(dealer.getHandVal() > 21) {
            result += "\nThe dealer busts! You Win!";
        }
        else if(dealer.getHandVal() < player.getHandVal()) {
            result += "\nYou have a better hand than the dealer! You Win!";
        }
        else if(dealer.getHandVal() == player.getHandVal()){
            result += "\nYou and the dealer have equivalent hands! You split the pot.";
        }
        else {
            result += "\nThe dealer has a better hand. You lose. Better luck next time!";
        }

        area.setText(result);
    }

    public static String sb(boolean showAll, Deck deck, Person dealer, Person player) {
        String result = "";

        if(showAll) {
            result = "The dealer has:";
            for(Card card : dealer.getHand()) {
                result += "\n" + card.getCardTitle();
            }
        }
        else {
            result = "The dealer is showing a " + dealer.getFaceUp().getCardTitle();
        }


        result += "\n\nYou have:";
        for(Card card : player.getHand()){
            result += "\n" + card.getCardTitle();
        }
        result += "\nValue:" + player.getHandVal();

        return result;
    }

}



//----------------Earlier Versions------------------------
// import java.util.ArrayList;
// import java.util.Scanner;

// import javax.swing.SwingUtilities;
// import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;
// import javax.swing.JFrame;
// import javax.swing.JPanel;
// import javax.swing.JTextArea;
// import javax.swing.JButton;
// import javax.swing.JScrollPane;

// import java.awt.BorderLayout;
// import java.awt.Color;

// public class App {
//     public static void main(String[] args) throws Exception {
//         //local game variables
//         Scanner keys = new Scanner(System.in);

//         //game elements
//         Deck deck = new Deck();
//         Person dealer = new Person();
//         Person player = new Person();

        
        
//         SwingUtilities.invokeLater(() -> {
//             var initialText = "Welcome to Blackjack";
//             var area = new JTextArea(initialText, 20, 50);

//             //buttons
//             var playButton = new JButton("Play");
//             var playAgainButton = new JButton("Play Again");
//             var hitButton = new JButton("Hit");
//             var stayButton = new JButton("Stay");


//             //start window
//             var buttonPanel = new JPanel();
//             buttonPanel.add(playButton);

//             //play button action -> initate game
//             playButton.addActionListener(e -> {
//                 area.setText(start(dealer, player, deck));
//                 buttonPanel.remove(playButton);

//                 boolean blackjack = bj(player);
                
//                 if(!blackjack){
//                     buttonPanel.add(hitButton);
//                     buttonPanel.add(stayButton);
//                 }
//                 else {
//                     buttonPanel.add(playAgainButton);
//                 }
                
//                 buttonPanel.updateUI();
//             });

//             //update = area.setText();

//             playAgainButton.addActionListener(e -> {
//                 dealer.reset();
//                 player.reset();
//                 deck.reset();

//                 area.setText(start(dealer, player, deck));
//                 buttonPanel.remove(playAgainButton);

//                 boolean blackjack = bj(player);
                
//                 if(!blackjack){
//                     buttonPanel.add(hitButton);
//                     buttonPanel.add(stayButton);
//                 }
//                 else {
//                     buttonPanel.add(playAgainButton);
//                 }
                
//                 buttonPanel.updateUI();
//             });

//             hitButton.addActionListener(e -> {
//                 hit(player, deck);
//                 boolean valid = playerPlay(player);

//                 // area.setText(playerSBv1(dealer, player));

//                 // boolean blackjack = bj(player);
//                 // boolean valid = eval(player);

//                 // if (!valid || blackjack) {
//                 //     buttonPanel.remove(hitButton);
//                 //     buttonPanel.remove(stayButton);
//                 //     buttonPanel.add(playAgainButton);
//                 // }
//                 // buttonPanel.updateUI();
//             });

//             stayButton.addActionListener(e -> {
//                 buttonPanel.remove(hitButton);
//                 buttonPanel.remove(stayButton);
//                 buttonPanel.add(playAgainButton);
//                 buttonPanel.updateUI();

//                 boolean win = compare(player, dealer, deck, area);

//             });
            
            
            




//             var frame = new JFrame("Blackjack");
//             frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//             frame.getContentPane().add(new JScrollPane(area), BorderLayout.CENTER);
//             frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
//             frame.pack();
//             frame.setVisible(true);

//         });
//     }

//     //------------------------v2----------------------------------

//     //start game
//     public static void startGame(Person player, Person dealer, Deck deck, JTextArea area, JPanel buttonPanel, JButton play, JButton hit, JButton stay, JButton playAgain) {
//         //reset game elements
//         dealer.reset();
//         player.reset();
//         deck.reset();
//         String text = "";

//         //deal
//         player.addToHand(deck.getNextCard());
//         dealer.addToHand(deck.getNextCard());
//         player.addToHand(deck.getNextCard());
//         dealer.addToHand(deck.getNextCard());

//         //show player
//         text = "The dealer is showing:\n" + dealer.getFaceUp().getCardTitle() + "\n\n" + playerSB(player);
//         area.setText(text);


//         //check natural bj
//         if(bj(player)) {
//             text += "\n\nBackjack! You win!";
//             buttonPanel.remove(hit);
//             buttonPanel.remove(stay);
//             buttonPanel.add(playAgain);
//             buttonPanel.updateUI();
//         }

//         //player play

//         //check hand


//         //show dealer hand


//         //dealer play


//         //validate dealer hand


//         //outcome?


//         //play again


//     }

//     //player play
//     public static boolean playerPlay(Person player) {
//         //hit
//         if(!validHand(player)) {
//             return false;
//         }

//         //stay or bust
//             //remove play buttons

//         return true;
//     }

//     //dealer automated play
//     public static void dealerPlay() {
//         //check 17
//             //hit
//     }

//     public static boolean validHand(Person player) {
//         if (player.getHandVal() > 21) {
//             return false;
//         }
//         return true;
//     }

//     public static String playerSB(Person player) {
//         String result = "You have:";

//         for(Card card : player.getHand()) {
//             result += "\n" + card.getCardTitle();
//         }

//         result += "\nValue:" + player.getHandVal();

//         return result;
//     }

//     public static boolean bj(Person player) {
//         if(player.getHandVal() == 21) {
//             return true;
//         }
//         else {
//             return false;
//         }
//     }

//     public static void hit(Person player, Deck deck) {
//         player.addToHand(deck.getNextCard());
//     }














//     //-----------------------------v1---------------------------------

//     public static boolean compare(Person player, Person dealer, Deck deck, JTextArea area) {
//         player.getHandVal();
//         dealer.getHandVal();
//         while(dealer.getHandVal() < 17) {
//             dealer.addToHand(deck.getNextCard());
//         }
//         area.setText(playerSBFinal(dealer, player));

//         if(dealer.getHandVal() > 21 || dealer.getHandVal() == player.getHandVal()) {
//             return true;
//         }

//         return false;
//     }

    

//     public static boolean eval(Person player) {
//         int num_aces = 0;
//         for (Card card : player.getHand()) {
//             if(card.isAce()) {
//                 num_aces++;
//             }
//         }

//         int value = player.getHandVal();

//         while(value > 21) {
//             if(num_aces > 0) {
//                 value -= 10;
//                 num_aces--;
//             }
//             else {
//                 return false;
//             }
//         }
//         if (value <= 21) {
//             return true;
//         }

//         return false;
        

//     }

    

//     public static String start(Person dealer, Person player, Deck deck) {
//         String result = "";

//         player.addToHand(deck.getNextCard());
//         dealer.addToHand(deck.getNextCard());
//         player.addToHand(deck.getNextCard());
//         dealer.addToHand(deck.getNextCard());

        
//         result += playerSB(dealer, player);


//         return result;
//     }


//     public static String playerSBv1(Person dealer, Person player) {
//         String result = "";

//         boolean blackjack = bj(player);
//         boolean valid = eval(player);

//         result += "The cards have been dealt and the dealer is showing a " + dealer.getFaceUp().getCardTitle();
//         result += "\n\n";
//         result += "You have: ";
//         for(Card card : player.getHand()) {
//             result += "\n" + card.getCardTitle();
//         }

//         if(blackjack) {
//             result += "\nYou got Blackjack, you win!";
//         }
//         else if (!valid) {
//             result += "\nOh no! You busted! Better luck next time";
//         }
//         else{
//             result += "\nValue: " + player.getHandVal();
//         }
        

//         return result;
//     }

//     public static String playerSBFinal(Person dealer, Person player) {
//         String result = "";

//         boolean blackjack = bj(player);
//         boolean valid = eval(player);

//         result += "The dealer has:";
//         for(Card card : dealer.getHand()) {
//             result += "\n" + card.getCardTitle();
//         }
//         result += "\n\n";
//         result += "You have: ";
//         for(Card card : player.getHand()) {
//             result += "\n" + card.getCardTitle();
//         }

//         if(blackjack) {
//             result += "\nYou got Blackjack, you win!";
//         }
//         else if (!valid) {
//             result += "\nOh no! You busted! Better luck next time";
//         }
//         else{
//             result += "\nValue: " + player.getHandVal();
//         }
        

//         return result;
//     }
// }
