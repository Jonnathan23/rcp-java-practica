package Model;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class UserDaoMain {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * @description create new user
	 * @param user
	 */
	public String createUser(UserModel user) {
		try {
			em.persist(user);
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
	        e.printStackTrace();
	        return null;
		}		
	}
	
	/**
	 * @decription List all users
	 * @return
	 */
	public List<UserModel> listUsers(){
		String jpql = "SELECT u FROM UserModel u";
		//***
	    System.out.println("Executing JPQL query: " + jpql); // Imprime el query en consola
		Query query = em.createQuery(jpql, UserModel.class);
		
		return query.getResultList();
	}
}
