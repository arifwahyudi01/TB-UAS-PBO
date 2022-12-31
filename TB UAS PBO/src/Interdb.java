import java.sql.*;

//interface Database
public interface Interdb
{
    void view() throws SQLException;
    void update() throws SQLException;
    void delete() throws SQLException;
    void save() throws SQLException;
    void search() throws SQLException;
}
