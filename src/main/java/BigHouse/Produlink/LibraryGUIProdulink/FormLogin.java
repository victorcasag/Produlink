package BigHouse.Produlink.LibraryGUIProdulink;

import BigHouse.Produlink.LibraryProdulink.Login.ModelLogin;
import BigHouse.Produlink.LibraryProdulink.Login.ServiceLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import javax.swing.*;
import java.awt.*;
import java.net.ConnectException;

public class FormLogin extends JDialog {
    JButton btnLogin = new JButton();
    JButton btnCancel = new JButton();
    JTextField txfLogin = new JTextField("admin");
    JPasswordField txfPassword = new JPasswordField("admin");

    public FormLogin(){
        setSize(500, 200);
        setModal(true);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        BuildForm();
        ActionsButtons();
        btnLogin.doClick();
    }

    private void BuildForm(){

        JLabel lblCopyright = new JLabel("Copyright by BigHouse V 1.0 ALPHA");
        lblCopyright.setBounds(130, 20, 500, 250);
        lblCopyright.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        getContentPane().add(lblCopyright);

        JLabel lblLogin = new JLabel("Username");
        lblLogin.setBounds(110, 10, 200, 25);
        lblLogin.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        getContentPane().add(lblLogin);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(110, 50, 200, 25);
        lblPassword.setFont(new Font(Font.MONOSPACED, Font.BOLD, 11));
        getContentPane().add(lblPassword);

        txfLogin.setBounds(170, 10, 200, 25);
        txfLogin.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        getContentPane().add(txfLogin);

        txfPassword.setBounds(170, 50, 200, 25);
        txfPassword.setFont(new Font(Font.MONOSPACED, Font.BOLD, 11));
        getContentPane().add(txfPassword);

        btnCancel = new JButton("Quit");
        btnCancel.setBounds(145, 90, 100, 25);
        btnCancel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 11));
        getContentPane().add(btnCancel);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(255, 90, 100, 25);
        btnLogin.setFont(new Font(Font.MONOSPACED, Font.BOLD, 11));
        getContentPane().add(btnLogin);
    }

    private void ActionsButtons(){
        btnLogin.addActionListener(e->{
            String username = txfLogin.getText();
            String password = new String(txfPassword.getPassword());

            try {
                if(username.isEmpty()) {
                    throw new Exception("Error: Username empty");
                }else if(password.isEmpty()) {
                    throw new Exception("Error: Password empty");
                }

                ServiceLogin serviceLogin = new ServiceLogin();

                ObjectMapper objectMapper = new ObjectMapper();


                System.out.println(serviceLogin.FindById(0));


                if (serviceLogin.FindById(0).contains("Not Found")){

                    ModelLogin login = new ModelLogin();

                    login.setLogin("admin");
                    login.setName("ADMIN");
                    login.setRole("ADMIN");
                    login.setPassword("admin");

                    serviceLogin.InsertNewLogin(login);
                }

                ModelLogin login = objectMapper.readValue(serviceLogin.FindByUsername(username), ModelLogin.class);

               if (login.getLogin().equals(txfLogin.getText()) && login.getPassword().equals(txfPassword.getText())){
                   dispose();
                   new FormMenu(txfLogin.getText(), login.getRole()).setVisible(true);
                }else {
                   JOptionPane.showMessageDialog(null, "Error: Invalid user or password");
               }

            }catch (MismatchedInputException e1){
                JOptionPane.showMessageDialog(null, "Error: Login not found");
            } catch (ConnectException e2){
                JOptionPane.showMessageDialog(null, "Error: API not working");
            } catch (Exception e3){
                JOptionPane.showMessageDialog(null, "Error: " + e3.getMessage());
            }
        });

        btnCancel.addActionListener(e->{
            System.exit(0);
        });
    }
}