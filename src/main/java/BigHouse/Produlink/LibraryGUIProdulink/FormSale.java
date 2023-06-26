package BigHouse.Produlink.LibraryGUIProdulink;

import BigHouse.Produlink.LibraryProdulink.Address.ModelAddress;
import BigHouse.Produlink.LibraryProdulink.Address.ServiceAddress;
import BigHouse.Produlink.LibraryProdulink.Client.ModelClient;
import BigHouse.Produlink.LibraryProdulink.Client.ServiceClient;
import BigHouse.Produlink.LibraryProdulink.Login.ModelLogin;
import BigHouse.Produlink.LibraryProdulink.Login.ServiceLogin;
import BigHouse.Produlink.LibraryProdulink.Observer.Observer;
import BigHouse.Produlink.LibraryProdulink.Product.ModelProduct;
import BigHouse.Produlink.LibraryProdulink.Product.ServiceProduct;
import BigHouse.Produlink.LibraryProdulink.Sale.ModelSale;
import BigHouse.Produlink.LibraryProdulink.Sale.ServiceSale;
import BigHouse.Produlink.LibraryProdulink.SaleProduct.ModelSaleProduct;
import BigHouse.Produlink.LibraryProdulink.SaleProduct.ServiceSaleProduct;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

public class FormSale extends JInternalFrame implements Observer {
    private JTable tblSales = new JTable();
    private JScrollPane spSales = new JScrollPane();
    private DefaultTableModel tblmodelSales= new DefaultTableModel(){public boolean isCellEditable(int row, int col) {return false;}};

    JTextField txfCodSale = new JTextField();
    JTextField txfCodClient = new JTextField();
    JTextField txfFullName = new JTextField("Marcel");
    JTextField txfCodSeller = new JTextField();
    JTextField txfNameSeller = new JTextField("Victor");
    JTextField txfCodProduct = new JTextField();
    JTextField txfNameProduct = new JTextField("Note");
    JTextField txfQuantityProduct = new JTextField("13");
    JTextField txfValueProduct = new JTextField("R$  ");
    JTextField txfDiscountProduct = new JTextField("R$ ");
    JTextField txfTotalValueProduct = new JTextField("R$ ");

    JButton btnCancel = new JButton("Cancel");

    JButton btnAddTable = new JButton("<html>Add Item<br>(ENTER)</html>");
    JButton btnSave = new JButton("Save");
    JButton btnFindClient = new JButton();
    JButton btnFindSeller = new JButton();
    JButton btnFindProduct = new JButton();

    JFormattedTextField txfDateCreation = new JFormattedTextField();
    JPanel panel = new JPanel();
    public FormSale() throws ParseException{
        setSize(1000,800);
        setTitle("Sales");
        setIconifiable(true);
        setMaximizable(false);
        setClosable(true);
        setResizable(false);
        setLocation(175, 20);
        BuildForm();
        ActionsButtons();
        LoadData();

    }

    private void LoadData(){
        LocalDate today = LocalDate.now();
        String date = today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        txfDateCreation.setText(date);
    }

    private void BuildForm() throws ParseException {

        panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(panel);
        panel.setLayout(null);

        //Table

        if (tblmodelSales.getColumnCount() == 0) {
            tblmodelSales.addColumn("Cod");
            tblmodelSales.addColumn("Name Product");
            tblmodelSales.addColumn("Quantity");
            tblmodelSales.addColumn("Value");
            tblmodelSales.addColumn("Discount");
            tblmodelSales.addColumn("Total value");
        }

        tblSales = new JTable(tblmodelSales);
        spSales = new JScrollPane(tblSales);
        spSales.setBounds(30, 230, 920, 400);
        panel.add(spSales);
        tblSales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Labels and TextFields

        JLabel lblCodClient = new JLabel("Cod Client");
        lblCodClient.setBounds(25, 25, 100, 25);
        lblCodClient.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblCodClient);

