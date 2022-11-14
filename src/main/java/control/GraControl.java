package control;

import database.Source;
import entity.Cource;
import entity.Grade;
import entity.Student;
import tools.mError;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 成绩控制
 *
 * @author 马文涛
 * @date 2022/11/08
 */
public class GraControl implements Control {
    private final Connection con;
    public static GraControl graControl;
    //用于聚合数据
    private StuControl stuControl;

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
        stuControl = StuControl.getInstance();
        con = source.getCon();
    }


    /**
     * 读 数据聚合
     *
     * @return {@link List}<{@link Student}>
     * @throws SQLException sqlexception异常
     */
    @Override
    public List<Student> read(Object o) throws SQLException, mError {
        if (o == null) {
            return read();
        } else if (o.getClass() == String.class) {
            return read((String) o);
        } else if (o.getClass() == Long.class) {
            return read((long) o);
        } else if (o.getClass() == Cource.class) {
            return read((Cource) o);
        }
        return null;
    }

    /**
     * 读 委托
     *
     * @return {@link List}<{@link Student}>
     * @throws SQLException sqlexception异常
     */
    private List<Student> read() throws SQLException, mError {
        String sql = "select * from Grades";
        PreparedStatement pre = con.prepareStatement(sql);
        ResultSet rest = pre.executeQuery();
        List<Student> result = new ArrayList<>();
        Map<Long, Student> map = stuControl.mapping();
        while (rest.next()) {
            Grade g = new Grade(rest.getLong("sno"),
                    rest.getString("cname"),
                    rest.getInt("score"));
            map.get(g.getSno()).addGrade(g);
        }
        for (Student s : map.values()) {
            if (s.getGrades() != null) { //有成绩的学生
                s.setSum_score(readSumScore(s.getSno())); //设置总分，用于报表
                result.add(s);
            }
        }
        return result;
    }

    /**
     * 读 学号查询
     *
     * @param sno 学号
     * @return {@link List}<{@link Grade}>
     * @throws SQLException sqlexception异常
     */
    private List<Student> read(long sno) throws SQLException, mError {
        String sql = "select * from Grades where sno =?";
        PreparedStatement pre = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        pre.setLong(1, sno);
        ResultSet rest = pre.executeQuery();
        List<Student> result = new ArrayList<>();
        Map<Long, Student> map = stuControl.mapping();
        if (!rest.next()) {
            throw new mError("提示", "学号不存在");
        }
        rest.beforeFirst();
        while (rest.next()) {
            Grade g = new Grade(rest.getLong("sno"),
                    rest.getString("cname"),
                    rest.getInt("score"));
            System.out.println(g);
            map.get(g.getSno()).addGrade(g);
        }


        for (Student s : map.values()) {
            if (s.getGrades() != null) { //有成绩的学生
                s.setSum_score(readSumScore(s.getSno())); //设置总分，用于报表
                result.add(s);
            }
        }
        return result;
    }

    private List<Student> read(Cource c) throws SQLException, mError {
        String sql = "select * from Grades where cname =?";
        PreparedStatement pre = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        pre.setString(1, c.getCname());
        ResultSet rest = pre.executeQuery();
        List<Student> result = new ArrayList<>();
        Map<Long, Student> map = stuControl.mapping();
        if (!rest.next()) {
            throw new mError("提示", c.getCname() + "课程不存在");
        }
        rest.beforeFirst();
        while (rest.next()) {
            Grade g = new Grade(rest.getLong("sno"),
                    rest.getString("cname"),
                    rest.getInt("score"));
            System.out.println(g);
            map.get(g.getSno()).addGrade(g);
        }
        for (Student s : map.values()) {
            if (s.getGrades() != null) { //有成绩的学生
                s.setSum_score(readSumScore(s.getSno())); //设置总分，用于报表
                result.add(s);
            }
        }
        return result;
    }

    /**
     * 重复查询
     *
     * @return {@link List}<{@link Student}>
     * @throws SQLException sqlexception异常
     * @throws mError       米误差
     */
    private boolean read(Grade g) throws SQLException, mError {
        String sql = "select * from Grades where cname =? and sno=?";
        PreparedStatement pre = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        pre.setString(1, g.getCname());
        pre.setLong(2, g.getSno());
        ResultSet rest = pre.executeQuery();
        return rest.next();
    }

    /**
     * 读 模糊查询
     *
     * @param name 名字
     * @return {@link List}<{@link Grade}>
     * @throws SQLException sqlexception异常
     */
    private List<Student> read(String name) throws SQLException, mError {
        StringBuffer _sql = new StringBuffer("select * from Grades where sno in (select sno from Students where name like '");
        _sql.append(name + "%" + "'" + ")");
        PreparedStatement pre = con.prepareStatement(_sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rest = pre.executeQuery();
        List<Student> result = new ArrayList<>();
        Map<Long, Student> map = stuControl.mapping();
        if (!rest.next()) {
            throw new mError("提示", "姓名不存在");
        }
        rest.beforeFirst();
        while (rest.next()) {
            Grade g = new Grade(rest.getLong("sno"),
                    rest.getString("cname"),
                    rest.getInt("score"));
            map.get(g.getSno()).addGrade(g);
        }
        for (Student s : map.values()) {
            if (s.getGrades() != null) { //有成绩的学生
                s.setSum_score(readSumScore(s.getSno())); //设置总分，用于报表
                result.add(s);
            }
        }
        return result;
    }

    @Override
    public void insert(Object o) throws SQLException, mError {
        Grade g = (Grade) o;
        insert(g);
    }

    /**
     * 插入的委托
     *
     * @param g 数据
     * @throws SQLException sqlexception异常
     */
    private void insert(Grade g) throws SQLException, mError {
        if (read(g)) {
            throw new mError("提示", "该成绩已存在");
        } else {
            String sql = "insert into Grades(sno,cname,score)values(?,?,?)";
            PreparedStatement psr = con.prepareStatement(sql);
            psr.setLong(1, g.getSno());
            psr.setString(2, g.getCname());
            psr.setInt(3, g.getScore());
            psr.execute();
        }
    }

    @Override
    public boolean update(Object _if, Object _new) throws SQLException {
        Grade oldG = (Grade) _if;
        Grade newG = (Grade) _new;
        return update(oldG, newG);
    }

    /**
     * 更新的委托
     *
     * @param oldG oldg
     * @param newG newg
     * @return boolean
     * @throws SQLException sqlexception异常
     *                      UI时注意该更新只能基于已有grade
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
        return delete(g);

    }


    /**
     * 删除的委托
     *
     * @param g g
     * @return boolean
     * @throws SQLException sqlexception异常
     */
    private boolean delete(Grade g) throws SQLException {
        String sql = "delete from Grades where sno=? and cname=?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setLong(1, g.getSno());
        pre.setString(2, g.getCname());
        return pre.execute();
    }

    //提供外部接口
    public double readSumScore(long sno) throws SQLException {
        String sql = "select sum(score) as sum from Grades where sno=?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setLong(1, sno);
        ResultSet rest = pre.executeQuery();
        rest.next();
        return rest.getDouble("sum");
    }

    //提供外部接口
    public double readClass_Course_Avg_Score(long cno, String cname) throws SQLException {
        String sql = "select avg(score) as avg from Grades,Students where Grades.sno=Students.sno and Grades.cname=? and Students.cno=?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setLong(2, cno);
        pre.setString(1, cname);
        ResultSet rest = pre.executeQuery();
        rest.next();
        return rest.getDouble("avg");
    }
}
