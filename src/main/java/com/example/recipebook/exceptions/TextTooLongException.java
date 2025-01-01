package com.example.recipebook.exceptions;

public class TextTooLongException extends Exception{
    public TextTooLongException() {
        super("Text must be between 3 and 2048 symbols long!");
    }
}
