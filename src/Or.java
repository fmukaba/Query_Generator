
/*					Author : Francois Mukaba
 * 					Instructor : Fatma Serce
 * 					Course : Algorithms / CS 401
 * 					Spring 2019
 */
import java.util.HashMap;
import java.util.HashSet;

public class Or implements Query {
	private Query[] queries;

	public Or(Query... queries) {
		this.queries = queries;

	}

	// return union of queries passed
	public HashSet<Integer> execute(HashMap<String, RedBlackTree<Comparable, HashSet<Integer>>> indexes) {
		HashSet<Integer> result = queries[0].execute(indexes);

		for (int i = 1; i < queries.length; i++) {
			HashSet<Integer> hs = queries[i].execute(indexes);
			if (hs != null) {
				result.addAll(hs);
			}
		}

		return result;

	}
}
