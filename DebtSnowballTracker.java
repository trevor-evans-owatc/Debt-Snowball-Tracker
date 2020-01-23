package debtsnowballtracker;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This program implements a GUI that allows the user to track their debt 
 * as they pay it down. It implements interactive and non-interactive lists,
 * multiple buttons, and interactive text boxes. All these items come 
 * together to assist the user in tracking their own debt in an easy to use
 * and cosmetically pleasing program.
 * @author trevo
 */
public class DebtSnowballTracker extends JFrame {
    // Create the Strings to access files
    private String infoFile = "infoFile.txt";    
    private String paidFile = "paidFile.txt";    
    private String dateFile = "dateFile.txt";   
    private String phoneFile = "phone.txt";
    private String originalDebt = "originalDebt.txt";
    
    // the GUI consists of 9 panels
    private JPanel topListPanel;
    private JPanel mnthAmntPanel;
    private JPanel mnthPanel;
    private JPanel buttonPanel;
    private JPanel CompanyListPanel;
    private JPanel LastPaymentListPanel;
    private JPanel owedListPanel;
    private JPanel datePanel;
    private JPanel DateSelectionPanel;
    private JPanel DateMonthPanel;
    private JPanel DateYearPanel;
    private JPanel DateDayPanel;
    private JPanel AmountPanel;
    // The GUI has 6 Lists
    private JList companyList;
    private JList owedList;
    private JList lastPaymentList;
    private JList monthList;
    private JList dayList;
    private JList yearList;
    
    // The GUI has 9 labels each displays a message to the user
    private JLabel companeyName;
    private JLabel owedLabel;
    private JLabel lastPaidLabel;
    private JLabel dateLabel;
    private JLabel monthLabel;
    private JLabel dayLabel;
    private JLabel yearLabel;
    private JLabel amountLabel;
    
    // Three of the lists will need scrollpanes
    private JScrollPane scroll1;
    private JScrollPane scroll2;
    private JScrollPane scroll3;
    
    // <-------- A scroll bar the entire program
    private JScrollPane frameScroll;   // Scroll bar for the window
    
    // The GUI has one text box
    JTextField amountTxt;    
    
    // The GUI has 3 buttons
    private JButton makePaymentButton;
    private JButton paymentDueButton;
    private JButton getInfoButton;
    
    // Menue items
    private JMenuBar menuBar;        // the menu bar
    private JMenu fileMenu;          // The File menu
    private JMenuItem exitItem;      // To exit
    private JMenuItem addDebt;      // to add a debt
    
    // Create an ArrayLists to hold information from each file
    private ArrayList<String> Names;
    private ArrayList<String> date;
    private ArrayList<String> paid;
    private ArrayList<String> phone;
    private ArrayList<String> startingDebt;
    
    // Create arrays to interact with JLists
    private String[] NMS;   // Will reference Names ArrayList
    private String[] dte;   // Will reference date
    private String[] pd;    // Will reference paid
    private String[] phn;   // Will reference phone
    private String[] strt;  // Will reference startingDebt
    private String[] owe;   // Will hold the value owed for each company
    
