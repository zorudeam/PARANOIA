package karurosu.paranoia;

import java.util.ArrayList;
import java.util.List;

/**
 * The class Node is equivalent to a neuron.
 * **/
public class Node {

    int avg;
    boolean interrupt;
    Thought t2; //actually, t2 is the thought before t.
    List<Node> nodes;
    List<Thought> thoughts;
    Idea idea;

    public void Node(){
        nodes = new ArrayList<>();
        thoughts = new ArrayList<>();
        avg = 0;
    }

    public Thought give(Node from, Thought thought){
        if(nodes.contains(from)){
            this.add(thought);
            return ask(thought);
        }
        else return null;
    }

    public Thought ask(Thought thought){
        return synthesize(thought);
    }

    /**Method synthesize decides what to do with a Thought object**/
    private Thought synthesize(Thought thought){

        //TODO: A new thought about thought. Here is where communication between nodes and creativity (should) occurs.

        if(thought.intensity.getIntensity() > getIntensityAvg()){
            add(thought);
        }
        return /*new*/ thought /*about the thought*/;
    }

    /**
     * White lists of thoughts and nodes
     * **/

    private void add(Thought thought){
        thoughts.add(thought);
    }

    public byte addNode(Node node){
        if(node!=null) nodes.add(node);
        else return 1;
        return 0;
    }

    public int getIntensityAvg(){
        int n=0;
        for(Thought t : thoughts){
            n++;
            avg = avg + t.intensity.getIntensity();
        }
        return avg/n;
    }

    /**
     * The tick method is supposed to increase the forgetness index of some thoughts and stop whenever boolean interrupt is true
     * TODO: call tick method and use the interrupt boolean
     * **/
    public void tick(){
        if (t2 != null) {
            for(Thought t : thoughts) {
                if(interrupt) return;
                if (t.intensity.getIntensity() < t2.intensity.getIntensity()) {
                    t.forgetness++;
                }
                t2 = t;
            }
        }
        else t2 = thoughts.get(0);
    }

    private Idea classifyIdea(){
        idea = new Idea();
        idea.archive(thoughts);
        return idea;
    }

    //TODO: Automatic interaction between nodes

}
