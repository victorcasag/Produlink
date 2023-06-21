package BigHouse.Produlink.LibraryGUIProdulink;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import BigHouse.Produlink.LibraryProdulink.Observer.ModelConsult;
import BigHouse.Produlink.LibraryProdulink.Observer.Observable;
import BigHouse.Produlink.LibraryProdulink.Observer.Observer;

public class FormConsultGeneric extends JDialog implements Observable{

    private JTable tbl;
    private JScrollPane spn;

    private DefaultTableModel tblModel= new DefaultTableModel(){public boolean isCellEditable(int row, int col) {return false;}};

    private JLabel lblSerch = new JLabel();

    private JTextField txfSerch = new JTextField();

    private JButton btnOk = new JButton();
    private JButton btnCancel = new JButton();

    private List<Observer> listObs = new ArrayList<Observer>();
    private final List<Object> service;

    public FormConsultGeneric(final List<Object> service, final String[] columns, final Observer obs) {

        setBounds(100, 100, 400, 250);
        setModal(true);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        this.service = service;

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

        while(tblModel.getRowCount()>0) {
            tblModel.removeRow(0);
        }

        try {

            for (Object obj : service) {
                tblModel.addRow(((ModelConsult)obj).GetReuslt());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
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
            obs.Update(service.get(tbl.getSelectedRow()));
        }
    }

}
