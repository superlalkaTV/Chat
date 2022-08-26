package Utils;

public class Const {
    public static final String GET_50_LAST_MESSAGES = "with last_messages as (\n" +
            "SELECT *\n" +
            "FROM messages\n" +
            "ORDER BY message_time DESC\n" +
            "LIMIT 50\n" +
            ")\n" +
            "select * from last_messages order by message_time ASC;";

    public static final String ADD_MESSAGE = "insert into messages (nick_name, message_time, message) values (?,now(),?);";
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/test";
    public static final String DATABASE_USER = "root";
    public static final String DATABASE_PASSWORD = "Qwe123#";

}
