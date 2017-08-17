package caseWithWord;

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){
        markov.setTraining(text);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    public void runModel(IMarkovModel markov, String text, int size, int seed){
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        // MarkovWordTwo markovWord = new MarkovWordTwo();
        // runModel(markovWord, st, 200);
        //MarkovWord markovWord = new MarkovWord(3);
        //markovWord.setRandom(621);
        EfficientMarkovWord markovWord = new EfficientMarkovWord(2);
        markovWord.setRandom(65);
        runModel(markovWord, st, 120);
        // MarkovWordOne markov1 = new MarkovWordOne();
        // markov1.setRandom(139);
        // runModel(markov1, st, 120);
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

    private void testHashMap(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        EfficientMarkovWord mkv = new EfficientMarkovWord(2);
        mkv.setRandom(42);
        runModel(mkv, st, 50);
    }
}
