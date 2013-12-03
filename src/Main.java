import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final int ThreadsCount = 5;
    private static ExecutorService executorService = Executors.newFixedThreadPool(ThreadsCount);
    private static Phone[] phones;
    private static Tower[] towers;

    public static void main(String[] args) throws InterruptedException {
        ReadData("input.bin");
        for (int i = 0; i < phones.length; i++) {
            executorService.execute(phones[i]);
        }

        for (int i = 0; i < phones.length; i++) {
            executorService.execute(towers[i]);
        }
        executorService.shutdown();
        executorService.awaitTermination(10000, TimeUnit.MILLISECONDS);
    }

    private static void ReadData(String fileName) {
        try {
            DataInputStream stream = new DataInputStream(new FileInputStream(fileName));
            int towersCount = stream.readInt();
            towers = new Tower[towersCount];
            for (int i = 0; i < towersCount; i++) {
                towers[i] = new Tower(new Coordinates(stream.readInt(), stream.readInt()),
                        stream.readInt());
            }

            int phonesCount = stream.readInt();
            phones = new Phone[phonesCount];
            for (int i = 0; i < phonesCount; i++) {
                phones[i] = new Phone(new Coordinates(stream.readInt(), stream.readInt()));
            }
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
