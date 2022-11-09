import com.alibaba.excel.EasyExcel;
import control.Control;
import control.GraControl;
import control.StuControl;
import entity.Grade;
import entity.Student;
import execl.Student_Grade_ToExecl;
import tools.Print;
import tools.mError;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws mError {
        try {
            Control sc = StuControl.getInstance();
            Control gc = GraControl.getInstance();
            Student_Grade_ToExecl student_grade_toExecl = new Student_Grade_ToExecl();
            student_grade_toExecl.createGrades("grades","测试",gc);
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}