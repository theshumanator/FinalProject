package com.example.javajoketeller.jokes;

import java.util.ArrayList;

/**
 * Created by fatoumeh on 18/06/2018.
 */

public class Jokes {

    /*
        Jokes were taken from:
         http://www.tomsitpro.com/articles/best-it-pro-jokes,1-3346.html
         https://www.rd.com/funny-stuff/short-jokes/
     */
    private static String[] jokesList= new String[]{
            "What’s the best thing about Switzerland? I don’t know, but the flag is a big plus.",
            "Did you hear about the claustrophobic astronaut? He just needed a little space.",
            "Why don’t scientists trust atoms? Because they make up everything. " +
                    "None, that's a hardware problem.",
            "How many programmers does it take to change a light bulb? None, it's a hardware problem",
            "How do you drown a hipster? Throw him in the mainstream.",
            "A password cracker walks into a bar. Orders a beer. Then a Beer. Then a BEER. beer." +
                    " b33r. BeeR. Be3r. bEeR. bE3R. BeEr",
            "The best thing about IPv4 jokes is that you can tell them 254 times before " +
                    "they’re exhausted.",
            "Multicast jokes are awesome. But you'll get them only if you bother to listen.",
            "I ran out of new IPv4 jokes. I could tell you an IPv6 one but I'm afraid, " +
                    "you might not understand it.",
            "Why do Python Devs need glasses? Because they don't C#." };

    public static String getJoke(int pos) {
        return jokesList[pos];
    }
}
