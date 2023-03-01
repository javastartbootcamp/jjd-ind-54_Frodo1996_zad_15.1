package pl.javastart.task;

public class Player {
    private final String firstName;
    private final String lastName;
    private final int result;

    public Player(String firstName, String lastName, int result) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.result = result;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getResult() {
        return result;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ";" + result;
    }
}