package com.example.vince.app5;

public class textAndImage {
    private int image;
    private String text;
    private char letter = ' ';


    public textAndImage(int image, String text) {
        this.image = image;
        this.text = text;
    }

    public textAndImage(int image, String text, char letter) {
        this.image = image;
        this.text = text;
        this.letter = letter;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }
}
