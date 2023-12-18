package java.servlet;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.dbHandler.MenuDbService;
import java.entity.MenuItem;
import java.exception.CustomException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/showMenu")
public class ShowAllMenuServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        logger.info(session.getAttribute("name"));
        logger.info(session.getAttribute("password"));
        try {
            ResultSet resultSet = MenuDbService.showAllMenuForAdmin();
            List<MenuItem> menuItemList = new ArrayList<>();
            while (resultSet.next()){
                logger.info(resultSet.getInt(1));
                logger.info(resultSet.getString(2));
                logger.info(resultSet.getInt(3));

                MenuItem menuItem = new MenuItem(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
                menuItemList.add(menuItem);
            }
            session.setAttribute("ResultList", menuItemList);
            resp.sendRedirect("/jsp/menuList.jsp");
        } catch (CustomException e) {
            logger.error(e.getMessage());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
