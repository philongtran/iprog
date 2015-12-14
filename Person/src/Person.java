import java.util.Arrays;
import java.util.Calendar;

/**
 * This class represents a person, which contains various informations
 * 
 * @author Manuel Wessner <191711>
 * @author Phi Long Tran <191624>
 * @author Steve Nono <191709>
 */
public class Person implements Cloneable, Comparable<Person>, SimpleTreeNode {

	private static final float TALL_BODYHEIGHT = 175;
	private static final float SMALL_BODYHEIGHT = 153;
	private final String name;
	private final String job;
	private final String favoriteColor;
	private final String favoriteAnimal;
	private final int birthyear;
	private final float bodyHeight;
	private final String bodyHeightDescription;

	private int CAPACITY;
	private String nameTree;
	private SimpleTreeNode[] childs;
	private int childcnt;

	/**
	 * main method which creates the objects of the interactiveIO class and the
	 * person classes. Afterwards it prints the the person to the console using
	 * {@link #toString()} and does a simple compare of these two persons
	 */
	public static void main(String[] args) throws Exception {
		Person person1 = new Person();
		Person person2 = new Person();
		Person[] personsArray = new Person[] { person1, person2 };
		for (Person person : personsArray) {
			IO.writeln(person.toString());
		}
		Arrays.sort(personsArray);
		IO.writeln("Nach sortieren: ");
		for (Person person : personsArray) {
			IO.writeln(person.toString());
		}
		Person clonedPerson1 = (Person) person1.clone();
		IO.writeln("Klon Person 1: " + clonedPerson1);
		person1.compare(person2);
		IO.writeln("" + person1.compareTo(person2));

	}

	public Person() throws Exception {
		this.name = IO.promptAndRead("Name:");
		this.job = IO.promptAndRead("Beruf:");
		this.favoriteColor = IO.promptAndRead("Farbe:");
		this.favoriteAnimal = IO.promptAndRead("Tier:");
		this.birthyear = IO.readInt("Geburstsjahr: ");
		this.bodyHeight = IO.readFloat("Körpergrösse: ");
		this.bodyHeightDescription = analyzeBodyHeight();

		this.CAPACITY = 1;
		this.nameTree = "testTree";
		this.childs = new SimpleTreeNode[CAPACITY];
		this.childcnt = 0;
	}

	/**
	 * returns the string representation of the Person object
	 */
	@Override
	public String toString() {
		String personAsString = "Name: " + name + " Beruf: " + job + " Farbe: "
				+ favoriteColor + " Tier: " + favoriteAnimal + " Alter: "
				+ getAge() + " Körpergrösse: " + bodyHeight + ", "
				+ bodyHeightDescription + ", Treename: " + nameTree;
		return personAsString;
	}

	private int getAge() {
		return Calendar.getInstance().get(Calendar.YEAR) - birthyear;
	}

	private String analyzeBodyHeight() {
		if (bodyHeight > TALL_BODYHEIGHT) {
			return "gross";
		} else if (bodyHeight < SMALL_BODYHEIGHT) {
			return "klein";
		} else {
			return "mittel";
		}
	}

	/**
	 * Compares two persons with each other - Currently supported: age +
	 * bodyHeight
	 */
	private void compare(Person otherPerson) {
		int ageDifference = Math.abs(getAge() - otherPerson.getAge());
		float bodyHeightDifference = Math.abs(this.bodyHeight
				- otherPerson.bodyHeight);
		IO.writeln("Die Personen haben einen Altersunterschied von "
				+ ageDifference + " Jahr(en) und einen Grössenunterschied von "
				+ bodyHeightDifference + "cm");
	}

	@Override
	public void addChild(SimpleTreeNode child) {
		if (childcnt >= CAPACITY) {
			CAPACITY *= 2; // Kapazitaets-Verdopplung
			SimpleTreeNode[] newchilds = new SimpleTreeNode[CAPACITY];
			for (int i = 0; i < childcnt; i++) {
				newchilds[i] = childs[i]; // alte Kinder eintragen
			}
			childs = newchilds;
		}
		childs[childcnt++] = child; // neues Kind einfuegen
	}

	@Override
	public int getChildCnt() {
		return childcnt;
	}

	@Override
	public SimpleTreeNode getChild(int pos) {
		return childs[pos];
	}

	@Override
	public int compareTo(Person arg0) {
		int ageDifference = Math.abs(getAge() - arg0.getAge());
		return ageDifference;
	}

}
