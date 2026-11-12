package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        System.out.print("Player 1 name: ");
//        Hand player1 = new Hand(scanner.nextLine());
//
//        System.out.print("Player 2 name: ");
//        Hand player2 = new Hand(scanner.nextLine());
        ArrayList<Hand> hands = new ArrayList<>();

        Hand player1 = new Hand("T");
        Hand player2 = new Hand("A");
        hands.add(player1);
        hands.add(player2);

        Deck deck = new Deck();
        deck.shuffle();

        player1.deal(deck.deal());
        player2.deal(deck.deal());
        player1.deal(deck.deal());
        player2.deal(deck.deal());

        player1.print();
        System.out.println("Value: " + player1.getValue());

        player2.print();
        System.out.println("Value: " + player2.getValue());

        int[] values = new int[2];

        values[0] = player1.getValue();
        values[1] = player2.getValue();


        int closest = 100;
        ArrayList<Integer> winnersIndex = new ArrayList<>();

        for(int i = 0; i < hands.size(); i++) {
            int distance = hands.get(i).getDistanceFrom21();
            System.out.println(distance);
            if (distance < 0) {
                continue;
            }
            if (distance < closest){
                closest = distance;
                winnersIndex.clear();
                winnersIndex.add(i);
                continue;
            }
            if (distance == closest){
                winnersIndex.add(i);
            }
        }

        System.out.println(winnersIndex.size() == 1 ? "Winner: " : "Winners: ");
        for (Integer index : winnersIndex) {
            System.out.print(hands.get(index).getName() + " ");
        }
    }

    public static int findClosest(int[] arr) {
        int closest = arr[0];
        int minDistance = Math.abs(arr[0] - 21);

        for (int i = 1; i < arr.length; i++) {
            int currentDistance = Math.abs(arr[i] - 21);
            if (currentDistance < minDistance && currentDistance >= 0) {
                minDistance = currentDistance;
                closest = arr[i];
            }
        }
        return closest;
    }
}
