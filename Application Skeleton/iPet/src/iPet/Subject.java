package iPet;

// this class handles  adding, deleting and updating all observers
public interface Subject {
	
	public void register(Observer o);
	public void unregister(Observer o);
	public void notifyAllObservers();
}
