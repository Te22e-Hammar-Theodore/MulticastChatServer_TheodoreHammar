import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastChatServer {
    public static void main(String args[]) throws Exception {

        // Default port number we are going to use
        int portnumber = 5001; // Uppdaterat till ett passande portnummer
        if (args.length >= 1) {
            portnumber = Integer.parseInt(args[0]);
        }

        // Create a MulticastSocket
        MulticastSocket serverMulticastSocket = new MulticastSocket(portnumber);
        System.out.println("MulticastSocket is created at port " + portnumber);

        // Determine the IP address of a host, given the host name
        InetAddress group = InetAddress.getByName("225.4.5.6");

        // Join the multicast group
        serverMulticastSocket.joinGroup(group);
        System.out.println("joinGroup method is called...");

        boolean infinite = true;

        // Continually receive data and print them
        while (infinite) {
            byte buf[] = new byte[1024];
            DatagramPacket data = new DatagramPacket(buf, buf.length);
            serverMulticastSocket.receive(data);
            String msg = new String(data.getData()).trim();
            System.out.println("Message received from client = " + msg);
        }

        // Unreachable in current loop, but included for completeness
        serverMulticastSocket.close();
    }
}
