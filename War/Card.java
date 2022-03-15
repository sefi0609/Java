package war;

/**
 * class for Card object
 * @author Yosefi Kroytoro ID: 204713119
 */
public class Card {
    private final String _face;
    private final String _suit;

//    create an object caed
    public Card(String face, String suit){
        _face = face;
        _suit = suit;
    }
//    return value of card
     public int cardValue(){
        String [] faces = {"Ace","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack", "Queen", "King"};
        for (int i = 0;i < 13;i++)
            if (_face == faces[i])
                return i+1;
        return 0;
    }
//    return a string presentation of a card
    public String toString(){
        return _face + " of " + _suit;
    }
}