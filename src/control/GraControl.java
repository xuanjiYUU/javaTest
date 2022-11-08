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
 * �ɼ�����
 *
 * @author ������
 * @date 2022/11/08
 */
public class GraControl implements Control {
    private final Connection con;
    public static GraControl graControl;

    /**
     * ���ʵ��
     *
     * @return {@link GraControl}
     * @throws PropertyVetoException �Ʋ��������
     * @throws SQLException          sqlexception�쳣
     */
    public static GraControl getInstance() throws PropertyVetoException, SQLException {
        return graControl == null ? new GraControl() : graControl;
    }
    private GraControl() throws PropertyVetoException, SQLException {
        Source source = new Source();
        con = Source.getCon();
    }
    /**
     * �� ȫ��
     *
     * @return {@link List}<{@link Grade}>
     * @throws SQLException sqlexception�쳣
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
     * �� ѧ�Ų�ѯ
     *
     * @param sno ѧ��
     * @return {@link List}<{@link Grade}>
     * @throws SQLException sqlexception�쳣
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
     * �� ģ����ѯ
     *
     * @param name ����
     * @return {@link List}<{@link Grade}>
     * @throws SQLException sqlexception�쳣
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
     * �����ί��
     *
     * @param g ����
     * @throws SQLException sqlexception�쳣
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
     * ���µ�ί��
     * @param oldG oldg
     * @param newG newg
     * @return boolean
     * @throws SQLException sqlexception�쳣
     * UIʱע��ø���ֻ�ܻ�������grade
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
     * ɾ����ί��
     * @param cname �γ���
     * @param sno   ѧ��
     * @return boolean
     * @throws SQLException sqlexception�쳣
     */
    private boolean delete(String cname, long sno) throws SQLException {
        String sql = "delete from Grades where sno=? and cname=?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setLong(1, sno);
        pre.setString(2, cname);
        return pre.execute();
    }
}
