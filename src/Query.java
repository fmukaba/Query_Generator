
/*					Author : Francois Mukaba
 * 					Instructor : Fatma Serce
 * 					Course : Algorithms / CS 401
 * 					Spring 2019
 */
import java.util.HashMap;
import java.util.HashSet;

public interface Query {
	final int dataBaseSize = 300;

	public HashSet<Integer> execute(HashMap<String, RedBlackTree<Comparable, HashSet<Integer>>> indexes);

	// create a set of all ids in database
	public default HashSet<Integer> initializeDBSet() {
		HashSet<Integer> db = new HashSet<Integer>();
		// ignore index 0
		for (int i = 1; i <= dataBaseSize; i++) {
			db.add(i);
		}
		return db;
	}
}
