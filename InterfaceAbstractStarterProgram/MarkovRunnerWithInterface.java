
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 
import java.lang.*;

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov.toString());
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 50;
        
        // String st = "Tyes-this-is-a-thin-pretty-pink-thistle";
        size = 100;
   
        EfficientMarkovModel m5 = new EfficientMarkovModel(5);
        runModel(m5, st, size, 531);
        /*
        EfficientMarkovModel= new EfficientMarkovModel();
        runModel(mOne, st, size, 32);
        
        EfficientMarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, 28);
        
        EfficientMarkovModel mFour = new MarkovFour();
        runModel(mFour, st, size, 47);
        */

    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
    public void compareMethods(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        
        int seed = 42;
        int size = 1000;

        
        double startTime = System.nanoTime();
        MarkovModel m2 = new MarkovModel(2);
        runModel(m2, st, size, seed);
        double endTime = System.nanoTime();
        System.out.println("The running time of MarkovModel is " + (endTime-startTime)/1000000000.0 + " seconds \n\n" );
        
        startTime = System.nanoTime();
        EfficientMarkovModel em2 = new EfficientMarkovModel(2);
        runModel(em2, st, size, seed);
        endTime = System.nanoTime();
        System.out.println("The running time of Efficient MarkovModel is " + (endTime-startTime)/1000000000.0 + " seconds\n\n" );
    }
}
