package BigHouse.Produlink.LibraryGUIProdulink;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import BigHouse.Produlink.LibraryProdulink.Client.ModelClient;
import BigHouse.Produlink.LibraryProdulink.Client.ServiceClient;
import BigHouse.Produlink.LibraryProdulink.Login.ModelLogin;
import BigHouse.Produlink.LibraryProdulink.Login.ServiceLogin;
import BigHouse.Produlink.LibraryProdulink.Observer.Observable;
import BigHouse.Produlink.LibraryProdulink.Observer.Observer;
import BigHouse.Produlink.LibraryProdulink.Product.ModelProduct;
import BigHouse.Produlink.LibraryProdulink.Product.ServiceProduct;
import BigHouse.Produlink.LibraryProdulink.Sale.ModelSale;
import BigHouse.Produlink.LibraryProdulink.Sale.ServiceSale;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class FormConsultGeneric extends JDialog implements Observable{

    private JTable tbl;
    private JScrollPane spn;

    private DefaultTableModel tblModel= new DefaultTableModel(){public boolean isCellEditable(int row, int col) {return false;}};

    private JLabel lblSerch = new JLabel();

    private JTextField txfSerch = new JTextField();

    private JButton btnOk = new JButton();
    private JButton btnCancel = new JButton();

    private List<Observer> listObs = new ArrayList<Observer>();
    private final Object type;
    private List<Object> listObj;

    public FormConsultGeneric(final Object type, final String[] columns, final Observer obs) {

        setBounds(100, 100, 400, 250);
        setModal(true);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        this.type = type;

        AddObserver(obs);

        buildWindow(columns);
        actionsButtons();
        loadData();
    }

    public void buildWindow(final String[] columns){
        for(String c : columns) {
            tblModel.addColumn(c);
        }

        tbl = new JTable(tblModel);
        spn = new JScrollPane(tbl);
        spn.setBounds(10, 65, 380, 150);
        getContentPane().add(spn);

        lblSerch = new JLabel("Search");
        lblSerch.setBounds(10, 10, 150, 20);
        lblSerch.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        getContentPane().add(lblSerch);

        txfSerch = new JTextField();
        txfSerch.setBounds(85, 10, 150, 20);
        txfSerch.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        getContentPane().add(txfSerch);

        btnOk = new JButton("OK");
        btnOk.setBounds(240, 10, 50, 20);
        btnOk.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        getContentPane().add(btnOk);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(290, 10, 90, 20);
        btnCancel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        getContentPane().add(btnCancel);
    }

    public void actionsButtons() {
        btnCancel.addActionListener(e->{
            dispose();
        });

        btnOk.addActionListener(e->{

            NotifyAllObserver();

            dispose();
        });

    }

    private void loadData() {

        try {
            while (tblModel.getRowCount() > 0) {
                tblModel.removeRow(0);
            }

            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.registerModule(new JavaTimeModule());

            if (type instanceof ModelClient) {
                ServiceClient serviceClient = new ServiceClient();
                List<ModelClient> clientList = objectMapper.readValue(serviceClient.FindAll(), new TypeReference<List<ModelClient>>() {});

                for (ModelClient objClient : clientList) {
                    Object[] rowData = {objClient.getId(), objClient.getName()};
                    tblModel.addRow(rowData);
                }
                listObj = new ArrayList<>();
                listObj.addAll(clientList);

            } else if (type instanceof ModelLogin) {
                ServiceLogin serviceLogin = new ServiceLogin();
                List<ModelLogin> loginList = objectMapper.readValue(serviceLogin.FindAll(), new TypeReference<List<ModelLogin>>() {});

                for (ModelLogin objLogin : loginList) {
                    Object[] rowData = {objLogin.getId(), objLogin.getLogin()};
                    tblModel.addRow(rowData);
                }
                listObj = new ArrayList<>();
                listObj.addAll(loginList);

            } else if (type instanceof ModelProduct) {
                ServiceProduct serviceProduct = new ServiceProduct();
                List<ModelProduct> productList = objectMapper.readValue(serviceProduct.FindAll(), new TypeReference<List<ModelProduct>>() {});

                for (ModelProduct objProduct : productList) {
                    Object[] rowData = {objProduct.getId(), objProduct.getName()};
                    tblModel.addRow(rowData);
                }
                listObj = new ArrayList<>();
                listObj.addAll(productList);
            }


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void AddObserver(Observer obj) {
        listObs.add(obj);
    }

    @Override
    public void RemoveObserver(Observer obj) {
        listObs.remove(obj);
    }

    @Override
    public void NotifyAllObserver() {
        for (int i=0; i < listObs.size(); i++ ) {
            Observer obs = listObs.get(i);
            obs.Update(listObj.get(tbl.getSelectedRow()));
        }
    }

}
