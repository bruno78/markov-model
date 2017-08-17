package caseWithWord;

import java.util.*;

public class EfficientMarkovWord implements IMarkovModel
{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> myMap;

    public EfficientMarkovWord(int order) {
        myOrder = order;
        myMap = new HashMap<WordGram, ArrayList<String>>();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.split("\\s+");
        buildMap();
        printHashMapInfo();
    }

    public ArrayList<String> getFollows(WordGram kGram){
        return myMap.get(kGram);
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

    public void printHashMapInfo(){
        int maxSetSize = -1;

        for (WordGram wg : myMap.keySet()){
            maxSetSize = Math.max(maxSetSize, myMap.get(wg).size());
        //    System.out.println(wg + "\t: " + myMap.get(wg));
        }

        System.out.println("Number of keys: " + myMap.keySet().size());
        System.out.println("Max Set Size: " + maxSetSize);
        System.out.println("Keys with Max Size:");

        //for(WordGram wg : myMap.keySet()) {
        //    if (myMap.get(wg).size() == maxSetSize) {
        //        System.out.println(wg);
        //    }
        //}
    }
    private void buildMap() {

        for (int i = 0; i < myText.length-myOrder; i++){
            WordGram wg = new WordGram(myText, i, myOrder);
            String next = myText[i+myOrder];

            if(myMap.containsKey(wg)){
                myMap.get(wg).add(next);
            }
            else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(next);
                myMap.put(wg, list);
            }
        }
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
