import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.Scanner;
public class Host extends UnicastRemoteObject implements IHost{
	
	static String registryReply="";
	static InetAddress selfIP;
	static InetAddress connectedRouter;
	
	protected Host() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String register(IHost host) throws RemoteException {


		Random r = new Random();
		String ip=r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);
		try {
			InetAddress selfIP = InetAddress.getByName(ip);
			return "success";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	public static String connectToRouter() {
		Scanner input=new Scanner(System.in);
		String routerip="";
		System.out.println("Enter the router ip:\n"); //this is usually done using hardware connections
		routerip=input.next();
		try {
			connectedRouter=InetAddress.getByName(routerip);
			return "success";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static void migrate(Packet p) {
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
			
			//connecting to available router
			String s=connectToRouter();
			while (!s.equals("success")){
				Thread.sleep(1000);
				s= connectToRouter();
			}
			System.out.println("Router Succesfully connected!\n");
			
			Scanner input=new Scanner(System.in);
			String m="";
			InetAddress dest;
			//get new request
			while(true)
			{
				System.out.println("Enter the message:\n");
				m=input.next();
				System.out.println("Enter the IP of the destination host:\n");
				dest=InetAddress.getByName(input.next());
				Packet p=new Packet(selfIP,dest,m,connectedRouter);
				migrate(p);

			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	



	
}
