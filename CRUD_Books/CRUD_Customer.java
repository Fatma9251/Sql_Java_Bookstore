package CRUD_Books;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CRUD_Customer {
    public void insert_data() {
        DB_Connection obj_DB_connection = new DB_Connection();
        Connection connection = obj_DB_connection.get_connection();
        PreparedStatement ps = null;

        try{
            String query = "insert into customer(id, name, age) values (?, ?, ?)";
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
        String id = JOptionPane.showInputDialog("Enter the id of that Customer: ");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String query = "select * from customer where id = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            System.out.println(ps);
            rs = ps.executeQuery();

            while(rs.next()){
                if(rs != null){
                    System.out.println("id: "+ rs.getString("id"));
                    System.out.println("name: "+ rs.getString("name"));
                    System.out.println("age: "+ rs.getString("age"));

                    System.out.println("--------------------------------");
                    JOptionPane.showMessageDialog(null, "id: "+ rs.getString("id")+"\nname: "+ rs.getString("name")+"\nage: "+rs.getString("age"));

                } else{
                    JOptionPane.showMessageDialog(null, "Not Found");
                }
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

            String query = "select * from customer";
            ps = connection.prepareStatement(query);


            System.out.println(ps);
            rs = ps.executeQuery();

            while (rs.next()){
                System.out.println("id : "+rs.getString("id"));
                System.out.println("name : "+rs.getString("name"));
                System.out.println("age : "+rs.getString("age"));

                System.out.println("***************************");
                // JOptionPane.showMessageDialog(null, "id: "+ rs.getString("id")+"\name: "+ rs.getString("name")+"\nage: "+rs.getString("age"));
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
            String query = "update customer set id = ?, name = ? , age = ? where id = ?";
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
        String id = JOptionPane.showInputDialog("Enter the id of that customer: ");
        PreparedStatement ps = null;
        try{
            String query = "delete from customer where id = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
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
        JTextField idField = new JTextField(10);
        JTextField nameField = new JTextField(10);
        JTextField ageField = new JTextField(10);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("id:"));
        myPanel.add(idField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("name:"));
        myPanel.add(nameField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("age:"));
        myPanel.add(ageField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please Enter id, Customer Name and Age Values", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println("id value: " + idField.getText());
            System.out.println("name value: " + nameField.getText());
            System.out.println("age value: " + ageField.getText());
            data[0] = idField.getText();
            data[1] = nameField.getText();
            data[2] = ageField.getText();
        }
        return data;
    }
    public  String[] takeUpdate(){
        String[] data = new String[4];
        JTextField idField = new JTextField(10);
        JTextField new_idField = new JTextField(10);
        JTextField nameField = new JTextField(10);
        JTextField ageField = new JTextField(10);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("current id:"));
        myPanel.add(idField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("new id:"));
        myPanel.add(new_idField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("new name:"));
        myPanel.add(nameField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("new age:"));
        myPanel.add(ageField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please Enter the Update for id, name and age Values", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println("id value: " + idField.getText());
            System.out.println("new id value: " + new_idField.getText());
            System.out.println("new name value: " + nameField.getText());
            System.out.println("new age value: " + ageField.getText());
            data[0] = idField.getText();
            data[1] = new_idField.getText();
            data[2] = nameField.getText();
            data[3] = ageField.getText();

        }
        return data;
    }
}
