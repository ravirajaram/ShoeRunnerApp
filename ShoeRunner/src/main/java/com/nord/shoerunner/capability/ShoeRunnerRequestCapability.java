/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nord.shoerunner.capability;

import com.nord.shoerunner.dao.ShoeRunnerRequestDao;
import com.nord.shoerunner.model.ShoeRunnerRequest;
import java.util.List;
import java.util.Random;

/**
 *
 * @author xqn7
 */
public class ShoeRunnerRequestCapability {

    public List<ShoeRunnerRequest> getShoeRunnerRequestList() {
        return new ShoeRunnerRequestDao().findAll();
    }

    public ShoeRunnerRequest getShoeRunnerRequestById(String id) {
        return new ShoeRunnerRequestDao().findById(id);
    }

    public List<ShoeRunnerRequest> createRequest(String upc) {
        ShoeRunnerRequest shoeRunnerRequest = new ShoeRunnerRequest();
        Random random = new Random();
        int id = random.nextInt(100 - 10) + 10;
        shoeRunnerRequest.setUpc(upc);
        shoeRunnerRequest.setItemColor("brown");
        shoeRunnerRequest.setItemDescription("brown shoe");
        shoeRunnerRequest.setItemImageUrl("brownshoe.jpg");
        shoeRunnerRequest.setRequestStatus("active");
        shoeRunnerRequest.setId(Integer.toString(id));
        new ShoeRunnerRequestDao().create(shoeRunnerRequest);
        return new ShoeRunnerRequestDao().findAll();
    }
}
