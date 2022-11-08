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
 * ����һ��grade��readӦͬʱread student
 * ��������ά��һ��ȫ�ֵ�ѧ�������߳�ˢ��
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