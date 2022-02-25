import java.sql.*;
import java.util.List;

public class InterviewQuestionMain {

    private String connectionUrl = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";

    public static void main(String[] args) {
        getListOfPersonNamesAgeBiggerThan(30);
    }


    public List<String> getListOfPersonNamesAgeBiggerThan(int age) {
        List<int> names;
        try {
            Connection connection = DriverManager.getConnection(connectionUrl, "username", "password");
            Statement statement = connection.createStatement(); // prepared statement must be used
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PERSON WHERE AGE > " + age);
            while (resultSet.next()) {
                names.add(resultSet.getObject("NAME").toString());
            }

            int max = 0;
            int min = 9999999;
            for (int i=0;i<names.size();i++) {
                if (i > max) max = i;
                if (i < min) min = i;
            }

            return Arrays.asList(min + "", max + "");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return names;
    }
}