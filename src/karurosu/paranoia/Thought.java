package karurosu.paranoia;

import java.io.Serializable;

/**
 * Created by Zorudeam on 09/06/2016.
 */
public class Thought implements Serializable{
    String e;

    public Thought(String n, Type type, Intensity intensity){
        this.e = n;
    }

    enum Intensity { //0-8 LEVELS, 4 is static
        INEXISTENT,
        FORGOTTEN,
        SOFT,
        MEH,
        STATIC,
        OK,
        HARD,
        EXTREME,
        TOTAL,
    }

    enum Type{
        GOOD,
        BAD
    }

}
