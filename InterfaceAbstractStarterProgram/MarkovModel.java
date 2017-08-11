
/**
 * Write a description of class MarkovModel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.ArrayList;

public class MarkovModel extends AbstractMarkovModel
{
    
    public MarkovModel(int n) {
        super(n);
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length()-nOrder);
        String key = myText.substring(index, index+nOrder);
        sb.append(key);
        
        for(int i = 0; i < numChars-nOrder; i++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        } 
        return sb.toString();
    }
        public String toString(){
        return ("MarkovModel of order " + nOrder);
    }
}