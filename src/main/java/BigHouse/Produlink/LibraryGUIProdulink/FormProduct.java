package BigHouse.Produlink.LibraryGUIProdulink;

import BigHouse.Produlink.LibraryProdulink.Observer.Observer;

import javax.swing.*;

public class FormProduct extends JInternalFrame implements Observer {
    public FormProduct() {

        setSize(1000,800);
        setTitle("Product");
        setIconifiable(true);
        setMaximizable(false);
        setClosable(true);
        setResizable(false);
        setLocation(175, 20);

        BuildForm();
        ActionsButtons();
    }

    private void BuildForm(){

    }

    private void ActionsButtons(){

    }

    @Override
    public void Update(Object obj) {

    }
}
