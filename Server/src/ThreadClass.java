
import java.sql.Statement;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sachin
 */
public class ThreadClass implements Runnable {

    Socket socket;
    Statement statement;
    ArrayList<Program> list;

    public ThreadClass(Socket socket) {
        this.socket = socket;
    }

    public ThreadClass() {

    }

    @Override
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@calvin.humber.ca:1521:grok", "ravinder", "oracle");
            Statement st = conn.createStatement();
            String choice = (String) oin.readObject();
            Program prog = null;
            if (choice.equals("Add Program")) {
                Program p = (Program) oin.readObject();
                int count = st.executeUpdate("INSERT INTO PROGRAMS VALUES('" + p.getId() + "','" + p.getName() + "'," + p.getSem() + "," + p.getMaxseats() + ")");
                System.out.println(count);
                JOptionPane.showMessageDialog(null, "Program Added Sucessfully");
            } else {

                list = new ArrayList<Program>();
                ResultSet rs = st.executeQuery("SELECT * FROM PROGRAMS");
                while (rs.next()) {
                    prog = new Program(rs.getString(1), rs.getString(2), Integer.parseInt(rs.getString(3)), Integer.parseInt(rs.getString(4)));
                    list.add(prog);
                }
                out.writeObject(list);
                out.close();
                oin.close();

                st.close();
                socket.close();
                conn.close();

            }
        } catch (IOException ex) {
            Logger.getLogger(ThreadClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThreadClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ThreadClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
