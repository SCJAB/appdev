import java.util.ArrayList;
import java.io.*;

class ClassCustomerRecord 
{
    public ClassCustomerRecord()
    {
        super();
    }
    private String customerNumber;
    private String customerName;
    private String customerAddress;
    private String customerContactNumber;
    
    public ClassCustomerRecord (String customerNumber, String customerName,
            String customerAddress,
            String customerContactNumber)
    {
        super();
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerContactNumber = customerContactNumber;
    }
    
    public void setCustomerNumber(String newCustomerNumber)
    {
        this.customerNumber = newCustomerNumber;
    }
    
    public String getCustomerNumber()
    {
        return customerNumber;
    }
    
    public void setCustomerName(String newCustomerName)
    {
        this.customerName = newCustomerName;
    }
    
    public String getCustomerName()
    {
        return customerName;
    }
    
    public void setCustomerAddress (String newCustomerAddress)
    {
        this.customerAddress = newCustomerAddress;
    }
    
    public String getCustomerAddress()
    {
        return customerAddress;
    }
    
    public void setCustomerContactNumber(String newCustomerContactNumber)
    {
        this.customerContactNumber = newCustomerContactNumber;
    }
    
    public String getCustomerContactNumber()
    {
        return customerContactNumber;
    }
}

public class CustomerRecord
{
    public static void main(String[] args) throws Exception
    {
        InputStreamReader read = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(read);
        
        ArrayList<ClassCustomerRecord> customers = new ArrayList<>();
        
        boolean done = true;
        boolean checker;
        String cNumber;
        String cName;
        String cAddress;
        String cContactNumber;
        String Response = "";
        String findRecord = "";
        int ifound = 0;
        
        do
        {
            ClassCustomerRecord ccr = new ClassCustomerRecord();
            System.out.println("----------MENU ITEMS---------");
            System.out.println("1.\tADD NEW RECORD");
            System.out.println("2.\tDELETE RECORD");
            System.out.println("3.\tVIEW RECORD LISTINGS");
            System.out.println("4.\tVIEW A RECORD");
            System.out.println("5.\tEXIT");
            System.out.println("-----------------------------");
            
            System.out.print("Enter your Choice: ");
            Response = br.readLine();
            
            switch(Response)
            {
                case "1":
                    do
                    {
                        ClassCustomerRecord ccrADD = new ClassCustomerRecord();
                        System.out.println("-----#ADD RECORD#-----");
                        System.out.print("Enter Customer Number: ");
                        cNumber = br.readLine();
                        ccrADD.setCustomerNumber(cNumber);
                        
                        System.out.print("Enter Name: ");
                        cName = br.readLine();
                        ccrADD.setCustomerName(cName);

                        System.out.print("Enter Address: ");
                        cAddress = br.readLine();
                        ccrADD.setCustomerAddress(cAddress);

                        System.out.print("Enter Contact#: ");
                        cContactNumber = br.readLine();
                        ccrADD.setCustomerContactNumber(cContactNumber);

                        System.out.print("Save? [Y/N]: ");
                        Response = br.readLine();
                        if (Response.equals("Y") || Response.equals("y"))
                        {
                            System.out.println("-----------------------------");
                            customers.add(ccrADD);
                            System.out.println("Successfully Saved!");
                            System.out.println("-----------------------------");
                        }
                        else
                        {
                            System.out.println("Saving Unsuccessful!");
                        }
                        System.out.print("Input Another Record? [Y/N]: ");
                        Response = br.readLine();
                        if(Response.equals("Y") || Response.equals("y"))
                        {
                            checker = true;
                        }
                        else
                        {
                            System.out.println("-----------------------------");
                            System.out.println("Press Enter Key to go back to Menu....");
                            System.out.println("-----------------------------");
                            Response = br.readLine();
                            checker = false;
                        }
                    }
                    while(checker);
                break;
                
                case "2":
                    System.out.println("--------#DELETE RECORD#--------");
                    System.out.print("Enter customer number: ");
                    findRecord = br.readLine();
                    ifound = 0;
                    for (ClassCustomerRecord c : customers) 
                    {
                        if (c.getCustomerNumber().equals(findRecord)) 
                        {
                            System.out.printf("%-7s%-20s%-10s%-5s%n",
                            c.getCustomerNumber(), c.getCustomerName(),
                            c.getCustomerAddress(),c.getCustomerContactNumber());
                            ifound = 1;

                            System.out.println("-----------------------------");
                            System.out.print("do you want to delete? Y/N: ");
                            String respsss = br.readLine();
                            System.out.println("-----------------------------");

                            if(respsss.equals("Y") || respsss.equals("y"))
                            {
                                System.out.print("Succesfully Deleted ");
                                System.out.printf("%-7s%-20s%-10s%-5s%n",
                                c.getCustomerNumber(), c.getCustomerName(),
                                c.getCustomerAddress(),c.getCustomerContactNumber());
                                customers.remove(c);
                            }
                            else
                            {
                                System.out.println("-----------------------------");
                                System.out.println("Unsuccesfully Deleted");
                                System.out.println("-----------------------------");
                            }
                            break;
                        }
                    }
                    if(ifound==0) 
                    { 
                        System.out.println("-----No records found-----"); 
                    }
                break;
                
                case "3":
                    System.out.println("--------#VIEW RECORD LISTINGS#--------");
                    for (ClassCustomerRecord c : customers)
                    {
                        System.out.printf("%-7s%-20s%-10s%-5s%n",
                        c.getCustomerNumber(), c.getCustomerName(),
                        c.getCustomerAddress(), c.getCustomerContactNumber());
                    }
                    System.out.println("-----------------------------");
                    System.out.println("Press Enter Key to go back to Menu....");
                    Response = br.readLine();
                    System.out.println("-----------------------------");
                break;
                
                case "4":
                    System.out.println("--------#VIEW RECORD#--------");
                    System.out.print("Enter customer number: ");
                    findRecord = br.readLine();
                    for (ClassCustomerRecord c : customers) 
                    {
                        if (c.getCustomerNumber().equals(findRecord)) 
                        {
                            System.out.printf("%-7s%-20s%-10s%-5s%n",
                            c.getCustomerNumber(), c.getCustomerName(),
                            c.getCustomerAddress(),c.getCustomerContactNumber());

                            System.out.println("-----------------------------");
                            System.out.print("Do you want to modify? [Y/N]: ");
                            System.out.println("-----------------------------");
                            String respsss = br.readLine();
                            int index = customers.indexOf(c);

                            if(respsss.equals("Y") || respsss.equals("y"))
                            {
                                System.out.println("-----#MODIFY RECORD#-----");
                                System.out.print("Enter New Customer Number: ");
                                cNumber = br.readLine();
                                ccr.setCustomerNumber(cNumber);

                                System.out.print("Enter New Name: ");
                                cName = br.readLine();
                                ccr.setCustomerName(cName);

                                System.out.print("Enter New Address: ");
                                cAddress = br.readLine();
                                ccr.setCustomerAddress(cAddress);

                                System.out.print("Enter Contact#: ");
                                cContactNumber = br.readLine();
                                ccr.setCustomerContactNumber(cContactNumber);

                                System.out.print("Save? [Y/N]: ");
                                Response = br.readLine();
                                if (Response.equals("Y") || Response.equals("y"))
                                {
                                    customers.set(index ,ccr);
                                    System.out.println("-----------------------------");
                                    System.out.println("Successfully Saved!");
                                    System.out.println("-----------------------------");
                                    System.out.println("Press Enter Key to go back to Menu....");
                                    Response = br.readLine();
                                }
                                else
                                {
                                    System.out.println("-----------------------------");
                                    System.out.println("Saving Unsuccessful!");
                                    System.out.println("-----------------------------");
                                }
                            }
                        }
                    }
                break;
                
                case "5":
                    System.out.println("EXIT");
                    done = false;
                break;
            }
        }
        while(done);
    }
}