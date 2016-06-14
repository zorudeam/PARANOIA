package karurosu.paranoia;

import java.util.ArrayList;
import java.util.List;

/**
 * The Idea class is supposed to be a wrapper for thoughts
 **/
public class Idea {
    List<Thought> list = new ArrayList<>();

    public int archive(Thought thought){
        list.add(thought);
        return thought.forgetness;
    }

    public List load(){
        return list;
    }
}
