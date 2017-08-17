package efficientMarkovModelWord;

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    protected int nOrder;

    public AbstractMarkovModel(int n) {
        nOrder = n;
        myRandom = new Random();
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows(String key){
        ArrayList<String> follows = new ArrayList<String>();
        int keyLength = key.length();
        for (int i = 0; i < myText.length() - keyLength; i++){

            if(key.equals(myText.substring(i, i + keyLength))){
                follows.add(myText.substring(i + keyLength, i + keyLength +1));
            }
        }
        return follows;
    }
}
