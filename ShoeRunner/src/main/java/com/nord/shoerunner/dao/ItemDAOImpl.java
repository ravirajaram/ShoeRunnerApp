/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nord.shoerunner.dao;

import com.nord.shoerunner.model.Item;

/**
 *
 * @author xqn7
 */
public class ItemDAOImpl implements ItemDAO {

    private static final Class TABLE_NAME = Item.class;

    @Override
    public Item add(Item item) {
        return add(item, TABLE_NAME);
    }

}
