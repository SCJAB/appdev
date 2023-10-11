import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        ArrayList<Customers> customers = new ArrayList<>();
        customers = MainCall(customers);
    }

    public static ArrayList<Customers> MainCall(ArrayList<Customers> customers) throws Exception {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader input = new BufferedReader(reader);

        boolean flag = true;

        do {
            System.out.println("----------MENU ITEMS---------");
            System.out.println("1.\tADD NEW RECORD");
            System.out.println("2.\tDELETE RECORD");
            System.out.println("3.\tVIEW RECORD LISTINGS");
            System.out.println("4.\tVIEW A RECORD");
            System.out.println("5.\tEXIT");
            System.out.println("-----------------------------");

            System.out.print("Enter your Choice: ");
            String resp = input.readLine();

            switch (resp.toUpperCase()) {
                case "1":
                    CreateClass create = new CreateClass(customers);
                    break;
                case "2":
                    DeleteRecords delete = new DeleteRecords(customers);
                    break;
                case "3":
                    int count = 0;
                    if (customers.size() <= 0) {
                        System.out.println("\n\tThere are no customer records. Please add a new record by pressing 1 in the menu. Press enter to return to the menu...");
                        resp = input.readLine();
                        break;
                    } else {
                        System.out.print("\n\nRecordList:");
                    }
                    for (Customers p : customers) {
                        System.out.printf("\n\nCustomer [%d]:\n%-7S%-10S%-10S%-7S\n", (count + 1),
                                ("\tCustomer Number: " + customers.get(count).getcNumber()),
                                ("\tCustomer Name: " + customers.get(count).getcName()),
                                ("\tCustomer Address: " + customers.get(count).getcAddress()),
                                ("\tCustomer Contact#: " + customers.get(count).getcContact()));
                        count++;
                    }
                    break;
                case "4":
                    ViewRecord view = new ViewRecord(customers);
                    break;
                case "5":
                    System.out.println("Terminating Program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("The value doesn't match any options. Please try again...");
            }
        } while (flag);

        return customers;
    }
}

class ViewRecord extends Customers {

    InputStreamReader reader = new InputStreamReader(System.in);
    BufferedReader input = new BufferedReader(reader);

    int ifound = 0;
    int count = 0;
    String resp = "";

    ViewRecord(ArrayList<Customers> customers) throws Exception {

        if (customers.size() <= 0) {
            System.out.println("\n\tThere are no customer records. Please add a record by pressing 1 in the menu.\n\t\tPress enter to return to the menu...");
            input.readLine();
            return;
        }

        do {
            System.out.printf("\nEnter Customer Number to view:\nResponse: ");
            resp = input.readLine();

            for (Customers p : customers) {
                if (p.getcNumber().equals(resp)) {
                    System.out.printf("\nCustomer Records:\nCustomer Number: %S\nCustomer Name: %S\nCustomer Address: %S\nCustomer Contact#: %S\n\nDo you want to modify the existing record? [Yes|No]:\nResponse: ", p.getcNumber(), p.getcName(), p.getcAddress(), p.getcContact());
                    ifound = 1;
                    resp = input.readLine();

                    if (ifound == 1 && (resp.equalsIgnoreCase("YES") || resp.equalsIgnoreCase("Y"))) {
                        System.out.printf("\nEnter new name:\nResponse: ");
                        String newName = input.readLine();
                        p.setcName(newName);

                        System.out.printf("\nEnter new address:\nResponse: ");
                        String newAddress = input.readLine();
                        p.setcAddress(newAddress);

                        System.out.printf("\nEnter new contact#:\nResponse: ");
                        String newContact = input.readLine();
                        p.setcContact(newContact);

                        System.out.printf("\n\t\tModification Success! Press enter to return to the menu or No to continue.\nResponse: ");
                        input.readLine();
                    }
                    else if (resp.equalsIgnoreCase("NO") || resp.equalsIgnoreCase("N")) 
                    {
                        input.readLine();
                    }

                    super.addRecords(customers);
                    break;
                }
                count++;
            }

            if (!(ifound == 1)) {
                System.out.printf("\n\tCustomer Records don't exist! Press enter to return to the menu or No to continue.\nResponse: ");
                input.readLine();
                ifound = 0;
                continue;
            }

        } while (resp.equalsIgnoreCase("NO") || resp.equalsIgnoreCase("N"));
    }
}

class DeleteRecords extends Customers {

    ArrayList<Customers> customersClone = new ArrayList<>();

    public DeleteRecords(ArrayList<Customers> customers) throws Exception {

        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader input = new BufferedReader(reader);

        String resp = null;

        do {
            if (customers.size() <= 0) {
                System.out.println("\n\tThere are no customer records. Please add a new record by pressing 1 in the menu. Press enter to return to the menu...");
                resp = input.readLine();
                break;
            }
            System.out.printf("\nEnter Customer Number to delete:\nResponse: ");
            resp = input.readLine();

            int ifound = 0;

            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getcNumber().equals(resp)) {
                    ifound = 1;

                    System.out.printf("\nCustomer Records:\nCustomer Number: %S\nCustomer Name: %S\nCustomer Address: %S\nCustomer Contact#: %S", customers.get(i).getcNumber(),
                            customers.get(i).getcName(),
                            customers.get(i).getcAddress(),
                            customers.get(i).getcContact());

                    System.out.println("-----------------------------");
                    System.out.print("Do you want to delete? Y/N: ");
                    String respsss = input.readLine();
                    System.out.println("-----------------------------");

                    if (respsss.equalsIgnoreCase("Y")) {
                        System.out.print("Successfully Deleted ");
                        System.out.printf("%-7s%-20s%-10s%-5s%n", customers.get(i).getcNumber(), customers.get(i).getcName(), customers.get(i).getcAddress(), customers.get(i).getcContact());
                        customers.remove(i);
                    } else {
                        System.out.println("-----------------------------");
                        System.out.println("Unsuccessfully Deleted");
                        System.out.println("-----------------------------");
                    }
                    break;
                }
            }

