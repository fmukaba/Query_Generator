
/*					Author : Francois Mukaba
 * 					Instructor : Fatma Serce
 * 					Course : Algorithms / CS 401
 * 					Spring 2019
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;

public class MovieDB {
	ArrayList<Movie> db;
	HashMap<String, RedBlackTree<Comparable, HashSet<Integer>>> indexes;

	// create database of movies from file
	public MovieDB(String fileName) {
		// index 0 should be empty to allow proper mapping

		db = new ArrayList<Movie>();
		// to fill '0' index
		db.add(new Movie());
		String line = "";
		String splitBy = "\t";
		indexes = new HashMap<String, RedBlackTree<Comparable, HashSet<Integer>>>();

		try (Scanner reader = new Scanner(new FileReader(fileName))) {

			// skip header row
			reader.nextLine();
			while (reader.hasNextLine()) {
				line = reader.nextLine();

				// use tab as separator to split line
				String[] sequence = line.split(splitBy);

				// prepare Movie fields
				int id = Integer.parseInt(sequence[0]);
				String color = sequence[1];
				String title = sequence[2];
				int duration = Integer.parseInt(sequence[3]);
				String director_name = sequence[4];
				String actor_1_name = sequence[5];
				String actor_2_name = sequence[6];
				String actor_3_name = sequence[7];
				String imdb_link = sequence[8];
				String language = sequence[9];
				String country = sequence[10];
				String rating = sequence[11];
				int title_year = Integer.parseInt(sequence[12]);
				double imdb_score = Double.parseDouble(sequence[13]);

				// create a Movie object
				Movie movie = new Movie(id, color, title, duration, director_name, actor_1_name, actor_2_name,
						actor_3_name, imdb_link, language, country, rating, title_year, imdb_score);

				// add movies to database
				db.add(movie);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			// necessary for potential white spaces after last row
		}
	}

	public void addFieldIndex(String str) {
		if (!indexes.containsKey(str)) {
			Field[] field = Movie.class.getDeclaredFields();

			try {
				// check if field exists
				boolean bool = false;
				for (Field f : field) {
					f.setAccessible(true);
					if (f.getName().equalsIgnoreCase(str)) {
						bool = true;
						RedBlackTree<Comparable, HashSet<Integer>> rbt = new RedBlackTree<Comparable, HashSet<Integer>>();
						// create tree of movie ids where key is the index field
						for (Movie mov : db) {
							Comparable key = (Comparable) f.get(mov);
							// store ids with same key in a set
							if (rbt.contains(key)) {
								HashSet<Integer> update = rbt.get(key);
								update.add(mov.getId());
								rbt.put(key, update);
							} else {
								HashSet<Integer> newSet = new HashSet<Integer>();
								newSet.add(mov.getId());
								rbt.put(key, newSet);
							}
						}
						// add red black trees to HashMap
						indexes.put(str, rbt);
					}
				}
				// field not found
				if (bool == false) {
					System.out.println("Why?");
					throw new IllegalArgumentException();
				}
			} catch (IllegalArgumentException e) {
				System.out.println("Please enter a proper input for the movie attribute");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("This index already exist and is ready to be used.");
		}
	}

	// returns the hash map for index trees (red black trees)
	public HashMap<String, RedBlackTree<Comparable, HashSet<Integer>>> getIndexTreeMap() {
		return indexes;
	}

	// print a movie at index passed
	public void print(int index) {
		System.out.println(db.get(index));
	}
}
