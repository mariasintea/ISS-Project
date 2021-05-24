package services.observer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Observer extends Remote {
    /**
     * update function
     */
    void update() throws RemoteException;
}
