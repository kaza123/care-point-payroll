/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.master.calander;

import com.mac.care_point.master.calander.model.Calander;
import com.mac.care_point.master.calander.model.CalanderDefault;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Kalum
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CalanderService {

    @Autowired
    private CalanderRepository calanderRepository;

    @Autowired
    private CalanderDefaultRepository calanderDefaultRepository;

    public List<Calander> findByMonthAndYear(String month, String year) {
        return calanderRepository.findByMonthAndYear(month, year);
    }

    public List<Calander> findMontPlanAvalability(Integer indexNo) {
        return calanderRepository.findByIndexNo(indexNo);
    }

    public Calander saveEvents(List<Calander> eventList) {

        if (!eventList.isEmpty()) {
            for (Calander calander1 : eventList) {
                if (calander1.getStatus() != null) {
                    String date = new SimpleDateFormat("yyyy-MM-dd").format(calander1.getDate());
                    List<Calander> findByDateList = calanderRepository.findByDate(date);
                    if (findByDateList.isEmpty()) {
                        calanderRepository.save(calander1);

                    } else {
                        Calander getCalander = findByDateList.get(0);
                        getCalander.setStatus(calander1.getStatus());
                        calanderRepository.save(getCalander);
                    }
                }
            }
            return eventList.get(eventList.size() - 1);

        }
        return null;
    }

    public List<Calander> findByMonthAndYearData(String month, String year) {
        return calanderRepository.findByMonthAndYearData(month, year);
    }

    public List<Calander> findEventList() {
        return calanderRepository.findByStatusNotIn("Working_Day");
    }

    @Transactional
    public Integer saveDefaultSundaySaturday(List<Calander> eventList) {
        String year = new SimpleDateFormat("yyyy").format(eventList.get(0).getDate());
        CalanderDefault calanderDefault = calanderDefaultRepository.findByYear(Integer.parseInt(year));
        if (calanderDefault != null) {
            if (!calanderDefault.isSetDefault()) {
                calanderDefault.setSetDefault(true);
                calanderDefaultRepository.save(calanderDefault);
                return calanderRepository.save(eventList).size();
            }
        }
        return -1;
    }

    public CalanderDefault getIsSet(Integer year) {
        return calanderDefaultRepository.findByYear(year);

    }
}
