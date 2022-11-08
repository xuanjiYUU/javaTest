import control.Control;
import control.CouControl;
import control.GraControl;
import control.StuControl;
import entity.Course;
import entity.Grade;
import entity.Student;
import tools.Date;
import tools.Print;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 方法一：grade的read应同时read student
 * 方法二：维护一个全局的学生表，用线程刷新
 */
public class Main {
    public static void main(String[] args) {
        try {
            Control sc = StuControl.getInstance();
            Control gc = GraControl.getInstance();
            Control cc = CouControl.getInstance();
            Print.PrintArray(gc.read());
            Print.PrintArray(sc.read());
            Print.PrintArray(cc.read());
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}