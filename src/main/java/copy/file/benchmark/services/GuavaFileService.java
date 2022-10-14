package copy.file.benchmark.services;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

public class GuavaFileService implements FileService {
    @Override
    public void copy(String original, String copy) throws IOException {
        Files.copy(new File(original), new File(copy));
    }
}
