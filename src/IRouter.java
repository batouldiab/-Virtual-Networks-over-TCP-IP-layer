import java.net.InetAddress;
import java.rmi.RemoteException;

public interface IRouter {
	public String register(IRouter router) throws RemoteException;

	public String hostConnect(InetAddress ip) throws RemoteException;
}
