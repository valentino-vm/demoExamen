package com.example.demoExamen.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demoExamen.entity.CityKpi;

@Repository
public interface CityKpiRepository extends JpaRepository<CityKpi, Long> {

    /**
     * Find CityKpi records by city name
     *
     * @param cityName Name of the city
     * @return List of CityKpi records for the given city
     */
    List<CityKpi> findByCityName(String cityName);

    /**
     * Find CityKpi records with pollution index greater than a specified value
     *
     * @param threshold Pollution index threshold
     * @return List of CityKpi records with pollution index greater than the
     * threshold
     */
    List<CityKpi> findByPollutionIndexGreaterThan(Double threshold);

    /**
     * Find CityKpi records within a date range
     *
     * @param startDate Start date of the range
     * @param endDate End date of the range
     * @return List of CityKpi records within the date range
     */
    @Query("SELECT k FROM CityKpi k WHERE k.kpiTimestamp BETWEEN :startDate AND :endDate")
    List<CityKpi> findByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    /**
     * Find the city with the highest pollution index
     *
     * @return CityKpi record with the highest pollution index
     */
    @Query("SELECT k FROM CityKpi k WHERE k.pollutionIndex = (SELECT MAX(c.pollutionIndex) FROM CityKpi c)")
    CityKpi findCityWithHighestPollution();
}
