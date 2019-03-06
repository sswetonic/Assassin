public class Assassin {
    private String name;
    private int skill;

    public Assassin() {
        this(null, 0);
    }

    public Assassin(String name, int skill) {
        this.name = name;
        this.skill = skill;
    }

    public String getName() {
        return name;
    }

    public int getSkill() {
        return skill;
    }

    @Override
    public String toString() {
        return name + " (" + skill + ")";
    }
}
