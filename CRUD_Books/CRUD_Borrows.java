package CRUD_Books;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CRUD_Borrows {
    public void insert_data() {
        DB_Connection obj_DB_connection = new DB_Connection();
        Connection connection = obj_DB_connection.get_connection();
        PreparedStatement ps = null;

        try{
            String query = "insert into borrows(customer_id, b_isbn, borrow_date) values (?, ?, ?)";
            ps = connection.prepareStatement(query);
            String[] data = takeInput();
            ps.setString(1, data[0]);
            ps.setString(2, data[1]);
            ps.setString(3, data[2]);
            System.out.println(ps);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error Occurred!\nProbably non-Existenet FK reference constraints");
        }
    }
    public void read_data_for_specific_book(){
        DB_Connection obj_DB_connection = new DB_Connection();
        Connection connection = obj_DB_connection.get_connection();
        String b_isbn = JOptionPane.showInputDialog("Enter the isbn of that book: ");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String query = "select * from borrows where b_isbn = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, b_isbn);
            System.out.println(ps);
            rs = ps.executeQuery();

            while(rs.next()){
                System.out.println("isbn: "+ rs.getString("isbn"));
                System.out.println("customer_id: "+ rs.getString("customer_id"));
                System.out.println("DateOfBorrow: "+ rs.getString("borrow_date"));

                System.out.println("--------------------------------");
                JOptionPane.showMessageDialog(null, "isbn: "+ rs.getString("isbn")+"\ncustomer_id: "+ rs.getString("customer_id")+"\nborrow_date: "+rs.getString("borrow_date"));
            }

        }catch (Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Not Found");
        }
    }
    public void read_data_for_specific_customer(){
        DB_Connection obj_DB_connection = new DB_Connection();
        Connection connection = obj_DB_connection.get_connection();
        String customer_id = JOptionPane.showInputDialog("Enter the id of that customer: ");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String query = "select * from borrows where customer_id = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, customer_id);
            System.out.println(ps);
            rs = ps.executeQuery();

            while(rs.next()){
                System.out.println("customer_id: "+ rs.getString("customer_id"));
                System.out.println("isbn: "+ rs.getString("isbn"));
                System.out.println("DateOfBorrow: "+ rs.getString("borrow_date"));

                System.out.println("--------------------------------");
                JOptionPane.showMessageDialog(null, "customer_id: "+ rs.getString("customer_id")+"\nisbn: "+ rs.getString("isbn")+"\nborrow_date: "+rs.getString("borrow_date"));
            }

        }catch (Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Not Found");
        }
    }
    public void show_all_records(){
        DB_Connection obj_DB_Connection = new DB_Connection();
        Connection connection = obj_DB_Connection.get_connection();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            String query = "select * from borrows";
            ps = connection.prepareStatement(query);


            System.out.println(ps);
            rs = ps.executeQuery();

            while (rs.next()){
                System.out.println("b_isbn : "+rs.getString("b_isbn"));
                System.out.println("customer_id : "+rs.getString("customer_id"));
                System.out.println("borrow_date : "+rs.getString("borrow_date"));

                System.out.println("***************************");
                // JOptionPane.showMessageDialog(null, "isbn: "+ rs.getString("isbn")+"\nauthor_name: "+ rs.getString("author_name")+"\ndomain: "+rs.getString("domain"));
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void update_data(){
        DB_Connection obj_DB_connection = new DB_Connection();
        Connection connection = obj_DB_connection.get_connection();

        PreparedStatement ps = null;
        try{
            String query = "update borrows set customer_id = ?, borrow_date = ? where b_isbn = ?";
            ps = connection.prepareStatement(query);
            String[] data = takeUpdate();
            ps.setString(1, data[0]);
            ps.setString(2, data[1]);
            ps.setString(3, data[2]);
            //ps.setString(4, data[1]);
            System.out.println(ps);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void delete_data(){
        DB_Connection DB_connectionObj = new DB_Connection();
        Connection connection = DB_connectionObj.get_connection();
        String [] data = take_deleta_info();
        PreparedStatement ps = null;
        try{
            String query = "delete from borrows where b_isbn = ? AND customer_id = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, data[1]);
            ps.setString(2, data[0]);
            System.out.println(ps);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Process of Delete Done Successfully");
        }catch (Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, " Error Occurred!");

        }
    }
    public  String[] takeInput(){
        String[] data = new String[3];
        JTextField customer_idField = new JTextField(10);
        JTextField b_isbnField = new JTextField(10);
        JTextField borrow_dateField = new JTextField(10);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("customer_id:"));
        myPanel.add(customer_idField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("b_isbn:"));
        myPanel.add(b_isbnField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Borrow_Date:"));
        myPanel.add(borrow_dateField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please Enter customer_id, b_isbn and Borrow_Date Values", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println("customer_id value: " + customer_idField.getText());
            System.out.println("b_isbn value: " + b_isbnField.getText());
            System.out.println("Borrow_Date value: " + borrow_dateField.getText());
            data[0] = customer_idField.getText();
            data[1] = b_isbnField.getText();
            data[2] = borrow_dateField.getText();
        }
        return data;
    }
    public  String[] takeUpdate(){
        String[] data = new String[3];
       // JTextField customer_idField = new JTextField(10);
        JTextField new_customer_idField = new JTextField(10);
        JTextField b_isbnField = new JTextField(10);
        JTextField new_borrow_dateField = new JTextField(10);

        JPanel myPanel = new JPanel();
        /*myPanel.add(new JLabel("customer_id:"));
        myPanel.add(customer_idField);*/
        //myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("new_customer_id:"));
        myPanel.add(new_customer_idField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("b_isbn:"));
        myPanel.add(b_isbnField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("update_Borrow_Date:"));
        myPanel.add(new_borrow_dateField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please Enter the Update for borrowing the book", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            //System.out.println("customer_id value: " + customer_idField.getText());
            System.out.println("new customer_id value: " + new_customer_idField.getText());
            System.out.println("b_isbn value: " + b_isbnField.getText());
            System.out.println("Update Borrow_Date value: " + new_borrow_dateField.getText());
            //data[0] = customer_idField.getText();
            data[0] = new_customer_idField.getText();
            data[1] = new_borrow_dateField.getText();
            data[2] = b_isbnField.getText();

        }
        return data;
    }
    public String [] take_deleta_info(){
        String data []= new String[2];
        JTextField customer_idField = new JTextField(5);
        JTextField b_isbnField = new JTextField(5);


        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("b_isbn:"));
        myPanel.add(b_isbnField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("customer_id:"));
        myPanel.add(customer_idField);


        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please Enter The customer_id and the isbn of the book borrowed to delete", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println("customer_id value: " + customer_idField.getText());
            System.out.println("b_isbn value: " + b_isbnField.getText());
            data[0] = customer_idField.getText();
            data[1] = b_isbnField.getText();
        }
        return data;
    }

}
