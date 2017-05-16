package socket;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import org.json.simple.JSONObject;

/**
 * Created by fabianrieger on 25.04.17.
 */
public class ClientSocket {
    private Socket serverSocket = null;
    private String host = null;
    private int port;
    private PrintWriter out;

     public ClientSocket(){
         this.host = "localhost";
         this.port = 47096;
         this.init();
     }

     private void init() {
         try
         {
             this.serverSocket = new Socket (this.host, this.port);
             this.out = new PrintWriter(this.serverSocket.getOutputStream(), true);
             Thread serverThread = new Thread(new ServerListener(serverSocket));
             serverThread.start();
         }
         catch (UnknownHostException ex)
         {
             System.out.println("UnknownHostException bei Verbindung zu Host bei Host: "+ this.host + " und Port: " + this.port + " Fehler:" + ex.getMessage());
             System.exit(-1);
         }
         catch (IOException ex)
         {
             System.out.println("IOException bei Verbindung zu Host bei Host: "+ this.host + " und Port: " + this.port + " Fehler:" + ex.getMessage());
             System.exit(-1);
         }
     }

    public void send(JSONObject json){
        this.out.println(json.toString());
        this.out.flush();
    }

    public void close(){
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            System.out.println("Konnte connection nicht schließen");
            e.printStackTrace();
        }
    }
}
