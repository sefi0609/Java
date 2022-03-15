package war;
import java.util.ArrayList;
import java.security.SecureRandom;
/**
 *class for Deck object
 * @author Yosefi Kroytoro ID: 204713119
 */
public class Deck {
    private ArrayList<Card> deck = new ArrayList<Card>();
    private static final int NUM_OF_CARDS = 52;
    private static final SecureRandom randomNumbers = new SecureRandom();
//    create a deck with values of cards
    public Deck(){
        String [] faces = {"Ace","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack", "Queen", "King"};
        String [] suits = {"Hearts", "Diamonds", "Spades", "Clubs"};
        for (int count = 0;count < NUM_OF_CARDS;count++)
            deck.add(new Card (faces[count%13],suits[count/13]));
    }
//    clear a deck
    public void cleardeck() {
        deck.clear();
    }
//    shuffle a deck
    public void shuffle() {
        // next call to method dealCard should start at deck[0] again
        // for each Card, pick another random Card (0-51) and swap them
        for(int first = 0; first < deck.size(); first++) {
            // select a random number between 0 and 51
            int second = randomNumbers.nextInt(NUM_OF_CARDS);
            // swap current Card with randomly selected Card
            Card temp = deck.get(first);
            deck.set(first,deck.get(second));
            deck.set(second,temp);
        }
    }
//    deal a card, take it out of the deck
    public Card deal(){
        if (!deck.isEmpty()){
            Card temp = deck.get(0);
            deck.remove(0);
            return temp;
        }
        else
            return null;
    }
//    check if the deck is empty
    public boolean isEmpty(){
        return deck.isEmpty();
    }
//    add a card to the deck
    public void addCard(Card c){
        deck.add(c);
    }
//  return the number of cards in the deck
    public int numOfCards(){
        return deck.size();
    }
}
