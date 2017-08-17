package efficientMarkovModelWord;
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel
{
    private HashMap<String, ArrayList<String>> myMap;

    public EfficientMarkovModel(int n) {
        super(n);
        myMap = new HashMap<String, ArrayList<String>>();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        super.setTraining(s);
        buildMap();
        printHashMapInfo();
    }

    public void buildMap(){

        for (int i = 0; i < myText.length() - nOrder; i++) {

            String key = myText.substring(i, i+nOrder);
            String follow = myText.substring(i+nOrder, i+nOrder+1);

            if(myMap.containsKey(key)){
                myMap.get(key).add(follow);
            }
            else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(follow);
                myMap.put(key, list);
            }
        }
    }

    public ArrayList<String> getFollows(String key) {
        return myMap.get(key);
    }

    public String getRandomText(int numChars){

        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length()-nOrder);
        String key = myText.substring(index, index+nOrder);
        sb.append(key);

        for(int i = 0; i < numChars-nOrder; i++){
            ArrayList<String> follows = getFollows(key);
            if(follows == null){
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
        return ("Efficient MarkovModel of order " + nOrder);
    }

    public void printHashMapInfo(){

        int maxSize = 0;
        for (String key : myMap.keySet()){

            //System.out.println("key: " + key + ", value: " + myMap.get(key));

            maxSize = Math.max(maxSize, myMap.get(key).size());
        }
        System.out.println("The max list size is: " + maxSize);
        System.out.println("The number of keys: " + myMap.keySet().size());

        for (String key : myMap.keySet()){
            if(myMap.get(key).size() == maxSize) {
                System.out.println("keys with max value: " + key);
            }
        }
    }
}
