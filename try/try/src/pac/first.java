/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pac;

/**
 *
 * @author sujay
 */
import javax.swing.*;
import java.awt.*;
import java.net.URL;
/**
 * @author sujay
 */
public class first extends JFrame
{
    /**
     * @param args the command line arguments
     */
    public static JTabbedPane jtp;
    public void setup()
    {
        ImageIcon tab2Icon = new ImageIcon(this.getClass().getResource("detl1.png"));

        jtp = new JTabbedPane();
        jtp.addTab("DETAILS",tab2Icon, new DetailFrame());
        getContentPane().add(jtp);
        int count=jtp.getTabCount();
        //System.out.print(count);
    }
    public static void main(String[] args)
    {
        // TODO code application logic here
        first jd=new first();
        jd.show();
        jd.setSize(1000,750);
        jd.setup();
    }
}