        txfCodClient.setBounds(25, 50, 75, 20);
        txfCodClient.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfCodClient);

        JLabel lblFullName = new JLabel("Name client");
        lblFullName.setBounds(130, 25, 100, 25);
        lblFullName.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblFullName);

        txfFullName.setBounds(130, 50, 300, 20);
        txfFullName.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfFullName);

        JLabel lblCodSeller = new JLabel("Cod Seller");
        lblCodSeller.setBounds(25, 75, 100, 25);
        lblCodSeller.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblCodSeller);

        txfCodSeller.setBounds(25, 100, 75, 20);
        txfCodSeller.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfCodSeller);

        JLabel lblSeller = new JLabel("Name seller");
        lblSeller.setBounds(130, 75, 100, 25);
        lblSeller.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblSeller);

        txfNameSeller.setBounds(130, 100, 300, 20);
        txfNameSeller.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfNameSeller);

        JLabel lblCodProduct = new JLabel("Cod Product");
        lblCodProduct.setBounds(25, 150, 100, 25);
        lblCodProduct.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblCodProduct);

        txfCodProduct.setBounds(25, 175, 75, 20);
        txfCodProduct.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfCodProduct);

        //Product

        JLabel lblNameProduct = new JLabel("Name Product");
        lblNameProduct.setBounds(130, 150, 100, 25);
        lblNameProduct.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblNameProduct);

        txfNameProduct.setBounds(130, 175, 300, 20);
        txfNameProduct.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfNameProduct);

        JLabel lblQuantityProduct = new JLabel("Quantity");
        lblQuantityProduct.setBounds(475, 150, 75, 25);
        lblQuantityProduct.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblQuantityProduct);

        txfQuantityProduct.setBounds(475, 175, 75, 20);
        txfQuantityProduct.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfQuantityProduct);

        JLabel lblValueProduct = new JLabel("Value");
        lblValueProduct.setBounds(550, 150, 100, 25);
        lblValueProduct.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblValueProduct);

        txfValueProduct.setBounds(550, 175, 75, 20);
        txfValueProduct.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfValueProduct);

        JLabel lblDiscountProduct = new JLabel("Discount");
        lblDiscountProduct.setBounds(625, 150, 100, 25);
        lblDiscountProduct.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblDiscountProduct);

        txfDiscountProduct.setBounds(625, 175, 75, 20);
        txfDiscountProduct.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfDiscountProduct);

        JLabel lblTotalValueProduct = new JLabel("Total");
        lblTotalValueProduct.setBounds(700, 150, 100, 25);
        lblTotalValueProduct.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblTotalValueProduct);

        txfTotalValueProduct.setBounds(700, 175, 75, 20);
        txfTotalValueProduct.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfTotalValueProduct);

        JLabel lblDateCreation = new JLabel("Date creation");
        lblDateCreation.setBounds(550, 25, 150, 25);
        lblDateCreation.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblDateCreation);

        txfDateCreation.setBounds(550, 50, 95, 20);
        txfDateCreation.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        MaskFormatter dateMaskDateCreation = new MaskFormatter("##/##/####");
        dateMaskDateCreation.install(txfDateCreation);
        panel.add(txfDateCreation);

        //Buttons

        btnCancel.setBounds(25, 650, 90, 65);
        btnCancel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(btnCancel);

        btnSave.setBounds(150, 650, 90, 65);
        btnSave.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(btnSave);

        btnFindClient.setBounds(440, 50, 20, 20);
        btnFindClient.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(btnFindClient);

        btnFindSeller.setBounds(440, 100, 20, 20);
        btnFindSeller.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(btnFindSeller);

        btnFindProduct.setBounds(440, 175, 20, 20);
        btnFindProduct.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(btnFindProduct);

        btnAddTable.setBounds(800, 150, 150, 75);
        btnAddTable.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(btnAddTable);

    }

    private void ActionsButtons(){
        btnCancel.addActionListener(e->{
            dispose();
        });

        btnFindClient.addActionListener(e->{
            try {
                ModelClient objClient = new ModelClient();

                new FormConsultGeneric(objClient, new String[]{"ID Client", "Name"}, FormSale.this).setVisible(true);
            }catch (Exception e1){
                System.out.println(e1.getMessage());
            }
        });

        btnFindProduct.addActionListener(e->{
            try {
                ModelProduct objProduct = new ModelProduct();

                new FormConsultGeneric(objProduct, new String[]{"ID Product", "Name"}, FormSale.this).setVisible(true);
            }catch (Exception e1){
                System.out.println(e1.getMessage());
            }
        });

        btnFindSeller.addActionListener(e->{
            try {
                ModelLogin ObjSeller = new ModelLogin();

                new FormConsultGeneric(ObjSeller, new String[]{"ID Seller", "Name"}, FormSale.this).setVisible(true);
            }catch (Exception e1){
                System.out.println(e1.getMessage());
            }
        });

        btnSave.addActionListener(e->{
            try {

                ServiceSale serviceSale = new ServiceSale();
                ServiceSaleProduct serviceSaleProduct = new ServiceSaleProduct();

                ObjectMapper objectMapper = new ObjectMapper();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                LocalDate dateCreation = LocalDate.parse(txfDateCreation.getText(), formatter);

                ModelSaleProduct saleProduct = new ModelSaleProduct();

                saleProduct.setIdSale(Long.valueOf(txfCodSale.getText()));
                saleProduct.setDateCreation(dateCreation);

                int totalRows = tblmodelSales.getRowCount();

                for (int i = 0; i < totalRows; i++) {
                    saleProduct.setDateCreation((LocalDate) tblmodelSales.getValueAt(i, 0));
                    saleProduct.setDiscount((double) tblmodelSales.getValueAt(i, 2));
                    saleProduct.setQuantity((int) tblmodelSales.getValueAt(i, 3));
                    saleProduct.setTotal((int) tblmodelSales.getValueAt(i, 4));

                    serviceSaleProduct.InsertSaleProduct(saleProduct);
                }

                java.util.List<ModelSaleProduct> saleProductList = objectMapper.readValue(serviceSaleProduct.FindAll(), new TypeReference<List<ModelSaleProduct>>() {});
                Long maxId = 0L;
                for(ModelSaleProduct obj : saleProductList){
                    if(obj.getId() > maxId){
                        maxId = obj.getId();
                    }
                }
                maxId++;
                saleProduct.setId(maxId);

                ModelSale sale = new ModelSale();

                java.util.List<ModelSale> saleList = objectMapper.readValue(serviceSale.FindAll(), new TypeReference<List<ModelSale>>() {});
                Long maxIdSale = 0L;
                for(ModelSale obj : saleList){
                    if(obj.getId() > maxIdSale){
                        maxIdSale = obj.getId();
                    }
                }

                maxIdSale++;

                sale.setId(maxIdSale);
                sale.setIdClient(Long.valueOf(txfCodClient.getText()));
                sale.setDateCreation(dateCreation);
                sale.setTotalValue(Double.parseDouble(txfTotalValueProduct.getText()));
                sale.setSaleProduct(saleProduct);

                serviceSale.InsertSale(sale);

                JOptionPane.showMessageDialog(null, "Sale saved");

            } catch (Exception e1){
                System.out.println("ERROR insert: " + e1.getMessage());
            }

        });

    }

    @Override
    public void Update(Object obj) {

        if (obj instanceof ModelClient) {
            ModelClient client = (ModelClient) obj;
            txfCodClient.setText(String.valueOf(client.getId()));
            txfFullName.setText(client.getName());
        } else if (obj instanceof ModelProduct) {
            ModelProduct product = (ModelProduct) obj;
            txfCodProduct.setText(String.valueOf(product.getId()));
            txfNameProduct.setText(product.getName());
        } else if (obj instanceof ModelLogin){
            ModelLogin login = (ModelLogin) obj;
            txfCodSeller.setText(String.valueOf(login.getId()));
            txfNameSeller.setText(login.getName());
        }

    }


}
