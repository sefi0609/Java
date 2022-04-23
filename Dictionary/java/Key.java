package com.example.dictionary;
//class to support the KeyHolder's class
public class Key {
    private String key;
    private String value;
    //class constructor
    public Key(String key, String value) {
        this.key = key;
        this.value = value;
    }
    //returns the key
    public String getKey() {
        return key;
    }
    //set the key
    public void setKey(String key) {
        this.key = key;
    }
    //returns the value
    public String getValue() {
        return value;
    }
    //set the value
    public void setValue(String value) {
        this.value = value;
    }
}
