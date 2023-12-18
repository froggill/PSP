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
import java.exception.CustomException;
import java.io.IOException;

@WebServlet("/addOrder")
public class AddOrderServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        logger.info(session.getAttribute("name"));
        logger.info(session.getAttribute("password"));

        try {
            if(MenuDbService.checkIfMenuItemAlreadyExists(req.getParameter("nameMenuItemToOrder"))){
                OrderDbService.addMenuItemToOrderDb(session.getAttribute("name").toString(), session.getAttribute("password").toString(), req.getParameter("nameMenuItemToOrder"), Integer.parseInt(req.getParameter("quantityMenuItemToOrder")));
            }
            else {
                session.setAttribute("error","Такого блюда нет в меню!");
            }
            resp.sendRedirect("/jsp/clientMainMenuForm.jsp");
        } catch (CustomException e) {
            logger.error(e.getMessage());
        }
    }
}
