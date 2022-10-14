package copy.file.benchmark;

import copy.file.benchmark.services.ApacheFileService;
import copy.file.benchmark.services.FileService;
import copy.file.benchmark.services.GuavaFileService;
import copy.file.benchmark.services.IOFileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        List<FileService> fileServices = new ArrayList<>();
        fileServices.add(new IOFileService());
        fileServices.add(new ApacheFileService());
        fileServices.add(new GuavaFileService());

        int times = 100;
        int size1 = 1024 * 1024;        //1 MB
        int size2 = 1024 * 1024 * 100;  //100 MB
        int size3 = 1024 * 1024 * 1024; //1GB

        runBenchmarks(fileServices, times, size1);
        runBenchmarks(fileServices, times, size2);
        runBenchmarks(fileServices, times, size3);
    }

    private static void runBenchmarks(List<FileService> fileServices, int times, int length) throws IOException {
        System.out.printf("%nCopy file %s bytes %s times%n", length, times);
        String file = "test.txt";
        Utils.createFile(file, length);

        for (FileService fileService : fileServices) {
            double avgTime = benchmark(fileService, file, times);
            System.out.printf("%s Average time: %s ms%n",
                    fileService.getClass().getSimpleName(), avgTime);
        }

        Utils.removeFile(file);
    }

    private static double benchmark(FileService fileService, String file, int times) throws IOException {
        String copy = "copy_" + file;
        List<Long> durations = new ArrayList<>(times);

        for (int i = 0; i < times; i++) {
            durations.add(copy(fileService, file, copy));

        }
        return durations.stream()
                .mapToLong(Long::longValue)
                .average()
                .orElse(0);
    }

    private static long copy(FileService fileService, String file, String copy) throws IOException {
        long start = System.currentTimeMillis();
        fileService.copy(file, copy);
        long duration = System.currentTimeMillis() - start;
        Utils.removeFile(copy);
        return duration;
    }
}
