import java.sql.*;

public class FirstDatabaseExample {

    static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://localhost/empl";

    static final String USER="root";
    static final String PASS="database";

    public static void main(String[] args)
    {
        Connection connection=null;
        Statement statement=null;

        try
        {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            connection=DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            statement=connection.createStatement();
            String sql;
            sql="SELECT id, first, last, age FROM Employees";
            ResultSet resultSet=statement.executeQuery(sql);

            while(resultSet.next())
            {
                int id=resultSet.getInt("id");
                int age=resultSet.getInt("age");
                String firstName=resultSet.getString("first");
                String lastName=resultSet.getString("last");

                System.out.println("ID: "+id);
                System.out.println(("Age: "+age));
                System.out.println("First Name: "+firstName);
                System.out.println("Last Name: "+lastName);
            }

            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (SQLException exc)
        {
            exc.printStackTrace();
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            try
            {
                if (statement!=null) statement.close();
            }
            catch (SQLException exc){}
            try
            {
                if (connection!=null) connection.close();
            }
            catch (SQLException exc)
            {
                exc.printStackTrace();
            }
        }

        System.out.println("Bye!");
    }
}
