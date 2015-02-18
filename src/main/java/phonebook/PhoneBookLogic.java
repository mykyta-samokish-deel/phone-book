package phonebook;

import java.sql.SQLException;
import java.util.*;

public class PhoneBookLogic {

    private ArrayList<Contact> phoneBook;
    public DBStorage dataBase = new DBStorage();


    public PhoneBookLogic() {
        this.phoneBook = new ArrayList<Contact>();
    }

    public void addUser() {
        saveToDB();
    }

    public void saveToDB() {
        try {
            dataBase.insertNameIntoTable();
            dataBase.insertPhoneIntoTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteFromDB() {
        try {
            dataBase.deleteRecordFromTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void readFromDB() {
        try {
            dataBase.selectRecordsFromTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void editContact() {
        try {
            dataBase.updateRecordToTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (Contact cont : phoneBook) {
            str += cont.toString() + "\n";
        }
        return str;
    }

    public void menuInterface() {
        System.out.println("1: Add Contact");
        System.out.println("2: Edit Contact");
        System.out.println("3: Delete From DataBase");
        System.out.println("4: Read Data From DataBase");
        System.out.println("0: Exit");
    }

    public void menu() {
        boolean flag = true;
        Scanner switchValue = new Scanner(System.in);
        while (flag) {
            menuInterface();
            System.out.print("What to Choose: ");
            int option = switchValue.nextInt();
            switch (option) {
                case 1:
                    addUser();
                    break;
                case 2:
                    editContact();
                    break;
                case 3:
                    deleteFromDB();
                    break;
                case 4:
                    readFromDB();
                    break;
                case 0:
                    System.out.println("BYE!");
                    flag = false;
                    break;
                default:
                    System.out.println("Wrong number! Try again.");
                    break;
            }
        }
    }
}
