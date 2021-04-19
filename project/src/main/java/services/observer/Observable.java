package services.observer;

public interface Observable {
    void addObserver(Observer observer);
    void notifyObservers();
}
