package collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest {
	public static void main(String[] args) {
		//Among Collection-Frameworks, let's learn about map
		Map<String,String> map = new HashMap<>();
		
		
		map.put("a1", "gana");
		map.put("a2", "hush");
		map.put("a3", "perero");
		
		
		Set set = map.keySet();
		Iterator<String> it =  set.iterator();
		while(it.hasNext()) {
			String key= it.next();
			String value = map.get(key);
			System.out.println("key is " + key + ", value is " + value);
		}
	}
}
