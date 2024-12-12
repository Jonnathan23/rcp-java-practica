package practica_rcp.main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

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
			System.out.println(
					"\n |----------------| |Servidor RMI iniciado y listo para aceptar llamadas. | |----------------|");

			Map<String, String> defualtUser = new HashMap<>();
			defualtUser.put("us_name", "Administrador");
			defualtUser.put("us_email", "admin@gmail.com");
			defualtUser.put("us_password", "root_123");
			
			String response = service.signUp(defualtUser);
			System.out.println("DefualtUser response:" + response);

		} catch (Exception e) {
			System.out.println("|----------------| | ERROR Servidor | |----------------| |");
			System.out.println(e);
		}
	}

}
