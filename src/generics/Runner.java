package generics;

public class Runner {

	public static void main(String[] args) {

		Pair<Integer, String> idToName = new Pair<>(1, "Gangadhar");
		Pair<Integer, Integer> idToAge = new Pair<>(1, 30);

		System.out.println(idToName.getLeft() + " " + idToName.getRight());
		System.out.println(idToAge.getLeft() + " " + idToAge.getRight());
	}
}
