package ntnu.gruppe22.game.utils;

import java.util.Timer;
import java.util.TimerTask;

import ntnu.gruppe22.game.scenes.MainGame;
import ntnu.gruppe22.game.states.GameRules;

/**
 * @author aadne on 27.03.2020 09:57
 */

public class MainGameTimer {

    private Timer timer;
    private String displayString;
    private MainGame mainGame;
    private GameRules gameRules;

    public MainGameTimer(MainGame mainGame) {
        this.mainGame = mainGame;
        timer = new Timer();
        gameRules = new GameRules();
        displayString = "";
    }


    /**
     * A runnable for the Round Count Down
     */
    class RoundCountDown extends TimerTask {
        int seconds = gameRules.ROUND_TIME;

        /**
         * this.cancel the runnable so that the thread no longer needs to run.
         * MainGame.bufferTime = true so that characters may not move during the intermediate phase.
         * setDisplayString to update the displayString
         * startBufferCountDown to start the new runnable buffer count down.
         */
        @Override
        public void run() {
            if (seconds <= 0) {
                this.cancel();
                MainGame.bufferTime = true;
                setDisplayString(seconds + " seconds remaining. Time is up!");
                startBufferCountDown();
            } else {
                setDisplayString(seconds + " seconds remaining.");
                seconds -= 1;
            }
        }
    }

    /**
     * A runnable for the Buffer Counter Down.
     */
    class BufferCountDown extends TimerTask {
        int seconds = GameRules.BUFFER_TIME;

        /**
         * this.cancel the runnable so that the thread no longer needs to run.
         * MainGame.buffer = false to indicate that a player has his or her turn and should be able to move.
         * startNewRoundCountDown to start a new runnable with the players count down.
         */
        @Override
        public void run() {
            if (seconds <= 0) {
                this.cancel();
                setNewTurn();
                MainGame.bufferTime = false;
                startNewRoundCountDown();
            } else {
                setDisplayString("Preparing game for next player");
                seconds -= 1;
            }
        }
    }

    /**
     * Begin the count down for the current players turn.
     */
    public void startNewRoundCountDown() {
        timer.schedule(new RoundCountDown(), 0, 1000);
    }

    /**
     * Start the count down for intermediate rounds. Buffer for the game to set up.
     */
    public void startBufferCountDown() {
        timer.schedule(new BufferCountDown(), 0, 1000);
    }

    /**
     * Set the string to display in the MainGame
     * @param message
     */
    private void setDisplayString(String message) {
        this.displayString = message;
    }

    /**
     * Usually the time remaining of the players turn
     * Also displaying if the game is loading between turns.
     * @return displayString
     */
    public String getDisplayString() {
        return this.displayString;
    }

    /**
     * Make the actual changes in MainGame to prepare for the next player.
     */
    public void setNewTurn() {
        mainGame.timesUp();
    }


    /**
     * Method to cancel the timer.
     * Save resources by using this if there is no longer need for the timer.
     */
    public void cancel() {
        this.cancel();
    }
}
