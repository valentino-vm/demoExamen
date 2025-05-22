package com.example.demoExamen.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoExamen.entity.CityKpi;
import com.example.demoExamen.service.CityKpiService;

@RestController
@RequestMapping("/api/city-kpi")
@CrossOrigin(origins = "*")
public class CityKpiController {

    @Autowired
    private CityKpiService cityKpiService;

    /**
     * Get all CityKpi records
     *
     * @return List of all CityKpi records
     */
    @GetMapping
    public ResponseEntity<List<CityKpi>> getAllCityKpis() {
        List<CityKpi> cityKpis = cityKpiService.getAllCityKpis();
        return new ResponseEntity<>(cityKpis, HttpStatus.OK);
    }

    /**
     * Get a CityKpi record by ID
     *
     * @param id The ID of the CityKpi record
     * @return The CityKpi record if found
     */
    @GetMapping("/{id}")
    public ResponseEntity<CityKpi> getCityKpiById(@PathVariable Long id) {
        Optional<CityKpi> cityKpi = cityKpiService.getCityKpiById(id);

        if (cityKpi.isPresent()) {
            return new ResponseEntity<>(cityKpi.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Create a new CityKpi record
     *
     * @param cityKpi The CityKpi record to create
     * @return The created CityKpi record
     */
    @PostMapping
    public ResponseEntity<CityKpi> createCityKpi(@RequestBody CityKpi cityKpi) {
        CityKpi createdCityKpi = cityKpiService.createCityKpi(cityKpi);
        return new ResponseEntity<>(createdCityKpi, HttpStatus.CREATED);
    }

    /**
     * Update an existing CityKpi record
     *
     * @param id The ID of the CityKpi record to update
     * @param cityKpiDetails The updated CityKpi details
     * @return The updated CityKpi record
     */
    @PutMapping("/{id}")
    public ResponseEntity<CityKpi> updateCityKpi(@PathVariable Long id, @RequestBody CityKpi cityKpiDetails) {
        CityKpi updatedCityKpi = cityKpiService.updateCityKpi(id, cityKpiDetails);

        if (updatedCityKpi != null) {
            return new ResponseEntity<>(updatedCityKpi, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete a CityKpi record
     *
     * @param id The ID of the CityKpi record to delete
     * @return Status message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCityKpi(@PathVariable Long id) {
        boolean deleted = cityKpiService.deleteCityKpi(id);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get CityKpi records by city name
     *
     * @param cityName Name of the city
     * @return List of CityKpi records for the given city
     */
    @GetMapping("/city/{cityName}")
    public ResponseEntity<List<CityKpi>> getCityKpisByCity(@PathVariable String cityName) {
        List<CityKpi> cityKpis = cityKpiService.getCityKpisByCity(cityName);
        return new ResponseEntity<>(cityKpis, HttpStatus.OK);
    }

    /**
     * Get CityKpi records with pollution index greater than a specified value
     *
     * @param threshold Pollution index threshold
     * @return List of CityKpi records with pollution index greater than the
     * threshold
     */
    @GetMapping("/pollution/above")
    public ResponseEntity<List<CityKpi>> getCityKpisWithPollutionAbove(@RequestParam Double threshold) {
        List<CityKpi> cityKpis = cityKpiService.getCityKpisWithPollutionAbove(threshold);
        return new ResponseEntity<>(cityKpis, HttpStatus.OK);
    }

    /**
     * Get CityKpi records within a date range
     *
     * @param startDate Start date of the range
     * @param endDate End date of the range
     * @return List of CityKpi records within the date range
     */
    @GetMapping("/date-range")
    public ResponseEntity<List<CityKpi>> getCityKpisInDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<CityKpi> cityKpis = cityKpiService.getCityKpisInDateRange(startDate, endDate);
        return new ResponseEntity<>(cityKpis, HttpStatus.OK);
    }

    /**
     * Get the city with the highest pollution index
     *
     * @return CityKpi record with the highest pollution index
     */
    @GetMapping("/highest-pollution")
    public ResponseEntity<CityKpi> getCityWithHighestPollution() {
        CityKpi cityKpi = cityKpiService.getCityWithHighestPollution();

        if (cityKpi != null) {
            return new ResponseEntity<>(cityKpi, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Test database connection
     *
     * @return Connection status message
     */
    @GetMapping("/test")
    public ResponseEntity<String> testConnection() {
        try {
            List<CityKpi> entities = cityKpiService.getAllCityKpis();
            return new ResponseEntity<>("Conexión exitosa a la base de datos! Se encontraron " + entities.size() + " registros de City KPI.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error de conexión: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
