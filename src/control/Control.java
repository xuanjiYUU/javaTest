package control;

import java.sql.SQLException;
import java.util.List;

public interface Control {
    List read() throws SQLException;

    void insert(Object o) throws SQLException;

    void updata(Object _if, Object _new);

    void delete(Object o);
}
