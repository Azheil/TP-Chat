/**
 * 16 nov. 2012
 * Seb
 * TP Chat 2
 */
/**
 * @author Seb
 */
import java.util.ArrayList;

public interface IChat extends java.rmi.Remote {
	public String connect(String s) throws java.rmi.RemoteException;
	public void send(String id, String s) throws java.rmi.RemoteException;
	public void bye(String id) throws java.rmi.RemoteException;
	public String who() throws java.rmi.RemoteException;
	public ArrayList<String> last(String id) throws java.rmi.RemoteException;
}
