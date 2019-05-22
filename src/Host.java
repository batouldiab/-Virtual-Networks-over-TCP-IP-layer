import java.io.IOException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
public class Host extends UnicastRemoteObject implements IHost{
	
	static String registryReply="";

	protected Host() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String register(IHost host) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void migrate(Packet p) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	@SuppressWarnings({ "deprecation", "resource" })
	public static void main(String args[]) {
		System.setSecurityManager(new RMISecurityManager());
		try {
			//registration
			Host h=new Host();
			registryReply= h.register(h);
			while (!registryReply.equals("success")){
				Thread.sleep(1000);
				registryReply= h.register(h);
			}
			System.out.println("Host Succesfully registered!\n");
			
			Scanner input=new Scanner(System.in);
			String r="";
			//get new request
			while(true)
			{
				System.out.println("Enter the request:\n");
				r=input.next();
				sendRequest();

			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	private static void sendRequest() {
		// TODO Auto-generated method stub
		
	}



	
}
