package CRUD_Books;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CRUD_Book{
    public void insert_data() {
        DB_Connection obj_DB_connection = new DB_Connection();
        Connection connection = obj_DB_connection.get_connection();
        PreparedStatement ps = null;

        try{
           String query = "insert into book(isbn, author_name, domain) values (?, ?, ?)";
           ps = connection.prepareStatement(query);
           String[] data = takeInput();
           ps.setString(1, data[0]);
           ps.setString(2, data[1]);
           ps.setString(3, data[2]);
            System.out.println(ps);
           ps.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error Occurred!");
        }
    }
    public void read_data(){
        DB_Connection obj_DB_connection = new DB_Connection();
        Connection connection = obj_DB_connection.get_connection();
        String isbn = JOptionPane.showInputDialog("Enter the isbn of that book: ");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String query = "select * from book where isbn = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, isbn);
            System.out.println(ps);
            rs = ps.executeQuery();

            while(rs.next()){
                System.out.println("isbn: "+ rs.getString("isbn"));
                System.out.println("author_name: "+ rs.getString("author_name"));
                System.out.println("domain: "+ rs.getString("domain"));

                System.out.println("--------------------------------");
                JOptionPane.showMessageDialog(null, "isbn: "+ rs.getString("isbn")+"\nauthor_name: "+ rs.getString("author_name")+"\ndomain: "+rs.getString("domain"));
            }

        }catch (Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error Occurred!");
        }
    }
    public void show_all_records(){
        DB_Connection obj_DB_Connection = new DB_Connection();
        Connection connection = obj_DB_Connection.get_connection();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            String query = "select * from book";
            ps = connection.prepareStatement(query);


            System.out.println(ps);
            rs = ps.executeQuery();

            while (rs.next()){
                System.out.println("isbn : "+rs.getString("isbn"));
                System.out.println("author : "+rs.getString("author_name"));
                System.out.println("domain : "+rs.getString("domain"));

                System.out.println("***************************");
                // JOptionPane.showMessageDialog(null, "isbn: "+ rs.getString("isbn")+"\nauthor_name: "+ rs.getString("author_name")+"\ndomain: "+rs.getString("domain"));
            }

        }catch (Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error Occurred!");
        }
    }
    public void update_data(){
        DB_Connection obj_DB_connection = new DB_Connection();
        Connection connection = obj_DB_connection.get_connection();

        PreparedStatement ps = null;
        try{
            String query = "update book set isbn = ?, author_name = ? , domain = ? where isbn = ?";
            ps = connection.prepareStatement(query);
            String[] data = takeUpdate();
            ps.setString(1, data[0]);
            ps.setString(2, data[2]);
            ps.setString(3, data[3]);
            ps.setString(4, data[1]);
            System.out.println(ps);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error Occurred!");
        }
    }
    public void delete_data(){
        DB_Connection DB_connectionObj = new DB_Connection();
        Connection connection = DB_connectionObj.get_connection();
        String isbn = JOptionPane.showInputDialog("Enter the isbn of that book: ");
        PreparedStatement ps = null;
        try{
            String query = "delete from book where isbn = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, isbn);
            System.out.println(ps);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Process of Delete Done Successfully");
        }catch (Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error Occurred!");
        }
    }

    public  String[] takeInput(){
        String[] data = new String[3];
        JTextField isbnField = new JTextField(10);
        JTextField author_nameField = new JTextField(10);
        JTextField domainField = new JTextField(10);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("isbn:"));
        myPanel.add(isbnField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("author_name:"));
        myPanel.add(author_nameField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("domain:"));
        myPanel.add(domainField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please Enter isbn, Author_Name and domain Values", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println("isbn value: " + isbnField.getText());
            System.out.println("author value: " + author_nameField.getText());
            System.out.println("domain value: " + domainField.getText());
            data[0] = isbnField.getText();
            data[1] = author_nameField.getText();
            data[2] = domainField.getText();
        }
        return data;
    }
    public  String[] takeUpdate(){
        String[] data = new String[4];
        JTextField isbnField = new JTextField(10);
        JTextField new_isbnField = new JTextField(10);
        JTextField author_nameField = new JTextField(10);
        JTextField domainField = new JTextField(10);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("current isbn:"));
        myPanel.add(isbnField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("new isbn:"));
        myPanel.add(new_isbnField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("new author_name:"));
        myPanel.add(author_nameField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("new domain:"));
        myPanel.add(domainField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please Enter The Update for isbn, Author_Name and domain Values", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println("isbn value: " + isbnField.getText());
            System.out.println("new isbn value: " + new_isbnField.getText());
            System.out.println("new author value: " + author_nameField.getText());
            System.out.println("new domain value: " + domainField.getText());
            data[0] = isbnField.getText();
            data[1] = new_isbnField.getText();
            data[2] = author_nameField.getText();
            data[3] = domainField.getText();

        }
        return data;
    }
}
