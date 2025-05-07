import java.net.*;

public class MulticastChatServer {
    public static void main(String args[]) throws Exception {

        int portnumber = 5001; // Valt portnummer
        if (args.length >= 1) {
            portnumber = Integer.parseInt(args[0]);
        }

        // Skapa MulticastSocket
        MulticastSocket serverMulticastSocket = new MulticastSocket(portnumber);
        System.out.println("MulticastSocket is created at port " + portnumber);

        // Bestäm IP-adress för multicast-grupp
        InetAddress group = InetAddress.getByName("225.4.5.6");

        // Gå med i gruppen
        serverMulticastSocket.joinGroup(group);
        System.out.println("joinGroup method is called...");

        boolean infinite = true;

        // Ta emot meddelanden kontinuerligt
        while (infinite) {
            byte buf[] = new byte[1024];
            DatagramPacket data = new DatagramPacket(buf, buf.length);
            serverMulticastSocket.receive(data);
            String msg = new String(data.getData()).trim();
            System.out.println("Message received from client = " + msg);
        }

        // Stäng socket (nåbar endast om loopen avslutas)
        // serverMulticastSocket.close();
    }
}
