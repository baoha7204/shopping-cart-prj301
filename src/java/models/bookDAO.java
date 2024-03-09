package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.utils;

public class bookDAO {

    public bookDAO() {
    }

    public bookDTO getBook(int id) {
        bookDTO book = null;
        try {
            Connection conn = utils.createConnection();
            PreparedStatement p = conn.prepareStatement("select * from tblBook where id = ? ");
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                String title = rs.getString("title");
                float price = rs.getFloat("price");
                book = new bookDTO(id, title, price);
            }
            rs.close();
            p.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Erorr connect DB");
        }
        return book;
    }

    public List<bookDTO> getBooks() {
        List<bookDTO> bookList = new ArrayList<>();
        try {
            Connection conn = utils.createConnection();
            PreparedStatement p = conn.prepareStatement("select * from tblBook ");
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                int bookId = rs.getInt(1);
                String bookTitle = rs.getString(2);
                float price = rs.getFloat(3);

                bookDTO book = new bookDTO(bookId, bookTitle, price);
                bookList.add(book);
            }
            rs.close();
            p.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Erorr connect DB");
        }
        return bookList;
    }
}
