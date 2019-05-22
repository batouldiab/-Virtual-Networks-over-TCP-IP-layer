import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IHost extends Remote{

	void migrate(Packet p) throws RemoteException;
	String register(IHost host) throws RemoteException;
}
