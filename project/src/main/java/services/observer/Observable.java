package services.observer;

public interface Observable {
    /**
     * adds observer for observer
     * @param observer - given entity of type Observer
     */
    void addObserver(Observer observer);

    /**
     * notifies observers that an update is required
     */
    void notifyObservers() throws Exception;
}
