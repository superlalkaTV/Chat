package servlet;

import DAO.MessageDAO;
import DAO.MessageDAOAbstraction;
import com.google.gson.Gson;
import com.mysql.cj.util.StringUtils;
import model.Message;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/chat"}, name = "chat")
public class ChatServlet extends HttpServlet {
    private final MessageDAOAbstraction messageDAO = new MessageDAO();
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Message> messages = messageDAO.getFiftyLastMessages();

        Gson gson = new Gson();
        String json = gson.toJson(messages);

        PrintWriter printWriter = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        printWriter.write(json);
        printWriter.close();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String json = parseJson(request);
        Gson gson = new Gson();

        Message message = gson.fromJson(json, Message.class);

        if (!StringUtils.isNullOrEmpty(message.getNickname()) && !StringUtils.isNullOrEmpty(message.getMessage())) {
            messageDAO.addMessage(message);
        }
        doGet(request, response);
    }

    private String parseJson(HttpServletRequest request) {
        StringBuilder jb = new StringBuilder();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jb.toString();
    }
}



