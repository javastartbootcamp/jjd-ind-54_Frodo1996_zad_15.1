package pl.javastart.task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TournamentStats {
    private static final String FILE_NAME = "stats.csv";
    List<Player> playerList = new ArrayList<>();

    void run(Scanner scanner) {
        try {
            mainLoop(scanner);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Podałeś błędne wartości spróbuj jeszcze raz ponownie uruchamiając program.");
        }
    }

    private void mainLoop(Scanner scanner) {
        boolean stop = false;
        do {
            System.out.println("Podaj wynik kolejnego gracza (lub STOP):");
            String result = scanner.nextLine();
            if (result.compareTo("STOP") == 0 || result.compareTo("stop") == 0) {
                Comparator<Player> comparator = chooseParameterToSortWith(scanner);
                playerList.sort(comparator);
                chooseSortOrderAndSaveListInFile(scanner, playerList);
                scanner.close();
                stop = true;
            } else {
                addPlayer(playerList, result);
            }
        } while (!stop);
    }

    private void addPlayer(Collection<Player> playerList, String result) {
        String[] split = result.split(" ");
        String firstName = split[0];
        String lastName = split[1];
        int playerResult = Integer.parseInt(split[2]);
        playerList.add(new Player(firstName, lastName, playerResult));
    }

    private void chooseSortOrderAndSaveListInFile(Scanner scanner, List<Player> playerList) {
        System.out.println("Sortować rosnąco czy malejąco? (1 - rosnąco, 2 - malejąco)");
        int sortOrder = scanner.nextInt();
        switch (sortOrder) {
            case 1 -> saveValuesInFile(playerList);
            case 2 -> saveReversedValuesInFile(playerList);
            default -> System.out.println("Podałeś błędny parametr sortowania.");
        }
        System.out.println("Dane posortowano i zapisano do pliku " + FILE_NAME + ".");
    }

    private Comparator<Player> chooseParameterToSortWith(Scanner scanner) {
        Comparator<Player> comparator = null;
        System.out.println("Po jakim parametrze posortować? (1 - imię, 2 - nazwisko, 3 - wynik)");
        int sortOrder = scanner.nextInt();
        switch (sortOrder) {
            case 1 -> comparator = new NameComparator();
            case 2 -> comparator = new LastNameComparator();
            case 3 -> comparator = new ResultComparator();
            default -> {
                System.out.println("Podałeś błędny parametr sortowania.");
                System.exit(0);
            }
        }
        return comparator;
    }

    private void saveValuesInFile(Collection<Player> playerList) {
        if (playerList.isEmpty()) {
            System.out.println("Brak graczy do dodania.");
        } else {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
                for (Player player : playerList) {
                    writer.write(player.toString());
                    writer.newLine();
                }
                writer.close();
            } catch (IOException e) {
                System.err.println("Nie udało się zapisać pliku.");
            }
        }
    }

    private void saveReversedValuesInFile(List<Player> playerList) {
        Collections.reverse(playerList);
        saveValuesInFile(playerList);
    }
}