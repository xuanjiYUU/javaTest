import control.Control;
import control.CouControl;
import control.GraControl;
import control.StuControl;
import execl.Student_Grade_ToExecl;
import tools.Print;
import tools.mError;


import java.beans.PropertyVetoException;
import java.sql.SQLException;

//--select sum(score) as sum from Grades,Students where Grades.sno=Students.sno and Grades.cname='JAVA' and Students.cno=1;
public class Main {
    public static void main(String[] args) throws mError {
        try {
            Control sc = StuControl.getInstance();
            Control gc = GraControl.getInstance();
            Control cc = CouControl.getInstance();
//            Print.PrintArray(gc.read(null));
            Student_Grade_ToExecl student_grade_toExecl = new Student_Grade_ToExecl();
            student_grade_toExecl.createGrades(gc.read(null));
//            System.out.println(student_grade_toExecl.getSumCoreGrades(gc.read(null), 0)
//            );

        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}