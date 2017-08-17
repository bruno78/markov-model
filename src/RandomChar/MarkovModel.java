package RandomChar;

import java.util.Random;
import java.util.ArrayList;

public class MarkovModel
{
    private String myText;
    private Random myRandom;
    private int nOrder;

    public MarkovModel(int n) {
        myRandom = new Random();
        nOrder = n;
    }

    public ArrayList<String> getFollows(String key){
        ArrayList<String> follows = new ArrayList<String>();
        int keyLength = key.length();
        for (int i = 0; i < myText.length() - keyLength; i++){

            if(key.equals(myText.substring(i, i + keyLength))){
                follows.add(myText.substring(i + keyLength, i + keyLength +1));
            }
        }

        return follows;
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
}
