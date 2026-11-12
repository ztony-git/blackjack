package com.pluralsight;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Hand> hands = new ArrayList<>();

        System.out.print("How many hands? ");
        int handAmount = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= handAmount; i++) {
            System.out.print("Player " + i + " name: ");
            Hand player = new Hand(scanner.nextLine());
            hands.add(player);
        }

        Deck deck = new Deck();
        deck.shuffle();

        // main game
        for (Hand hand : hands) {
            hand.deal(deck.deal());
            hand.deal(deck.deal());
            hand.print();
            System.out.println("Value: " + hand.getValue());

            String option = "";

            do {
                if (hand.getValue() == 21) {
                    System.out.println("Blackjack!");
                    break;
                }

                System.out.println(hand.getName() + " Hit or Stay: ");
                option = scanner.nextLine();

                switch (option.toLowerCase()) {
                    case "hit" -> {
                        hand.deal(deck.deal());

                        hand.print();
                        System.out.println("Value: " + hand.getValue());

                        if(hand.getValue() > 21) {
                            System.out.println("Bust!");
                            option = "Stay";
                        }

                    }
                }

            } while (!option.equalsIgnoreCase("stay"));
            System.out.println("--------------------------------");
        }

        int closest = 100;
        ArrayList<Integer> winnersIndex = new ArrayList<>();

        for (int i = 0; i < hands.size(); i++) {
            int distance = hands.get(i).getDistanceFrom21();
            System.out.println(distance);
            if (distance < 0) {
                continue;
            }
            if (distance < closest) {
                closest = distance;
                winnersIndex.clear();
                winnersIndex.add(i);
                continue;
            }
            if (distance == closest) {
                winnersIndex.add(i);
            }
        }

        System.out.println(winnersIndex.size() == 1 ? "Winner: " : "Winners: ");
        for (Integer index : winnersIndex) {
            System.out.print(hands.get(index).getName() + " ");
        }
    }
}
