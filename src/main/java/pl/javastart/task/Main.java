package pl.javastart.task;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TournamentStats tournamentStats = new TournamentStats();
        Scanner scanner = new Scanner(System.in);
        tournamentStats.run(scanner);
    }

}