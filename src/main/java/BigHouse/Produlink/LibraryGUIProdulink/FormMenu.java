package BigHouse.Produlink.LibraryGUIProdulink;

import BigHouse.Produlink.LibraryProdulink.Backup.Backup;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.ParseException;

public class FormMenu extends JFrame {
    private JMenuBar menuBar = new JMenuBar();

    private JMenu menuSystem = new JMenu();
    private JMenu menuClient = new JMenu();
    private JMenu menuHelp = new JMenu();
    private JMenu menuReport = new JMenu();

    private JMenuItem menuItemReports = new JMenuItem();
    private JMenuItem menuItemHelp = new JMenuItem();

    private JMenuItem menuItemClient = new JMenuItem();
    private JMenuItem menuItemClientPayment = new JMenuItem();

    private JMenuItem menuItemConfigure = new JMenuItem();
    private JMenuItem menuItemCreateUser = new JMenuItem();
    private JMenuItem menuItemExit = new JMenuItem();

    private JButton btnClient = new JButton();
    private JButton btnSell = new JButton();
    private JButton btnBackupSystem = new JButton();
    private JButton btnExit = new JButton();
    private JDesktopPane desktop = new JDesktopPane();

    public FormMenu(final String login, final String role) throws ParseException, IOException, InterruptedException {

        setSize(1600, 900);
        setTitle("Produlink V 1.0 ALPHA Logged: " + login);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        BuildForm();
        ActionsButtons();
        RolePermissions(role);
        btnSell.doClick();
    }

    public void RolePermissions(final String role) {

        if (role.equals("ADMIN")) menuItemCreateUser.setEnabled(true);

    }

    public void BuildForm() {

        desktop = new JDesktopPane();
        desktop.setBackground(Color.LIGHT_GRAY);
        setContentPane(desktop);

        setJMenuBar(menuBar);

        /*
         * Bar Menu System
         */

        menuSystem = new JMenu("System");
        menuBar.add(menuSystem);

        menuItemConfigure = new JMenuItem("Configuration");
        menuSystem.add(menuItemConfigure);

        menuItemCreateUser = new JMenuItem("Create User");
        menuSystem.add(menuItemCreateUser);

        menuItemExit = new JMenuItem("Quit");
        menuSystem.add(menuItemExit);

        /*
         * Bar Menu Client
         */

        menuClient = new JMenu("Client");
        menuBar.add(menuClient);

        menuItemClient = new JMenuItem("Register");
        menuClient.add(menuItemClient);

        menuItemClientPayment = new JMenuItem("Sell");
        menuClient.add(menuItemClientPayment);

        /*
         * Bar Menu Help
         */

        menuReport = new JMenu("Report");
        menuBar.add(menuReport);

        menuItemReports = new JMenuItem("Report");
        menuReport.add(menuItemReports);

        /*
         * Bar Menu Help
         */

        menuHelp = new JMenu("Help");
        menuBar.add(menuHelp);

        menuItemHelp = new JMenuItem("Support");
        menuHelp.add(menuItemHelp);

        menuItemHelp = new JMenuItem("About");
        menuHelp.add(menuItemHelp);

        /*
         * Buttons
         */

        btnClient = new JButton("Client");
        btnClient.setBounds(25, 150, 75, 70);
        btnClient.setFont(new Font(Font.MONOSPACED, Font.BOLD, 10));
        btnClient.setIcon(new ImageIcon("C:\\Users\\victo\\Desktop\\ProjectGymUNESC\\ClassImageLibrary\\src\\48x48\\Client.png"));
        btnClient.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnClient.setHorizontalTextPosition(SwingConstants.CENTER);
        getContentPane().add(btnClient);

        btnSell = new JButton("Sell");
        btnSell.setBounds(25, 245, 75, 70);
        btnSell.setFont(new Font(Font.MONOSPACED, Font.BOLD, 10));
        btnSell.setIcon(new ImageIcon("C:\\Users\\victo\\Desktop\\ProjectGymUNESC\\ClassImageLibrary\\src\\48x48\\Cashier.png"));
        btnSell.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnSell.setHorizontalTextPosition(SwingConstants.CENTER);
        getContentPane().add(btnSell);

        btnBackupSystem = new JButton("Backup");
        btnBackupSystem.setBounds(25, 340, 75, 70);
        btnBackupSystem.setFont(new Font(Font.MONOSPACED, Font.BOLD, 11));
        btnBackupSystem.setIcon(new ImageIcon("C:\\Users\\victo\\Desktop\\ProjectGymUNESC\\ClassImageLibrary\\src\\48x48\\Backup.png"));
        btnBackupSystem.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnBackupSystem.setHorizontalTextPosition(SwingConstants.CENTER);
        getContentPane().add(btnBackupSystem);

        btnExit = new JButton("Quit");
        btnExit.setBounds(25, 435, 75, 70);
        btnExit.setFont(new Font(Font.MONOSPACED, Font.BOLD, 10));
        btnExit.setIcon(new ImageIcon("C:\\Users\\victo\\Desktop\\ProjectGymUNESC\\ClassImageLibrary\\src\\48x48\\Exit.png"));
        btnExit.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnExit.setHorizontalTextPosition(SwingConstants.CENTER);
        getContentPane().add(btnExit);
    }

    private final FormClient formClient = new FormClient();
    private final FormCreateLogin formCreateLogin = new FormCreateLogin();
    private final FormSale formSale = new FormSale();

    public void ActionsButtons() {

        menuItemCreateUser.addActionListener(e->{
            try {
                if(CheckForm(formCreateLogin.getName())) {
                    FormFocus(formCreateLogin);
                }else {
                    formCreateLogin.setName("formCreateLogin");
                    desktop.add(formCreateLogin).setVisible(true);
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Erro: " + e1.getMessage(), "Erro abrir createUser Button", JOptionPane.ERROR_MESSAGE);
            }
        });

        menuItemClient.addActionListener(e->{

            if(CheckForm(formClient.getName())) {
                FormFocus(formClient);
            }else {
                formClient.setName("formClient");
                desktop.add(formClient).setVisible(true);
            }

        });
        
        btnSell.addActionListener(e->{
            if(CheckForm(formSale.getName())) {
                FormFocus(formSale);
            }else {
                formSale.setName("formSale");
                desktop.add(formSale).setVisible(true);
            }
        });
        
        btnClient.addActionListener(e->{
            if(CheckForm(formClient.getName())) {
                FormFocus(formClient);
            }else {
                formClient.setName("formClient");
                desktop.add(formClient).setVisible(true);
            }
        });

        menuItemExit.addActionListener(e->{
            dispose();
        });

        btnBackupSystem.addActionListener(e->{
            Backup.MakeBackup();
        });

        btnExit.addActionListener(e->{
            dispose();
            new FormLogin().setVisible(true);
        });
    }
    private boolean CheckForm(final String formName) {

        JInternalFrame[] form = desktop.getAllFrames();

        for(JInternalFrame frame : form) {
            if(frame.getName().equals(formName)) {
                return true;
            }
        }
        return false;
    }
    private void FormFocus(final JInternalFrame frame) {
        try {
            frame.setSelected(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error focus function", JOptionPane.ERROR_MESSAGE);
        }

    }
}
