/*

Find number of Employees Under every Employee
Given a dictionary that contains mapping of employee and his manager as a number of (employee, manager) pairs like below.

{ "A", "C" },
{ "B", "C" },
{ "C", "F" },
{ "D", "E" },
{ "E", "F" },
{ "F", "F" } 

In this example C is manager of A, 
C is also manager of B, F is manager 
of C and so on.

Output should be a Dictionary that contains following.

A - 0  
B - 0
C - 2
D - 0
E - 1
F - 5 

*/

/*

SOLUTION
---------------

1. Create a reverse map with Manager->DirectReportingEmployee 
    combination. Off-course employee will be multiple so Value 
    in Map is List of Strings.
        "C" --> "A", "B",
        "E" --> "D" 
        "F" --> "C", "E", "F"

 
2. Now use the given employee-manager map to iterate  and at 
   the same time use newly reverse map to find the count of 
   employees under manager.
   
   Let the map created in step 2 be 'mngrEmpMap' 
   Do following for every employee 'emp'.
     a) If 'emp' is not present in 'mngrEmpMap' 
          Count under 'emp' is 0 [Nobody reports to 'emp']
     b) If 'emp' is present in 'mngrEmpMap' 
          Use the list of direct reports from map 'mngrEmpMap'
          and recursively calculate number of total employees
          under 'emp'. 


A trick in step 2.b is to use memorization(Dynamic programming) while finding number of employees under a manager so that we don’t need to find number of employees again for any of the employees. 
		  
*/

// Java program to find number of persons under every employee
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class NumberEmployeeUnderManager
{
    // A hashmap to store result. It stores count of employees
    // under every employee, the count may by 0 also
    static Map<String,Integer> result =
                             new HashMap<String, Integer>();
 
    // Driver function
    public static void main(String[] args){
        Map<String, String> dataSet = new HashMap<String, String>();
        dataSet.put("A", "C");
        dataSet.put("B", "C");
        dataSet.put("C", "F");
        dataSet.put("D", "E");
        dataSet.put("E", "F");
        dataSet.put("F", "F");
 
        populateResult(dataSet);

        System.out.println("result = " + result);
    }
 
    private static void populateResult(Map<String, String> dataSet){
		
        Map<String, List<String>> mngrEmpMap = new HashMap<String, List<String>>();
 
        // To fill mngrEmpMap, iterate through the given map
        for (Map.Entry<String,String> entry: dataSet.entrySet()){
            
			String emp = entry.getKey();
            String mngr = entry.getValue();

            if (!emp.equals(mngr)) { // excluding emp-emp entry            
	
                List<String> directReportList = mngrEmpMap.get(mngr);
 
                if (directReportList == null)
                    directReportList = new ArrayList<String>();
 
                directReportList.add(emp);
                 
                mngrEmpMap.put(mngr, directReportList);
            }
        }
 
        // Now use manager-Emp map built above to populate result 
        // with use of populateResultUtil()
 
        // note- we are iterating over original emp-manager map and
        // will use mngr-emp map in helper to get the count
        for (String mngr: dataSet.keySet())
            populateResultUtil(mngr, mngrEmpMap);
    
	}
 
    // This is a recursive function to fill count for 'mgr' using
    // mngrEmpMap.  This function uses memoization to avoid re-
    // computations of subproblems.
    private static int populateResultUtil(String mngr, Map<String, List<String>> mngrEmpMap)
    {
        int count = 0;
 
        // means employee is not a manager of any other employee
        if (!mngrEmpMap.containsKey(mngr)){
            result.put(mngr, 0); // we store count in final result
            return 0; // we need to return count , as this is the base condition
        }
 
        // this employee count has already been done by this
        // method, so avoid re-computation
		// memoization
        else if (result.containsKey(mngr))
            count = result.get(mngr);
 
        else{
            List<String> directReportEmpList = mngrEmpMap.get(mngr);
            count = directReportEmpList.size();
			
			// for each employee, we recursively find their employees
            for (String directReportEmp: directReportEmpList)
               count +=  populateResultUtil(directReportEmp, mngrEmpMap);
 
            result.put(mngr, count);
        }
        return count;
    }
}