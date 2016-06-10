package karurosu.paranoia;

import java.io.Serializable;

public class Thought implements Serializable{
    String message;
    Intensity intensity;
    Type type;

    public Thought(String message, Type type, Intensity intensity){
        this.message = message;
        this.intensity = intensity;
        this.type = type;
    }

    enum Intensity {//0-8, 4 is static
        INEXISTENT(0),
        FORGOTTEN(1),
        SOFT(2),
        MEH(3),
        STATIC(4),
        OK(5),
        HARD(6),
        EXTREME(7),
        TOTAL(8);
        Intensity(int i){ intensity = i;}
        private int intensity;
        public int getIntensity(){
            return intensity;
        }
    }

    enum Type{
        GOOD,
        BAD
    }

}
