package mk.iwec.bookshelf.infrastucture;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Endpoints {
    public static final String BASE = "/api/";

    public static final String PUBLISHERS = BASE + "publishers/";
    public static final String BOOKS = BASE + "books/";
    public static final String AUTHORS = BASE + "authors/";
    public static final String FILES = BASE + "files/";

    public static final String UPLOAD_FILE = FILES + "upload/";
    public static final String DOWNLOAD_FILE = FILES + "download/";
    public static final String DELETE_FILE = FILES + "delete/";

}