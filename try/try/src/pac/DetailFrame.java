/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pac;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 * @author sujay
 */
public class DetailFrame extends JPanel
{
        JSplitPane splitPaneV;
	JSplitPane splitPaneH;
        JSplitPane splitPaneTop;
	JSplitPane splitPaneBot;

        JPanel controlProPanel;
	JPanel propertyPanel;
	JPanel viewPanel;
        JPanel headPanel;
      	JPanel footPanel;

        JComboBox jcb = new JComboBox();
        JComboBox jscreenName=new JComboBox();


        //head panel controlls
        JLabel screenLbl=new JLabel("Screen Name  :");;
        //public JTextField screenName=new JTextField();
        JLabel dommyL1=new JLabel("");
        JLabel dommyL2=new JLabel("");
        JLabel dommyL3=new JLabel("");
        JLabel screenRowLbl=new JLabel("ROWS               :");
        JTextField screenRowVal=new JTextField(20);
        JLabel screenColLbl=new JLabel("COLUMNS        :");
        JTextField screenColVal=new JTextField(20);


        //property panel controlls
        JTextField minText=new JTextField(20);
        JTextField maxText=new JTextField(20);
        JTextField fixText=new JTextField(20);
        JTextField dataTypeText=new JTextField(20);
        JTextField businessText=new JTextField(20);
        JTextField validateText=new JTextField(20);

        //controlProPanel controls
        JLabel imgLbl=new JLabel("IMAGE PATH");
        JTextField imgTxt=new JTextField(20);
        JLabel rownoLbl=new JLabel("ROW NO   :");
	JTextField rownoTxt=new JTextField(20);
	JLabel colnoLbl=new JLabel("COLUMN NO:");
	JTextField colnoTxt=new JTextField(20);
        JLabel rowmergeLbl=new JLabel("ROW MERGE   :");
        JCheckBox rowmergChk=new JCheckBox();
        JLabel cntrl=new JLabel("CONTROL   :");
        JLabel cntrlNmeLbl=new JLabel("CONTROL NAME:");
        JTextField cntrlTxt=new JTextField(20);
        JLabel defaulVlLbl=new JLabel("DEFAULT VAl:");
        JTextField defaulTxt=new JTextField(20);

        String cntRow,cntCol,cntrlNme,cntrlSel,defValue,imagePath="null";
        int rowMrg=0;

        String minVal,maxVal,fixVal,dataType,busines,validation,selectedScrn,scrnID;
        //createfootPanel controls
        JButton saveBtn=new JButton("SAVE");
        JButton clearBtn=new JButton("CLEAR");
        JButton deleteBtn=new JButton("DELETE");
        JButton viewBtn=new JButton("VIEW");

//        ImageIcon textIcon = new ImageIcon(this.getClass().getResource("bglogintextbox.png"));

        //controlls of view frame
        static String columnNames[];
        static String dataValues[][];
        static JTable table;
        JScrollPane scrollPaneTable;
        String cols;
        String rows;

        static int rowcnt=2;
        static int colcnt=4;

        Connection con;
        Statement st;
        ResultSet rs;





        public DetailFrame()
        {

//        JCheckBox b1 = new JCheckBox("Red");
//        JCheckBox b2 = new JCheckBox("Green");
//        JCheckBox b3 = new JCheckBox("Blue");
//        add(b1); add(b2);add(b3);
             jcb.addItem("Label");
             jcb.addItem("TextField");
             jcb.addItem("CheckBox");
             jcb.addItem("ComboBox");
             jcb.addItem("Image");
             jcb.addItem("CheckBox");
             jcb.addItem("Menu");
             jcb.addItem("ProgressBar");
             jcb.addItem("TextArea");
             jcb.addItem("Button");
             jcb.addItem("RadioButton");

             loadData();



                setBackground(Color.lightGray );

                this.setLayout(new BorderLayout());

		// Create the panels
		createviewPanel();
                createheadPanel();
                createfootPanel();

		// Create a splitter pane


                splitPaneV = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT );
                 splitPaneBot=new JSplitPane(JSplitPane.VERTICAL_SPLIT);

                this.add(splitPaneBot,BorderLayout.SOUTH);

	
		splitPaneV.setLeftComponent(headPanel);
		splitPaneV.setRightComponent(viewPanel);

                
                            //splitPaneTop.setBounds(0,0,600,600);

                splitPaneBot.setTopComponent(splitPaneV);
                splitPaneBot.setBottomComponent(footPanel);



