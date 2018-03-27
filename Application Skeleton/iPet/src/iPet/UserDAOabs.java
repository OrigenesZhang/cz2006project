package iPet;
import java.util.ArrayList;

public abstract class UserDAOabs implements Subject{
	static ArrayList<Observer> observers = null; 
	
	public UserDAOabs() {
		// register subscribers upon init
	}
	
	public void register(Observer newObserver) {
		observers.add(newObserver);
	}

	public void unregister(Observer deleteObserver) {
		
        // Get the index of the observer to delete
        int observerIndex = observers.indexOf(deleteObserver);
        // Print out message (Have to increment index to match)
        System.out.println("Observer " + (observerIndex+1) + " deleted");
        // Removes observer from the ArrayList
        observers.remove(observerIndex);
	}
	
	// Notify subscribers that new updates are available
	public void notifyAllObservers() {
		for(Observer observer : observers) {
			observer.update();
		}
	}
	
	abstract User getCurrentUser();
	abstract void getNewUser(String username);
	abstract void updateUser(User updateUser);
	
	/*add admin priviledge as well */
	abstract ArrayList<User> getAllUsers();
	abstract void addUser(User newuser);
	abstract void deleteUser(String username);
}
