package user;

import domain.Cell;

import java.util.Random;

public class User implements Runnable {
    private final Cell[] gameBoard;

    private int currentPosition = 0;

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public User(Cell[] gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void run() {
        while (getCurrentPosition() <= 100) {
            synchronized (gameBoard) {
                play();
            }
            if (getCurrentPosition() == 100) {
                System.out.println(Thread.currentThread().getName() + " wins. Game Over. Ignore scores.");
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void play() {
        int previousState = getCurrentPosition();
        int diceNumber = rollADice();
        int newPosition = getCurrentPosition() + diceNumber;
        if (newPosition <= 100) {
            moveToNewPositionAndPrintScore(previousState, diceNumber);
        } else {
            System.out.println(Thread.currentThread().getName() + " rolled more than 100. Skipping chance.");
        }
    }

    public void moveToNewPositionAndPrintScore(int previousState, int diceNumber) {
        this.currentPosition += diceNumber;
        this.setCurrentPosition(gameBoard[getCurrentPosition()].getPitStop().getPitFallPosition());
        System.out.printf("%s\t%d\t\t\t%d\t\t\t\t%d\n", Thread.currentThread().getName(), diceNumber, previousState, currentPosition);
    }

    public int rollADice() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

}
