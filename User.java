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
	 * Red�finition de la m�thode equals
	 * @param usr	User � comparer avec this 
	 * @return true(vrai) si les nom des users sont �gaux false(faux) sinon
	 */
	public boolean equals(User usr)
	{
		return (this.id.equals(usr.id));
	}
	
	/**
	 * M�thode d'ajout d'un nouveau message non lu
	 * @param id	Nom de l'expediteur du message 
	 * @param message	Nouveau message 
	 */
	public void add(String id, String message)
	{
		messages.add(id+" > "+message);
	}
	
	/**
	 * M�thode de r�cup�ration des messages non lus
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
