package java.servlet;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.dbHandler.MenuDbService;
import java.dbHandler.OrderDbService;
import java.entity.Order;
import java.exception.CustomException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/showOrdersForUser")
public class ShowOrdersForClientServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        logger.info(session.getAttribute("name"));
        logger.info(session.getAttribute("password"));
        try {
            ResultSet resultSet = OrderDbService.showAllOrdersForUser(session.getAttribute("name").toString(), session.getAttribute("password").toString());
            List<Order> finalResList = new ArrayList<>();
            while (resultSet.next()){

                logger.info(resultSet.getInt(1));
                logger.info(resultSet.getInt(2));
                logger.info(resultSet.getInt(3));
                logger.info(resultSet.getInt(4));


                Order order = new Order(resultSet.getInt(1), MenuDbService.getNameById(resultSet.getInt(2)), resultSet.getInt(4) );
                finalResList.add(order);

            }
            session.setAttribute("ResultList",finalResList);
            resp.sendRedirect("/jsp/ordersListForUser.jsp");
        } catch (CustomException e) {
            logger.error(e.getMessage());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

