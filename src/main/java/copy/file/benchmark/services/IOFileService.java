package copy.file.benchmark.services;

import java.io.*;

public class IOFileService implements FileService {
    @Override
    public void copy(String original, String copy) throws IOException {
        try (InputStream in = new BufferedInputStream(new FileInputStream(original));
             OutputStream out = new BufferedOutputStream(new FileOutputStream(copy))) {

            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, lengthRead);
                out.flush();
            }
        }
    }
}
