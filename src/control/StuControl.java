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


/**
 * ѧ������
 *
 * @author ������
 * @date 2022/11/08
 */
public class StuControl implements Control {
    public static StuControl stuControl;
    private final Connection con;

    /**
     * ���ʵ��
     *
     * @return {@link StuControl}
     * @throws PropertyVetoException �Ʋ��������
     * @throws SQLException          sqlexception�쳣
     */
    public static StuControl getInstance() throws PropertyVetoException, SQLException {
        return stuControl == null ? new StuControl() : stuControl;
    }

    private StuControl() throws PropertyVetoException, SQLException {
        Source source = new Source();
        con = Source.getCon();
    }

    //��ȡ���ѧ�ţ��������ʱ�����ظ�
    public int getMaxSno() throws SQLException {
        String sql = "select max(sno) as sno from Students";
        PreparedStatement pre = con.prepareStatement(sql);
        ResultSet rest = pre.executeQuery();
        rest.next();
        return rest.getInt("sno");
    }

    //��ȡ
    @Override
    public List<Student> read() throws SQLException {
        String sql = "select * from Students";
        PreparedStatement pre = con.prepareStatement(sql);
        ResultSet rest = pre.executeQuery();
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

    public List<Student> read(long sno) throws SQLException {
        String sql = "select * from Students where sno=?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setLong(1, sno);
        ResultSet rest = pre.executeQuery();
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
        insert(s);
    }

    /**
     * �����ί��
     *
     * @param s ����
     * @throws SQLException sqlexception�쳣
     */
    private void insert(Student s) throws SQLException {
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
        long sno = (long) _if;
        return update(_if, newStudent);
    }

    /**
     * ����ί��
     *
     * @param sno  ѧ��
     * @param _new ������
     * @return boolean
     * @throws SQLException sqlexception�쳣
     */
    private boolean update(long sno, Student _new) throws SQLException {
        String sql = "update Students set name=?,sex=?,brithday=? where sno=?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(1, _new.getName());
        pre.setString(2, _new.getSex());
        pre.setDate(3, Date.Format_Date(_new.getBrithday()));
        return pre.execute();
    }

    @Override
    public boolean delete(Object o) throws SQLException {
        long sno = (long) o;
        return delete(sno);
    }

    /**
     * ɾ����ί��
     *
     * @param sno ѧ��
     * @return boolean
     * @throws SQLException sqlexception�쳣
     */
    private boolean delete(long sno) throws SQLException {
        String sql = "delete from Student where sno=?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setLong(1, sno);
        return pre.execute();
    }
}
