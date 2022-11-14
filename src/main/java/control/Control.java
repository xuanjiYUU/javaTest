package control;

import tools.mError;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;

/**
 * 控制
 * @author 马文涛
 * @date 2022/11/08
 */
public interface Control {
    List read(Object o) throws SQLException, mError;

    void insert(Object o) throws SQLException, mError;

    boolean update(Object _if, Object _new) throws SQLException;

    /**
     * @param o
     * @return boolean
     * @throws SQLException
     * @throws PropertyVetoException
     */
    boolean delete(Object o) throws SQLException, PropertyVetoException;

}
