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

public class GraControl implements Control {
    private Connection con;

    public GraControl() throws PropertyVetoException, SQLException {
        Source source = new Source();
        con = source.getCon();
    }

    @Override
    public List<Grade> read() throws SQLException {
        String sql = "select * from Grades";
        PreparedStatement per = con.prepareStatement(sql);
        ResultSet rest = per.executeQuery();
        List<Grade> grades = new ArrayList<>();
        while (rest.next()) {
            Grade g = new Grade(rest.getLong("sno"),
                    rest.getString("cname"),
                    rest.getInt("score"));
            grades.add(g);
        }
        return grades;
    }

    public List<Grade> read(long sno) throws SQLException {
        String sql = "select * from Grades where sno =?";
        PreparedStatement per = con.prepareStatement(sql);
        per.setLong(1, sno);
        ResultSet rest = per.executeQuery();
        List<Grade> grades = new ArrayList<>();
        while (rest.next()) {
            Grade g = new Grade(rest.getLong("sno"),
                    rest.getString("cname"),
                    rest.getInt("score"));
            grades.add(g);
        }
        return grades;
    }

    public List<Grade> read(String name) throws SQLException {
        StringBuffer _sql = new StringBuffer("select * from Grades where sno in (select sno from Students where name like '");
        _sql.append(name + "%" + "'" + ")");
        System.out.println(_sql.toString());
        PreparedStatement per = con.prepareStatement(_sql.toString());
        ResultSet rest = per.executeQuery();
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
        String sql = "insert into Grades(sno,cname,score)values(?,?,?)";
        PreparedStatement psr = con.prepareStatement(sql);
        psr.setLong(1, g.getSno());
        psr.setString(2, g.getCname());
        psr.setInt(3, g.getScore());
        psr.execute();
    }

    @Override
    public boolean update(Object _if, Object _new) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }


}
