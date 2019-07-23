
/*					Author : Francois Mukaba
 * 					Instructor : Fatma Serce
 * 					Course : Algorithms / CS 401
 * 					Spring 2019
 */
import java.util.HashMap;
import java.util.HashSet;

public class Not implements Query {
	HashSet<Integer> h1;
	HashSet<Integer> db;
	Query q1;

	public Not(Query q1) {
		this.q1 = q1;
		db = this.initializeDBSet();

	}

	// return difference from database to query passed
	public HashSet<Integer> execute(HashMap<String, RedBlackTree<Comparable, HashSet<Integer>>> indexes) {
		h1 = q1.execute(indexes);
		db.removeAll(h1);
		return db;
	}

}
