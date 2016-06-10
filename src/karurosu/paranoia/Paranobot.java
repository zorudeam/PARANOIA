package karurosu.paranoia;

import java.io.*;

public class Paranobot {

    private static BufferedReader in;
    private static int point; //use only to index
    private static Thought[] buffer;
    /**Each file contains one thought**/
    public static void main(String[] args) {
        point = 0; //file of point 0 doesn't exist, this is for convenience
        initialize(args);
    }

    private static void initialize(String[] args){
        in = new BufferedReader(new InputStreamReader(System.in));
        output("STARTING PARANOIA PROJECT");
        saveHard(new Thought("one", Thought.Type.GOOD, Thought.Intensity.OK));
        saveHard(new Thought("one", Thought.Type.GOOD, Thought.Intensity.OK));
        saveHard(new Thought("one", Thought.Type.GOOD, Thought.Intensity.OK));
        output("point "+point);
    }

    public synchronized static void output(String s){
        System.out.println(s);
    }

    public synchronized static void saveHard(Thought o){
        ObjectOutputStream oos;
        FileOutputStream fos;
        try{
            File path = new File("Hard");
            path.mkdirs();
            String fileName = "thought"+makeHardPoint()+".paranoia";

            File f = new File(path, fileName);

            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(o);
            oos.close();
            fos.close();
        }catch(IOException ioe){
            output("Did not save hard");
        }
    }

    private static void readHard(){
        ObjectInputStream ois;
        FileInputStream fis;
        buffer = new Thought[point];
        int e = point;
        try{

            for(int i=0; i<point; i++){
                fis = new FileInputStream("/Hard/thought"+ e-- +".paranoia");
                ois = new ObjectInputStream(fis);
                buffer[i] = (Thought) ois.readObject();
                fis.close();
                ois.close();
            }
        }catch(IOException ioe){
            output("Did not read hard");
        }
        catch(ClassNotFoundException cnfe){
            output("Class not found");
        }
    }

    private static int makeHardPoint() throws IOException {
        /**
         * A hard point is an index of the hard thought of the AI.
         * Each time this method is called the point is updated to the current and substituted in the file by a new one
         **/
        File path = new File("Hard");
        path.mkdirs();
        String fileName = "point.paranoia";

        File pointFile = new File(path, fileName);

        if(pointFile.exists() && !pointFile.isDirectory()){
            FileInputStream fis = new FileInputStream(pointFile);
            output("good!");
            point = fis.read();
            pointFile.delete();
        }
            pointFile = new File(path, fileName);
            FileOutputStream fos = new FileOutputStream(pointFile);
            newHardPoint(); //++point
            fos.write(point);
            return point;
    }

    private static void newHardPoint(){
        ++point;
    }
}