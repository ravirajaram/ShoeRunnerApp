/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nord.shoerunner.dao;

import com.nord.shoerunner.model.ShoeRunnerRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xqn7
 */
public class ShoeRunnerRequestDao {

    public List<ShoeRunnerRequest> findAll() {
        List<ShoeRunnerRequest> list = new ArrayList<ShoeRunnerRequest>();
        Connection c = null;
        String sql = "SELECT * FROM Shoerunner_request";
        try {
            c = ConnectionHelper.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return list;
    }

    public ShoeRunnerRequest findById(String id) {
        String sql = "SELECT * FROM Shoerunner_request WHERE id = ?";
        ShoeRunnerRequest shoeRunnerRequest = null;
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                shoeRunnerRequest = processRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return shoeRunnerRequest;
    }

    public ShoeRunnerRequest create(ShoeRunnerRequest shoeRunnerRequest) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionHelper.getConnection();
            ps = c.prepareStatement("INSERT into Shoerunner_request(id, upc,description,color,image_url,request_status) VALUES (?, ?, ?, ?, ?, ?)",
                    new String[]{"ID"});
            ps.setString(1, shoeRunnerRequest.getId());
            ps.setString(2, shoeRunnerRequest.getUpc());
            ps.setString(3, shoeRunnerRequest.getItemDescription());
            ps.setString(4, shoeRunnerRequest.getItemColor());
            ps.setString(5, shoeRunnerRequest.getItemImageUrl());
            ps.setString(6, shoeRunnerRequest.getRequestStatus());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            // Update the id in the returned object. This is important as this value must be returned to the client.
            String id = rs.getString(1);
            shoeRunnerRequest.setId(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return shoeRunnerRequest;
    }

    protected ShoeRunnerRequest processRow(ResultSet rs) throws SQLException {
        ShoeRunnerRequest shoeRunnerRequest = new ShoeRunnerRequest();
        shoeRunnerRequest.setId(rs.getString("id"));
        shoeRunnerRequest.setUpc(rs.getString("upc"));
        shoeRunnerRequest.setItemColor(rs.getString("color"));
        shoeRunnerRequest.setItemDescription(rs.getString("description"));
        shoeRunnerRequest.setItemImageUrl(rs.getString("image_url"));
        shoeRunnerRequest.setRequestStatus(rs.getString("request_status"));
        return shoeRunnerRequest;
    }
}
