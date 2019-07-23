
/*					Author : Francois Mukaba
 * 					Instructor : Fatma Serce
 * 					Course : Algorithms / CS 401
 * 					Spring 2019
 */
import java.util.HashMap;
import java.util.HashSet;

public class And implements Query {
	private Query[] queries;

	public And(Query... queries) {
		this.queries = queries;

	}

	// return intersection of queries passed
	public HashSet<Integer> execute(HashMap<String, RedBlackTree<Comparable, HashSet<Integer>>> indexes) {
		HashSet<Integer> result = queries[0].execute(indexes);
		try {
			for (int i = 1; i < queries.length; i++) {
				HashSet<Integer> hs = queries[i].execute(indexes);
				result.retainAll(hs);
			}
		} catch (Exception e) {
			System.out.println("Not all queries exzecuted");
		}
		return result;
	}
}
