
/**
 * Write a description of class MarkovWord here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovWord implements IMarkovModel
{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
    }
    
    public ArrayList<String> getFollows(WordGram kGram){
        ArrayList<String> follows = new ArrayList<String>();
        
        int pos = 0; 
        while( pos < myText.length) {
            
            int start = indexOf(myText, kGram, pos);
            
            if (start == -1 || start + 1 >= myText.length-1){
                break;
            }
            
            follows.add(myText[start + myOrder]);
            pos = start + 1;
            
        }
        return follows;
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length-myOrder);
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key.toString());
        sb.append(" ");
        
        for(int i = 0; i < numWords-myOrder; i++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        } 
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words, WordGram target, int start){
        
        for (int k = start; k < words.length - myOrder; k++){
            WordGram wg = new WordGram(words, k, myOrder);
            if(wg.equals(target)){
                return k;
            }
        }
        return -1;
    }
}
