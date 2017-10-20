/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.master.items.item_unit;

import com.mac.care_point.master.items.item_unit.model.MItemUnits;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Kavish Manjitha
 */
public interface ItemUnitRepository extends JpaRepository<MItemUnits, Serializable> {

    public List<MItemUnits> findByItemAndItemUnitType(Integer item,String itemUnitType);

    public List<MItemUnits> findByItem(Integer item);
}
