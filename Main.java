import CRUD_Books.CRUD_Book;
import CRUD_Books.CRUD_Borrows;
import CRUD_Books.CRUD_Customer;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        start_op();


    }

    public static void start_op() {
        int order;
        CRUD_Book book = new CRUD_Book();
        CRUD_Customer customer = new CRUD_Customer();
        CRUD_Borrows borrows = new CRUD_Borrows();
        String choice = JOptionPane.showInputDialog("WELCOME TO BOOKSTORE! \n Enter The Table to proceed with: \n1) Book.\n2) Customer.\n3) Borrows. \n Or 'q' to Quit");
        if (choice.equals("q")) {
            JOptionPane.showMessageDialog(null, "Thank You!");
            System.exit(0);
        }
        if (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4") && !choice.equals("5")) {
            JOptionPane.showMessageDialog(null, "Invalid Input, Please Try AGAIN!");
            continue_();
            start_op();
        }
        switch (choice) {
            case "1":
                order = takeOrder("Book");
                switch (order) {
                    case 1: // insert
                        book.insert_data();
                        continue_();
                        start_op();
                        break;
                    case 2:
                        book.read_data();
                        continue_();
                        start_op();
                        break;
                    case 3:
                        book.delete_data();
                        continue_();
                        start_op();
                        break;
                    case 4:
                        book.update_data();
                        continue_();
                        start_op();
                        break;
                    case 5:
                        book.show_all_records();
                        continue_();
                        start_op();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid Input");
                        start_op();
                }
            case "2":
                order = takeOrder("Customer");
                switch (order) {
                    case 1: // insert
                        customer.insert_data();
                        continue_();
                        start_op();
                        break;
                    case 2:
                        customer.read_data();
                        continue_();
                        start_op();
                        break;
                    case 3:
                        customer.delete_data();
                        continue_();
                        start_op();
                        break;
                    case 4:
                        customer.update_data();
                        continue_();
                        start_op();
                        break;
                    case 5:
                        customer.show_all_records();
                        continue_();
                        start_op();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid Input");
                        start_op();
                }

            case "3":
                order = takeOrder("Borrows");
                switch (order) {
                    case 1: // insert
                        borrows.insert_data();
                        continue_();
                        start_op();
                        break;
                    case 2:
                        int sub_answer = Integer.parseInt(JOptionPane.showInputDialog("1) Read Data about a Specific Book.\n2) Read Data about a Specific Customer."));
                        switch (sub_answer) {
                            case 1:
                                borrows.read_data_for_specific_book();
                                continue_();
                                start_op();
                                break;
                            case 2:
                                borrows.read_data_for_specific_customer();
                                continue_();
                                start_op();
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Couldn't Handle your input Please TRY AGAIN");
                                start_op();
                        }
                        break;
                    case 3:
                        borrows.delete_data();
                        continue_();
                        start_op();
                        break;
                    case 4:
                        borrows.update_data();
                        continue_();
                        start_op();
                        break;
                    case 5:
                        borrows.show_all_records();
                        continue_();
                        start_op();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid Input");
                        start_op();
                }
            default:

        }
    }

    public static int takeOrder(String table) {
       // UIManager.put("OptionPane.minimumSize",new Dimension(500,500));
        int order = Integer.parseInt(JOptionPane.showInputDialog("What operation do you wanna proceed on table " + table + "?\n1) Insert new" +
                "record.\n2) Show records according to some criteria.\n3) Delete some record.\n4) Update a record\n5) Show All records in " + table + "."));
        return order;
    }

    public static void continue_() {
        int reply = JOptionPane.showConfirmDialog(null,
                "DONE\n\nRepeat the CRUD Process AGAIN?", "Continue CRUD Process!", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.NO_OPTION)
            System.exit(0);
    }
}