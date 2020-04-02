import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Communicator {
    public static void main(String[] args) {

    }

    Socket sender=null;
    Socket listener=null;
    ServerSocket server=null;
    DataInputStream input=null;
    DataOutputStream output=null;
    Scanner skaner=null;


    Communicator(String IP, int port)
    {
        try {
            server = new ServerSocket(port);
            listener=server.accept();

            sender=new Socket(IP,port);
            input=new DataInputStream(listener.getInputStream());
            output=new DataOutputStream(sender.getOutputStream());
            System.out.println("Machines are connected");
        }catch(Exception e)
        {
            System.out.println(e);
        }
        skaner=new Scanner(System.in);
        try{
            String line="";
            while(!line.equals("Over"))
            {
                line=input.readUTF();
                System.out.println(line);

                line=skaner.nextLine();
                output.writeUTF(line);
                output.flush();
            }
        }catch (Exception e)
        {
            System.out.println(e);
        }
        try{
            input.close();
            output.close();
            server.close();
            sender.close();
            listener.close();
        }catch (Exception e)
        {
            System.out.println(e);
        }

    }

}
