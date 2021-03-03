package mk.iwec.bookshelf.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    String save(MultipartFile file);

    ResponseEntity downloadFile(String fileName) throws IOException;

}
