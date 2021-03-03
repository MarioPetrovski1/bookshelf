package mk.iwec.bookshelf.repository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileRepository {

    String save(MultipartFile file);

    ResponseEntity downloadFile(String fileName) throws IOException;


}
