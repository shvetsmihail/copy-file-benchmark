package copy.file.benchmark.services;

import java.io.IOException;

public interface FileService {
    void copy(String original, String copy) throws IOException;
}
