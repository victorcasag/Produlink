package BigHouse.Produlink.LibraryGUIProdulink;

import BigHouse.Produlink.LibraryProdulink.Address.ModelAddress;
import BigHouse.Produlink.LibraryProdulink.Client.ModelClient;
import BigHouse.Produlink.LibraryProdulink.Login.ModelLogin;
import BigHouse.Produlink.LibraryProdulink.Login.ServiceLogin;
import BigHouse.Produlink.LibraryProdulink.Observer.Observer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;

import java.awt.Font;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FormCreateLogin extends JInternalFrame implements Observer {

        private String[] strProfile = {"Select","ADMIN","Register","Finance"};

        private JTextField txfUsername = new JTextField();
        private JTextField txfName= new JTextField();
        private JPasswordField txfPassword = new JPasswordField();
        private JPasswordField txfConfirmPassword = new JPasswordField();

        private JButton btnSave = new JButton();
        private JButton btnCancel = new JButton();
        private JButton btnConsult = new JButton();

        JComboBox<String> cbProfile = new JComboBox<>();

        JPanel panel = new JPanel();

        public FormCreateLogin () {

            setSize(370,290);
            setTitle("Create Login");
            setResizable(false);

            setIconifiable(true);
            setMaximizable(false);
            setClosable(true);
            setResizable(false);
            setLocation(450, 200);
            setResizable(false);

            buildWindow();
            actionsButtons();

            setVisible(true);
        }

        public void buildWindow() {

            panel = new JPanel();
            panel.setBorder(new EmptyBorder(5,5,5,5));
            setContentPane(panel);
            panel.setLayout(null);

            JLabel lblName = new JLabel("Name");
            lblName.setBounds(25, 20, 100, 25);
            lblName.setFont(new Font(Font.MONOSPACED, Font.BOLD, 11));
            panel.add(lblName);

            JLabel lblUsername = new JLabel("Login");
            lblUsername.setBounds(25, 50, 100, 25);
            lblUsername.setFont(new Font(Font.MONOSPACED, Font.BOLD, 11));
            panel.add(lblUsername);

            JLabel lblPassword = new JLabel("Password");
            lblPassword.setBounds(25, 80, 100, 25);
            lblPassword.setFont(new Font(Font.MONOSPACED, Font.BOLD, 11));
            panel.add(lblPassword);

            JLabel lblConfirmPassword = new JLabel("Confirm password");
            lblConfirmPassword.setBounds(25, 110, 150, 25);
            lblConfirmPassword.setFont(new Font(Font.MONOSPACED, Font.BOLD, 11));
            panel.add(lblConfirmPassword);

            JLabel lblProfile = new JLabel("Role");
            lblProfile.setBounds(25, 140, 100, 25);
            lblProfile.setFont(new Font(Font.MONOSPACED, Font.BOLD, 11));
            panel.add(lblProfile);

            txfName = new JTextField();
            txfName.setBounds(150, 20, 150, 25);
            panel.add(txfName);

            txfUsername = new JTextField();
            txfUsername.setBounds(150, 50, 150, 25);
            panel.add(txfUsername);

            txfPassword = new JPasswordField();
            txfPassword.setBounds(150, 80, 150, 25);
            panel.add(txfPassword);

            txfConfirmPassword = new JPasswordField();
            txfConfirmPassword.setBounds(150, 110, 150, 25);
            panel.add(txfConfirmPassword);

            cbProfile = new JComboBox<String>(strProfile);
            cbProfile.setBounds(150, 140, 140, 25);
            panel.add(cbProfile);

            btnConsult = new JButton("Consult");
            btnConsult.setBounds(130, 190, 100, 25);
            btnConsult.setFont(new Font(Font.MONOSPACED, Font.BOLD, 11));
            panel.add(btnConsult);

            btnCancel = new JButton("Cancel");
            btnCancel.setBounds(240, 190, 100, 25);
            btnCancel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 11));
            panel.add(btnCancel);

            btnSave = new JButton("Save");
            btnSave.setBounds(25, 190, 100, 25);
            btnSave.setFont(new Font(Font.MONOSPACED, Font.BOLD, 11));
            panel.add(btnSave);
        }

    ServiceLogin serviceLogin = new ServiceLogin();
        public void actionsButtons() {

            btnSave.addActionListener(e->{

                if (validations() == true) {return;}

                try {
                    ModelLogin login = new ModelLogin();
                    login.setLogin(txfUsername.getText());
                    login.setPassword(txfPassword.getText());
                    login.setName(txfName.getText());
                    login.setRole(String.valueOf(cbProfile.getSelectedItem()));

                    if (serviceLogin.Exists(login.getLogin())){
                        if(JOptionPane.showConfirmDialog(null, "Already exists. \nDo you want to overwrite?", "Already Exists", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){

                            ObjectMapper objectMapper = new ObjectMapper();

                            login = objectMapper.readValue(serviceLogin.FindByUsername(login.getLogin()), ModelLogin.class);

                            login.setLogin(txfUsername.getText());
                            login.setPassword(txfPassword.getText());
                            login.setName(txfName.getText());
                            login.setRole(String.valueOf(cbProfile.getSelectedItem()));

                            serviceLogin.InsertNewLogin(login);
                        }
                    }else{
                        serviceLogin.InsertNewLogin(login);
                    }

                    JOptionPane.showMessageDialog(null, "Inserted login");

                    txfName.setText("");
                    txfUsername.setText("");
                    txfPassword.setText("");
                    txfConfirmPassword.setText("");
                    cbProfile.setSelectedIndex(0);

                }catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Error inserting login: " + e1.getMessage());
                }
            });


            btnConsult.addActionListener(e->{

                ModelLogin objLogin = new ModelLogin();

                new FormConsultGeneric(objLogin, new String[]{"ID Login", "Login"}, FormCreateLogin.this).setVisible(true);

            });

            btnCancel.addActionListener(e->{
                dispose();
            });

        }

        public boolean validations() {
            boolean flag = false;

            String username = txfUsername.getText();
            String password = new String(txfPassword.getPassword());
            String confirmPassword = new String(txfConfirmPassword.getPassword());

            int profile = cbProfile.getSelectedIndex();
            try {
                if(username.isEmpty()) {
                    throw new Exception("Login field empty");
                }else if(password.isEmpty()) {
                    throw new Exception("Password field empty");
                }else if(confirmPassword.isEmpty()) {
                    throw new Exception("Confirm password field empty");
                }else if(profile == 0) {
                    throw new Exception("Select role");
                }else if(!password.equals(confirmPassword)) {
                    throw new Exception("Passwords are not the same");
                }
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                flag = true;
            }
            return flag;

        }

        @Override
        public void Update(Object obj) {
            ModelLogin login = (ModelLogin) obj;
            txfName.setText(login.getName());
            txfUsername.setText(login.getLogin());
            txfPassword.setText(login.getPassword());
            txfConfirmPassword.setText(login.getPassword());
            cbProfile.setSelectedItem(login.getRole());
        }

}

