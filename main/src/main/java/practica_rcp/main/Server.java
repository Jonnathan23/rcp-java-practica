package practica_rcp.main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


import Service.HelloServiceImpl;
import interface_service.HelloService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Singleton
@Startup
public class Server {
	
	@PostConstruct
	public void init() {
		try {
			HelloService service = new HelloServiceImpl();
			
			Registry registry = LocateRegistry.createRegistry(8081);
			
			 registry.rebind("Registro", service);
			 System.out.println("\n |----------------| |Servidor RMI iniciado y listo para aceptar llamadas. | |----------------|");
			 
		} catch (Exception e) {
			System.out.println("|----------------| | ERROR Servidor | |----------------| |");
			System.out.println(e);
		}
	}
	
}
