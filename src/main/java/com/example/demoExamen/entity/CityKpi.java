package com.example.demoExamen.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CITY_KPI")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityKpi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CITY_NAME")
    private String cityName;

    @Column(name = "POLLUTION_INDEX")
    private Double pollutionIndex;

    @Column(name = "TRAFFIC_CONGESTION")
    private Double trafficCongestion;

    @Column(name = "ELECTRIC_CONSUMPTION")
    private Double electricConsumption;

    @Column(name = "KPI_TIMESTAMP")
    private LocalDateTime kpiTimestamp;
}
