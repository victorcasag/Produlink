package BigHouse.Produlink.LibraryGUIProdulink;

import BigHouse.Produlink.LibraryProdulink.Client.ModelClient;
import BigHouse.Produlink.LibraryProdulink.Client.ServiceClient;
import BigHouse.Produlink.LibraryProdulink.Observer.Observable;
import BigHouse.Produlink.LibraryProdulink.Observer.Observer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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


public class FormConsultClient extends JDialog implements Observable {

    private JTable tbl;
    private JScrollPane spn;

    private DefaultTableModel tblModel= new DefaultTableModel(){public boolean isCellEditable(int row, int col) {return false;}};

    private JLabel lblSearch = new JLabel();

    private JTextField txfSearch = new JTextField();

    private JButton btnOk = new JButton();
    private JButton btnCancel = new JButton();

    private List<Observer> listObs = new ArrayList<Observer>();
    private List<Object> listObj;


    public FormConsultClient(final String[] columns, final Observer obs) {

        setBounds(100, 100, 400, 250);
        setModal(true);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        AddObserver(obs);

        buildWindow(columns);
        actionsButtons();
    }

    public void buildWindow(final String[] columns){
        for(String c : columns) {
            tblModel.addColumn(c);
        }

        tbl = new JTable(tblModel);
        spn = new JScrollPane(tbl);
        spn.setBounds(10, 65, 380, 150);
        getContentPane().add(spn);

        lblSearch = new JLabel("Search");
        lblSearch.setBounds(10, 10, 150, 20);
        lblSearch.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        getContentPane().add(lblSearch);

        txfSearch = new JTextField();
        txfSearch.setBounds(85, 10, 150, 20);
        txfSearch.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        getContentPane().add(txfSearch);

        btnOk = new JButton("Ok");
        btnOk.setBounds(240, 10, 50, 20);
        btnOk.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        getContentPane().add(btnOk);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(290, 10, 90, 20);
        btnCancel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        getContentPane().add(btnCancel);

        loadData();

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
            ServiceClient serviceClient = new ServiceClient();

            ObjectMapper objectMapper = new ObjectMapper();

            List<ModelClient> clientList = objectMapper.readValue(serviceClient.FindAll(), new TypeReference<List<ModelClient>>() {});

            for (ModelClient obj : clientList) {
                Object[] rowData = {obj.getId(), obj.getName()};
                tblModel.addRow(rowData);
            }
            listObj = new ArrayList<>();
            listObj.addAll(clientList);
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