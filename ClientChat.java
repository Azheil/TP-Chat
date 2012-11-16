/**
 * 16 nov. 2012
 * Seb
 * TP Chat 2
 */
/**
 * @author Seb
 */
import java.io.*;
import java.net.InetAddress;
import java.rmi.*;
import java.util.ArrayList;

public class ClientChat {

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
			String id = "";

			boolean test = true;
			while(test)
			{
				cmd = clavier.readLine();
				String[] cmdSplit;

				cmdSplit = cmd.split(" ",2);
				if(cmdSplit[0].equals("connect"))
				{
					id = cmdSplit[1];
					obj.connect(id);
					System.out.println("Vous êtes connecté sous le nom de "+id);
				} else if (!id.equals(""))
				{
					if(cmdSplit[0].equals("send"))
					{
						obj.send(id,cmdSplit[1]);
					} else if(cmdSplit[0].equals("last"))
					{
						ArrayList<String> msgs = obj.last(id);
						for(String s : msgs)
						{
							System.out.println(s);
						}
					} else if(cmdSplit[0].equals("who"))
					{
						System.out.println(obj.who());
					} else if(cmdSplit[0].equals("bye"))
					{
						obj.bye(id);
						System.out.println("Deconnecté");
					} else if(cmdSplit[0].equals("exit"))
					{
						test = false;
						System.out.println("Fermeture");
					} else {
						System.out.println("Commande invalide : "+cmdSplit[0]);
					}
				} else {
					System.out.println("Vous n'êtes pas connecté");
				}
			}
			


		} catch (Exception exc)
		{
			exc.printStackTrace();
		}



	}

}
