package control;

import database.Source;
import entity.Cource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程控制
 * 测试通过
 * * @author 马文涛
 * @date 2022/11/08
 */
public class CouControl implements Control {
    private final Connection con;
    public static CouControl couControl;

    /**
     * 获得实例
     * @return {@link CouControl}
     * @throws PropertyVetoException 财产否决例外
     * @throws SQLException          sqlexception异常
     */
    public static CouControl getInstance() throws PropertyVetoException, SQLException {
        return couControl == null ? new CouControl() : couControl;
    }

    private CouControl() throws PropertyVetoException, SQLException {
        Source source = new Source();
        con = Source.getCon();
    }

    @Override
    public List<Cource> read(Object o) throws SQLException {
        String sql = "select * from Courses";
        PreparedStatement pre = con.prepareStatement(sql);
        ResultSet rest = pre.executeQuery();
        List<Cource> cours = new ArrayList<>();
        while (rest.next()) {
            Cource c = new Cource(rest.getString("cname"));
            cours.add(c);
        }
        return cours;
    }

    @Override
    public void insert(Object o) throws SQLException {
        Cource c = (Cource) o;
        insert(c);
    }

    /**
     * 插入的委托
     *
     * @param c 数据
     * @throws SQLException sqlexception异常
     */
    private void insert(Cource c) throws SQLException {
        String sql = "insert into Courses(cname)values(?)";
        PreparedStatement psr = con.prepareStatement(sql);
        psr.setString(1, c.getCname());
        psr.execute();
    }

    @Override
    public boolean update(Object _if, Object _new) throws SQLException {
        //if (true) { //判断是否仍然存在课程名为_if的成绩
            return update((Cource) _if, (Cource) _new);
        //} else {
         //   return false;
        //}
    }
    /**
     * 更新的委托
     * @param _old 老
     * @param _new 新
     * @return boolean
     * @throws SQLException sqlexception异常
     */
    private boolean update(Cource _old, Cource _new) throws SQLException {
        String sql = "update Courses set cname=? where cname=?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(2, _old.getCname());
        pre.setString(1, _new.getCname());
        return pre.execute();
    }

    @Override
    public boolean delete(Object o) throws SQLException {
        return delete((Cource) o);
    }


    /**
     * 删除的委托
     *
     * @param g g
     * @return boolean
     * @throws SQLException
     */
    public boolean delete(Cource g) throws SQLException {
        String sql = "delete from Courses where cname=?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(1, g.getCname());
        return pre.execute();
    }


}
