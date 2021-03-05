package mk.iwec.bookshelf.repository.impl;

import mk.iwec.bookshelf.infrastucture.exception.ResourceNotFoundException;
import mk.iwec.bookshelf.repository.FileRepository;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Repository
public class FileRepositoryImpl implements FileRepository {
    private static final String FILES_DIR = System.getProperty("user.dir") + "/uploads/";

    @Override
    public String save(MultipartFile file) {
        try {
            file.transferTo(new File(FILES_DIR + file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file.getOriginalFilename();
    }

    @Override
    public ResponseEntity downloadFile(String fileName) throws IOException {
        String[] fileName1 = fileName.split(":");
        String[] splitFileName = fileName1[1].split("\"");
        String fileForReturn = FILES_DIR + splitFileName[1];

        File file = new File(fileForReturn);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(file.length())
                .body(resource);
    }

    @Override
    public ResponseEntity deleteFile(String fileName) throws IOException {
        String fileForDelete = FILES_DIR + fileName;

        File file = new File(fileForDelete);
        if (file.delete()) {
            return ResponseEntity.ok()
                    .body("File deleted : " + fileForDelete);
        } else {
            return ResponseEntity.ok()
                    .body("File not found");
        }

    }
}
