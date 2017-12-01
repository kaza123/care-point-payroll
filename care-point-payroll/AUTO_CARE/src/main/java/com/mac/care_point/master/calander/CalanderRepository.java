/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.care_point.master.calander;

import com.mac.care_point.master.calander.model.Calander;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author 'Kasun Chamara'
 */
public interface CalanderRepository extends JpaRepository<Calander, Integer> {

    @Query(nativeQuery = true, value = "select \n"
            + "index_no, \n"
            + "date, \n"
            + "status \n"
            + "from \n"
            + "m_calander \n"
            + "where \n"
            + "MONTH(m_calander.date)>=:month\n"
            + " and \n"
            + " YEAR(m_calander.date) =:year")
    public List<Calander> findByMonthAndYear(@Param("month") String month, @Param("year") String year);

    @Query(nativeQuery = true, value = "select \n"
            + "index_no, \n"
            + "date, \n"
            + "status \n"
            + "from \n"
            + "m_calander \n"
            + "where \n"
            + "MONTH(m_calander.date)=:month\n"
            + " and \n"
            + " YEAR(m_calander.date) =:year")
    public List<Calander> findByMonthAndYearData(@Param("month") String month, @Param("year") String year);

    @Query(nativeQuery = true, value = "select \n"
            + "index_no, \n"
            + "date, \n"
            + "status \n"
            + "from \n"
            + "m_calander \n"
            + "where \n"
            + "m_calander.date=:date")
    public List<Calander> findByDate(@Param("date")String date); 

    public List<Calander> findByIndexNo(Integer indexNo);

    public List<Calander> findByStatusNotIn(String working_Day);

    public Calander findByStatusNotInAndDate(String working_Day, Date date);

    
}
