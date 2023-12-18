package java.dbHandler;



import java.exception.CustomException;
import java.sql.*;

public class OrderDbService {
    private final static String DB_URL = "jdbc:postgresql://localhost:5432/restaurant";
    private final static String DB_USER = "lisa";
    private final static String DB_PASSWORD = "1111";
    public static void addMenuItemToOrderDb(String name, String password, String item_name, int quantity) throws CustomException {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            int user_id = UserDbService.getIdByNameAndPassword(name, password);
            int menuItem_id =MenuDbService.getIdByName(item_name);
            PreparedStatement ps = con.prepareStatement("INSERT INTO public .\"orders\""+"(menuItem_id, user_id, quantity)" +"VALUES (?,?,?);");

            ps.setInt(1, menuItem_id);
            ps.setInt(2, user_id);
            ps.setInt(3, quantity);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new CustomException(e.getMessage());
        }
    }

    public static ResultSet showAllOrdersForAdmin() throws CustomException {
        ResultSet rs = null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            rs = con.createStatement().executeQuery("SELECT * FROM public .\"orders\" ");
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new CustomException(e.getMessage());
        }

        return rs;
    }


    public static ResultSet showAllOrdersForUser(String name, String password) throws CustomException {
        ResultSet rs = null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            int user_id = UserDbService.getIdByNameAndPassword(name, password);
            rs = con.createStatement().executeQuery("SELECT * FROM public .\"orders\" WHERE user_id = " + user_id);
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new CustomException(e.getMessage());
        }

        return rs;
    }
    public static boolean checkIfOrderExists(int order_id) throws CustomException {
        boolean orderExists = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM public .\"orders\" ;");
            while (rs.next()){
                if(rs.getInt(1) == order_id){
                    orderExists=true;
                }
            }
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new CustomException(e.getMessage());
        }

        return orderExists;
    }

    public static Integer getUserIdById(int order_id) throws CustomException {
        int resId =0;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM public .\"orders\" ;");
            while (rs.next()){
                if(rs.getInt(1) == order_id){
                    resId = rs.getInt(3);
                    break;
                }
            }
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new CustomException(e.getMessage());
        }

        return resId;
    }


    public static void delOrderFromDb(int order_id) throws CustomException {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            PreparedStatement ps = con.prepareStatement("DELETE FROM public .\"orders\""+"WHERE id = ?;");
            ps.setInt(1, order_id);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new CustomException(e.getMessage());
        }
    }

}
