package BigHouse.Produlink.LibraryGUIProdulink;

import BigHouse.Produlink.LibraryProdulink.Address.ModelAddress;
import BigHouse.Produlink.LibraryProdulink.Address.ServiceAddress;
import BigHouse.Produlink.LibraryProdulink.Client.ModelClient;
import BigHouse.Produlink.LibraryProdulink.Client.ServiceClient;
import BigHouse.Produlink.LibraryProdulink.Observer.Observer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

public class FormClient extends JInternalFrame implements Observer {
    Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
    String[] combBoxTextsSex = new String[] {"Select", "Female", "Male"};
    String[] combBoxTextsStates = new String[]
            {"SC", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES",
                    "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE",
                    "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};
    JLabel lblImage = new JLabel();

    JTextField txfCountry = new JTextField("Brasil");
    JTextField txfCodClient = new JTextField();
    JTextField txfFullName = new JTextField("marcel");
    JTextField txfEmail = new JTextField("email");
    JTextField txfNeighborhood = new JTextField("asdsad");
    JTextField txfCity = new JTextField("asdasd");
    JTextField txfStreet = new JTextField("adsasd");
    JTextField txfReference = new JTextField("asdwasd");
    JTextField txfProfession = new JTextField("profissao");
    JTextField txfNameOfMother = new JTextField("nome mae");
    JTextField txfNameOfFather = new JTextField("nome pai");

    JTextArea txfDescription = new JTextArea("asdasid");

    JFormattedTextField txfCodHouse = new JFormattedTextField("123");
    JFormattedTextField txfZipCode = new JFormattedTextField();
    JFormattedTextField txfRG = new JFormattedTextField("12312311");
    JFormattedTextField txfCPF = new JFormattedTextField();
    JFormattedTextField txfDateOfBirth = new JFormattedTextField("01/01/2020");
    JFormattedTextField txfDateCreation = new JFormattedTextField("01/01/2020");
    JFormattedTextField txfDateLastUpdate = new JFormattedTextField("01/01/2020");
    JFormattedTextField txfNumberPhone = new JFormattedTextField();
    JFormattedTextField txfTelephoneHouse = new JFormattedTextField();

    JButton btnAddImage = new JButton("+");
    JButton btnSave = new JButton("Save");
    JButton btnConsultClient = new JButton("Consult");
    JButton btnConsulHistorytClient = new JButton("History");
    JButton btnCancel = new JButton("Cancel");

    JComboBox<String> cbSex = new JComboBox<String>(combBoxTextsSex);
    JComboBox<String> cbState = new JComboBox<String>(combBoxTextsStates);
    JPanel panel = new JPanel();

    public FormClient() throws ParseException, IOException, InterruptedException {

        setSize(1000,800);
        setTitle("Client");
        setIconifiable(true);
        setMaximizable(false);
        setClosable(true);
        setResizable(false);
        setLocation(175, 20);

        BuildForm();
        ActionsButtons();
        LoadData();
    }

    public void LoadData() throws IOException, InterruptedException {
        ServiceClient serviceClient = new ServiceClient();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        java.util.List<ModelClient> clientList = objectMapper.readValue(serviceClient.FindAll(), new TypeReference<List<ModelClient>>() {});
        Long maxId = Long.valueOf(0);
        for(ModelClient obj : clientList){
            if(obj.getId() > maxId){
                maxId = obj.getId();
            }
        }
        maxId++;
        txfCodClient.setText(String.valueOf(maxId));
    }

    public void BuildForm() throws ParseException{

        panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(panel);
        panel.setLayout(null);

        //Label

        JLabel lblCodClient = new JLabel("Cod");
        lblCodClient.setBounds(25, 25, 100, 25);
        lblCodClient.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblCodClient);

        JLabel lblFullName = new JLabel("Full name");
        lblFullName.setBounds(150, 25, 100, 25);
        lblFullName.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblFullName);

