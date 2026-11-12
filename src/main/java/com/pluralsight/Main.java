package com.pluralsight;

import java.util.ArrayList;
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

//        Hand player1 = new Hand("T");
//        Hand player2 = new Hand("A");
//        hands.add(player1);
//        hands.add(player2);

        Deck deck = new Deck();
        deck.shuffle();

        for (Hand hand : hands) {
            hand.deal(deck.deal());
            hand.deal(deck.deal());
            hand.print();
            System.out.println("Value: " + hand.getValue());
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
