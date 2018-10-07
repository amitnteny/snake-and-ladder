package game;

import domain.Cell;
import domain.PitStop;
import domain.PitStopType;
import user.User;

import java.util.Scanner;

public class SnakeAndLadder {
    public static void main(String[] args) {

        Cell[] gameBoard = new Cell[101];
        initializeGameboardWithPitStops(gameBoard);

        System.out.println("Board prepared for both users.");

        Thread user1 = new Thread(new User(gameBoard), "user1");
        Thread user2 = new Thread(new User(gameBoard), "user2");

        System.out.println("Players ready.");
        System.out.println("Game starts.");
        System.out.printf("%s\t%s\t%s\t%s\n", "USER", "DICE NO.", "PREVIOUS STATE", "CURRENT STATE");
        System.out.println("-------------------------------------------------");
        user1.start();
        user2.start();
        try {
            user1.join();
            user2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Game Over...");
    }

    private static void initializeGameboardWithPitStops(Cell[] gameBoard) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i <= 100; i++) {
            gameBoard[i] = new Cell(i, new PitStop(PitStopType.NONE, i));
        }
        for (int i = 0; i < n; i++) {
            int snakeTail = scanner.nextInt();
            int snakeHead = scanner.nextInt();
            int ladderBottom = scanner.nextInt();
            int ladderTop = scanner.nextInt();
            gameBoard[snakeTail].setPitStop(new PitStop(PitStopType.SNAKE_TAIL, snakeHead));
            gameBoard[snakeHead].setPitStop(new PitStop(PitStopType.SNAKE_HEAD, snakeTail));
            gameBoard[ladderBottom].setPitStop(new PitStop(PitStopType.LADDER_BOTTOM, ladderTop));
            gameBoard[ladderTop].setPitStop(new PitStop(PitStopType.LADDER_TOP, ladderBottom));
        }
    }
}
