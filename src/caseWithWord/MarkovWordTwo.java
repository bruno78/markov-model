package caseWithWord;

import java.util.*;

public class MarkovWordTwo implements IMarkovModel
{
    private String[] myText;
    private Random myRandom;

    public MarkovWordTwo() {
        myRandom = new Random();
    }

    public ArrayList<String> getFollows(String key1, String key2){
        ArrayList<String> follows = new ArrayList<String>();

        int pos = 0;
        while( pos < myText.length) {

            int start = indexOf(myText, key1, key2, pos);

            if (start == -1 || start + 2 >= myText.length-1){
                break;
            }

            follows.add(myText[start + 2]);
            pos = start + 2;

        }
        return follows;
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.split("\\s+");
    }

    public String getRandomText(int numChars){

        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length-2);
        String key1 = myText[index];
        String key2 = myText[index + 1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");

        for(int i = 0; i < numChars-2; i++){
            ArrayList<String> follows = getFollows(key1, key2);
            if(follows.size() == 0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        return sb.toString().trim();
    }

    private Integer indexOf(String[] words, String target1, String target2, int start){

        for (int k = start; k < words.length; k++){
            if(words[k].equals(target1) && words[k+1].equals(target2)){
                return k;
            }
        }
        return -1;
    }

    public void testIndexOf(){
        String s = "this is just a test yes this is a simple test";
        String[] arr = s.split("\\s+");
        System.out.println(arr.toString());
        /*
        int idx = indexOf(arr, "this", 0); // 0
        int idx2 = indexOf(arr, "this", 3);  // 6
        int idx3 = indexOf(arr, "frog", 0);  // -1
        int idx4 = indexOf(arr, "frog", 5); // -1
        int idx5 = indexOf(arr, "simple", 2); // 9
        int idx6 = indexOf(arr, "test", 5); // 10
        System.out.println(idx);
        System.out.println(idx2);
        System.out.println(idx3);
        System.out.println(idx4);
        System.out.println(idx5);
        System.out.println(idx6);
        */
    }
}
