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
 * 学生控制
 *
 * @author 马文涛
 * @date 2022/11/08
 */
public class StuControl implements Control {
    public static StuControl stuControl;
    private final Connection con;

    /**
     * 获得实例
     *
     * @return {@link StuControl}
     * @throws PropertyVetoException 财产否决例外
     * @throws SQLException          sqlexception异常
     */
    public static StuControl getInstance() throws PropertyVetoException, SQLException {
        return stuControl == null ? new StuControl() : stuControl;
    }

    private StuControl() throws PropertyVetoException, SQLException {
        Source source = new Source();
        con = Source.getCon();
    }

    //获取最大学号，避免添加时主键重复
    public int getMaxSno() throws SQLException {
        String sql = "select max(sno) as sno from Students";
        PreparedStatement pre = con.prepareStatement(sql);
        ResultSet rest = pre.executeQuery();
        rest.next();
        return rest.getInt("sno");
    }

    //读取
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
     * 插入的委托
     *
     * @param s 数据
     * @throws SQLException sqlexception异常
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
     * 更新委托
     *
     * @param sno  学号
     * @param _new 新数据
     * @return boolean
     * @throws SQLException sqlexception异常
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
     * 删除的委托
     *
     * @param sno 学号
     * @return boolean
     * @throws SQLException sqlexception异常
     */
    private boolean delete(long sno) throws SQLException {
        String sql = "delete from Student where sno=?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setLong(1, sno);
        return pre.execute();
    }
}
