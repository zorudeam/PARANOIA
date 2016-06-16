package karurosu.paranoia;


import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Interface implements Runnable{

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Interface instance = new Interface();
    static Thread t;

    public static Interface getInstance() {
        return instance;
    }

    private Interface() { //still to test, I have never done this before
        t = new Thread(instance);
        t.start();
    }

    public static synchronized void output(String s){System.out.println(s);}

    public void run() {

        output("Welcome to PARANOIA project version Development.0 !");
        output("As this is the development version, you have to specify" +
                " how many nodes you need. Please write it with integer numbers.");
        int nodes = (Integer)input(Integer.class);
        //TODO:Continue whatever I was doing, IDK, it's 5AM...
    }

    public String input(){
        return (String) input(String.class);
    }

    public static synchronized Object input(Class c){
        String message = null;
        boolean allright = false;
        do{
            try {
                message = reader.readLine();

                if (c == String.class) return message;
                if (c == Integer.class) return Integer.parseInt(message);
                if (c == Double.class) return Double.parseDouble(message);
                if (c == Float.class) return Float.parseFloat(message);
                if (c == Byte.class) return Byte.parseByte(message);
                if (c == Boolean.class) return Boolean.parseBoolean(message);
                if (c == Long.class) return Long.parseLong(message);
                if (c == Short.class) return Short.parseShort(message);

                throw new Exception();

            } catch (Exception e) {
                output("Hello, I am here to handle all your invalid inputs! Enter information again.");
            }
        }while(!allright);
        return null; //Compiler, why am I forced to write this?
    }
}
