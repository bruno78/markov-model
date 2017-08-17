package test;

import caseWithWord.MarkovRunner;
import efficientMarkovModelWord.MarkovRunnerWithInterface;

public class Main {

	public static void main(String[] args) {
		
		// Prints a Markov Model of order 5:
		// MarkovRunnerWithInterface mk = new MarkovRunnerWithInterface();
		// mk.runMarkov();
		
		// The case below is based on Confucius text, using 2 consecutive
		// words to predict the next (N = 2), word length set to 120 and seed 65:
		MarkovRunner cw = new MarkovRunner();
		cw.runMarkov();

	}

}