    // Create arrays for the date
    private String[] months = {"1","2","3","4","5","6","7",
        "8","9","10","11","12"};
    private String[] days = new String[31];
    private String[] year = new String[10];
    /*
    Constructor
    */
    public DebtSnowballTracker() throws Exception
    {
        Names = new ArrayList<String>();
        date = new ArrayList<String>();
        paid = new ArrayList<String>();
        startingDebt = new ArrayList<String>();
        phone = new ArrayList<String>();
        
        // Read information from each file to populate each
        // array
        String line;    // To hold the string from each line
        int index = 0;  // To index the array for the file
        
        // Create a file path
        File file = new File(infoFile);
        
        Scanner inFile = new Scanner(file);
        
        // Populate the Names array
        while (inFile.hasNext())
        {
            line = inFile.nextLine();
            Names.add(index, line);
            index++;  
        }
        
        // Close inFile
        inFile.close();
        
        // Reset index
        index = 0;
        
        // Get information from the paidInfo file
        file = new File(paidFile);
        inFile = new Scanner(file);
        
         // Populate the paid array
        while (inFile.hasNext())
        {
            line = inFile.nextLine();
            paid.add(index, line);
            index++;  
        }
        
        // Close inFile
        inFile.close();
        
        // Reset index
        index = 0;
        
        // Get information from the dateFile
        file = new File(dateFile);
        inFile = new Scanner(file);
        
         // Populate the paid array
        while (inFile.hasNext())
        {
            line = inFile.nextLine();
            date.add(index, line);
            index++;  
        }
        
        // Close inFile
        inFile.close();
        
        // Reset index
        index = 0;
        
          // Get information from the dateFile
        file = new File(phoneFile);
        inFile = new Scanner(file);
        
         // Populate the paid array
        while (inFile.hasNext())
        {
            line = inFile.nextLine();
            phone.add(index, line);
            index++;  
        }
        
        // Close inFile
        inFile.close();
        
        // Reset index
        index = 0;
        
        // Get information from the originalDebt
        file = new File(originalDebt);
        inFile = new Scanner(file);
        
         // Populate the paid array
        while (inFile.hasNext())
        {
            line = inFile.nextLine();
            startingDebt.add(index, line);
            index++;  
        }
        
        // Close inFile
        inFile.close();
        
        // Allocate each of the arrays
        NMS = new String[Names.size()];
        for (int x = 0; x < NMS.length; x++)
            NMS[x] = Names.get(x);
        
        dte = new String[date.size()];
        for(int x = 0; x < dte.length; x++)
            dte[x] = date.get(x);
        
        pd = new String[paid.size()];
        for(int x = 0; x < pd.length; x++)
            pd[x] = paid.get(x);
        
        phn = new String[phone.size()];
        for(int x = 0; x < phn.length; x++)
            phn[x] = phone.get(x);
        
         strt = new String[startingDebt.size()];
        for(int x = 0; x < strt.length; x++)
            strt[x] = startingDebt.get(x);
        
        // populate the date arrays
        for(int n = 0; n < days.length;n++)
            days[n] = Integer.toString(n+1);
        
        for (int n = 0; n < year.length; n++)
               year[n] = Integer.toString(2017 + n);
        
        // Call the setOwe method
        setOwe();
        
        // Build the GUI
        
        // Set Title.
        setTitle("Debt Snowball Tracker");
        
         // Specify close button action
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
         // Set a grid layout.
        setLayout(new GridLayout(3,1));
        
        // Build the panels
        buildListPanel();
        buildMnthAmntPanel();
        buildButtonPanel();
        
        // build the menu bar
        buildMenuBar();
       
        frameScroll = new JScrollPane(topListPanel);
        add(frameScroll);  
        add(mnthAmntPanel);
        add(buttonPanel);
        
        // Add the JScrollPane to the window
         // Pack and display frame
        pack();
        setVisible(true);
        
    }
    
    /**
     * the buildsListPanel method add three panels to a grid
     */
    private void buildListPanel()
    {
        // Build top list panel
        topListPanel = new JPanel();
        
        // Set a grid layout
        topListPanel.setLayout(new GridLayout(1,3));
        
        // Build a panel for each list so that the alinment is acceptable
        buildCompanyListPanel();
        buildLastPaymentListPanel();
        buildOwedListPanel();
        
        // add the panels to this panel 
        topListPanel.add(CompanyListPanel);
        topListPanel.add(LastPaymentListPanel);
        topListPanel.add(owedListPanel);
    }
  
