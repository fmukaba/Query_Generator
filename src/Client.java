
/*					Author : Francois Mukaba
 * 					Instructor : Fatma Serce
 * 					Course : Algorithms / CS 401
 * 					Spring 2019
 */

import java.util.HashSet;

public class Client {
	public static void main(String[] args) {
		// initialize database
		MovieDB mov = new MovieDB("movie_metadata.csv");

		// add indexes
		mov.addFieldIndex("color");
		mov.addFieldIndex("imdb_score");
		mov.addFieldIndex("title_year");

		// run some queries
		System.out.println("Query 1: Movies where the year is 2012 and the imbd score is 6.1 : \n");
		Query query1 = new And(new Equal("title_year", 2012), new Equal("imdb_score", 6.1));
		HashSet<Integer> result = query1.execute(mov.getIndexTreeMap());
		if (result != null && !result.isEmpty()) {
			System.out.println(result);
			for (int index : result) {
				mov.print(index);
			}
		} else {
			System.out.println("There were no match for the query entered !");
		}
		System.out
				.println("Query 2: Movies where the year is 1996 or the imbd score is 5.1 or in black and white : \n");
		Query query2 = new Or(new Equal("title_year", 1996), new Equal("imdb_score", 5.1),
				new Equal("color", " Black and White"));
		HashSet<Integer> result2 = query2.execute(mov.getIndexTreeMap());
		if (result2 != null && !result2.isEmpty()) {
			System.out.println(result2);
			for (int index : result2) {
				mov.print(index);
			}

		} else {
			System.out.println("There were no match for the query entered !");
		}

		System.out.println(
				"Query 3: Movies where the year is before 2000 and the imbd score is greater than 7.0 and in color: \n");
		Query query3 = new And(new LT("title_year", 2000), new GT("imdb_score", 7.0),
				new NotEqual("color", " Black and White"));
		HashSet<Integer> result3 = query3.execute(mov.getIndexTreeMap());
		if (result3 != null && !result3.isEmpty()) {
			System.out.println(result3);
			for (int index : result3) {
				mov.print(index);
			}

		} else {
			System.out.println("There were no match for the query entered !");
		}

		System.out.println("Query 4: Movies where the color is not not equal to Black and white");
		System.out.println("Logically, Movies where the color is equal to Black and white : \n");
		Query query4 = new Not(new NotEqual("color", " Black and White"));
		HashSet<Integer> result4 = query4.execute(mov.getIndexTreeMap());
		if (result4 != null && !result4.isEmpty()) {
			System.out.println(result4);
			for (int index : result4) {
				mov.print(index);
			}

		} else {
			System.out.println("There were no match for the query entered !");
		}
	}
}
