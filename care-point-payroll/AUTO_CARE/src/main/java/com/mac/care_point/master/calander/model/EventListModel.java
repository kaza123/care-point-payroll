/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.master.calander.model;

import java.util.List;

/**
 *
 * @author Kalum
 */
public class EventListModel {
    private List<Calander> eventList;

    public EventListModel() {
    }

    public List<Calander> getEventList() {
        return eventList;
    }

    public void setEventList(List<Calander> eventList) {
        this.eventList = eventList;
    }

}
