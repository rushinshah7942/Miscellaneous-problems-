/*
2 teams play a match of Volleyball

During the course of the game, each team gets points, and thus increases its score by 1.

The initial score is 0 for both teams.

The game ends when:

One of the teams gets 25 points and another team has < 24 points ( strictly less than 24).
If the score ties at 24:24, the teams continue to play until the absolute difference between the scores is 2.
Given the final score of a game in the format A:*B* i.e., the first team has scored A points and the second has scored B points, can you print the number of different sequences of getting points by teams that leads to this final score?

i.e. combinations
*/


Define NumScores(A,B):

If A=0 and B=0, return 1
Else If A<0 or B<0, return 0
Else If A<25 and B<25, return NumScores(A-1,B) + NumScores(A,B-1)
Else If |A-B|>1, return 0
Else return NumScores(A-1,B) + NumScores(A,B-1)