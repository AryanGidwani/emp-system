
import java.util.*;


public class MyHashTable {
	// ATTRIBUTES
        private MyHashTable HT;
	// buckets is an array of ArrayList.  Each item in an ArrayList is an EmployeeInfo object.
	public ArrayList<EmployeeInfo>[] buckets;
	public int numInHashTable;

	
	// CONSTRUCTOR

	public MyHashTable(int howManyBuckets) {
		// Construct the hash table (open hashing/closed addressing) as an array of howManyBuckets ArrayLists.

		// Instantiate an array to have an ArrayList as each element of the array.
		buckets = new ArrayList[howManyBuckets];

		// For each element in the array, instantiate its ArrayList.
		for (int i = 0; i < howManyBuckets; i++) {
			buckets[i] = new ArrayList<EmployeeInfo>();  // Instantiate the ArrayList for bucket i.
		}
		
		numInHashTable = 0;
	}

	
	// METHODS
	
	public int getNumInHashTable() {
		return numInHashTable;
	}

	
	
	public int calcBucket(int keyValue) {
		// Returns the bucket number as the integer keyValue modulo the number of buckets for the hash table.
		return(keyValue % buckets.length);
	}
	

	
	public boolean addEmployee(EmployeeInfo theEmployee) {

		// Add the employee to the hash table.  Return true if employee is added successfully,
		// return false otherwise.
		
		if (theEmployee == null) {
			return(false);
		}
		else {
			int targetBucket = calcBucket(theEmployee.getEmpNum());
			// Add the employee to the ArrayList for the targetBucket.
			boolean addStatus = buckets[targetBucket].add(theEmployee);
			numInHashTable++;
			return addStatus;
		}
		
	} // end AddEmployee
	
	public void displayContents() {
		
		System.out.println("\n\nDISPLAYING THE CONTENTS OF A HASH TABLE!");
		
		System.out.println("\n  This hash table contains " + numInHashTable + " entries.");
		
		// Print the employee numbers for the employees stored in each bucket's ArrayList,
		// starting with bucket 0, then bucket 1, and so on.
		
		for (int i = 0; i < buckets.length; i++) {

		    // For the current bucket, print out the emp num for each item
                    // in its ArrayList.

                    System.out.println("\n  Examining the ArrayList for bucket " + i);
                    int listSize = buckets[i].size();
                    if (listSize == 0) {
                    	System.out.println("    Nothing in its ArrayList!");
                    }
                    else {
                        for (int j = 0; j < listSize; j++) {
                           int theEmpNum = buckets[i].get(j).getEmpNum();
                           System.out.println("    Employee " + theEmpNum);
                        }
                    }

                }

	} // end displayContents
        
        public EmployeeInfo searchContents(int theEmpNum) {
            // search for employee in the table
            // most of the code should be the same as displayContents()
            int bucket = calcBucket(theEmpNum); //find the Employee's bucket
            
            for (int i = 0; i < buckets[bucket].size(); i++){
                EmployeeInfo theEmployee = buckets[bucket].get(i); //look for the emp in the bucket
                if (theEmpNum == theEmployee.empNum){
                    return theEmployee;
                }
            }
            
            return null; //no employee was found
            
        }
        
        
        public EmployeeInfo getEmployee(int theEmpNum) {
            int target = calcBucket(theEmpNum);
            EmployeeInfo theEmployee;
            for (int i = 0; i < buckets[target].size(); i++) {
                theEmployee = buckets[target].get(i);
                if (theEmpNum == theEmployee.empNum) {
                    return theEmployee;
                }
            }
            // Get (but don't remove) this employee from the hash table and return their ref val to the caller.
            // If the employee is not in the hash table, return null to the caller.
            return null;
        }
        
        public EmployeeInfo removeEmployee(int employeeNum) {
            int currentBucket = calcBucket(employeeNum); // stores the bucket containing the employee in a variable
            for (int i = 0; i < buckets[currentBucket].size(); i++) {
                EmployeeInfo foundEmployee = buckets[currentBucket].get(i);
                if (foundEmployee.empNum == employeeNum) {
                    numInHashTable--;
                    buckets[currentBucket].remove(foundEmployee);
                    return foundEmployee;
                }
            }
            return null;
        }
        
        // edit employee method that first removes the employee, and then should *add* a new one
        public EmployeeInfo editEmployee(int employeeNum) {
            int currentBucket = calcBucket(employeeNum);
            for (int i = 0; i < buckets[currentBucket].size(); i++) {
                EmployeeInfo editedEmployee = buckets[currentBucket].get(i);
                if (editedEmployee.empNum == employeeNum) {
                    buckets[currentBucket].remove(editedEmployee);
                    return editedEmployee;
                }
            }
            return null;
        }

	
} // end MyHashTable
