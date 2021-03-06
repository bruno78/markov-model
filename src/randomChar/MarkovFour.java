package randomChar;

import java.util.Random;
import java.util.ArrayList;

public class MarkovFour
{
    private String myText;
    private Random myRandom;

    public MarkovFour() {
        myRandom = new Random();
    }

    public ArrayList<String> getFollows(String key){
        ArrayList<String> follows = new ArrayList<String>();
        int keyLength = key.length();
        for (int i = 0; i < myText.length() - keyLength; i++){

            if(key.equals(myText.substring(i, i + keyLength))){
                follows.add(myText.substring(i + keyLength, i + keyLength +1));
            }
        }
        /*
        int pos = 0;
        while (pos < myText.length() - key.length()){
            pos = myText.indexOf(key, pos+1) + key.length();
            if(pos >= myText.length() || pos == 0){
                break;
            }
            follows.add(myText.substring(pos, pos+1));
        }
        */
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
        int index = myRandom.nextInt(myText.length()-4);
        String key = myText.substring(index, index+4);
        sb.append(key);

        for(int i = 0; i < numChars-4; i++){
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
