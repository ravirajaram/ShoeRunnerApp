/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nord.shoerunner.capability;

import com.nord.shoerunner.dao.ShoeRunnerRequestDAOImpl;
import com.nord.shoerunner.model.Item;
import com.nord.shoerunner.model.ShoeRunnerRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author xqn7
 */
public class ShoeRunnerRequestCapability {

    public ShoeRunnerRequest createRequest(String upc, String employeeId) {
        ShoeRunnerRequest shoeRunnerRequest = new ShoeRunnerRequest();
        Item item = new Item();
        item.setUpc(upc);
        item.setSku("123");
        item.setColor("brown");
        item.setDescription("brown shoe");
        item.setItemImageUrl("brownshoe.jpg");
        shoeRunnerRequest.setRequestStatusId(1);
        shoeRunnerRequest.setItem(item);
        item.setShoeRunnerRequest(shoeRunnerRequest);
        shoeRunnerRequest.setEmployeeId(employeeId);
        shoeRunnerRequest.setShoeRunnerId(null);
        return new ShoeRunnerRequestDAOImpl().add(shoeRunnerRequest);
    }

    public Collection<ShoeRunnerRequest> createRequestAndReturnAll(String upc, String employeeId) {
        ShoeRunnerRequest shoeRunnerRequest = new ShoeRunnerRequest();
        Item item = new Item();
        item.setUpc(upc);
        item.setSku("123");
        item.setColor("brown");
        item.setDescription("brown shoe");
        item.setItemImageUrl("brownshoe.jpg");
        shoeRunnerRequest.setRequestStatusId(1);
        shoeRunnerRequest.setItem(item);
        item.setShoeRunnerRequest(shoeRunnerRequest);
        shoeRunnerRequest.setEmployeeId(employeeId);
        shoeRunnerRequest.setShoeRunnerId(null);
        return getAllRequests();
    }

    public Collection<ShoeRunnerRequest> getAllRequests() {
        Map params = new HashMap();
        params.put("orderBy:Desc", "createdDateTime");
        return new ShoeRunnerRequestDAOImpl().get(params, ShoeRunnerRequest.class);
    }

    public ShoeRunnerRequest getRequestById(String id) {
        Map params = new HashMap();
        params.put("id", id);
        Collection<ShoeRunnerRequest> requests = new ShoeRunnerRequestDAOImpl().get(params, ShoeRunnerRequest.class);
        Iterator itr = requests.iterator();
        return (ShoeRunnerRequest) itr.next();
    }

}
