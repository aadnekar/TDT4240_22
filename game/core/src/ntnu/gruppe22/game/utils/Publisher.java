package ntnu.gruppe22.game.utils;

import androidx.collection.ArraySet;

import java.util.Set;

public class Publisher {

    private Set<Subscriber> subscribers;

    public Publisher() {
        subscribers = new ArraySet<>();
    }


    /**
     * Adds a new subscriber to the subscribers list if it is not already subscribed.
     * @param s the subscriber to be added to the subscribers list.
     */
    public void subscribe(Subscriber s) {
        subscribers.add(s);
    }


    /**
     * Removes a subscriber if it is in the subscribers list.
     * @param s the subscriber to be removed from the subscribers list.
     */
    public void unsubscribe(Subscriber s) {
        subscribers.remove(s);
    }


    /**
     * Notify all subscribers in the subscribers list.
     * @param username of the winner.
     * @param score of the winner.
     */
    public void notifySubscribers(String username, int score) {
        for (Subscriber s: subscribers) {
            s.update(username, score);
        }
        System.out.println("All subscribers are notified");
    }

    /**
     * Specific publishing case when a game has ended.
     * @param username of the winner of the game.
     * @param score of the winner of the game.
     */
    public void notifyGameover(String username, int score) {
        notifySubscribers(username, score);
    }
}
