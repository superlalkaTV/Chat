package DAO;

import Utils.Const;
import model.Message;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class MessageDAO implements MessageDAOAbstraction {

    @Override
    public List<Message> getFiftyLastMessages() {
        try {
            Class.forName(Const.JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = DriverManager.getConnection(Const.DATABASE_URL, Const.DATABASE_USER, Const.DATABASE_PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(Const.GET_50_LAST_MESSAGES);
            List<Message> messages = new LinkedList<>();
            while (rs.next()) {
                String nickname = rs.getString("nick_name");
                Time messageTime = rs.getTime("message_time");
                String message = rs.getString("message");
                messages.add(new Message(nickname, messageTime, message));
            }
            return messages;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }
    @Override
    public void addMessage(Message message) {
        try {
            Class.forName(Const.JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection(Const.DATABASE_URL, Const.DATABASE_USER, Const.DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(Const.ADD_MESSAGE);) {
            preparedStatement.setString(1, message.getNickname());
            preparedStatement.setString(2, message.getMessage());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
