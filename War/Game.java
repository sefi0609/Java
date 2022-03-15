package war;

import java.util.ArrayList;

/**
 *class for game logic
 * @author Yosefi Kroytoro ID: 204713119
 */
public class Game {
    private Deck gameDeck,p1,p2;
//    class contractor 
    public Game(){
    gameDeck = new Deck();
    p1 = new Deck();
    p2 = new Deck();
    p1.cleardeck();
    p2.cleardeck();
    }
    
    public void start(){
//        initalize Game
        ArrayList<Card> tempDeck = new ArrayList<Card>();
        gameDeck.shuffle();
        for (int currCard = 0;currCard < 26;currCard++){
            p1.addCard(gameDeck.deal());
            p2.addCard(gameDeck.deal());
        }

//        let's play
        while(!p1.isEmpty() && !p2.isEmpty()){
            tempDeck.clear();
            tempDeck.add(p1.deal());
            tempDeck.add(p2.deal());
            System.out.println("Player 1 card is: " + tempDeck.get(0));
//            show the number of cards for player 1 including the one on the table
            System.out.println("Player 1 number of cards: " + (p1.numOfCards()+1));
            System.out.println("Player 2 card is: " + tempDeck.get(1));
//            show the number of cards for player 2 including the one on the table
            System.out.println("Player 2 number of cards: " + (p2.numOfCards()+1));
            if (tempDeck.get(0).cardValue() > tempDeck.get(1).cardValue()){
                p1.addCard(tempDeck.get(0));
                p1.addCard(tempDeck.get(1));
            }
            else if (tempDeck.get(0).cardValue() < tempDeck.get(1).cardValue()){
                p2.addCard(tempDeck.get(0));
                p2.addCard(tempDeck.get(1));
            }
//            War!!!
            else{
                if ((p1.numOfCards() < 3) || (p2.numOfCards() < 3))
                    break;
                System.out.println("War!!!");
                while((p1.numOfCards() >= 3) && (p2.numOfCards() >= 3)){
                    for (int i = 0;i < 3;i++){
                        tempDeck.add(p1.deal());
                        System.out.println("Player 1 card is: " + tempDeck.get(tempDeck.size()-2));
                        tempDeck.add(p2.deal());
                        System.out.println("Player 2 card is: " + tempDeck.get(tempDeck.size()-1));
                    }
                    int ref = tempDeck.get(tempDeck.size()-2).cardValue() - tempDeck.get(tempDeck.size()-1).cardValue();
                    if (ref > 0){
                        for (int i = 0;i < tempDeck.size();i++){
                            p1.addCard(tempDeck.get(i));
                        }
                        break;
                    }
                    else if (ref < 0){
                        for (int i = 0;i < tempDeck.size();i++){
                            p2.addCard(tempDeck.get(i));
                        }
                        break;
                    }
                }    
                System.out.println("End of War");
            }
        }
//        print the winner
        if (p1.numOfCards() < 3)
            System.out.println("Player 2 won!");
        if (p2.numOfCards() < 3)
            System.out.println("Player 1 won!");
    }
}
