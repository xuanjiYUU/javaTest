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

public class CouControl implements Control {
    private Connection con;

    public CouControl() throws PropertyVetoException, SQLException {
        Source source = new Source();
        con = source.getCon();
    }
    @Override
    public List<Course> read() throws SQLException {
        String sql = "select * from Courses";
        PreparedStatement per = con.prepareStatement(sql);
        ResultSet rest = per.executeQuery();
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
        String sql = "insert into Courses(cname)values(?)";
        PreparedStatement psr = con.prepareStatement(sql);
        psr.setString(1, c.getCname());
        psr.execute();
    }

    @Override
    public void updata(Object _if, Object _new) {

    }

    @Override
    public void delete(Object o) {

    }
}
