package interface_service;

import java.rmi.RemoteException;

public interface HelloService {
	String sayHello(String name) throws RemoteException;
}
