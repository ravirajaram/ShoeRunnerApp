/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nord.shoerunner.dao;

import com.nord.shoerunner.model.ShoeRunnerRequest;

/**
 *
 * @author xqn7
 */
public interface ShoeRunnerRequestDAO extends AddDAO<ShoeRunnerRequest>, GetDAO<ShoeRunnerRequest> {

    public ShoeRunnerRequest add(ShoeRunnerRequest shoeRunnerRequest);

    //public ShoeRunnerRequest getById(String id);
}
