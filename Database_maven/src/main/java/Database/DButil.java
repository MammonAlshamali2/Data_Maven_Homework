package Database;
import java.sql.*;
public abstract class DButil {
    protected Connection con = null;
    protected Statement stmt = null;
    abstract Statement connect() throws SQLException;

    abstract void disconnect() throws SQLException;
    public void select(String table) throws SQLException {
        ResultSet rs=connect().executeQuery("select * from "+table);
        while(rs.next())
        {
            System.out.println("ID      : "+rs.getInt("user_id"));
            System.out.println("username: "+rs.getString("username"));
            System.out.println("password: "+rs.getString("password"));
        }
        disconnect();
    }
    public void insert(String table, String[] columns, String[] values){
        try {

            StringBuilder builder = new StringBuilder();
            builder.append("insert into ");
            builder.append(table);
            builder.append("(");
            for (int i = 0; i < columns.length; i++) {
                if (i != columns.length - 1) {
                    builder.append(columns[i]).append(",");
                } else {
                    builder.append(columns[i]);
                }
            }
            builder.append(")");
            builder.append(" values");
            builder.append("(");
            for (int i = 0; i < values.length; i++) {
                if (i != columns.length - 1) {
                    builder.append("'").append(values[i]).append("',");
                } else {
                    builder.append("'").append(values[i]).append("'");
                }
            }
            builder.append(")");
            System.out.println(builder);
            connect().execute(builder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkUserIfExist(String username) {
        try {
            ResultSet rs = connect().executeQuery("select * from users where username='" + username + "'");
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean checkUserIfExist(int ID) {
        try {
            ResultSet rs = connect().executeQuery("select * from users where user_id=" + ID);
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public void update(String table, String[] columns, String[] values,int ID){
        try {

            StringBuilder builder = new StringBuilder();
            builder.append("UPDATE ");
            builder.append(table);
            builder.append(" SET ");
            for (int i = 0; i < columns.length; i++) {
                if (i != columns.length - 1) {
                    builder.append(columns[i]).append("='").append(values[i]).append("',");
                } else {
                    builder.append(columns[i]).append("='").append(values[i]).append("'");
                }
            }
            builder.append(" where ");
            builder.append("user_id=").append(ID);
            System.out.println(builder);
            connect().execute(builder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void delete(String query){
        try {
            connect().execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
