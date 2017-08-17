package RandomChar;

import edu.duke.*;

public class tester
{
    public void testGetFollows(){
        String myText = "this is a test yes this is a test.";
        MarkovOne markov = new MarkovOne();
        markov.setTraining(myText);
        System.out.println(markov.getFollows("t"));
        System.out.println(markov.getFollows("e"));
        System.out.println(markov.getFollows("es"));
        System.out.println(markov.getFollows("."));
        System.out.println(markov.getFollows("t."));
    }

    public void testGetFollowsWithAFile(){
        FileResource fr = new FileResource();
        String s = fr.asString().trim();
        MarkovOne markov = new MarkovOne();
        markov.setTraining(s);
        System.out.println(markov.getFollows("he").size());

    }
}
