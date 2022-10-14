package copy.file.benchmark;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Utils {
    public static void createFile(String name, long length) throws IOException {
        RandomAccessFile file = new RandomAccessFile(name, "rw");
        file.setLength(length);
    }

    public static void removeFile(String name) {
        File f = new File(name);
        f.deleteOnExit();
    }
}
