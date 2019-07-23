
/*					Author : Francois Mukaba
 * 					Instructor : Fatma Serce
 * 					Course : Algorithms / CS 401
 * 					Spring 2019
 */

public class Movie implements Comparable {
	private int id;
	private String color;
	private String movie_title;
	private int duration;
	private String director_name;
	private String actor_1_name;
	private String actor_2_name;
	private String actor_3_name;
	private String movie_imdb_link;
	private String language;
	private String country;
	private String content_rating;
	private int title_year;
	private double imdb_score;

	public Movie(int id, String color, String movie_title, int duration, String director_name, String actor_1_name,
			String actor_2_name, String actor_3_name, String movie_imdb_link, String language, String country,
			String content_rating, int title_year, double imdb_score) {
		this.id = id;
		this.color = color;
		this.movie_title = movie_title;
		this.duration = duration;
		this.director_name = director_name;
		this.actor_1_name = actor_1_name;
		this.actor_2_name = actor_2_name;
		this.actor_3_name = actor_3_name;
		this.movie_imdb_link = movie_imdb_link;
		this.language = language;
		this.country = country;
		this.content_rating = content_rating;
		this.title_year = title_year;
		this.imdb_score = imdb_score;
	}

	// To account for zero-indexing
	public Movie() {
		id = 0;
		color = "null";
		movie_title = "null";
		duration = 0;
		director_name = "null";
		actor_1_name = "null";
		actor_2_name = "null";
		actor_3_name = "null";
		movie_imdb_link = "null";
		language = "null";
		country = "null";
		content_rating = "null";
		title_year = -1;
		imdb_score = -1;
	}

	@Override
	public String toString() {
		return "-------------------------------------------------" + "\n" + "id: " + id + "\n" + "color: " + color
				+ "\n" + "title: " + movie_title + "\n" + "director: " + director_name + "\n" + "year: " + title_year
				+ "\n" + "imdb_score: " + imdb_score + "\n" + "-------------------------------------------------"
				+ "\n";
	}

	@SuppressWarnings("null")
	@Override
	public int compareTo(Object o) {
		if (o instanceof Movie) {
			Movie mov = (Movie) o;
			return (id < mov.id) ? -1 : ((id == mov.id) ? 0 : 1);
		}
		return (Integer) null;
	}

	public int getId() {
		return id;
	}

	public String getColor() {
		return color;
	}

	public String getMovie_title() {
		return movie_title;
	}

	public int getDuration() {
		return duration;
	}

	public String getDirector_name() {
		return director_name;
	}

	public String getActor_1_name() {
		return actor_1_name;
	}

	public String getActor_2_name() {
		return actor_2_name;
	}

	public String getActor_3_name() {
		return actor_3_name;
	}

	public String getMovie_imdb_link() {
		return movie_imdb_link;
	}

	public String getLanguage() {
		return language;
	}

	public String getCountry() {
		return country;
	}

	public String getContent_rating() {
		return content_rating;
	}

	public int getTitle_year() {
		return title_year;
	}

	public double getImdb_score() {
		return imdb_score;
	}

}
