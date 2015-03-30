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
public interface ItemDAO extends AddDAO<Item>{
    Item add(Item item);
}
