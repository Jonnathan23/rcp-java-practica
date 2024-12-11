package practica_rcp.main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Service.HelloServiceImpl;
import interface_service.HelloService;

public class Server {

	public static void main(String[] args) {
	
		try {
			HelloService service = new HelloServiceImpl();
			
			Registry registry = LocateRegistry.createRegistry(1099);
			
			 registry.rebind("HelloService", service);
			 
			 System.out.println("Servidor RMI iniciado y listo para aceptar llamadas.");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}	
}
