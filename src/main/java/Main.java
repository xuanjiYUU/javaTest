import GUI.LoginClass;
import control.Control;
import control.GraControl;
import control.StuControl;
import entity.Student;
import tools.Print;
import tools.mError;

import javax.swing.*;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws mError, PropertyVetoException, SQLException {
//        Control gc = GraControl.getInstance();
//        Control sc = StuControl.getInstance();
//        Print.PrintArray(gc.read(null));
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new LoginClass();
            }
        });

    }

}