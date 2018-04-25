import util.PostUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ChatServlet", urlPatterns = "/chat")
public class ChatServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String messageToSend = request.getParameter("messageToSend");
        messageToSend=new String(messageToSend.getBytes("utf-8"));
        String messageRcv = PostUtil.sendMessage(messageToSend);

        request.setAttribute("messageRcv", messageRcv);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

}
