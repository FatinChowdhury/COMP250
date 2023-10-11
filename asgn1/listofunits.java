package Assignment1;

public class ListOfUnits {
	private String[] ListOfUnits; // gotta fix this later
	private int size;
	
	
/* A constructor that takes no inputs and creates an empty list of units. To do so, the
fields should be initialized to reflect the fact that at the moment there are no units in
the list. */
	public ListOfUnits() { 
		String[] ListOfUnits = new String[10]; // we can adjust this later
		size = 0; // everything is null
	}
	
/* A getSize() method that takes no inputs and returns the number of units that are part
of this list. */
	
	public int getSize() {
		return size;
	}

/*A getList() method which takes no inputs and returns an array containing all the units
that are part of this list. The units should appear in the order in which they have joined
the list. This array must contain as many elements as the number of units in the list,
and it should not contain any null elements. */
	
	public String[] getList() {
		// incomplete
		for (int i=0; i<=size; i++) {
			if (ListOfUnits[i] == null) {
				size++;
			}
		}
		
		return ListOfUnits;
	}
	
/* A getUnit() method which takes as input an int and returns the reference of the unit
at the specified position in this list. If the integer received is out of range, i.e. negative
or greater than or equal to the number of units in the list, then the method should throw
an IndexOutOfBoundsException. */
	
	public String getUnit(int i, String e) {
		// incomplete
		if (i >=0 && i <= size) {
			return ListOfUnits[i];
		}
		else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	
/* An addUnit() method which takes as input a Unit and does not return any value. The
method adds the Unit at the end of this list. Make sure to handle the case in which
there might not be enough space for this unit to join the list. In such case, you need to
make sure to create additional space. No unit should be rejected from joining the list.
If need be, this is a great place to create your own private method to help you with the
implementation. Warning: make sure that no unit is removed from the list as a result of
adding the new one. */
	
	public void addUnit(int Unit) {
		// incomplete
		
	}
	
/* An indexOf() method which takes as input a Unit and returns an int indicating the
position of the first occurrence of the specified element in this list. If no such unit exists,
then the method returns −1. Remember that when comparing reference types you do
not want to use ‘==’. */
	
	public int indexOf() {
		// incomplete
		return -1;
	}
	
/*A removeUnit() method which takes as input a Unit and returns a boolean. The
method removes the first occurrence of the specified element from the array of units of
this list. If no such unit exists, then the method returns false, otherwise, after removing
it, the method returns true. Note that this method removes a unit from the list if and
only if such unit is equal to the input received. For example, it is not possible to remove
an Aztec settler in place of a Sumerian settler. */
	
	public boolean removeUnit() {
		// incomplete
		return true;
	}
	
/* A getArmy() method that takes no inputs and returns an array of MilitaryUnits. The
array should contain all MilitaryUnits that are part of this list. Note that not all units
in the list are military units. A ListOfUnits may include also setters and workers. The
array returned by the method should not contain any null elements. */
	
	public getArmy() {
		// incomplete
		return;
	}
	
	
}
