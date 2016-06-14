package karurosu.paranoia;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Logger {

    private static Logger instance = null;
    private static FileOutputStream errorStream, dumpStream;
    private static OutputStreamWriter errorWriter, dumpWriter;

    public Logger getLogger(){
        if (instance == null){
            instance = new Logger();
            File path = new File("/logs");
            path.mkdirs();
            File error = new File(path, "error.txt");
            File dump = new File(path, "dump.txt");
            try {
                errorStream = new FileOutputStream(error);
                dumpStream = new FileOutputStream(dump);
                errorWriter = new OutputStreamWriter(errorStream);
                dumpWriter = new OutputStreamWriter(dumpStream);
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
            System.out.println(message);
        }
        else if(log_type == Log_type.DUMP) {
            errorWriter.write(message);
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
