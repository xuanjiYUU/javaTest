package control;

import database.Source;
import entity.Student;
import tools.Date;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StuControl implements Control {
    private Connection con;

    //��ȡ���ѧ�ţ��������ʱ�����ظ�
    public int getMaxSno() throws SQLException {
        String sql = "select max(sno) as sno from Students";
        PreparedStatement per = con.prepareStatement(sql);
        ResultSet rest = per.executeQuery();
        rest.next();
        return rest.getInt("sno");
    }

    public StuControl() throws PropertyVetoException, SQLException {
        Source source = new Source();
        con = source.getCon();
    }

    //��ȡ
    @Override
    public List<Student> read() throws SQLException {
        String sql = "select * from Students";
        PreparedStatement per = con.prepareStatement(sql);
        ResultSet rest = per.executeQuery();
        List<Student> students = new ArrayList<>();
        while (rest.next()) {
            Student s = new Student(rest.getInt("sno"),
                    rest.getString("name"),
                    rest.getString("sex"),
                    Date.Format_String(rest.getDate("brithday")));
            students.add(s);
        }
        return students;
    }

    @Override
    public void insert(Object o) throws SQLException {
        Student s = (Student) o;
        String sql = "insert into Students(sno,name,sex,brithday)values(?,?,?,?)";
        PreparedStatement psr = con.prepareStatement(sql);
        psr.setLong(1, s.getSno());
        psr.setString(2, s.getName());
        psr.setString(3, s.getSex());
        psr.setDate(4, Date.Format_Date(s.getBrithday()));
        psr.execute();
    }

    @Override
    public boolean update(Object _if, Object _new) throws SQLException {
        Student newStudent = (Student) _new;
        String sql = "update Students set name=?,sex=?,brithday=? where sno=?";
        PreparedStatement per = con.prepareStatement(sql);
        per.setString(1, newStudent.getName());
        per.setString(2, newStudent.getSex());
        per.setDate(3, Date.Format_Date(newStudent.getBrithday()));
        return per.execute();
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }
}
