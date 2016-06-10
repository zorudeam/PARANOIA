package karurosu.paranoia;

import java.io.*;

public class Paranobot {

    private static BufferedReader in;
    private static int point; //use only to index

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

    private static Thought readHard(int p){
        ObjectInputStream ois;
        FileInputStream fis;
        Thought res;
        try{
                String path = "Hard/thought"+ p +".paranoia";

                fis = new FileInputStream(path);
                ois = new ObjectInputStream(fis);
                res = (Thought) ois.readObject();
                fis.close();
                ois.close();
                return res;

            } catch (FileNotFoundException e) {
            output("File not found.");
            }
            catch(IOException ioe){
            output("Did not read hard");
            }
            catch(ClassNotFoundException cnfe){
            output("Class not found");
            }
        return null;
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