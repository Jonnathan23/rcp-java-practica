package Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interface_service.HelloService;

public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {
	
	 public HelloServiceImpl() throws RemoteException {
	        super();
	 }

    @Override
    public String sayHello(String name) throws RemoteException {
        return "Hello, " + name + "!";
    }

}
