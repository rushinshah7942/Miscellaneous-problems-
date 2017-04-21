/*

(1) Mean of a very large array
(2) Mean of stream of numbers 

*/


/*
(1.1)

x[0]/N + x[1]/N + x[2]/N ...  x[9]/N 

so if you have an array of say N elements 
you can calculate the average by dividing each of the number by N and adding them up 

*/

//suppose array of length N is stored in arr 
double ans=0.0;
for(int i = 0; i<N ; i++){
     ans += arr[i] / N;
}
return ans;


// (1.2) Can use BigNumber or BigInteger data types

// Stream of Numbers and we need to compute
// (2) Let average of n numbers is x 	and (n+1)th number is m,
//  then, 
//        (n*x + m)
// avg =  ---------
//           n+1 
// 
// avg =   (n/n+1) * x  +  m/(n+1)

def average(numList):
  av = 0.0
  n = 1.0
  for num in numList:
    av = (n - 1) / n * av +  num / n
    n += 1.0
  return av


