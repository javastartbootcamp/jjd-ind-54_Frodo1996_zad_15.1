package pl.javastart.task;

import java.util.Comparator;

public class LastNameComparator implements Comparator<Player> {
    @Override
    public int compare(Player player1, Player player2) {
        return player1.getLastName().compareTo(player2.getLastName());
    }
}