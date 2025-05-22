package com.example.demoExamen.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoExamen.entity.CityKpi;
import com.example.demoExamen.repository.CityKpiRepository;

@Service
public class CityKpiService {

    @Autowired
    private CityKpiRepository cityKpiRepository;

    /**
     * Get all CityKpi records
     *
     * @return List of all CityKpi records
     */
    public List<CityKpi> getAllCityKpis() {
        return cityKpiRepository.findAll();
    }

    /**
     * Get a CityKpi record by ID
     *
     * @param id The ID of the CityKpi record
     * @return The CityKpi record if found
     */
    public Optional<CityKpi> getCityKpiById(Long id) {
        return cityKpiRepository.findById(id);
    }

    /**
     * Create a new CityKpi record
     *
     * @param cityKpi The CityKpi record to create
     * @return The created CityKpi record
     */
    public CityKpi createCityKpi(CityKpi cityKpi) {
        return cityKpiRepository.save(cityKpi);
    }

    /**
     * Update an existing CityKpi record
     *
     * @param id The ID of the CityKpi record to update
     * @param cityKpiDetails The updated CityKpi details
     * @return The updated CityKpi record
     */
    public CityKpi updateCityKpi(Long id, CityKpi cityKpiDetails) {
        Optional<CityKpi> cityKpi = cityKpiRepository.findById(id);

        if (cityKpi.isPresent()) {
            CityKpi existingCityKpi = cityKpi.get();

            existingCityKpi.setCityName(cityKpiDetails.getCityName());
            existingCityKpi.setPollutionIndex(cityKpiDetails.getPollutionIndex());
            existingCityKpi.setTrafficCongestion(cityKpiDetails.getTrafficCongestion());
            existingCityKpi.setElectricConsumption(cityKpiDetails.getElectricConsumption());
            existingCityKpi.setKpiTimestamp(cityKpiDetails.getKpiTimestamp());

            return cityKpiRepository.save(existingCityKpi);
        } else {
            return null;
        }
    }

    /**
     * Delete a CityKpi record
     *
     * @param id The ID of the CityKpi record to delete
     * @return true if deleted successfully, false otherwise
     */
    public boolean deleteCityKpi(Long id) {
        try {
            cityKpiRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get CityKpi records by city name
     *
     * @param cityName Name of the city
     * @return List of CityKpi records for the given city
     */
    public List<CityKpi> getCityKpisByCity(String cityName) {
        return cityKpiRepository.findByCityName(cityName);
    }

    /**
     * Get CityKpi records with pollution index greater than a specified value
     *
     * @param threshold Pollution index threshold
     * @return List of CityKpi records with pollution index greater than the
     * threshold
     */
    public List<CityKpi> getCityKpisWithPollutionAbove(Double threshold) {
        return cityKpiRepository.findByPollutionIndexGreaterThan(threshold);
    }

    /**
     * Get CityKpi records within a date range
     *
     * @param startDate Start date of the range
     * @param endDate End date of the range
     * @return List of CityKpi records within the date range
     */
    public List<CityKpi> getCityKpisInDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return cityKpiRepository.findByDateRange(startDate, endDate);
    }

    /**
     * Get the city with the highest pollution index
     *
     * @return CityKpi record with the highest pollution index
     */
    public CityKpi getCityWithHighestPollution() {
        return cityKpiRepository.findCityWithHighestPollution();
    }
}
