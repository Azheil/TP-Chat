/**
 * 16 nov. 2012
 * Seb
 * TP Chat 2
 */
/**
 * @author Seb
 */
import java.util.ArrayList;;

public class User
{
	/**
	 * Nom de l'utilisateur
	 */
	String id;
	
	/**
	 * Liste des messages non lus de cet utilisateur
	 */
	ArrayList<String> messages;
	
	/**
	 * Constructeur de User
	 * @param nom	Nom (ou id) de l'utilisateur 
	 */
	public User(String nom)
	{
		id = nom;
		messages = new ArrayList<String>();
	}
	
	/**
	 * Redéfinition de la méthode equals
	 * @param usr	User à comparer avec this 
	 * @return true(vrai) si les nom des users sont égaux false(faux) sinon
	 */
	public boolean equals(User usr)
	{
		return (this.id.equals(usr.id));
	}
	
	/**
	 * Méthode d'ajout d'un nouveau message non lu
	 * @param id	Nom de l'expediteur du message 
	 * @param message	Nouveau message 
	 */
	public void add(String id, String message)
	{
		messages.add(id+" > "+message);
	}
	
	/**
	 * Méthode de récupération des messages non lus
	 * @return la liste des messages non lus
	 */
	public ArrayList<String> last()
	{
		ArrayList<String> msg = new ArrayList<String>();
		for (String s : messages)
		{
			msg.add(s);
		}
		messages.clear();
		return msg;
	}

}
