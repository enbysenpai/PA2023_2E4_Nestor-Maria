package org.example;

public class Token
{
    private int number;

    public Token(int number)
    {
        this.number=number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString()
    {
        return String.valueOf(number);
    }
}
