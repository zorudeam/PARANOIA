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

    public void archive(List<Thought> thoughts){
        list.addAll(thoughts);
    }

    public List load(){
        return list;
    }

    public int size(){
        return list.size();
    }

    public void limit(int limit){
        /**Limits the Idea's thought list to the number specified. It erases thoughts from older to newer.**/
        for(int i=0; i<list.size() - limit; i++){
            list.remove(i);
        }
    }
}
