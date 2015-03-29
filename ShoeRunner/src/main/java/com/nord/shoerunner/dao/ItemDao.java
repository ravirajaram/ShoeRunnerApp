/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nord.shoerunner.dao;

import com.nord.shoerunner.model.Item;
import com.nord.shoerunner.model.ShoeRunnerRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author xqn7
 */
public class ItemDao {

    public Item findByUpc(String upc) {
        String sql = "SELECT * FROM item WHERE upc = ?";
        Item item = null;
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, upc);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                item = processRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return item;
    }

    protected Item processRow(ResultSet rs) throws SQLException {
        Item item = new Item();
        item.setUpc(rs.getString("upc"));
        item.setUpc(rs.getString("sku"));
        item.setUpc(rs.getString("description"));
        item.setUpc(rs.getString("imageUrl"));
        return item;
    }
}
