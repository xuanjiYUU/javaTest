package control;

import database.Source;
import entity.Grade;
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
 * 成绩控制
 *
 * @author 马文涛
 * @date 2022/11/08
 */
public class GraControl implements Control {
    private final Connection con;
    public static GraControl graControl;

    /**
     * 获得实例
     *
     * @return {@link GraControl}
     * @throws PropertyVetoException 财产否决例外
     * @throws SQLException          sqlexception异常
     */
    public static GraControl getInstance() throws PropertyVetoException, SQLException {
        return graControl == null ? new GraControl() : graControl;
    }
    private GraControl() throws PropertyVetoException, SQLException {
        Source source = new Source();
        con = Source.getCon();
    }
    /**
     * 读 全表
     *
     * @return {@link List}<{@link Grade}>
     * @throws SQLException sqlexception异常
     */
    @Override
    public List<Grade> read() throws SQLException {
        String sql = "select * from Grades";
        PreparedStatement pre = con.prepareStatement(sql);
        ResultSet rest = pre.executeQuery();
        List<Grade> grades = new ArrayList<>();
        while (rest.next()) {
            Grade g = new Grade(rest.getLong("sno"),
                    rest.getString("cname"),
                    rest.getInt("score"));
            grades.add(g);
        }
        return grades;
    }

    /**
     * 读 学号查询
     *
     * @param sno 学号
     * @return {@link List}<{@link Grade}>
     * @throws SQLException sqlexception异常
     */
    public List<Grade> read(long sno) throws SQLException {
        String sql = "select * from Grades where sno =?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setLong(1, sno);
        ResultSet rest = pre.executeQuery();
        List<Grade> grades = new ArrayList<>();
        while (rest.next()) {
            Grade g = new Grade(rest.getLong("sno"),
                    rest.getString("cname"),
                    rest.getInt("score"));
            grades.add(g);
        }
        return grades;
    }

    /**
     * 读 模糊查询
     *
     * @param name 名字
     * @return {@link List}<{@link Grade}>
     * @throws SQLException sqlexception异常
     */
    public List<Grade> read(String name) throws SQLException {
        StringBuffer _sql = new StringBuffer("select * from Grades where sno in (select sno from Students where name like '");
        _sql.append(name + "%" + "'" + ")");
        System.out.println(_sql);
        PreparedStatement pre = con.prepareStatement(_sql.toString());
        ResultSet rest = pre.executeQuery();
        List<Grade> grades = new ArrayList<>();
        while (rest.next()) {
            Grade g = new Grade(rest.getLong("sno"),
                    rest.getString("cname"),
                    rest.getInt("score"));
            grades.add(g);
        }
        return grades;
    }

    @Override
    public void insert(Object o) throws SQLException {
        Grade g = (Grade) o;
        insert(g);
    }

    /**
     * 插入的委托
     *
     * @param g 数据
     * @throws SQLException sqlexception异常
     */
    private void insert(Grade g) throws SQLException {
        String sql = "insert into Grades(sno,cname,score)values(?,?,?)";
        PreparedStatement psr = con.prepareStatement(sql);
        psr.setLong(1, g.getSno());
        psr.setString(2, g.getCname());
        psr.setInt(3, g.getScore());
        psr.execute();
    }

    @Override
    public boolean update(Object _if, Object _new) throws SQLException {
        Grade oldG = (Grade) _if;
        Grade newG = (Grade) _new;
        return update(oldG, newG);
    }

    /**
     * 更新的委托
     * @param oldG oldg
     * @param newG newg
     * @return boolean
     * @throws SQLException sqlexception异常
     * UI时注意该更新只能基于已有grade
     */
    private boolean update(Grade oldG, Grade newG) throws SQLException {
        String sql = "update Grades set score=? where sno=? and cname =?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setInt(1, newG.getScore());
        pre.setLong(2, oldG.getSno());
        pre.setString(3, oldG.getCname());
        return pre.execute();
    }

    @Override
    public boolean delete(Object o) throws SQLException {
        Grade g = (Grade) o;
        return delete(g.getCname(), g.getSno());

    }

    /**
     * 删除的委托
     * @param cname 课程名
     * @param sno   学号
     * @return boolean
     * @throws SQLException sqlexception异常
     */
    private boolean delete(String cname, long sno) throws SQLException {
        String sql = "delete from Grades where sno=? and cname=?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setLong(1, sno);
        pre.setString(2, cname);
        return pre.execute();
    }
}