        JLabel lblCPF = new JLabel("CPF");
        lblCPF.setBounds(480, 25, 50, 25);
        lblCPF.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblCPF);

        JLabel lblRG = new JLabel("RG");
        lblRG.setBounds(480, 220, 50, 25);
        lblRG.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblRG);

        JLabel lblDateOfBirth = new JLabel("Date of born");
        lblDateOfBirth.setBounds(25, 75, 150, 25);
        lblDateOfBirth.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblDateOfBirth);

        JLabel lblNumberPhone = new JLabel("Phone");
        lblNumberPhone.setBounds(170, 75, 150, 25);
        lblNumberPhone.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblNumberPhone);

        JLabel lblNumberTelephone = new JLabel("Telephone");
        lblNumberTelephone.setBounds(320, 75, 200, 25);
        lblNumberTelephone.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblNumberTelephone);

        JLabel lblSex = new JLabel("Gender");
        lblSex.setBounds(480, 75, 100, 25);
        lblSex.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblSex);

        JLabel lblProfession = new JLabel("Profession");
        lblProfession.setBounds(25, 275, 100, 25);
        lblProfession.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblProfession);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(250, 275, 100, 25);
        lblEmail.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblEmail);

        JLabel lblDescription = new JLabel("Description");
        lblDescription.setBounds(25, 510, 100, 50);
        lblDescription.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblDescription);

        JLabel lblDateCreation = new JLabel("Date Creation");
        lblDateCreation.setBounds(400, 580, 150, 50);
        lblDateCreation.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblDateCreation);

        JLabel lblDateLastUpdate = new JLabel("Last Update");
        lblDateLastUpdate.setBounds(400, 510, 100, 50);
        lblDateLastUpdate.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblDateLastUpdate);

        //Address

        JLabel lblCodHouse = new JLabel("Number house");
        lblCodHouse.setBounds(400, 120, 150, 25);
        lblCodHouse.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        panel.add(lblCodHouse);

        JLabel lblStreet = new JLabel("Street");
        lblStreet.setBounds(25, 120, 150, 25);
        lblStreet.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblStreet);

        JLabel lblZipCode = new JLabel("Zipcode");
        lblZipCode.setBounds(25, 165, 100, 25);
        lblZipCode.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblZipCode);

        JLabel lblReference = new JLabel("Reference");
        lblReference.setBounds(150, 165, 100, 25);
        lblReference.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblReference);

        JLabel lblNameOfMother = new JLabel("Name Of Mother");
        lblNameOfMother.setBounds(25, 350, 150, 25);
        lblNameOfMother.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblNameOfMother);

        JLabel lblNameOfFather = new JLabel("Name Of Father");
        lblNameOfFather.setBounds(25, 425, 150, 25);
        lblNameOfFather.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblNameOfFather);

        JLabel lblNeighborhood = new JLabel("Neighborhood");
        lblNeighborhood.setBounds(470, 165, 100, 25);
        lblNeighborhood.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblNeighborhood);

        JLabel lblCity = new JLabel("City");
        lblCity.setBounds(25, 220, 100, 25);
        lblCity.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblCity);

        JLabel lblState = new JLabel("State");
        lblState.setBounds(200, 220, 100, 25);
        lblState.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblState);

        JLabel lblCountry = new JLabel("Country");
        lblCountry.setBounds(320, 220, 100, 25);
        lblCountry.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(lblCountry);

        lblImage = new JLabel();
        lblImage.setBounds(710, 25, 250, 250);
        panel.add(lblImage);


        //JTextFields

        txfCodClient.setBounds(25, 50, 100, 20);
        txfCodClient.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfCodClient);

        txfFullName.setBounds(150, 50, 300, 20);
        txfFullName.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfFullName);

        txfCPF.setBounds(480, 50, 130, 20);
        txfCPF.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        MaskFormatter maskCPF = new MaskFormatter("###.###.###-##");
        maskCPF.install(txfCPF);
        panel.add(txfCPF);

        txfRG.setBounds(480, 245, 100, 20);
        txfRG.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        MaskFormatter maskRG = new MaskFormatter("########-#");
        maskRG.install(txfRG);
        panel.add(txfRG);

        txfProfession.setBounds(25, 300, 200, 20);
        txfProfession.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfProfession);

        txfEmail.setBounds(250, 300, 250, 20);
        txfEmail.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfEmail);

        txfNameOfMother.setBounds(25, 375, 300, 20);
        txfNameOfMother.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfNameOfMother);

        txfNameOfFather.setBounds(25, 450, 300, 20);
        txfNameOfFather.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfNameOfFather);

        txfDateOfBirth.setBounds(25, 100, 95, 20);
        txfDateOfBirth.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        MaskFormatter dateMaskDateOfBirth = new MaskFormatter("##/##/####");
        dateMaskDateOfBirth.install(txfDateOfBirth);
        panel.add(txfDateOfBirth);

        txfDateCreation.setBounds(400, 620, 95, 20);
        txfDateCreation.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        MaskFormatter dateMaskDateCreation = new MaskFormatter("##/##/####");
        dateMaskDateCreation.install(txfDateCreation);
        panel.add(txfDateCreation);

        txfDateLastUpdate.setBounds(400, 550, 95, 20);
        txfDateLastUpdate.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        MaskFormatter dateMaskDateLastUpdate = new MaskFormatter("##/##/####");
        dateMaskDateLastUpdate.install(txfDateLastUpdate);
        panel.add(txfDateLastUpdate);

        txfNumberPhone.setBounds(170, 100, 140, 20);
        txfNumberPhone.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        MaskFormatter dateMaskNumberPhone = new MaskFormatter("(##) # ####-####");
        dateMaskNumberPhone.install(txfNumberPhone);
        panel.add(txfNumberPhone);

        txfTelephoneHouse.setBounds(320, 100, 135, 20);
        txfTelephoneHouse.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        MaskFormatter masktxfTelephoneHouse = new MaskFormatter("(##) ####-####");
        masktxfTelephoneHouse.install(txfTelephoneHouse);
        panel.add(txfTelephoneHouse);

        txfStreet.setBounds(25, 145, 350, 20);
        txfStreet.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfStreet);

        txfCodHouse.setBounds(400, 145, 90, 20);
        txfCodHouse.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        MaskFormatter maskCodHouse = new MaskFormatter("########");
        maskCodHouse.install(txfCodHouse);
        panel.add(txfCodHouse);

        txfZipCode.setBounds(25, 190, 100, 20);
        txfZipCode.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        MaskFormatter maskZipCode = new MaskFormatter("#####-###");
        maskZipCode.install(txfZipCode);
        panel.add(txfZipCode);

        txfReference.setBounds(150, 190, 300, 20);
        txfReference.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfReference);

        txfNeighborhood.setBounds(470, 190, 100, 20);
        txfNeighborhood.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfNeighborhood);

        txfCity.setBounds(25, 245, 150, 20);
        txfCity.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfCity);

        txfCountry.setBounds(320, 245, 150, 20);
        txfCountry.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(txfCountry);

        txfDescription.setBounds(25, 550, 350, 100);
        txfDescription.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        txfDescription.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        panel.add(txfDescription);

        //ComboBox

        cbSex.setBounds(480, 100, 110, 20);
        cbSex.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(cbSex);

        cbState.setBounds(200, 245, 100, 20);
        cbState.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(cbState);

        //Buttons

        btnAddImage.setBounds(915, 280, 50, 25);
        btnAddImage.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(btnAddImage);

        btnConsultClient.setBounds(275, 670, 90, 65);
        btnConsultClient.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        btnConsultClient.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnConsultClient.setHorizontalTextPosition(SwingConstants.CENTER);
        panel.add(btnConsultClient);

        btnSave.setBounds(150, 670, 90, 65);
        btnSave.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        btnSave.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnSave.setHorizontalTextPosition(SwingConstants.CENTER);
        panel.add(btnSave);

        btnConsulHistorytClient.setBounds(400, 670, 90, 65);
        btnConsulHistorytClient.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        btnConsulHistorytClient.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnConsulHistorytClient.setHorizontalTextPosition(SwingConstants.CENTER);
        panel.add(btnConsulHistorytClient);

        btnCancel.setBounds(25, 670, 90, 65);
        btnCancel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        panel.add(btnCancel);
    }

    public void ActionsButtons() {

        btnAddImage.addActionListener(e->{
            ChooseImageForPerson(lblImage);
        });

        btnConsultClient.addActionListener(e->{
            new FormConsultClient(new String[] {"ID Client", "Name"}, FormClient.this).setVisible(true);
        });

        btnSave.addActionListener(e->{
            try {
                Validations();

                ServiceClient serviceClient = new ServiceClient();
                ServiceAddress serviceAddress = new ServiceAddress();

                ObjectMapper objectMapper = new ObjectMapper();

                try {
                    ModelClient client = objectMapper.readValue(serviceClient.FindById(Long.valueOf(txfCodClient.getText())), ModelClient.class);
                    //update
                }catch (Exception e1){
                    //continua codigo
                }

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                LocalDate dateCreation = LocalDate.parse(txfDateCreation.getText(), formatter);
                LocalDate dateLastUpdate = LocalDate.parse(txfDateLastUpdate.getText(), formatter);
                LocalDate dateOfBirth = LocalDate.parse(txfDateOfBirth.getText(), formatter);

                ModelAddress address = new ModelAddress();

                address.setCountry(txfCountry.getText());
                address.setNumber(Integer.parseInt(txfCodHouse.getText().trim()));
                address.setReference(txfReference.getText());
                address.setNeighborhood(txfNeighborhood.getText());
                address.setState((String) cbState.getSelectedItem());
                address.setStreet(txfStreet.getText());
                address.setZipcode(txfZipCode.getText());
                address.setCity(txfCity.getText());

                java.util.List<ModelAddress> addressList = objectMapper.readValue(serviceAddress.FindAll(), new TypeReference<List<ModelAddress>>() {});
                Long maxId = Long.valueOf(0);
                for(ModelAddress obj : addressList){
                    if(obj.getId() > maxId){
                        maxId = obj.getId();
                    }
                }
                maxId++;
                address.setId(maxId);

                ModelClient client = new ModelClient();

                java.util.List<ModelClient> clientList = objectMapper.readValue(serviceClient.FindAll(), new TypeReference<List<ModelClient>>() {});
                Long maxIdClient = Long.valueOf(0);
                for(ModelClient obj : clientList){
                    if(obj.getId() > maxIdClient){
                        maxIdClient = obj.getId();
                    }
                }
                maxIdClient++;

                client.setId(maxIdClient);
                client.setName(txfFullName.getText());
                client.setCpf(txfCPF.getText());
                client.setEmail(txfEmail.getText());
                client.setNameOfFather(txfNameOfFather.getText());
                client.setNameOfMother(txfNameOfMother.getText());
                client.setTelephone(txfNumberPhone.getText());
                client.setLastUpdate(dateLastUpdate);
                client.setProfession(txfProfession.getText());
                client.setDescription(txfDescription.getText());
                client.setDateCreation(dateCreation);
                client.setPhone(txfNumberPhone.getText());
                client.setAddress(address);
                client.setDateOfBorn(dateOfBirth);
                client.setSex((String) cbSex.getSelectedItem());
                client.setRg(txfRG.getText());
                client.setUrlPhoto(lblImage.getText());
                client.setFlagCreation(true);

                serviceClient.InsertClient(client);

                JOptionPane.showMessageDialog(null, "Client saved");

            } catch (Exception e1){
                System.out.println("ERROR insert: " + e1.getMessage());
            }

        });

        btnCancel.addActionListener(e->{
            dispose();
        });

    }
    @Override
    public void Update(Object obj) {

        ModelClient client = (ModelClient) obj;

        txfCodClient.setText(String.valueOf(client.getId()));
        txfFullName.setText(client.getName());
        txfCPF.setText(client.getCpf());
        txfDateOfBirth.setText(String.valueOf(client.getDateOfBorn()));
        txfNumberPhone.setText(client.getPhone());
        txfTelephoneHouse.setText(client.getTelephone());
        cbSex.setSelectedItem(client.getSex());
        txfStreet.setText(client.address.getStreet());
        txfCodHouse.setText(String.valueOf(client.address.getNumber()));
        txfZipCode.setText(String.valueOf(client.address.getZipcode()));
        txfReference.setText(client.address.getReference());
        txfNeighborhood.setText(client.address.getNeighborhood());
        txfCity.setText(client.address.getCity());
        cbState.setSelectedItem(client.address.getState());
        txfCountry.setText(client.address.getCountry());
        txfRG.setText(client.getRg());
        txfProfession.setText(client.getProfession());
        txfNameOfMother.setText(client.getNameOfMother());
        txfNameOfFather.setText(client.getNameOfFather());
        txfDescription.setText(client.getDescription());
        txfDateCreation.setText(String.valueOf(client.getDateCreation()));
        txfDateLastUpdate.setText(String.valueOf(client.getLastUpdate()));
    }

    public void ChooseImageForPerson(JLabel lblImage) {
        try{
            FormClient form = new FormClient();
            JFileChooser fcFile = new JFileChooser();
            fcFile.setDialogTitle("Select a image");
            fcFile.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int op = fcFile.showOpenDialog(form);
            if(op == JFileChooser.APPROVE_OPTION) {
                ImageIcon image = new ImageIcon(fcFile.getSelectedFile().getPath());
                lblImage.setIcon(new ImageIcon(image.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_DEFAULT)));
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        }

    }

    public Exception Validations() {
        try {
            if(cbSex.getSelectedIndex() == 0) {
                throw new Exception("Select a gender");
            }

            if (txfCPF.getText().length() != 11) {
                throw new Exception("CPF wrong");
            }





        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro Validação cpf: " + e.getMessage());
        }
        return null;
    }

}