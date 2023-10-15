package Assignment1;

public class ListOfUnits {
	private Unit[] ListOfUnits;
	private int size;
	
	public ListOfUnits() { 
		ListOfUnits = new Unit[10];
		size = 0;
	}
	
	public int getSize() {
		return size;
	}

	public Unit[] getList() {
		ListOfUnits = new Unit[size];
		for (int i=0; i<=size; i++) {
			if (ListOfUnits[i] == null) {
				size++;
			}
		}
		
		return ListOfUnits;
	}
	
	
	public Unit getUnit(int i) {
		// incomplete
		if (i >=0 && i < size) {
			return ListOfUnits[i];
		}
		else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	public void addUnit(Unit Unit) {
		if (Unit == null) {
			throw new IllegalArgumentException();
		}
		
		else if (ListOfUnits.length == size) {
			fixList();
		}
			ListOfUnits[size] = Unit;
			size++;
	}
	
	// creating my own private method
	private void fixList() {
		Unit[] bigger = new Unit[ListOfUnits.length*2];
		for(int position=0; position < size; position++) {
			bigger[position] = ListOfUnits[position];
		}
		ListOfUnits = bigger; 
	}
	
	public int indexOf(Unit Unit) {
		if (size == 0)
			throw new IllegalArgumentException();
		for (int position = 0; position < size; position++) { 
			if (ListOfUnits[position] == null) {
				throw new NullPointerException();
			}
			else if (ListOfUnits[position].equals(Unit)) { // i love ed discussions lol
				return position;
			}
		}
		return -1;
	}
	
	public boolean removeUnit(Unit Unit) {
		if (size == 0)
			throw new IllegalArgumentException();
		
		for(int position = 0; position < size; position++) {
			if (ListOfUnits[position] == null) {
				throw new NullPointerException();
			}
			
			else if (ListOfUnits[position].equals(Unit)) {
				
				for (int positionShifted = position; positionShifted < size - 1;
						positionShifted++) {
					
					ListOfUnits[positionShifted] = ListOfUnits[positionShifted + 1];
				}
				ListOfUnits[size - 1] = null;
				size--;
				return true;
			}
		}
		return false;
	}
	
	public Unit[] getArmy() {
		int sizeMilitary = 0;
		
		for (int position = 0; position < size; position++) {
			if (ListOfUnits[position] != null) {
				sizeMilitary++;
			}
		}
		
		Unit[] MilitaryUnits = new Unit[sizeMilitary];
		int positionMilitary = 0;
		
		for (int position = 0; position < size; position++) {
			
			if (ListOfUnits[position] != null) {
				MilitaryUnits[positionMilitary] = ListOfUnits[position];
				positionMilitary++;
			}
		}
		return MilitaryUnits;
	}
	
	
}
