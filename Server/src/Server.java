
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jrockit.jfr.VMJFR;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sachin
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Socket socket;
        try {
            // TODO code application logic here
            ServerSocket server = new ServerSocket(8082);
            while (true) {
                socket = server.accept();
                Thread t1 = new Thread(new ThreadClass(socket));
                t1.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