    /**
     * the buildCompanyListPanel adds a list and a label to a panel 
     **/
    private void buildCompanyListPanel()
    {
        CompanyListPanel = new JPanel();
        
        // Set a grid layout
        CompanyListPanel.setLayout(new BoxLayout(CompanyListPanel, 
                BoxLayout.Y_AXIS));
        
       // Create company name label
        companeyName = new JLabel("Name of company");
        
        // Create Company List
        companyList = new JList(NMS);
        
        // Set companyList to multiple interval selection
        companyList.setSelectionMode
        (ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        // add componenets to the panel
        CompanyListPanel.add(companeyName);
        CompanyListPanel.add(Box.createHorizontalGlue());
        CompanyListPanel.add(companyList);
    }
    
     /**
     * the buildCompanyListPanel adds a list and a label to a panel 
     **/
    private void buildLastPaymentListPanel()
    {
        LastPaymentListPanel = new JPanel();
        
        // Set a grid layout
        LastPaymentListPanel.setLayout(new BoxLayout(LastPaymentListPanel, 
                BoxLayout.Y_AXIS));
        
         // Create lastPayment List it is not enabled
        lastPaymentList = new JList(dte);
        lastPaymentList.setEnabled(false);
        
        lastPaidLabel = new JLabel("Last payment made");
        
        // add componenets to the panel
        LastPaymentListPanel.add(lastPaidLabel);
        LastPaymentListPanel.add(Box.createHorizontalGlue());
        LastPaymentListPanel.add(lastPaymentList);
    }
    
     /**
     * the  buildOwedListPanel method adds a list and a label to a panel 
     **/
    private void  buildOwedListPanel()
    {
        owedListPanel = new JPanel();
        
        // Set a grid layout
        owedListPanel.setLayout(new BoxLayout(owedListPanel, BoxLayout.Y_AXIS));
        
        // Create owed List it is not enabled
        owedList = new JList(owe);
        owedList.setEnabled(false);
        
        // Create the label
        owedLabel = new JLabel("total owed");
        
        // add componenets to the panel
        owedListPanel.add(owedLabel);
        owedListPanel.add(Box.createHorizontalGlue());
        owedListPanel.add(owedList);
    }
    
    /**
     * The buildMnthAmntPanel method attaches a panel, a label,
     *  and a text box to this panel
     */
    private void buildMnthAmntPanel()
    {
        mnthAmntPanel = new JPanel();
        
        // Set a grid layout
        mnthAmntPanel.setLayout(new GridLayout(2,1));
        
        // Build panels 
        buildDatePanel();
        
        // Add componenets to the panel
        mnthAmntPanel.add(datePanel);
        
    }
    
    /**
     * The buildDatePanel method puts a label and panel within this panel
     */
    private void buildDatePanel()
    {
        // Build the date panel
        datePanel = new JPanel();
        
        // Set a grid layout
        datePanel.setLayout(new GridLayout(2,1));
        
        // Build the date selection panel
        buildDateSelectionPanel();
        
        // Build the date message label panel
        dateLabel = new JLabel("Select the month, day, and year that is todays"
                + " date.");
        
        // Add componenets to the panel
        datePanel.add(dateLabel);
        datePanel.add(DateSelectionPanel);
    }
    
    /**
     * The buildDateSelectionPanel method adds three panels to a panel
     */
    private void buildDateSelectionPanel()
    {
        // Build the DateSelectionPanel
        DateSelectionPanel = new JPanel();
        
        // Set a Grid layout
        DateSelectionPanel.setLayout(new GridLayout(1,3));
        
        // Build the three new panels
        buildDateMonthPanel();
        buildDateYearPanel();
        buildDateDayPanel();
        
        // Add the three panels to this panel
        DateSelectionPanel.add(DateMonthPanel);
        DateSelectionPanel.add(DateDayPanel);  
        DateSelectionPanel.add(DateYearPanel);
    }
    
    /**
     * the buildDateMonthPanel builds and adds a label and a list to a panel
     */
    private void buildDateMonthPanel()
    {
        // Build panel
        DateMonthPanel = new JPanel();
        
        // Set a Grid
        DateMonthPanel.setLayout(new GridLayout(2,1));
        
        // Create month label
        monthLabel = new JLabel("MONTH");
        
        // Create monthList
        monthList = new JList(months);
        
        // Set the visibility to 5
        monthList.setVisibleRowCount(5);
        
       // Add list scroll pane
       scroll1 = new JScrollPane(monthList);
       
       // Add items to the panel
       DateMonthPanel.add(monthLabel);
       DateMonthPanel.add(scroll1);
    }
        
    /**
     * the buildDateYearPanel adds a label and a list to a panel
     */
    private void buildDateYearPanel()
    {
        // Build panel
        DateYearPanel = new JPanel();
        
        // Set a Grid
        DateYearPanel.setLayout(new GridLayout(2,1));
        
        // Create month label
        yearLabel = new JLabel("YEAR");
        
        // Create monthList
        yearList = new JList(year);
        
        // Set the visibility to 5
        yearList.setVisibleRowCount(5);
        
       // Add list scroll pane
       scroll2 = new JScrollPane(yearList);
       
       // Add items to the panel
       DateYearPanel.add(yearLabel);
       DateYearPanel.add(scroll2);
    }
    
    /**
     * the buildDateDayPanel adds a label and a list to a panel
     */
    private void buildDateDayPanel()
    {
        // Build panel
        DateDayPanel = new JPanel();
        
        // Set a Grid
        DateDayPanel.setLayout(new GridLayout(2,1));
        
        // Create month label
        dayLabel = new JLabel("DAY");
        
        // Create monthList
        dayList = new JList(days);
        
        // Set the visibility to 5
        dayList.setVisibleRowCount(5);
        
       // Add list scroll pane
       scroll3 = new JScrollPane(dayList);
       
       // Add items to the panel
       DateDayPanel.add(dayLabel);
       DateDayPanel.add(scroll3);
    }
    
    /**
     * The buildMenubar method builds a menu bar
     */
    private void buildMenuBar()
    {
        // Create the menu bar
        menuBar = new JMenuBar();
        
        // Create the file menu
        buildFileMenu();
        
        // Add the file and text menus to the menu bar.
        menuBar.add(fileMenu);
        
        // Set the window's menu bar
        setJMenuBar(menuBar);
    }
    
    /**
     * The buildFileMenu method builds the file menu and 
     *  returns a reference to its Jmenu object.
     */
    private void buildFileMenu()
    {
         // Create an Exit menu Item
        exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.addActionListener(new ExitListener());
        
        // Create add debt Item
        addDebt = new JMenuItem("Add Debt");
        addDebt.setMnemonic(KeyEvent.VK_I);
        addDebt.addActionListener(new AddDebtListener());
        
        //Create a JMenu object for the File menu
        fileMenu = new JMenu("Files");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        // Add the menu items to the File menu
        fileMenu.add(addDebt);
        fileMenu.add(exitItem);
    }
    /**
     * the buildButtonPanel method creates and adds three buttons to a panel
     */
    private void buildButtonPanel()
    {
        // Create an instance of the button panel
        buttonPanel = new JPanel();
        
        // Build an amount panel
        BuildAmountPanel();
        // Set up a grid layout
        buttonPanel.setLayout(new GridLayout(2,2));
        // Build the buttons
        makePaymentButton = new JButton("Initialize Payment");
        
        // Create a button listener for the makePaymentButton
        makePaymentButton.addActionListener(new mkPayButtonListener());
        
        paymentDueButton = new JButton("payment Due");
        paymentDueButton.setSize(100,50);
        // Create a button Listener for the paymentDueButton
        paymentDueButton.addActionListener(new pymtDueBtnListener());
        
        getInfoButton = new JButton("get info");
        
        // Create a button listener for the paymentDueButton
        getInfoButton.addActionListener(new getInfoButtonListener());
        
        // Add the buttons to the panel 
        buttonPanel.add(AmountPanel);
        buttonPanel.add(makePaymentButton);
        buttonPanel.add(getInfoButton);
        buttonPanel.add(paymentDueButton);
    }
    /**
     * The buildAmountPanel attaches a text field and a label to a panel
     */
    private void BuildAmountPanel()
    {
        // maek the amount panel
        AmountPanel = new JPanel();
        AmountPanel.setLayout(new GridLayout(2,1));
         // Build amount label
        amountLabel = new JLabel("enter amount paid");
        
        // Build test field
        amountTxt = new JTextField("0");
        amountTxt.setEditable(true);
        AmountPanel.add(amountLabel);
        AmountPanel.add(amountTxt);
    }
    
    /**
     * mkPayButtonListener is a private inner class that reacts to the 
     * event when the makePaymentButton is clicked.
     */
    private class mkPayButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            // Create boolean operators to identify that all lists had a
            // selection
            boolean isCompany = false, isMonth = false,
                    isDay = false, isYear = false, isDate = false; 
            // Check to make sure there is a company selected
            if (!companyList.isSelectionEmpty())
            {                
                isCompany = true;
            }
            else
            {
                // Display error message and set Company list to red
                JOptionPane.showMessageDialog(null, "Error: You must "
                        + "select the company(s) that were paid.");
                companyList = new JList(NMS);
                companyList.setBackground(Color.RED);
            }
            
            // Check to see if a date was selected
            if (!monthList.isSelectionEmpty())
            {
                isMonth = true;
            }
            else
            {
                // Display error message and set month list to red 
                JOptionPane.showMessageDialog(null, "Error: You select "
                        + "The current month from the month list.");
                monthList = new JList(months);
                monthList.setBackground(Color.red);
            }
            
            // Make sure a day is selected
            if (!dayList.isSelectionEmpty())
            {
                isDay = true;
            }
            else
            {
                 // Display error message and set day list to red 
                JOptionPane.showMessageDialog(null, "Error: You select "
                        + "The current day from the days list.");
                dayList = new JList(days);
                dayList.setBackground(Color.red);
            }
            
            // Make sure a year is selected
            if (!yearList.isSelectionEmpty())
            {
                isYear = true;
            }
            else
            {
                // Display error message and set day list to red 
                JOptionPane.showMessageDialog(null, "Error: You select "
                        + "The current year from the year list.");
                yearList = new JList(year);
                yearList.setBackground(Color.red);
            }
            
            // if all the date list are true then isDate is set to true
            if (isMonth && isDay && isYear)
                isDate = true;
            
            // If the user has selected something from each list continue
            if (isCompany && isDate)
            {
                String txtBoxIn;         // To hold the text box input
                String newDate = "";     // To hold the new payment date
                Object[] selection;      // To hold the selected items
                
                // Set txtBoxIn equal to the value in the txt box
                txtBoxIn = amountTxt.getText();
                
                // initialize selection
                selection = companyList.getSelectedValues();
                
                // Get the indices of the selected items
                int[] SelectIndices = companyList.getSelectedIndices();
                
                // Set the newDate
                newDate += monthList.getSelectedValue();
                newDate += "/";
                newDate += dayList.getSelectedValue();
                newDate += "/";
                newDate += yearList.getSelectedValue();
                
                // Subtract the txtBoxIn value from the money owed to 
                // each selected company
                for (int index = 0; index < SelectIndices.length; index++)
                {
                    //re-initialize the value back into its proper indice
                    pd[SelectIndices[index]] = (
                         Double.toString(Double.parseDouble(
                         pd[SelectIndices[index]]) + 
                                 Double.parseDouble(txtBoxIn)));
                }
                
                // reevaluate owed
                setOwe();
                
                // Replace the payment date with what 
                // was selected by the user
                for (int i = 0; i < SelectIndices.length; i++)
                {
                    dte[SelectIndices[i]] = newDate;
                }

                // Create a string that will display payment
                String message = "";
                
                for(int i =0; i < SelectIndices.length; i++)
                {
                    message += (String.format(selection[i] + " was paid on $" +  
                         "%,.2f leaving a balnace of $%,.2f" +
                         "\n The new balance will " +
                            "Show the next time this program is loaded\n",
                            Double.parseDouble(amountTxt.getText()),
                            Double.parseDouble(owe[SelectIndices[i]])));
                }
                
                // Display the message to the user
                JOptionPane.showMessageDialog(null, message);
                
                // Save new data
                 try 
                   {
                       SaveData();
                   } catch (Exception ex) {
                       Logger.getLogger(DebtSnowballTracker.class.getName())
                               .log(Level.SEVERE, null, ex);
                   }
            }
        }
    }
    
    /**
     * The payments due class is a private inner class that handles the 
     * event that the user clicks the payments due class
     */
    private class pymtDueBtnListener implements ActionListener
    {
       public void actionPerformed(ActionEvent e)
       {
           
           
           
           // Makes sure date is selected
           
            //Create boolean operators to identify that all lists had a
            // selection
            boolean isMonth = false, isDay = false,
                    isYear = false, isDate = false; 
            
            // Check to see if a date was selected
            if (!monthList.isSelectionEmpty())
            {
                isMonth = true;
            }
            else
            {
                // Display error message and set month list to red 
                JOptionPane.showMessageDialog(null, "Error: You select "
                        + "The current month from the month list.");
                monthList = new JList(months);
                monthList.setBackground(Color.red);
            }
            
            // Make sure a day is selected
            if (!dayList.isSelectionEmpty())
            {
                isDay = true;
            }
            else
            {
                 // Display error message and set day list to red 
                JOptionPane.showMessageDialog(null, "Error: You select "
                        + "The current day from the days list.");
                dayList = new JList(days);
                dayList.setBackground(Color.red);
            }
            
            // Make sure a year is selected
            if (!yearList.isSelectionEmpty())
            {
                isYear = true;
            }
            else
            {
                // Display error message and set day list to red 
                JOptionPane.showMessageDialog(null, "Error: You select "
                        + "The current year from the year list.");
                yearList = new JList(year);
                yearList.setBackground(Color.red);
            }
            
            // if all the date list are true then isDate is set to true
            if (isMonth && isDay && isYear)
                isDate = true;
           
            // If date was selected continue 
            if(isDate)
            {
                // Create variables for the date
                String MONTH = "", DAY =  "", YEAR = "";
                
                MONTH += monthList.getSelectedValue();
                DAY += dayList.getSelectedValue();
                YEAR += yearList.getSelectedValue();
                
                // Create a string array that will hold the company names
                // of those who's payments are coming up due.
                String[] PayDue = new String[NMS.length];
               
                // Seperate the array into variables
                String Sday, Smth, Syr, Sfull;
                
                String[] tokens;
                
                int index = 0; // Keeps place holder for PayDue
                
                // Step through the array of dates and compare values
                for(int i = 0; i < dte.length; i++)
                {
                     Sfull = dte[i];
                    // Create tokens for each 
                    tokens = Sfull.split("/");
                    
                    // Set tokens to each string
                    Smth = tokens[0];
                    Sday = tokens[1];
                    Syr = tokens[2];
                    
                    
                    // Compare the tokens to the date
                    if (Integer.parseInt(Syr) < Integer.parseInt(YEAR))
                    {
                        // If Syr is greater then YEAR then omit the month
                        // and check the days for being 20 or more 
                        // apart from one anouther
                        if(Integer.parseInt(DAY) > 10)
                        {
                            // if the day is greater then 10 mineus the
                            // DAY from Sday
                           if ((Integer.parseInt(DAY) - 10) 
                                   <= Integer.parseInt(Sday))
                           {
                              PayDue[index] =  NMS[i];
                              
                              // iderate index
                              index++;
                           }
                        }
                        else
                        {
                            // otherwise check if DAY is 10 days away in 
                            // rotation
                            if (30 - (Integer.parseInt(DAY)) 
                                    <= Integer.parseInt(Sday))
                            {
                                PayDue[index] = NMS[i];
                                
                                // iderate index
                                index++;
                            }
                        }
                    }
                    else
                    {
                        // If Syr is the same then check month
                        // for greater value
                        if (Integer.parseInt(Smth) <= Integer.parseInt(MONTH))
                        {
                            //check the days for being 20 or more 
                            // apart from one anouther
                            if(Integer.parseInt(DAY) > 10)
                            {
                                // if the day is greater then 10 mineus the
                                // DAY from Sday
                               if ((Integer.parseInt(DAY) - 10) 
                                       <= Integer.parseInt(Sday))
                               {
                                  PayDue[index] =  NMS[i];
                                  
                                  // iderate index
                                  index++;
                               }
                            }
                            else
                            {
                                // otherwise check if DAY is 10 days away in 
                                // rotation
                                if ((30 - Integer.parseInt(DAY)) 
                                        <= (Integer.parseInt(Sday) + 10))
                                {
                                    PayDue[index] = NMS[i]; 
     
                                    // iderate index
                                    index++;
                                }
                            }
                        }
                    }
                }
                
                // Create a string to hold a list of company names
                String message = "";
                
                for(int i = 0; i < PayDue.length; i++)
                {
                    // check a null value is placed in current position
                    if(PayDue[i] == null)
                    {
                        // If valuse is null the end of the list is reached
                        // and the loop can be broken
                        break;
                    }
                    else
                    {
                        // Otherwise add the value to the string
                        message += (PayDue[i] + "\n");
                    }
                }
                
                // Display the message to the user
                JOptionPane.showMessageDialog(null, "Here is a list of " +
                        "the companies that have payments due:\n" + 
                        message);
            }
       }
    }
    /**
     * getInfoButtonListener is a private inner class that handles the event
     * that the user clicks the getInfo button
     */
     private class getInfoButtonListener implements ActionListener
     {
         public void actionPerformed(ActionEvent e)
         {
             boolean isCompany = false; // True if company was selected
             
            // Check to make sure there is a company selected
            if (!companyList.isSelectionEmpty())
            {                
                isCompany = true;
            }
            else
            {
                // Display error message and set Company list to red
                JOptionPane.showMessageDialog(null, "Error: You must "
                        + "select the company(s) that were paid.");
                companyList = new JList(NMS);
                companyList.setBackground(Color.RED);
            } 
           if (isCompany)
           {                               
               // Get the indices of the selected items
               int[] SelectIndices = companyList.getSelectedIndices();
               
               // Create string to hold information
               String message = "";
               
               for(int i =0; i < SelectIndices.length; i++)
               {
                   message += (NMS[SelectIndices[i]] + " " +
                           phn[SelectIndices[i]] + "\n");
               }
               
               // Display information
               JOptionPane.showMessageDialog(null, "Company Name:     Phone: \n"
                       + message);
           }
         }
     }
    
    /**
     *  ExitListener is a private inner class that handles the event 
     * when the user selects the exit menu item
     */
     private class ExitListener implements ActionListener
    {
       public void actionPerformed(ActionEvent e)
       {
           System.exit(0);
       }
     }
    
     /**
      * AddDebtListener is a private inner class that handles the event
      * when the user selects the add debt menu item
      */
     private class AddDebtListener implements ActionListener
    {
       public void actionPerformed(ActionEvent e)
       {
           // Create strings to hold the new information
           String inputString;
           
           // Create a string for each portion of the date 
           // ie one for month anouther for year and anouther 
           // fo the day of the month
           String InDay, InMonth, InYear, FullDate;
           
           // Get the information from the user to add to each array
           inputString = JOptionPane.showInputDialog("Enter is the " +
                   "name of the company:");
           
           // Trim any leading and trailing white spaces
           inputString = inputString.trim();
           
           // append inputString to the Names ArrayList
           Names.add(inputString);
           
           // Get the starting debt
           inputString = JOptionPane.showInputDialog("Enter is the amount " +
                   "owed to this company:");
           
            // Trim any leading and trailing white spaces
           inputString = inputString.trim();
           
           // Create a flag to check input
           boolean isNum;  // Flag for checking numbers
           
            // Ensure data is a number
            do
            {
                isNum = true;
               for(int i = 0; i < inputString.length(); i++)
               {
                    if(!Character.isDigit(inputString.charAt(i)))
                         isNum = false;                        
               }
               if (!isNum)
               {
               // any of the characters were not a number display an error
               // message and prompt user for valid input
               inputString = JOptionPane.showInputDialog("Error: " +
                       inputString + " is not a number. Please enter a number " 
                       + " for the amount owed to this company:");
               
                inputString = inputString.trim();
               }
            }while(!isNum); // IF error is throw restart process
            
            // Appent to the amount owed array
            startingDebt.add(inputString);
            
            
            JOptionPane.showMessageDialog(null, "In the next three text panes" +
                    "please enter a number for the prompted date\n" +
                    "of when the first, or next, payment is due.");
            
           InMonth = JOptionPane.showInputDialog("Enter the month, (1-12)");
           
            // Trim any leading and trailing white spaces
           InMonth = InMonth.trim();
           
           // validate input
           do
           {
               isNum = true; // Reset flag
               if(!Character.isDigit(InMonth.charAt(0)))
                   isNum = false;
               else if (InMonth.length() > 1) // if two digits are entered
               {
                   if(!Character.isDigit(InMonth.charAt(1)))
                   isNum = false;
               }
               else if(Integer.parseInt(InMonth) > 12 || 
                  Integer.parseInt(InMonth) < 1)
               {
                   isNum = false;
               }
               
               // if isNum comes up false have the user re-enter data
               if (!isNum)
               {
               InMonth = JOptionPane.showInputDialog("Error: " + InMonth +
                       "is not valid. Enter the month, (1-12)");
               
                // Trim any leading and trailing white spaces
                InMonth = inputString.trim();
                }
           }while(!isNum);
           
           // If everything is valid change to the prior month 
           // so as to get the payment due feature updated
           InMonth = Integer.toString(Integer.parseInt(InMonth) - 1);
           
           // Get the day
           InDay = JOptionPane.showInputDialog("Enter the day, (1-31)");
           
           InDay = InDay.trim();
           
           // validate input
           do
           {
               isNum = true; // Reset flag
               if(!Character.isDigit(InDay.charAt(0)))
                   isNum = false;
               else if (InDay.length() > 1) // if two digits are entered
               {
                   if(!Character.isDigit(InDay.charAt(1)))
                   isNum = false;
               }
               else if(Integer.parseInt(InDay) > 31 || 
                  Integer.parseInt(InDay) < 1)
               {
                   isNum = false;
               }
               
               // if isNum comes up false have the user re-enter data
               if (!isNum)
                    InDay = JOptionPane.showInputDialog("Error: " + InDay +
                       "is not valid. Enter the day, (1-31)");
               
               InDay = InDay.trim();
           }while(!isNum);
           
           // Get the year
           InYear = JOptionPane.showInputDialog("Enter the year:");
           
           InYear = InYear.trim();
           
           // Validate the year
           // Ensure data is a number
            do
            {
                isNum = true;
                if (InYear.length() == 4)
                {
                   for(int i = 0; i < InYear.length(); i++)
                   {
                        if(!Character.isDigit(InYear.charAt(i)))
                             isNum = false;  
                        break;
                   }
                }
                else 
                    isNum = false;
                
                if (!isNum)
                {
                   InYear = JOptionPane.showInputDialog("Error: " + InYear +
                       "is not valid. Enter the year ie (2017)");
                
                InYear = InYear.trim();
                }
            }while(!isNum);
            
            // Ajust the month and the year in thw case that the month
            // entered was january
            if (Integer.parseInt(InMonth) == 0)
            {
                InMonth = "12";
                InYear = Integer.toString(Integer.parseInt(InYear) - 1);
            }
            
            // Create a full String for the date
            FullDate = (InMonth + "/" + InDay + "/" + InYear);
            
            // Attach to the date array list
            date.add(FullDate);
            
            // Get the companies phone number
            inputString = JOptionPane.showInputDialog("Eneter the company's " +
                    "phone number\n Enter only the numbers, do not enter " +
                    "parenthesis or dashes:");
            
            // Trim any leading and trailing white spaces
           inputString = inputString.trim();
           
            // Validate input
             do   
            {
                isNum = true;
               for(int i = 0; i < inputString.length(); i++)
               {
                    if(!Character.isDigit(inputString.charAt(i)))
                         isNum = false;  
                    break;
               }
               
               // any of the characters were not a number display an error
               // message and prompt user for valid input
               if (!isNum)
               {
               inputString = JOptionPane.showInputDialog("Error: " +
                       inputString + " is not valid.\nEneter the company's " +
                    "phone number\n Enter only the numbers, do not enter " +
                    "parenthesis or dashes:");
               
               // Trim any leading and trailing white spaces
               inputString = inputString.trim();
               }
            }while(!isNum); // IF error is thrown restart process */
          
             JOptionPane.showMessageDialog(null, "Before phone array list");
            
            // Reformat inputString to have a phone number format
            String phNumber = "";
            int numLength = inputString.length();
            String Last4 = inputString.substring((numLength - 4), numLength);
            String mid3 = inputString.substring((numLength - 7), 
                    (numLength - 4));
            String areaCode = inputString.substring((numLength - 10), 
                    (numLength - 7));
            
            phNumber += "(" + areaCode + ")" + mid3 + "-" + Last4;
            // Add inputString to phone Array list 
            phone.add(phNumber);
            
            // <<<<< DRIVER
            String ArLstStr = "";
            
            for (int x = 0; x < phone.size(); x++)
            {
                ArLstStr += phone.get(x);
            }
            JOptionPane.showMessageDialog(null, ArLstStr);
            // Add a zreo to the paid array list for this debt
            paid.add("0");
            
            // Reset each array to match the ajusted array list
            
            
        // Allocate each of the arrays
        NMS = new String[Names.size()];
        for (int x = 0; x < NMS.length; x++)
            NMS[x] = Names.get(x);
        
        dte = new String[date.size()];
        for(int x = 0; x < dte.length; x++)
            dte[x] = date.get(x);
        
        pd = new String[paid.size()];
        for(int x = 0; x < pd.length; x++)
            pd[x] = paid.get(x);
        
        phn = new String[phone.size()];
        for(int x = 0; x < phn.length; x++)
            phn[x] = phone.get(x);
        
         strt = new String[startingDebt.size()];
        for(int x = 0; x < strt.length; x++)
            strt[x] = startingDebt.get(x);
        
        // Save changed data
           try 
           {
               SaveData();
           } catch (Exception ex) {
               Logger.getLogger(DebtSnowballTracker.class.getName())
                       .log(Level.SEVERE, null, ex);
           }
           
           try {
               // Rebuild the frame
               new DebtSnowballTracker();
           } catch (Exception ex) {
               Logger.getLogger(DebtSnowballTracker.class.
                       getName()).log(Level.SEVERE, null, ex);
           }
       }
     }
     
     /**
      * The SaveData method saves five arrays to individual files
      */
     private void SaveData() throws Exception
     {
         String line;   // to hold each variable in array 
         
         // Open the infoFile
         PrintWriter outPut = new PrintWriter(infoFile);
         
         // Write data to the file
         for (int i = 0; i < NMS.length; i++)
         {
             // Set line equal to the next variable
             line = NMS[i];
             
             // Write line to file
             outPut.println(line);
         }
         // Close the file
         outPut.close();
         
         // Set Output to hold date file
         outPut = new PrintWriter(dateFile);
         
        // Write data to the file
        for (String dte1 : dte) {
            // Set line equal to the next variable
            line = dte1;
            // Write line to file
            outPut.println(line);
        }
         // Close the file
         outPut.close();
         
         // Set outPut to phone file
        outPut = new PrintWriter(phoneFile);
         
        // Write data to the file
        for (String phn1 : phn) {
            // Set line equal to the next variable
            line = phn1;
            // Write line to file
            outPut.println(line);
        }
         // Close the file
         outPut.close(); 
         
          // Set outPut to  file
        outPut = new PrintWriter(paidFile);
         
        // Write data to the file
        for (String pd1 : pd) {
            // Set line equal to the next variable
            line = pd1;
            // Write line to file
            outPut.println(line);
        }
         // Close the file
         outPut.close(); 
         
         // Set outPut to phone file
        outPut = new PrintWriter(originalDebt);
         
        // Write data to the file
        for (String strt1 : strt) {
            // Set line equal to the next variable
            line = strt1;
            // Write line to file
            outPut.println(line);
        }
         // Close the file
         outPut.close();  
     }
    /**
     *  The setOwe method adjusts the value owed to each company
     */
    private void setOwe()
    {
        owe = new String[strt.length];
        for(int n = 0; n < strt.length; n++)
            owe[n] = (Double.toString(Double.parseDouble(strt[n])
                    - Double.parseDouble(pd[n])));
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
       new DebtSnowballTracker();
    }
}
