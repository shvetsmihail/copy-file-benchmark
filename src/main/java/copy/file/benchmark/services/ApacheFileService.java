package copy.file.benchmark.services;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ApacheFileService implements FileService {

    @Override
    public void copy(String original, String copy) throws IOException {
        FileUtils.copyFile(new File(original), new File(copy));

    }
}
