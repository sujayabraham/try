/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pac;

import java.io.IOException;
import java.sql.Connection;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manojb
 */
public class dbcon {

     public static  Connection con=null;
     public static String uru="jdbc:mysql://localhost:3306/admintool";
    public static Connection myconnection() throws IOException, SQLException{
        try {
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex);
        }

        try {
            con = DriverManager.getConnection(uru,"root","");
        } catch (SQLException ex) {
            ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex);
        }
        return con;
    }

}
