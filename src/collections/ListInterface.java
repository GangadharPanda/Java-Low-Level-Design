package collections;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListInterface {

	public static void main(String[] args) {

		List<Integer> list = new ArrayList<>(3);
		
		/*
		 * 
		 * 1. void	add(int index, E element)
		 * 2. boolean addAll(int index, Collection<? extends E> c)
		 * 3. E	get(int index)
		 * 4. int	indexOf(Object o)
		 * 5. int	lastIndexOf(Object o)
		 * 
		 * 6. ListIterator<E>	listIterator()
		 * 
		 * 7. ListIterator<E>	listIterator(int startIndex)
		 * 
		 * 8. E	remove(int index)
		 * 9. E	set(int index, E element)
		 * 10. List<E>	subList(int fromIndex, int toIndex) [fromIndex, toIndex)
		 *  
		 * 11. default void	replaceAll(UnaryOperator<E> operator)
		 * 12. default void	sort(Comparator<? super E> c)
		 */
		
		list.add(1);
		list.add(20);
		list.add(30);
		list.add(40);

		list.add(4, 50);// index must be >=0 && <= Size
		list.set(4, 1);// index must be >=0 && <= Size
		
		System.out.println(list);

		System.out.println(list.get(0));// 1

		System.out.println(list.indexOf(1));// 0

		System.out.println(list.lastIndexOf(1));// 4

		ListIterator<Integer> listIterator = list.listIterator();

		while (listIterator.hasNext()) {
			Integer value = listIterator.next();
			System.out.print(value + " ");// [1, 20, 30, 40, 1]
		}
		System.out.println();
		listIterator = list.listIterator(1);
		while (listIterator.hasNext()) {
			Integer value = listIterator.next();
			System.out.print(value + " ");// [20, 30, 40, 1]
		}

		System.out.println();

		System.out.println("Removed " + list.remove(1));// This will remove Object at index 1, Removed 20

		System.out.println(list);// [1, 30, 40, 1]

		System.out.println(list.remove(Integer.valueOf(1)));// When we pass an Wrapper object then only it will remove
															// based on value

		System.out.println(list);// [30, 40, 1], NOTE : Only first occurrence will be removed
	}

}
