package DAO;

import model.Message;

import java.util.List;

public interface MessageDAOAbstraction {

    public List<Message> getFiftyLastMessages();

    public void addMessage(Message message);
}
