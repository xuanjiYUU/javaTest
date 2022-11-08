package control;

import database.Source;
import entity.Course;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * �γ̿���
 * ����ͨ��
 * * @author ������
 * @date 2022/11/08
 */
public class CouControl implements Control {
    private final Connection con;
    public static CouControl couControl;

    /**
     * ���ʵ��
     * @return {@link CouControl}
     * @throws PropertyVetoException �Ʋ��������
     * @throws SQLException          sqlexception�쳣
     */
    public static CouControl getInstance() throws PropertyVetoException, SQLException {
        return couControl == null ? new CouControl() : couControl;
    }

    private CouControl() throws PropertyVetoException, SQLException {
        Source source = new Source();
        con = Source.getCon();
    }

    @Override
    public List<Course> read(Object o) throws SQLException {
        String sql = "select * from Courses";
        PreparedStatement pre = con.prepareStatement(sql);
        ResultSet rest = pre.executeQuery();
        List<Course> courses = new ArrayList<>();
        while (rest.next()) {
            Course c = new Course(rest.getString("cname"));
            courses.add(c);
        }
        return courses;
    }

    @Override
    public void insert(Object o) throws SQLException {
        Course c = (Course) o;
        insert(c);
    }

    /**
     * �����ί��
     *
     * @param c ����
     * @throws SQLException sqlexception�쳣
     */
    private void insert(Course c) throws SQLException {
        String sql = "insert into Courses(cname)values(?)";
        PreparedStatement psr = con.prepareStatement(sql);
        psr.setString(1, c.getCname());
        psr.execute();
    }

    @Override
    public boolean update(Object _if, Object _new) throws SQLException {
        //if (true) { //�ж��Ƿ���Ȼ���ڿγ���Ϊ_if�ĳɼ�
            return update((Course) _if, (Course) _new);
        //} else {
         //   return false;
        //}
    }
    /**
     * ���µ�ί��
     * @param _old ��
     * @param _new ��
     * @return boolean
     * @throws SQLException sqlexception�쳣
     */
    private boolean update(Course _old, Course _new) throws SQLException {
        String sql = "update Courses set cname=? where cname=?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(2, _old.getCname());
        pre.setString(1, _new.getCname());
        return pre.execute();
    }

    @Override
    public boolean delete(Object o) throws SQLException {
        return delete((Course) o);
    }


    /**
     * ɾ����ί��
     *
     * @param g g
     * @return boolean
     * @throws SQLException
     */
    public boolean delete(Course g) throws SQLException {
        String sql = "delete from Courses where cname=?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setString(1, g.getCname());
        return pre.execute();
    }


}
