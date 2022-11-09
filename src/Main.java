import control.Control;
import control.CouControl;
import control.GraControl;
import control.StuControl;
import entity.Course;
import entity.Grade;
import entity.Student;
import tools.Print;
import tools.mError;

import java.awt.*;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Control sc = StuControl.getInstance();
            Control gc = GraControl.getInstance();
            Print.PrintArray(sc.read(1111l));
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (mError e) {
            throw new RuntimeException(e);
        }
    }

}