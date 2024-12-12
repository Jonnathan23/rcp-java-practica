package Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;

import Model.UserDaoMain;
import Model.UserModel;
import interface_service.HelloService;
import jakarta.inject.Inject;

public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {
	
	private String errorResponse = "Error al guardar";
	private String successResponse = "Success";
	
	private UserDaoMain userDao;
	
	public HelloServiceImpl() throws RemoteException {
	    super();
	    try {
	        InitialContext context = new InitialContext();
	        this.userDao = (UserDaoMain) context.lookup("java:module/UserDaoMain");
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RemoteException("Error inicializando el DAO", e);
	    }
	}


    @Override
    public String sayHello(String name) throws RemoteException {
        return "Hola, " + name + " bienvenido!";
    }
    
    @Override
    public String signUp(Map<String, String> userData) throws RemoteException {
        try {
            UserModel userEntity = new UserModel();
            userEntity.setUs_name(userData.get("us_name"));
            userEntity.setUs_email(userData.get("us_email"));
            userEntity.setUs_password(userData.get("us_password"));

            String response = userDao.createUser(userEntity);

            if (response == null) {
                return "Error al guardar";
            }

            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al guardar";
        }
    }

    @Override
    public List<Map<String, String>> listAllUsers() throws RemoteException {
        try {
            List<UserModel> users = userDao.listUsers();
            List<Map<String, String>> userList = new ArrayList<>();

            for (UserModel user : users) {
                Map<String, String> userData = new HashMap<>();
                userData.put("us_name", user.getUs_name());
                userData.put("us_email", user.getUs_email());
                userData.put("us_password", user.getUs_password());
                userList.add(userData);
            }

            return userList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
