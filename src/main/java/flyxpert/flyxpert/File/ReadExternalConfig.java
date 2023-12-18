package flyxpert.flyxpert.File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadExternalConfig {
    //Reading constants from an external configuration
    public static FileReader config_reader;
    public static Properties config;
    public ReadExternalConfig(){
        try{
            config_reader = new FileReader("FlyXpert.properties");
            config = new Properties();
            config.load(config_reader);
        }
        catch(IOException e){
            System.out.println("Cannot find file FlyXpert.properties");
        }
    }
}
