package BigHouse.Produlink.LibraryGUIProdulink;

import javax.swing.*;

public class MainProject {

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        new FormLogin().setVisible(true);
    }

}
