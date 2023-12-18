package java.servlet;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.dbHandler.UserDbService;
import java.entity.User;
import java.exception.CustomException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/showUsers")
public class ShowUsersServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        logger.info(session.getAttribute("name"));
        logger.info(session.getAttribute("password"));
        try {
            ResultSet resultSet = UserDbService.getUsersFromDb();
            List<User> finalResList = new ArrayList<>();
            while (resultSet.next()){
                logger.info(resultSet.getInt(1));
                logger.info(resultSet.getString(2));
                logger.info(resultSet.getString(3));
                logger.info(resultSet.getBoolean(4));

                User user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getBoolean(4));
                finalResList.add(user);
            }
            session.setAttribute("ResultList",finalResList);
            resp.sendRedirect("/jsp/usersList.jsp");
        } catch (CustomException e) {
            logger.error(e.getMessage());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
