package java.dbHandler;

import java.exception.CustomException;
import java.sql.*;
import java.util.Objects;

public class MenuDbService {
    private final static String DB_URL = "jdbc:postgresql://localhost:5432/restaurant";
    private final static String DB_USER = "lisa";
    private final static String DB_PASSWORD = "1111";
    public static void addMenuItemToDb(String menuItem_name, int menuItem_price) throws CustomException {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            PreparedStatement ps = con.prepareStatement("INSERT INTO public .\"menu\""+"(name,price)" +"VALUES (?,?);");

            ps.setString(1,menuItem_name);
            ps.setInt(2, menuItem_price);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new CustomException(e.getMessage());
        }
    }
    public static Integer getIdByNumber(Integer number) throws CustomException {
        int resId =0;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM public .\"rooms_data\" ;");
            while (rs.next()){
                if(rs.getInt(3)==number){
                    resId = rs.getInt(2);
                    break;
                }
            }
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new CustomException(e.getMessage());
        }
        return resId;
    }
    public static void delMenuItemFromDb(String menuItem_name) throws CustomException {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            PreparedStatement ps = con.prepareStatement("DELETE FROM public .\"menu\""+"WHERE name = ?;");
            ps.setString(1, menuItem_name);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new CustomException(e.getMessage());
        }
    }
    public static boolean checkIfMenuItemAlreadyExists(String menuItem) throws CustomException {
        boolean menuItemExists = false;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            ResultSet rs = con.createStatement().executeQuery("SELECT name FROM public .\"menu\" ;");
            while (rs.next()){
                if(Objects.equals(rs.getString(1), menuItem)){
                    menuItemExists=true;
                }
            }
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new CustomException(e.getMessage());
        }

        return menuItemExists;
    }
    public static Integer getIdByName(String name) throws CustomException {
        int resId =0;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM public .\"menu\" ;");
            while (rs.next()){
                if(rs.getString(2).equals(name)){
                    resId = rs.getInt(1);
                    break;
                }
            }
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new CustomException(e.getMessage());
        }

        return resId;
    }

    public static String getNameById(int menuItem_id) throws CustomException {
        String resName = null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM public .\"menu\" ;");
            while (rs.next()){
                if(rs.getInt(1) == menuItem_id){
                    resName = rs.getString(2);
                    break;
                }
            }
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new CustomException(e.getMessage());
        }

        return resName;
    }
    public static ResultSet showAllMenuForAdmin() throws CustomException {
        ResultSet rs = null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            rs = con.createStatement().executeQuery("SELECT * FROM public .\"menu\" ");
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new CustomException(e.getMessage());
        }

        return rs;
    }
}
