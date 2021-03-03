package mk.iwec.bookshelf.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
@Slf4j
@Transactional
public class FileService {
    private static final String FILES_DIR = "/home/mario/eclipse-workspace/bookshelf/backend/uploads/";


    public String save(MultipartFile file) {
        try {
            file.transferTo(new File(FILES_DIR + file.getOriginalFilename()));
            log.info(String.format("File name '%s' uploaded successfully.", file.getOriginalFilename()));
        } catch (IOException e) {
            log.error(String.format("File name '%s' has error: '%s", file.getOriginalFilename(), e.getMessage()));
            e.printStackTrace();
        }

        return file.getOriginalFilename();
    }

    public ResponseEntity downloadFile(String fileName) throws IOException {
        String[] fileName1 = fileName.split(":");
        String[] splitFileName = fileName1[1].split("\"");
        String fileForReturn = FILES_DIR + splitFileName[1];
        log.info(String.format("File name '%s' downloaded successfully.", splitFileName[1]));

        File file = new File(fileForReturn);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(file.length())
                .body(resource);
    }

}
