import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AssassinManager {
    private LinkedList<Assassin> killRing;
    private static final List<String> weapons = Arrays.asList("Gun", "Knife", "Shotgun", "Sword", "Baseball bat", "Car");
    private static final List<String> locations = Arrays.asList("Library", "School", "House", "Train", "Gym");
    private List<List<String>> killPairs;

    private AssassinManager(String filePath) throws FileNotFoundException {
        killRing = new LinkedList<>();
        killPairs = new ArrayList<>();
        Scanner sc = new Scanner(new File(filePath));
        while (sc.hasNextLine()) {
            Scanner lineScan = new Scanner(sc.nextLine());
            String name = lineScan.next();
            name = name.substring(0, name.indexOf(','));
            int skill = lineScan.nextInt();
            Assassin player = new Assassin(name, skill);
            killRing.add(new Node<>(player));

        }
    }

    //For sake of simplicity, I printed out who kills who in this function, because play() only gets which assassin to delete
    private Node<Assassin> getNextVictim() {
        Node<Assassin> first = killRing.first;
        Node<Assassin> second = first.next;
        String weapon = weapons.get(new Random().nextInt(weapons.size()));
        String location = locations.get(new Random().nextInt(locations.size()));
        while (second.next != null) {
            if (first.data.getSkill() > second.data.getSkill()) {
                //first kills second
                killPairs.add(Arrays.asList(first.data.getName(), second.data.getName()));
                System.out.println(first.data.getName() + " kills " + second.data.getName() + " with a " + weapon + " in the " + location + "!");
                return second;
            } else if(first.data.getSkill() == second.data.getSkill()) {
                //second kills first
                killPairs.add(Arrays.asList(second.data.getName(), first.data.getName()));
                System.out.println(second.data.getName() + " kills " + first.data.getName() + " with a " + weapon + " in the " + location +  "!");
                return first;
            } else {
                first = second;
                second = second.next;
            }
        }

        //Hard to read, but this handles cases where the highest skill player is last in the ring
        //and for when there are only two players left
        if (killRing.first.data.getSkill() > second.data.getSkill()) {
            //kill second
            killPairs.add(Arrays.asList(killRing.first.data.getName(), second.data.getName()));
            System.out.println(killRing.first.data.getName() + " kills " + second.data.getName() + " with a " + weapon + " in the " + location + "!");
            return second;
        } else if (killRing.first.data.getSkill() == second.data.getSkill()) {
            //kill first
            killPairs.add(Arrays.asList(second.data.getName(), killRing.first.data.getName()));
            System.out.println(second.data.getName() + " kills " + killRing.first.data.getName() + " with a " + weapon + " in the " + location + "!");
            return killRing.first;
        } else {
            killPairs.add(Arrays.asList(second.data.getName(), killRing.first.data.getName()));
            System.out.println(second.data.getName() + " kills " + killRing.first.data.getName() + " with a " + weapon + " in the " + location + "!");
            return killRing.first;
        }
    }

    private void play() {
        while (killRing.size() > 1) {
            Node<Assassin> playerKilled = getNextVictim();
            killRing.remove(playerKilled);

        }
        System.out.println(killRing.first.data.getName() + " is the winner!");
    }

    private void deathRecap() {
        System.out.print("Death recap: ");
        for (int i = 0; i < killPairs.size()-1; i++) {
            System.out.print(killPairs.get(i).get(0) + " killed " + killPairs.get(i).get(1) + ", ");
        }
        System.out.println(killPairs.get(killPairs.size()-1).get(0) + " killed " + killPairs.get(killPairs.size()-1).get(1));
    }

    @Override
    public String toString() {
        return killRing.toString();
    }

    public static void main(String[] args) throws FileNotFoundException{
        AssassinManager test = new AssassinManager("src/playerFile.txt");
        System.out.println("Kill Ring: " + test);
        test.play();
        test.deathRecap();
    }
}
