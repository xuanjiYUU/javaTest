package database;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class Source {
    private ComboPooledDataSource dataSource;
    public static Connection con;
    public Source() throws PropertyVetoException, SQLException {
        getConnection(); //初始化配置
        con=dataSource.getConnection();
    }
    public void getConnection() throws PropertyVetoException, SQLException {
        dataSource = new ComboPooledDataSource();
        //对池进行四大参数的配置
        dataSource.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setJdbcUrl("jdbc:sqlserver://localhost:1433;DatabaseName=StuMS;trustServerCertificate=true");
        dataSource.setUser("sa");
        dataSource.setPassword("mawt1016?");
        //池配置
        dataSource.setAcquireIncrement(5);
        dataSource.setInitialPoolSize(10);
        dataSource.setMinPoolSize(2);
        dataSource.setMaxPoolSize(30);
    }

    public static Connection getCon() {
        return con;
    }
    public void closeConnection() throws SQLException {
        con.close();
    }
}
