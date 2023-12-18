package java.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.dbHandler.OrderDbService;
import java.dbHandler.UserDbService;
import java.exception.CustomException;
import java.io.IOException;

@WebServlet("/delOrder")
public class RemoveOrderServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        logger.info(session.getAttribute("name"));
        logger.info(session.getAttribute("password"));
        try {
            int user_id_1 = UserDbService.getIdByNameAndPassword(session.getAttribute("name").toString(),session.getAttribute("password").toString());
            logger.info(user_id_1);
            int user_id_2 = OrderDbService.getUserIdById(Integer.parseInt(req.getParameter("idOrderToDel")));
            logger.info(user_id_2);
            if(OrderDbService.checkIfOrderExists(Integer.parseInt(req.getParameter("idOrderToDel"))) && user_id_1 == user_id_2){
                OrderDbService.delOrderFromDb(Integer.parseInt(req.getParameter("idOrderToDel")));
            }
            else {
                session.setAttribute("error","Такого заказа нет!.");
            }
            resp.sendRedirect("/jsp/clientMainMenuForm.jsp");
        } catch (CustomException e) {
            logger.error(e.getMessage());
        }
    }
}
