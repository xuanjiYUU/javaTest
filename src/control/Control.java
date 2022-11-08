package control;

import entity.Grade;
import entity.Student;

import java.sql.SQLException;
import java.util.List;

/**
 * ¿ØÖÆ
 * @author ÂíÎÄÌÎ
 * @date 2022/11/08
 */
public interface Control {
    List read(Object o) throws SQLException;

    void insert(Object o) throws SQLException;

    boolean update(Object _if, Object _new) throws SQLException;

    boolean delete(Object o) throws SQLException;

}
