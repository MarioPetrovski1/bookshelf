package mk.iwec.bookshelf.api;

import mk.iwec.bookshelf.infrastucture.Endpoints;
import mk.iwec.bookshelf.service.impl.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
public class FileController {

    @Autowired
    FileService service;

    @PostMapping(value = Endpoints.UPLOAD_FILE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFile(@ModelAttribute MultipartFile file) {
        service.save(file);
        return ResponseEntity.ok().build();
    }

    @PostMapping(Endpoints.DOWNLOAD_FILE)
    public ResponseEntity downloadFile(@RequestBody String fileName) throws IOException {
        return service.downloadFile(fileName);
    }

}
