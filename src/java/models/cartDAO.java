package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import utils.utils;

public class cartDAO {

    public boolean addCart(cartDTO cart) {
        boolean check = true;
        try {
            Connection conn = utils.createConnection();

            int userId = cart.getUser().getId();
            PreparedStatement p = conn.prepareStatement("INSERT INTO tblOrder (user_id) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            p.setInt(1, userId);
            int result_order = p.executeUpdate();
            if (result_order == 0) {
                p.close();
                return false;
            }
            ResultSet generatedKeys = p.getGeneratedKeys();
            int orderId = 0;
            if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
            }
            if (orderId == 0) {
                p.close();
                return false;
            }       
            List<bookCartDTO> list = cart.getList();
            for (bookCartDTO book : list) {
                p = conn.prepareStatement("INSERT INTO tblOrderItem (order_id, book_id, quantity) VALUES (?, ?, ?)");
                p.setInt(1, orderId);
                p.setInt(2, book.getId());
                p.setFloat(3, book.getQuantity());

                int result_orderItem = p.executeUpdate();
                if (result_orderItem <= 0) {
                    check = false;
                    break;
                }
            }
            p.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error add cart");
        }

        return check;
    }
}
