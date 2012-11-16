/**
 * 16 nov. 2012
 * Seb
 * TP Chat 2
 */
/**
 * @author Seb
 * Fichier de test du client
 */
import java.io.*;
import java.net.InetAddress;
import java.rmi.*;
import java.util.ArrayList;

public class ClientTestChat {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		try {
			// Récupération d'un stub sur l'objet serveur.
			String URL = "//"+InetAddress.getLocalHost().getHostName()+":8080/mon_serveur";
			IChat obj = (IChat) Naming.lookup(URL);

			String idTest = "test";
			obj.connect(idTest);
			obj.who();
			obj.send(idTest, "Bonjour");
			obj.last(idTest);
			obj.bye(idTest);
				
			BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
			String cmd;				
			
			boolean test = true;
			while(test)
			{
				cmd = clavier.readLine();
				String[] cmdSplit;
				String id;

				cmdSplit = cmd.split(" ",3);
				if(cmdSplit[0].equals("connect"))
				{
					id = obj.connect(cmdSplit[1]);
					System.out.println("Vous êtes connecté sous le nom de "+id);
				} else if(cmdSplit[0].equals("send"))
				{
					obj.send(cmdSplit[1],cmdSplit[2]);
				} else if(cmdSplit[0].equals("last"))
				{
					ArrayList<String> msgs = obj.last(cmdSplit[1]);
					for(String s : msgs)
					{
						System.out.println(s);
					}
				} else if(cmdSplit[0].equals("who"))
				{
					System.out.println(obj.who());
				} else if(cmdSplit[0].equals("bye"))
				{
					obj.bye(cmdSplit[1]);
					System.out.println(cmdSplit[1]+"Deconnecté");
				} else if(cmdSplit[0].equals("exit"))
				{
					test = false;
				} else {
					System.out.println("Commande invalide : "+cmdSplit[0]);
				}
			}
			System.out.println("Sortie de boucle");
			
			
		} catch (Exception exc)
		{
			exc.printStackTrace();
		}



	}

}
