package Source;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Singleton {
    private static final Singleton inst = new Singleton();

    private Singleton(){
        super();
    }

    public synchronized void writeToFile(String path, String text) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path,true));
        writer.write(text);
        writer.newLine();
        writer.close();
    }

    public static Singleton getInstance(){
        return inst;
    }
}
