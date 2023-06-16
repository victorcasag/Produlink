package BigHouse.Produlink.LibraryProdulink.Backup;

import java.io.File;

import javax.swing.JOptionPane;

public class Backup {
    public static void MakeBackup() {

        File directory = new File("/Users/victorcasagrande/Desktop/Projects/Produlink/src/main/java/BigHouse/Produlink/LibraryProdulink/Backup");
        File archive = new File("/Users/victorcasagrande/Desktop/Projects/Produlink/src/main/java/BigHouse/Produlink/LibraryProdulink/Backup/ProdulinkBackup.sql");

        if(!directory.isDirectory()) {
            directory.mkdir();
        }

        try {
            if(archive.exists()) {
                if(JOptionPane.showConfirmDialog(null, "Backup file already exists. \nWant to overwrite?", "Backup", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    archive.delete();

                    if(new File("/Users/victorcasagrande/Desktop/Projects/Produlink/src/main/java/BigHouse/Produlink/LibraryProdulink/Backup/Backup.sh").isFile()) {
                        Process process = Runtime.getRuntime().exec("/Users/victorcasagrande/Desktop/Projects/Produlink/src/main/java/BigHouse/Produlink/LibraryProdulink/Backup/Backup.sh");
                        process.waitFor();
                        if(process.exitValue()==0) {
                            JOptionPane.showMessageDialog(null, "Backup Done Successfully!");
                        }else {
                            JOptionPane.showMessageDialog(null, "Backup Failed");
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "Missing .SH file");
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Backup canceled by user!");

                }

            }else if(new File("/Users/victorcasagrande/Desktop/Projects/Produlink/src/main/java/BigHouse/Produlink/LibraryProdulink/Backup/Backup.sh").isFile()) {
                Process process = Runtime.getRuntime().exec("/Users/victorcasagrande/Desktop/Projects/Produlink/src/main/java/BigHouse/Produlink/LibraryProdulink/Backup/Backup.sh");
                process.waitFor();
                if(process.exitValue()==0) {
                    JOptionPane.showMessageDialog(null, "Backup Done Successfully!");
                }else {
                    JOptionPane.showMessageDialog(null, "Backup Failed");
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Backup Exception: " + e.getMessage());
        }


    }
}