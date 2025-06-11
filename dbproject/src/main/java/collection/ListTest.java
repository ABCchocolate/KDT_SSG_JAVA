package collection;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
	public static void main(String[] args) {
		/*
		 * Java's collection framework is supported by the java.util package..
			We learn about a representative List for processing ordered sets among them.
		
		 * List  vs Array
		 * Common points - have order and are accessible by index
		 * Differences - Array must specify the size when created and can also contain basic data types
			However, for the collection, only objects can be contained.
			
			Since the collection framework mainly uses the method of the top-level interfaces, 
			the method use is consistent no matter what implementation object below is used.
			When it is contained, it is almost added and the length is almost size().
		 */
		//If <data type> is specified, other data types are rejected. That is, the data type is checked at the compilation time.
		// It calls "generic type"
		List<String> list = new ArrayList(); //an arrangement of rubber bands
		list.add("apple");
		list.add("banana");
		list.add("grape");
		list.add("orange");
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		//improved for sentence
		//when Taking some data... using this improved for sentence
		
		for(String obj : list) {
			System.out.println(obj);
		}
	}
}
