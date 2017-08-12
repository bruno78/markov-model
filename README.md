# Markov Model - _A Study on predicting text_

This project is the early stage of analysis using the Markov Model. According to [Wikipedia](https://en.wikipedia.org/wiki/Markov_model):

> _In probability theory, a Markov model is a stochastic model used to model randomly changing systems where it is assumed that future states depend only on the current state not on the events that occurred before it (that is, it assumes the Markov property)._

In this case we are using the Markov model to predict the next word based on one or on n number of previous words using a training text as reference.

## Test Cases

## Random case

The process starts by selecting a random word or a sequence of n words from the training text, then getting its next word and adding to a string, limited by a given length of words. Then it takes the previous word or n words (just added to the string) and find the next word and so on, creating a chain of words.

### Process

### Using characters

First, it started by coding with characters. Beginning with case 0 which just select random characters. The use of 1 character that would be used to predict the next and this 'next' will be used to predict next and so forth. Next steps were done with 2, 4, and then a model that can be done with n numbers of consecutive characters determined by user input.

The examples below are based on Confucius text. The result is based on 500 random characters from the text, and can be reproduceable with their respective seeds:

*Case 0 character - Markov Zero @ seed: 1024:*
<div style="text-align:center"><img src="https://cdn.rawgit.com/bruno78/markov-model/a1259eb6/images/MarkovCharZero.png" alt="Markov Model Char 0" /></div>

*Case 1 character - Markov One @ seed: 365:*
<div style="text-align:center"><img src="https://cdn.rawgit.com/bruno78/markov-model/a1259eb6/images/MarkovCharOne.png" alt="Markov Model Char 1" /></div>

*Case 4 characters - Markov Four @ seed: 715*
<div style="text-align:center"><img src="https://cdn.rawgit.com/bruno78/markov-model/a1259eb6/images/MarkovCharFour.png" alt="Markov Model Char 4" /></div>

*Case 7 characters - Markov Model (N) @ seed: 953:*
<div style="text-align:center"><img src="https://cdn.rawgit.com/bruno78/markov-model/a1259eb6/images/MarkovCharModel7.png" alt="Markov Model Char 7" /></div>

### Using words

Once using characters passed the test, the next step was to use words. The principle is basic the same, but instead of picking characters from the text, the case is word.

The case below is based on Confucius text, with word length set to 120 and seed 65:

<div style="text-align:center"><img src="https://cdn.rawgit.com/bruno78/markov-model/a1259eb6/images/MarkovWordGramO2.png" alt="Markov Model Order of 2 words" /></div>

### Problems

The program is a bit inneficient since it stumbles on the possibility to check the same sequence of character(s) or word(s) multiple times. The best solution for this case is to map these occurrences to a HashMap.

Comparing efficiency before and after mapping words using Confucius text with a model order of 2, with text length set to 1000 @ seed 42.:

Before:
<div><img src="https://cdn.rawgit.com/bruno78/markov-model/a1259eb6/images/MarkovModelCompare-Orderof2.png" alt="Markov Model Order of 2 words" /></div>

After:
<div><img src="https://cdn.rawgit.com/bruno78/markov-model/a1259eb6/images/MarkovModelEFFCompare-Order2-2.png" alt="Markov Model Order of 2 words" /></div>

### Result structure (UML):

Once the cases were created and tested, they can be easily generalized and these generalizations can be put into a Interface and Abstract class. The final result is a Markov Model that can be extend either for using characters or words in the order of N.

<p align="center"><img src ="https://cdn.rawgit.com/bruno78/markov-model/a1259eb6/images/MarkovStructure.png" alt="Markov Model Structure" /></div>

## Probability Case

In the next case the word or words are mapped with its occurrences, then these occurrences are quantified and transformed into probabilities. The highest number of a occurrence, the highest is the probability of occur again, and instead choose the word or words solely by random, choose them by probability now.

(_ongoing study_)
