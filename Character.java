public class Character {
    private String name;
    private String house;
    private boolean wizard;

    public Character(String name, String house, boolean wizard) {
        this.name = name;
        this.house = house;
        this.wizard = wizard;
    }

    public String name() {
        return name;
    }

    public String house() {
        return house;
    }

    public boolean wizard() {
        return wizard;
    }
}