package karurosu.paranoia;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Logger {

    private static Logger instance = null;
    private static FileOutputStream errorStream, dumpStream, regularStream;
    private static OutputStreamWriter errorWriter, dumpWriter, regularWriter;

    public static Logger getLogger(){
        if (instance == null){
            instance = new Logger();
            File path = new File("/logs");
            path.mkdirs();
            File error = new File(path, "error.txt");
            File dump = new File(path, "dump.txt");
            File regular = new File(path, "regular.txt");
            try {
                errorStream = new FileOutputStream(error);
                dumpStream = new FileOutputStream(dump);
                regularStream = new FileOutputStream(regular);
                errorWriter = new OutputStreamWriter(errorStream);
                dumpWriter = new OutputStreamWriter(dumpStream);
                regularWriter = new OutputStreamWriter(regularStream);
            }catch(IOException ioe){System.out.println("IOException !");}
        }
        return instance;
    }

    public static synchronized void log(String message){
        log(message, Log_type.REGULAR);
    }

    public static synchronized void log(String message, Log_type log_type ){
        try {
        if(log_type == Log_type.REGULAR) {
            regularWriter.write(message);
        }
        else if(log_type == Log_type.DUMP) {
            dumpWriter.write(message);
        }
        else if(log_type == Log_type.ERROR){
            errorWriter.write(message);
        }
        }catch (IOException e) {
            System.out.println("Logger error !");
        }
    }

    enum Log_type{
        REGULAR,
        ERROR,
        DUMP
    }
}