            if (!(ifound == 1)) {
                ifound = 0;
                System.out.println("\tCustomer Record doesn't exist! Please press enter to return to the menu or No to continue.\nResponse:");
                resp = input.readLine();
                continue;
            }
        } while (resp.equalsIgnoreCase("NO") || resp.equalsIgnoreCase("N"));
    }
}

class CreateClass extends Customers {

    InputStreamReader reader = new InputStreamReader(System.in);
    BufferedReader input = new BufferedReader(reader);

    int ifound = 0;
    boolean checker;

    public CreateClass(ArrayList<Customers> customers) throws Exception {

        String resp = null;

        int count = 0;

        do {
            System.out.println("-----#ADD RECORD#-----");
            System.out.print("Enter Customer Number: ");
            String cNumber = input.readLine();

            for (Customers p : customers) {
                if (p.getcNumber().equals(cNumber)) {
                    ifound = 1;
                    break;
                }
            }

            if (!(ifound == 0)) {
                ifound = 0;
                System.out.println("\tThis Customer Number already exists! Please try a different Customer Number");
                checker = true;
                break;
            }

            System.out.printf("Enter Name: ");
            String cName = input.readLine();

            System.out.printf("Enter Address: ");
            String cAddress = input.readLine();

            System.out.printf("Enter Contact#: ");
            String cContact = input.readLine();

            System.out.printf("\n\nRecordList:\n\nCustomer [%d]:\n%-7S%-10S%-10S%-7S\n\nDo you want to save? [Yes|No]\nResponse: ",
            (count + 1),
            ("\tCustomer Number: " + cNumber),
            ("\tCustomer Name: " + cName),
            ("\tCustomer Address: " + cAddress),
            ("\tCustomer Contact#: " + cContact));
            resp = input.readLine();
            count = 0;

            if (resp.equalsIgnoreCase("YES") || resp.equalsIgnoreCase("Y")) 
            {
                customers.add(new Customers(cNumber, cName, cAddress, cContact));
                super.addRecords(customers);
                System.out.printf("\n\tThe record has been saved!\n\t\tPlease press enter to return to the menu or No to continue.\nResponse: ");
                resp = input.readLine();
                if(resp.equalsIgnoreCase("NO") || resp.equalsIgnoreCase("N"))
                {
                    System.out.print("Input Another Record? [Y/N]: ");
                    resp = input.readLine();
                    if (resp.equalsIgnoreCase("Y")) 
                    {
                        checker = true;
                    } 
                    else 
                    {
                        System.out.println("-----------------------------");
                        System.out.println("Press Enter Key to go back to Menu....");
                        System.out.println("-----------------------------");
                        resp = input.readLine();
                        checker = false;
                    }
                }
                else
                {
                    checker = false;
                }
            } 
            else 
            {
                System.out.printf("\n\tThe record didn't save!\n\t\tPress Enter to return to the menu or No to continue.\nResponse: ");
                resp = input.readLine();
                if(resp.equalsIgnoreCase("NO") || resp.equalsIgnoreCase("N"))
                {
                    System.out.print("Input Another Record? [Y/N]: ");
                    resp = input.readLine();
                    if (resp.equalsIgnoreCase("Y")) 
                    {
                        checker = true;
                    } 
                    else 
                    {
                        System.out.println("-----------------------------");
                        System.out.println("Press Enter Key to go back to Menu....");
                        System.out.println("-----------------------------");
                        resp = input.readLine();
                        checker = false;
                    }
                }
                else
                {
                    checker = false;
                }
            }
        } while (checker);

        Main.MainCall(customers);
    }
}

class Customers {

    private String cNumber;
    private String cName;
    private String cAddress;
    private String cContact;

    public Customers(String cNumber, String cName, String cAddress, String cContact) {
        this.cNumber = cNumber;
        this.cName = cName;
        this.cAddress = cAddress;
        this.cContact = cContact;
    }

    public Customers() {
    }

    public String toString() {
        return "\nCustomer Number=" + cNumber + ", Name=" + cName + ", Address=" + cAddress + ", Contact#=" + cContact + "\n";
    }

    public void setcNumber(String cNumber) {
        this.cNumber = cNumber;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
    }

    public void setcContact(String cContact) {
        this.cContact = cContact;
    }

    public String getcNumber() {
        return cNumber;
    }

    public String getcName() {
        return cName;
    }

    public String getcAddress() {
        return cAddress;
    }

    public String getcContact() {
        return cContact;
    }

    public void addRecords(ArrayList<Customers> customers) {
        // Not needed, can be removed
    }

    // Not needed, can be removed
    public ArrayList<Customers> giveRecords() {
        return null;
    }
}
