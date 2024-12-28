package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

    private static Properties properties = new Properties ();
    private static final String CONFIG_FILE_PATH=System.getProperty ("user.dir")+"/src/main/resources/config.properties";

    static {
        try(FileInputStream fileInputStream = new FileInputStream ( CONFIG_FILE_PATH )){

            properties.load (fileInputStream);

        }catch (IOException e) {
            throw new RuntimeException ( "File not found" + e );
        }
    }

    public static String getProperty(String key){
        String value = properties.getProperty ( key );
        if (value!=null){
            return value;
        }else {
            throw new RuntimeException( key + ":- not specified in the Configuration.properties file.");
        }
    }

}
