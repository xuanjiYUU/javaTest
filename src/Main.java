import control.Control;
import control.CouControl;
import control.GraControl;
import control.StuControl;
import entity.Course;
import entity.Grade;
import entity.Student;
import tools.Print;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

/**
 * 方法一：grade的read应同时read student
 * 方法二：维护一个全局的学生表，用线程刷新
 */
public class Main {
    public static void main(String[] args) {

        try {
            Control sc = StuControl.getInstance();
            GraControl gc = GraControl.getInstance();
           // gc.insert(new Grade(1l,"JAVA",100));
            Print.PrintArray(gc.read("南"));
            //sc.insert(new Student(1l,1l,"南希","男","2022-11-11"));

            //Print.PrintArray(sc.read(null));
            //Print.PrintArray(sc.read(1l));

        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}