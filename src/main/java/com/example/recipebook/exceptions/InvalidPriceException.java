package com.example.recipebook.exceptions;

public class InvalidPriceException extends Exception{
    public InvalidPriceException() {
        super("Invalid price!");
    }
}