                jscreenName.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {


                String selectedValue = jscreenName.getSelectedItem().toString();

                try {
                con = dbcon.myconnection();
                st= con.createStatement();
                rs=st.executeQuery("select * from screen_detail");


                while(rs.next())
                {

                    if(selectedValue.equals(rs.getString(2)))
                    {
                       screenRowVal.setText(String.valueOf(rs.getString(3)));
                       screenColVal.setText(String.valueOf(rs.getString(4)));
                       cols=rs.getString(4);
                       rows=rs.getString(3);
                    }
                 }
               } catch (IOException ex) {
                    
               } catch (SQLException ex) {
               }

                //throw new UnsupportedOperationException("Not supported yet.");
            }
            });

    }

    public void loadData()
    {
           try {
                con = dbcon.myconnection();

                st= con.createStatement();
                rs=st.executeQuery("select * from screen_detail");
                while(rs.next()==true)
                {
                    jscreenName.addItem(rs.getString(2));

                }

                int cnt=jscreenName.getItemCount()-1;
                //int cnt1=Integer.parseInt(CDepno.getItem(cnt))+1 ;

//                screenRowVal.setText(String.valueOf(cnt));
                st.close();
                rs.close();
                con.close();


                System.out.println("Retrived successfully....");
                } catch (IOException ex) {
                 } catch (SQLException ex) {
                }
    }

    public void createviewPanel()
    {

		viewPanel = new JPanel();
		viewPanel.setLayout( new BorderLayout());
                //viewPanel.setPreferredSize( new Dimension(500,500 ));
                //viewPanel.setMinimumSize( new Dimension(100,50));
		//viewPanel.add( new JLabel("Notes:"),BorderLayout.NORTH );
//		viewPanel.add( new JTextArea(), BorderLayout.CENTER );
                viewPanel.setPreferredSize( new Dimension(200,520));
                viewPanel.setMinimumSize( new Dimension(100,100));
                 // Create columns
		CreateColumns(colcnt);
		CreateData(rowcnt,colcnt);
                setSize(300,200);

               table=new JTable(dataValues,columnNames){

                    public boolean isCellEditable(int rowIndex, int vColIndex) {
                    return false;
                    }
                };

                table.getModel().addTableModelListener(new TableModelListener() {

                    public void tableChanged(TableModelEvent e) {
                    System.out.println("........"+e);
                }
                });
                //table.setValueAt("1,2",0,0);

                System.out.println("Row   :"+rowcnt);
                System.out.println("Col  :"+colcnt);

                // Configure some of JTable's paramters
		table.setShowHorizontalLines( false );
                table.setShowVerticalLines(false);
		table.setRowSelectionAllowed( false );
		table.setColumnSelectionAllowed(false);

		// Change the selection colour
		table.setSelectionForeground( Color.white );
		table.setSelectionBackground( Color.red );

		// Add the table to a scrolling pane
		scrollPaneTable = JTable.createScrollPaneForTable( table );
		viewPanel.add(scrollPaneTable, BorderLayout.CENTER );
		viewPanel.add(table,BorderLayout.CENTER);


    }
    public static void CreateColumns(int colcnt)
    {
		// Create column string labels
                    System.out.println("This is to test:"+colcnt);
                    columnNames =new String[colcnt];
        	    for(int iCtr=0;iCtr<colcnt;iCtr++ )
			columnNames[iCtr]="Col:"+ iCtr;
    }
    public static void CreateData(int rowcnt, int colcnt)
    {
		// Create data for each element
         System.out.println("This is to test row:"+rowcnt);

                   dataValues = new String[rowcnt][colcnt];

                   for(int iX=0;iX<rowcnt;iX++)
                   {
			for(int iY=0;iY<colcnt;iY++)
			{
				dataValues[iX][iY] =""+iX+","+iY;
			}
                   }
    }

    public void createheadPanel()
    {
		headPanel = new JPanel();
		headPanel.setLayout(new GridLayout(3,3));


		headPanel.add(screenLbl);
                headPanel.add(jscreenName);headPanel.add(dommyL1);
                headPanel.add(screenRowLbl);
                headPanel.add(screenRowVal);headPanel.add(dommyL2);
                headPanel.add(screenColLbl);
                headPanel.add(screenColVal);headPanel.add(dommyL3);

               //screenName,screenRowLbl,screenRowVal,screenColLbl,screenColVal
               //headPanel.setBounds(0,0,1000,300);
    }
    public void createfootPanel()
    {
		footPanel=new JPanel();

		footPanel.setLayout( new FlowLayout());
		footPanel.add(saveBtn);
		footPanel.add(clearBtn);
		footPanel.add(deleteBtn);
                footPanel.add(viewBtn);

                viewBtn.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {

                    DetailFrame t=new DetailFrame();
                     rowcnt=Integer.parseInt(rows);
                     colcnt=Integer.parseInt(cols);
                      t.createviewPanel();
                      viewPanel.repaint();
                      splitPaneV.setRightComponent(viewPanel);

                //rows=screenRowVal.getText();



                }
                });
                saveBtn.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    JOptionPane.showMessageDialog(null,"Do you want to continue..?");

                    cntRow=rownoTxt.getText();
                    cntCol=colnoTxt.getText();
                    boolean selected = rowmergChk.getModel().isSelected();
                    if(selected)
                    {
                        rowMrg=1;
                    }
                    cntrlNme=cntrlTxt.getText();
                    cntrlSel=jcb.getSelectedItem().toString();
                    defValue=defaulTxt.getText();
                    imagePath=imgTxt.getText();

                    minVal=minText.getText();
                    maxVal=maxText.getText();
                    fixVal=fixText.getText();
                    dataType=dataTypeText.getText();
                    busines=businessText.getText();
                    validation=validateText.getText();

                    selectedScrn=jscreenName.getSelectedItem().toString();


                    try {
                        con = dbcon.myconnection();
                        Statement st= con.createStatement();
                        rs=st.executeQuery("select * from screen_detail");
                        while(rs.next()==true)
                        {
                            if(selectedScrn.equals(rs.getString(2)))
                            {
                                scrnID=rs.getString(1).toString();
                            }

                        }

                        st.executeUpdate("insert into control_detail(scrn_id,cont_row,cont_col,row_merge,cont_name,control,def_val,img_path,min_len,max_len,fix_len,data_type,busines_log,validate)"+ "values('"+scrnID+"','"+cntRow+"','"+cntCol+"','"+rowMrg+"','"+cntrlNme+"','"+cntrlSel+"','"+defValue+"','"+imagePath+"','"+minVal+"','"+maxVal+"','"+fixVal+"','"+dataType+"','"+busines+"','"+validation+"')");
                        System.out.println("Inserted successfully....");
                        }
                        catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,"Input error");

                        }catch(SQLException ex)
                        {
                            JOptionPane.showMessageDialog( null,ex);
                            //System.exit(1);
                        }catch(Exception ex){
                        JOptionPane.showMessageDialog(null,"Invalid Inputs");
                        //System.exit(1);
                    }
            }
        });
    }
}
