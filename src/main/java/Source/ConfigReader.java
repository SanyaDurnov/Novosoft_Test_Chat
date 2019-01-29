package Source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);
    private final String PROPERTIES_FILE_NAME = "config.properties";
    private final String DIR_SEPARATOR = System.getProperty("file.separator");
    private final File currentDir = new File(".");
    public static String inputFilePath = null;
    public static String outputFilePath = null;


    public ConfigReader() {
        try {
            String sFilePath = currentDir.getCanonicalPath() + DIR_SEPARATOR + PROPERTIES_FILE_NAME;
            FileInputStream fileInputStream = new FileInputStream(sFilePath);
            Properties conf = new Properties();
            conf.load(fileInputStream);
            inputFilePath = conf.getProperty("fileInputPath");
            outputFilePath = conf.getProperty("fileOutputPath");


        } catch (FileNotFoundException e) {
            logger.error("Configuration file not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            logger.error("Configuration parameter not found");
            e.printStackTrace();
        }
    }
}