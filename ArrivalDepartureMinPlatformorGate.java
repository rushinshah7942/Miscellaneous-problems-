/*
You are given arrival and departure time of trains reaching to a particular station. You need to find minimum number of platforms required to accommodate the trains at any point of time.
For example: 

arrival[] = {1:00, 1:40, 1:50, 2:00, 2:15, 4:00} 
departure[] = {1:10, 3:00, 2:20, 2:30, 3:15, 6:00}

No. of platforms required in above scenario = 4

*/

/*
We will use logic very much similar to merge sort.

Compare current element in arrival and departure array and pick smaller one among both.
- If element is pick up from arrival array then increment platform_needed.
- If element is pick up from departure array then decrement platform_needed.

While performing above steps, we need track count of maximum value reached for platform_needed.

In the end, we will return maximum value reached for platform_needed.

    arr[]  = {9:00,  9:40, 9:50,  11:00, 15:00, 18:00}
    dep[]  = {9:10, 12:00, 11:20, 11:30, 19:00, 20:00}

All events sorted by time.

Total platforms at any time can be obtained by subtracting total 
departures from total arrivals by that time.

 Time     Event Type     Total Platforms Needed at this Time                               
 9:00       Arrival                  1
 9:10       Departure                0
 9:40       Arrival                  1
 9:50       Arrival                  2
 11:00      Arrival                  3 
 11:20      Departure                2
 11:30      Departure                1
 12:00      Departure                0
 15:00      Arrival                  1
 18:00      Arrival                  2 
 19:00      Departure                1
 20:00      Departure                0

Minimum Platforms needed on railway station = Maximum platforms 
                                              needed at any time 
                                           = 3  

Time: O(n logn)

*/


public int findPlatformsRequiredForStation(int arr[], int dep[], int n)
{
	 int platform_needed = 0, maxPlatforms = 0;
	 int i = 0, j = 0;
 
	 // Similar to merge in merge sort
	 while (i < n && j < n) {
		 if (arr[i] < dep[j]) {
			platform_needed++;
			i++;
			if (platform_needed > maxPlatforms) 
				maxPlatforms = platform_needed;
		 }
		 else {
			platform_needed--;
			j++;
		 }
	 }
	 
	 return maxPlatforms;
 }