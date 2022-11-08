package control;

import database.Source;
import entity.Grade;
import entity.Student;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * �ɼ�����
 *
 * @author ������
 * @date 2022/11/08
 */
public class GraControl implements Control {
    private final Connection con;
    public static GraControl graControl;
    //���ھۺ�����
    private StuControl stuControl;

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
        stuControl = StuControl.getInstance();
        con = source.getCon();
    }


    /**
     * �� ���ݾۺ�
     *
     * @return {@link List}<{@link Student}>
     * @throws SQLException sqlexception�쳣
     */
    @Override
    public List<Student> read(Object o) throws SQLException {
        if (o == null) {
            return read();
        } else if (o.getClass() == String.class) {
            return read((String) o);
        } else if (o.getClass() == Long.class) {
            return read((long) o);
        }
        return null;
    }

    /**
     * �� ί��
     *
     * @return {@link List}<{@link Student}>
     * @throws SQLException sqlexception�쳣
     */
    private List<Student> read() throws SQLException {
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
            if (s.getGrades() != null) { //�гɼ���ѧ��
                result.add(s);
            }
        }

        return result;
    }

    /**
     * �� ѧ�Ų�ѯ
     *
     * @param sno ѧ��
     * @return {@link List}<{@link Grade}>
     * @throws SQLException sqlexception�쳣
     */
    private List<Student> read(long sno) throws SQLException {
        String sql = "select * from Grades where sno =?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setLong(1, sno);
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
            if (s.getGrades() != null) { //�гɼ���ѧ��
                result.add(s);
            }
        }
        return result;
    }

    /**
     * �� ģ����ѯ
     *
     * @param name ����
     * @return {@link List}<{@link Grade}>
     * @throws SQLException sqlexception�쳣
     */
    private List<Student> read(String name) throws SQLException {
        StringBuffer _sql = new StringBuffer("select * from Grades where sno in (select sno from Students where name like '");
        _sql.append(name + "%" + "'" + ")");
        PreparedStatement pre = con.prepareStatement(_sql.toString());
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
            if (s.getGrades() != null) { //�гɼ���ѧ��
                result.add(s);
            }
        }
        return result;
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
     *
     * @param oldG oldg
     * @param newG newg
     * @return boolean
     * @throws SQLException sqlexception�쳣
     *                      UIʱע��ø���ֻ�ܻ�������grade
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
     * ɾ����ί��
     *
     * @param g g
     * @return boolean
     * @throws SQLException sqlexception�쳣
     */
    private boolean delete(Grade g) throws SQLException {
        String sql = "delete from Grades where sno=? and cname=?";
        PreparedStatement pre = con.prepareStatement(sql);
        pre.setLong(1, g.getSno());
        pre.setString(2, g.getCname());
        return pre.execute();
    }
}
