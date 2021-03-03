package mk.iwec.bookshelf.service.impl;

import lombok.extern.slf4j.Slf4j;
import mk.iwec.bookshelf.repository.FileRepository;
import mk.iwec.bookshelf.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Slf4j
@Transactional
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository repository;

    @Override
    public String save(MultipartFile file) {
        log.info(String.format("File name '%s' is uploading.", file.getOriginalFilename()));
        return repository.save(file);
    }

    @Override
    public ResponseEntity downloadFile(String fileName) throws IOException {
        log.info(String.format("File name '%s' is downloading.", fileName));
        return repository.downloadFile(fileName);
    }

}
