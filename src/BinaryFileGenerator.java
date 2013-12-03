import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class BinaryFileGenerator {
    private int towersCount;
    private int callsCount;

    public BinaryFileGenerator(int towersCount, int callsCount) {
        this.towersCount = towersCount;
        this.callsCount = callsCount;
    }

    public void generateFile(String filename) throws IOException {
        DataOutputStream stream = null;
        try {
            stream = new DataOutputStream(new FileOutputStream(filename, false));
            stream.writeInt(towersCount);
            Random rnd = new Random(System.currentTimeMillis());
            for (int i = 0; i < towersCount; i++) {
                stream.writeInt(rnd.nextInt(200) - 100);
                stream.writeInt(rnd.nextInt(200) - 100);
                stream.writeInt(rnd.nextInt(50) + 20);
            }

            stream.writeInt(callsCount);
            for (int i = 0; i < callsCount; i++) {
                stream.writeInt(rnd.nextInt(300) - 150);
                stream.writeInt(rnd.nextInt(300) - 150);
            }
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        BinaryFileGenerator generator = new BinaryFileGenerator(
                Integer.parseInt(args[0]),
                Integer.parseInt(args[1]));
        try {
            generator.generateFile("input.bin");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}