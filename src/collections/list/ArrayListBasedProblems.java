package collections.list;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class ArrayListBasedProblems {

	public static List<Integer> removeDuplicateUseJava8(List<Integer> list) {
		return list.stream().distinct().collect(Collectors.toList());
	}

	public static List<Integer> removeDuplicateWithoutStreams(List<Integer> list) {
		return new ArrayList<>(new HashSet<>(list));
	}

	public static void main(String[] args) {

		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		
		Queue<Integer> q = new LinkedList<>();

		q.add(10);
		list.add(1);
		System.out.println(list);

		System.out.println(removeDuplicateUseJava8(list));

		System.out.println(list);

		System.out.println(removeDuplicateWithoutStreams(list));

		System.out.println(list);

	}

}
