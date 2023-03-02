package pl.javastart.task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class TournamentStats {
    private static final String FILE_NAME = "stats.csv";

    void run(Scanner scanner) {
        try {
            mainLoop(scanner);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Podałeś błędne wartości spróbuj jeszcze raz ponownie uruchamiając program.");
        }
    }

    private void mainLoop(Scanner scanner) {
        boolean stop = false;
        List<Player> playerList = readDataFromUser(scanner, stop);
        Comparator<Player> comparator = createComparatorWithOrder(scanner);
        playerList.sort(comparator);
        scanner.close();
        saveValuesInFile(playerList);
    }

    private List<Player> readDataFromUser(Scanner scanner, boolean stop) {
        final List<Player> playerList = new ArrayList<>();
        do {
            System.out.println("Podaj wynik kolejnego gracza (lub STOP):");
            String result = scanner.nextLine();
            if ("stop".equalsIgnoreCase(result)) {
                stop = true;
            } else {
                addPlayer(playerList, result);
            }
        } while (!stop);
        return playerList;
    }

    private void addPlayer(Collection<Player> playerList, String result) {
        String[] split = result.split(" ");
        String firstName = split[0];
        String lastName = split[1];
        int playerResult = Integer.parseInt(split[2]);
        playerList.add(new Player(firstName, lastName, playerResult));
    }

    private Comparator<Player> createComparatorWithOrder(Scanner scanner) {
        System.out.println("Po jakim parametrze posortować? (1 - imię, 2 - nazwisko, 3 - wynik)");
        int sortPerimeter = scanner.nextInt();
        Comparator<Player> comparator = switch (sortPerimeter) {
            case 1 -> comparator = new NameComparator();
            case 2 -> comparator = new LastNameComparator();
            case 3 -> comparator = new ResultComparator();
            default -> throw new IllegalArgumentException("Błędna opcja");
        };
        System.out.println("Sortować rosnąco czy malejąco? (1 - rosnąco, 2 - malejąco)");
        int sortOrder = scanner.nextInt();
        if (sortOrder == 2) {
            comparator = comparator.reversed();
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
                System.out.println("Dane posortowano i zapisano do pliku " + FILE_NAME + ".");
            } catch (IOException e) {
                System.err.println("Nie udało się zapisać pliku.");
            }
        }
    }
}