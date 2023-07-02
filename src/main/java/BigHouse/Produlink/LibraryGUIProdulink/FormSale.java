package BigHouse.Produlink.LibraryGUIProdulink;

import BigHouse.Produlink.LibraryProdulink.Client.ModelClient;
import BigHouse.Produlink.LibraryProdulink.Login.ModelLogin;
import BigHouse.Produlink.LibraryProdulink.Observer.Observer;
import BigHouse.Produlink.LibraryProdulink.Product.ModelProduct;
import BigHouse.Produlink.LibraryProdulink.Sale.ModelSale;
import BigHouse.Produlink.LibraryProdulink.Sale.ServiceSale;
import BigHouse.Produlink.LibraryProdulink.SaleProduct.ModelSaleProduct;
import BigHouse.Produlink.LibraryProdulink.SaleProduct.ServiceSaleProduct;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FormSale extends JInternalFrame implements Observer {
    private JTable tblSales = new JTable();
    private JScrollPane spSales = new JScrollPane();
    private DefaultTableModel tblmodelSales= new DefaultTableModel(){public boolean isCellEditable(int row, int col) {return false;}};


    JLabel lblTotal = new JLabel("Total: ");
    JTextField txfCodSale = new JTextField(String.valueOf(SetMaxIdSale()));
    JTextField txfCodClient = new JTextField();
    JTextField txfFullName = new JTextField();
    JTextField txfCodSeller = new JTextField();
    JTextField txfNameSeller = new JTextField();
    JTextField txfCodProduct = new JTextField();
    JTextField txfNameProduct = new JTextField();
    JTextField txfQuantityProduct = new JTextField("1");
    JTextField txfValueProduct = new JTextField();
    JFormattedTextField txfDiscountProduct = new JFormattedTextField("0");
    JTextField txfTotalValueProduct = new JTextField();

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
        setLayout(new FlowLayout());
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

        JLabel lblCodSale = new JLabel("Cod Sale");
        lblCodSale.setBounds(750, 25, 100, 25);
        lblCodSale.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblCodSale);

        txfCodSale.setBounds(750, 50, 75, 20);
        txfCodSale.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfCodSale);

        txfCodSale.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    ValidateCodSale();
                } catch (IOException | InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JLabel lblCodClient = new JLabel("Cod Client");
        lblCodClient.setBounds(25, 25, 100, 25);
        lblCodClient.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblCodClient);

        txfCodClient.setBounds(25, 50, 75, 20);
        txfCodClient.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfCodClient);
        txfCodClient.setEnabled(false);

        JLabel lblFullName = new JLabel("Name client");
        lblFullName.setBounds(130, 25, 100, 25);
        lblFullName.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblFullName);

        txfFullName.setBounds(130, 50, 300, 20);
        txfFullName.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfFullName);
        txfFullName.setEnabled(false);

        JLabel lblCodSeller = new JLabel("Cod Seller");
        lblCodSeller.setBounds(25, 75, 100, 25);
        lblCodSeller.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblCodSeller);

        txfCodSeller.setBounds(25, 100, 75, 20);
        txfCodSeller.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfCodSeller);
        txfCodSeller.setEnabled(false);

        JLabel lblSeller = new JLabel("Name seller");
        lblSeller.setBounds(130, 75, 100, 25);
        lblSeller.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblSeller);

        txfNameSeller.setBounds(130, 100, 300, 20);
        txfNameSeller.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfNameSeller);
        txfNameSeller.setEnabled(false);

        JLabel lblCodProduct = new JLabel("Cod Product");
        lblCodProduct.setBounds(25, 150, 100, 25);
        lblCodProduct.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblCodProduct);

        txfCodProduct.setBounds(25, 175, 75, 20);
        txfCodProduct.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfCodProduct);
        txfCodProduct.setEnabled(false);

        //Product

        JLabel lblNameProduct = new JLabel("Name Product");
        lblNameProduct.setBounds(130, 150, 100, 25);
        lblNameProduct.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblNameProduct);

        txfNameProduct.setBounds(130, 175, 300, 20);
        txfNameProduct.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfNameProduct);
        txfNameProduct.setEnabled(false);

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

        JLabel lblDiscountProduct = new JLabel("Discount (%)");
        lblDiscountProduct.setBounds(625, 150, 100, 25);
        lblDiscountProduct.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblDiscountProduct);

        txfDiscountProduct.setBounds(625, 175, 75, 20);
        txfDiscountProduct.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        MaskFormatter maskDiscount = new MaskFormatter("###");
        maskDiscount.install(txfDiscountProduct);
        panel.add(txfDiscountProduct);

        txfDiscountProduct.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                ValidateDiscount();
            }
        });


        JLabel lblTotalValueProduct = new JLabel("Total");
        lblTotalValueProduct.setBounds(725, 150, 100, 25);
        lblTotalValueProduct.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblTotalValueProduct);

        txfTotalValueProduct.setBounds(725, 175, 75, 20);
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
        txfDateCreation.setEnabled(false);

        lblTotal.setBounds(600, 675, 500, 25);
        lblTotal.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
        panel.add(lblTotal);


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

        txfDiscountProduct.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                CalculateTotalValue();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                CalculateTotalValue();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                CalculateTotalValue();
            }
        });
        txfValueProduct.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                CalculateTotalValue();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                CalculateTotalValue();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                CalculateTotalValue();
            }
        });

        txfQuantityProduct.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                CalculateTotalValue();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                CalculateTotalValue();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                CalculateTotalValue();
            }
        });

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

        btnAddTable.addActionListener(e->{
            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                LocalDate dateCreation = LocalDate.parse(txfDateCreation.getText(), formatter);

                ModelSale sale = new ModelSale();

                sale.setId(Long.valueOf(txfCodSale.getText()));

                ModelProduct product = new ModelProduct();

                product.setId(Long.valueOf(txfCodProduct.getText()));
                product.setName(txfNameProduct.getText());
                product.setPrice(Double.parseDouble(txfValueProduct.getText()));

                ModelSaleProduct saleProduct = new ModelSaleProduct();

                saleProduct.setId(SetMaxIdSaleProduct());
                saleProduct.setDiscount(Double.parseDouble(txfDiscountProduct.getText()));
                saleProduct.setDateCreation(dateCreation);
                saleProduct.setSale(sale);
                saleProduct.setQuantity(Integer.parseInt(txfQuantityProduct.getText()));
                saleProduct.setProduct(product);
                saleProduct.setTotal(Double.parseDouble(txfTotalValueProduct.getText().replace(",", ".")));

                Object[] rowData = {saleProduct.getId(), saleProduct.product.getName(), saleProduct.getQuantity(), saleProduct.product.getPrice() , saleProduct.getDiscount(), saleProduct.getTotal()};
                tblmodelSales.addRow(rowData);

                lblTotal.setText("Total: " + SetTotalLabel());
            }catch (Exception e1){
                System.out.println(e1.getMessage());
            }
        });

        btnSave.addActionListener(e->{
            try {

                ServiceSale serviceSale = new ServiceSale();
                ServiceSaleProduct serviceSaleProduct = new ServiceSaleProduct();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                LocalDate dateCreation = LocalDate.parse(txfDateCreation.getText(), formatter);

                ModelSaleProduct saleProduct = new ModelSaleProduct();

                //saleProduct.setIdSale(Long.valueOf(txfCodSale.getText()));
                saleProduct.setDateCreation(dateCreation);

                int totalRows = tblmodelSales.getRowCount();

                for (int i = 0; i < totalRows; i++) {
                    saleProduct.setDateCreation((LocalDate) tblmodelSales.getValueAt(i, 0));
                    saleProduct.setDiscount((double) tblmodelSales.getValueAt(i, 2));
                    saleProduct.setQuantity((int) tblmodelSales.getValueAt(i, 3));
                    saleProduct.setTotal((int) tblmodelSales.getValueAt(i, 4));

                    serviceSaleProduct.InsertSaleProduct(saleProduct);
                }

                saleProduct.setId(SetMaxIdSaleProduct());

                ModelSale sale = new ModelSale();

                sale.setId(SetMaxIdSale());
                //sale.setIdClient(Long.valueOf(txfCodClient.getText()));
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

    private String SetTotalLabel(){
        double sum = 0.0;

        int column = 5;

        int lines = tblmodelSales.getRowCount();

        for (int i = 0; i < lines; i++) {
            double valor = Double.parseDouble(tblmodelSales.getValueAt(i, column).toString());
            sum += valor;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        String valorTotalFormat = decimalFormat.format(sum);

        return valorTotalFormat;
    }

    public Long SetMaxIdSaleProduct(){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            ServiceSaleProduct serviceSaleProduct = new ServiceSaleProduct();
            java.util.List<ModelSaleProduct> saleProductList = objectMapper.readValue(serviceSaleProduct.FindAll(), new TypeReference<List<ModelSaleProduct>>() {});
            Long maxId = 0L;
            for(ModelSaleProduct obj : saleProductList){
                if(obj.getId() > maxId){
                    maxId = obj.getId();
                }
            }
            maxId++;

            return maxId;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Long SetMaxIdSale(){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            ServiceSale serviceSale = new ServiceSale();
            java.util.List<ModelSale> saleList = objectMapper.readValue(serviceSale.FindAll(), new TypeReference<List<ModelSale>>() {});
            Long maxId = 0L;
            for(ModelSale obj : saleList){
                if(obj.getId() > maxId){
                    maxId = obj.getId();
                }
            }
            maxId++;

            return maxId;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }


    private void ValidateDiscount(){
        String text = txfDiscountProduct.getText();
        if (!text.isEmpty()) {
            try {
                text = text.trim().replace(" ", "");
                int discount = Integer.parseInt(text.trim());
                if (discount < 0 || discount > 100) {
                    JOptionPane.showMessageDialog(this, "The discount must be between 0 and 100.", "Invalid value", JOptionPane.ERROR_MESSAGE);
                    txfDiscountProduct.setText("0");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid value.", "Error", JOptionPane.ERROR_MESSAGE);
                txfDiscountProduct.setText("");
            }
        }
    }

    private void ValidateCodSale() throws IOException, InterruptedException {
        String text = txfCodSale.getText();
        if (!text.isEmpty()){
            text = text.trim().replace(" ", "");
            Long cod = Long.parseLong(text.trim());
            ObjectMapper objectMapper = new ObjectMapper();
            ServiceSale serviceSale = new ServiceSale();
            ModelSale sale = objectMapper.readValue(serviceSale.FindById(cod), ModelSale.class);

            txfCodClient.setText(String.valueOf(sale.client.getId()));
            txfFullName.setText(sale.client.getName());

            txfCodSeller.setText(String.valueOf(sale.));

            //java.util.List<ModelSaleProduct> saleList = objectMapper.readValue(serviceSaleProduct.FindById(sale.getId()), new TypeReference<List<ModelSaleProduct>>() {});


        }
    }

    private void CalculateTotalValue(){
        try {
            double value = Double.parseDouble(txfValueProduct.getText());
            int quantity = Integer.parseInt(txfQuantityProduct.getText());
            double discount = Double.parseDouble(txfDiscountProduct.getText());
            double totalValue = (value - (value * discount / 100)) * quantity;

            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
            String valorTotalFormat = decimalFormat.format(totalValue);

            txfTotalValueProduct.setText(valorTotalFormat);

        } catch (NumberFormatException e) {
            txfTotalValueProduct.setText("0");
        }
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
            txfValueProduct.setText(String.valueOf(product.getPrice()));
        } else if (obj instanceof ModelLogin){
            ModelLogin login = (ModelLogin) obj;
            txfCodSeller.setText(String.valueOf(login.getId()));
            txfNameSeller.setText(login.getName());
        }

    }


}
