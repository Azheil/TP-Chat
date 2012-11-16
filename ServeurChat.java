/**
 * 16 nov. 2012
 * Seb
 * TP Chat 2
 */
/**
 * @author Seb
 */
import java.util.ArrayList;
import java.io.IOException;
import java.net.InetAddress;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServeurChat extends UnicastRemoteObject
implements IChat 
{
	/**
	 * Num�ro de version du serveur
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Liste des utilisateurs connect�s
	 */
	ArrayList<User> users;
	
	/**
	 * Contructeur du serveur
	 * @throws java.rmi.RemoteException 
	 */
	public ServeurChat() throws java.rmi.RemoteException
	{
		users = new ArrayList<User>();
	}
	
	/**
	 * M�thode pour trouver l'index d'un utilisateur si il est connect�
	 * @param id	Nom de l'utilisateur 
	 * @return l'index de l'utilisateur ou -1 si il n'est pas connect�
	 */
	public int find(String id)
	{
		int index = -1;
		int size = users.size();
		for (int i=0; i<size; i++)
		{
			if (users.get(i).id.equals(id)) index = i;
		}
		return index;
	}

	/**
	 * M�thode de connexion d'un utilisateur
	 * @param id	Nom de l'utilisateur 
	 * @return le nom qui a �t� attribu� � l'utilisateur ou null si le nom est d�j� utilis�
	 * @throws java.rmi.RemoteException 
	 */
	public String connect(String id) throws java.rmi.RemoteException
	{
		int index = find(id);
		if (index == -1)
		{
			users.add(new User(id));
		} else {
			System.out.println(id+" est de nouveau connect�");
			return null;
		}
		System.out.println("Nouvel utilisateur "+id);
		return id;
	}
	
	/**
	 * M�thode d'envoi d'un message
	 * @param id	Nom de l'expediteur 
	 * @param msg	Message � envoyer 
	 * @throws java.rmi.RemoteException 
	 */
	public void send(String id, String msg) throws java.rmi.RemoteException
	{
		for(User e : users)
		{
			e.add(id, msg);
		}
	}
	
	/**
	 * M�thode de d�connexion d'un utilisateur
	 * @param id	Nom de l'utilisateur 
	 * @throws java.rmi.RemoteException 
	 */
	public void bye(String id) throws java.rmi.RemoteException
	{
		int index = find(id);
		if (index!=-1)
		{
			users.remove(index);
		}
	}
	
	/**
	 * M�thode pour r�cup�rer la liste des utilisateurs
	 * @return la liste des utilisateurs
	 * @throws java.rmi.RemoteException 
	 */
	public String who() throws java.rmi.RemoteException
	{
		String list = "";
		for(User e : users)
		{
			if (list=="")
			{
				list = e.id;
			} else {
				list += ", "+e.id;
			}
		}
		return(list);
	}
	
	/**
	 * M�thode de r�cup�ration des derniers messages non lus d'un utilisateur
	 * @param id	Nom de l'utilisateur 
	 * @return la liste des messages sous forme d'ArrayList
	 * @throws java.rmi.RemoteException 
	 */
	public ArrayList<String> last(String id) throws java.rmi.RemoteException
	{
		int index = find(id);
		if (index!=-1)
		{
			User usr = users.get(index);
			return usr.last();
			
		} else {
			ArrayList<String> msgs = new ArrayList<String>();
			msgs.add("Vous n'etes pas connect�");
			return msgs;
		}
	}
	
	
	/**
	 * M�thode Main
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String args[]) throws IOException {
		int port; String URL;
		try { // transformation d �une cha�ne de caract�res en entier
			Integer I = new Integer(8080); port = I.intValue();
		} catch (Exception ex) {
			System.out.println(" Please enter: Server <port>"); return;
		}
		try {
			// Cr�ation du serveur de nom - rmiregistry
			Registry registry = LocateRegistry.createRegistry(port);
			// Cr�ation d �une instance de l�objet serveur
			IChat obj = new ServeurChat();
			// Calcul de l�URL du serveur
			URL = "//"+InetAddress.getLocalHost().getHostName()+":"+
			port+"/mon_serveur";
			Naming.rebind(URL, obj);
		} catch (Exception exc)
		{ 
			exc.printStackTrace();
		}
	}
}
