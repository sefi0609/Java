package com.example.dictionary;
import java.util.*;

public class myDictionary {
    //the structure of the dictionary is a hashtable
    //the dictionary is not sorted, but in the GUI the user see a sorted list of keys
    private Hashtable <String, String> d;
    //constructor
    public myDictionary(){
        d = new Hashtable<String, String>();
    }
    //adds a word to the dictionary and sort the dictionary
    public void insert(String key, String value){d.put(key, value);}
    //returns all the keys in the dictionary
    public Set<String> getKeys(){return d.keySet();}
    //removes a word from the dictionary
    //returns true if the word is removed, false if the word is not in the dictionary
    public boolean delete(String key){
        for(String s : d.keySet())
            if (s.equals(key)) {
                d.remove(key);
                return true;
            }
        d.remove(key);
        return false;
    }
    //update value of a key in the dictionary
    public void update(String key, String value){
        for(String s : d.keySet()) {
            if (s.equals(key))
                d.put(key, value);
        }
    }
    //search for a word in the dictionary
    public String search(String key){return d.get(key);}
    //return all the words that start with str
    public String[] startWith(String str){
        String[] temp = new String[d.size()];
        int index = 0;
        for(String s : d.keySet()) {
            if (s.startsWith(str)) {
                temp[index] = s;
                index++;
            }
        }
        String[] result = new String[index];
        for(int i = 0; i < index; i++)
            result[i] = temp[i];
        return result;
    }
    //returns a string representation of a single word
    public String toString(String str){
        String s = "";
        for(String key: d.keySet()){
            if(key.equals(str))
                s += key + " : " + d.get(key) + "\n";
        }
        return s;
    }
    // save the file to the dictionary
    public void save(String str){
        String[] s = str.split("\"");
        String[] word = new String[2];
        for(int i = 0; i < s.length; i++) {
            if (!(s[i].matches("[a-zA-Z0-9]+") || s[i].contains(".")))
                continue;
            if (word[0] == null)
                word[0] = s[i];
            else if (word[1] == null)
                word[1] = s[i];
            if (word[0] != null && word[1] != null) {
                insert(word[0], word[1]);
                word = new String[2];
            }
        }
    }
}
