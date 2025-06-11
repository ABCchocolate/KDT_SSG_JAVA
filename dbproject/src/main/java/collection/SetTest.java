package collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetTest {
	
	public static void main(String[] args) {
		
		//Let's learn about Set , it supported by collection framework.
		Set<String> set = new HashSet<>();
		
		set.add("BMW");
		set.add("Bentz");
		set.add("AUDI");
		set.add("K9");
		
		
		//there is no sequence in set array...but you can know the size of set
		//so if you want to for-sentence to set.. iterator to all set
		Iterator<String> it = set.iterator();
		
		
		
		while(it.hasNext()) {
			//until next source is existed
			String obj = it.next(); //Move to next location from current location
			System.out.println(obj);
		}
	}
}
