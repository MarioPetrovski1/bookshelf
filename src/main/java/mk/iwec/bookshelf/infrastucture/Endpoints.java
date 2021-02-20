package mk.iwec.bookshelf.infrastucture;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Endpoints {
	public static final String BASE = "/api/";
	
	public static final String PUBLISHERS = BASE + "publishers/";
	public static final String BOOKS = BASE + "books/";

}