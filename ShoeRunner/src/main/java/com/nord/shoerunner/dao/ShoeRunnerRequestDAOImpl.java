/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nord.shoerunner.dao;

import com.nord.shoerunner.model.ShoeRunnerRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;

/**
 *
 * @author xqn7
 */
public class ShoeRunnerRequestDAOImpl implements ShoeRunnerRequestDAO {

    private static final Class TABLE_NAME = ShoeRunnerRequest.class;

    @Override
    public ShoeRunnerRequest add(ShoeRunnerRequest shoeRunnerRequest) {
        return add(shoeRunnerRequest, TABLE_NAME);
    }

    @Override
    public Collection<ShoeRunnerRequest> get(Map parameters) {
        parameters.put("requestStatus", new Integer(1));
        Session session = getSession();
        try {
            List<ShoeRunnerRequest> shoeRunnerRequests = (List<ShoeRunnerRequest>) getWithOpenSession(parameters, TABLE_NAME, session);
            return shoeRunnerRequests;
        } finally {
            commitAndClose(session);
        }
    }

 /*   @Override
    public ShoeRunnerRequest getById(String id) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("id", id);
        ShoeRunnerRequest shoeRunnerRequest, loadedRequest = null;
        Session session = getSession();
        try {
            shoeRunnerRequest = daoUtility.getOnlyObjectInCollection(getWithOpenSession(parameters, session), "ShoeRunnerRequest", "id");
        } catch (Exception ex) {
        } finally {
            commitAndClose(session);
        }
        return loadedRequest;
    }*/
}
