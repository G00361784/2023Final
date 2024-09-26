package ie.atu.mountainappmanager;

import java.util.ArrayList;
import java.util.ListIterator;

public class Mountainmanager {

    ArrayList<Mountain> mountainList = new ArrayList<Mountain>();
    Mountain mountainA = new Mountain("j2345",12.34,15);
    Mountain mountainB = new Mountain("H6754",123.56,455);

    public void startlist(){
            mountainList.add(mountainA);
            mountainList.add(mountainB);
    }
   
    // Add mountain to list
    public void addMountainToList(String mountainIdAdd, double heightMetresAdd, int walkDurationMinutesAdd) {

        try {
            if (Mountain.isValid(mountainIdAdd, heightMetresAdd, walkDurationMinutesAdd) && !isOnList(mountainIdAdd)) {
                Mountain mountainNew = new Mountain(mountainIdAdd, heightMetresAdd, walkDurationMinutesAdd);
                mountainList.add(mountainNew);
                System.out.println("Added to arraylist");
            }

        } catch (Exception ex) {
            System.err.println();

        }

    }

    // Check if mountain with given ID is already on the list
    private boolean isOnList(String mountainId) {
        for (Mountain mountain : mountainList) {
            if (mountain.getMountainId().equals(mountainId)) {
                System.out.println("Mountain with ID " + mountainId + " is already on the list!");
                return true;
            }
        }
        return false;
    }

    // remove mountain form list

    public void deleteMountainFromList() {
        
        if (!mountainList.isEmpty()) {
            mountainList.remove(0);
            System.out.println("Item sucessfully removed");
        }   
    }

//find mountain with iterator
	public Mountain findMountainByIdWithIterator(String mountainId) {
		ListIterator<Mountain> mountainListIterator = mountainList.listIterator();
		while (mountainListIterator.hasNext()) {
			Mountain mountain = mountainListIterator.next();
			if (mountain.getMountainId().equals(mountainId)) {
				return mountain;
			}
		}
		return null;
	}
    //returns a value of interger for the length of the mountainList arrayList
    public int getMountainListLength(){
        return mountainList.size();
    }

}

 
