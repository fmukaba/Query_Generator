
/*					Author : Francois Mukaba
 * 					Instructor : Fatma Serce
 * 					Course : Algorithms / CS 401
 * 					Spring 2019
 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Equal implements Query {
	String index;
	Comparable key;

	public Equal(String index, Comparable key) {
		this.index = index;
		this.key = key;
	}

	// returns all the values of keys that are equal to the key field
	public HashSet<Integer> execute(HashMap<String, RedBlackTree<Comparable, HashSet<Integer>>> indexes) {
		try {
			Set<String> set = indexes.keySet();
			if (!set.contains(index)) {
				throw new IllegalArgumentException();
			}
			HashSet<Integer> hs = (indexes.get(index)).get(key);
			return hs;

		} catch (IllegalArgumentException e) {
			System.out.println("The index: '" + index
					+ "' is not one of our search indexes. Please create the index first before running the query");
		}
		return null;

	}

}
