package java.servlet;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.dbHandler.MenuDbService;
import java.exception.CustomException;
import java.io.IOException;

@WebServlet("/addMenuItem")
public class AddMenuItemServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        logger.info(session.getAttribute("name"));
        logger.info(session.getAttribute("password"));

        try {
            if(!MenuDbService.checkIfMenuItemAlreadyExists(req.getParameter("nameMenuItemToAdd"))){
                MenuDbService.addMenuItemToDb(req.getParameter("nameMenuItemToAdd"), Integer.parseInt(req.getParameter("priceMenuItemToAdd")));
            }
            else {
                session.setAttribute("error","Такое блюдо уже добавлено!");
            }
            resp.sendRedirect("/jsp/admMainMenuForm.jsp");
        } catch (CustomException e) {
            logger.error(e.getMessage());
        }
    }
}
