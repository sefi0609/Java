package com.example.dictionary;

import java.util.Set;

//class to pass data between the two scenes (newPopup,updatePopup)
//the constructor is private - so we create only one instance of this class
public final class KeyHolder {
    //the new key from layout2
    private Key key;//the key and the values
    //creating only one instance of this class
    private final static KeyHolder INSTANCE = new KeyHolder();
    //empty private constructor
    private KeyHolder() {}
    //return the class instance
    public static KeyHolder getInstance() {return INSTANCE;}
    //set the new key
    public void setKey(Key k) {this.key = k;}
    //return the new key
    public Key getKey() {return this.key;}
}
