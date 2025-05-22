import React, { useEffect, useState } from 'react';
import CityKpiService from '../services/CityKpiService';
import { Alert, Table, Spinner, Button } from 'react-bootstrap';
import { ArrowClockwise } from 'react-bootstrap-icons';

const CityKpiList = () => {
  const [cityKpis, setCityKpis] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchCityKpis();
  }, []);

  const fetchCityKpis = () => {
    setLoading(true);
    setError(null);

    CityKpiService.getAllCityKpis()
      .then(response => {
        setCityKpis(response.data);
        setLoading(false);
      })
      .catch(error => {
        setError(error.response ? error.response.data : error.message);
        setLoading(false);
      });
  };

  const formatDateTime = (dateTimeStr) => {
    if (!dateTimeStr) return 'N/A';
    const date = new Date(dateTimeStr);
    return date.toLocaleString();
  };

  return (
    <div>
      <div className="d-flex justify-content-end mb-3">
        <Button 
          variant="outline-primary" 
          onClick={fetchCityKpis}
          disabled={loading}
        >
          <ArrowClockwise className={loading ? "spinner" : ""} /> Actualizar
        </Button>
      </div>

      {loading ? (
        <div className="text-center my-4">
          <Spinner animation="border" variant="primary" />
          <p className="mt-2">Cargando datos...</p>
        </div>
      ) : error ? (
        <Alert variant="danger">
          Error al cargar los datos: {error}
        </Alert>
      ) : cityKpis.length === 0 ? (
        <Alert variant="info">
          No se encontraron datos de KPIs de ciudades.
        </Alert>
      ) : (
        <div className="table-responsive">
          <Table striped bordered hover>
            <thead>
              <tr>
                <th>ID</th>
                <th>Ciudad</th>
                <th>Índice de Contaminación</th>
                <th>Congestión de Tráfico</th>
                <th>Consumo Eléctrico</th>
                <th>Fecha y Hora</th>
              </tr>
            </thead>
            <tbody>
              {cityKpis.map(kpi => (
                <tr key={kpi.id}>
                  <td>{kpi.id}</td>
                  <td>{kpi.cityName}</td>
                  <td>{kpi.pollutionIndex}</td>
                  <td>{kpi.trafficCongestion}</td>
                  <td>{kpi.electricConsumption}</td>
                  <td>{formatDateTime(kpi.kpiTimestamp)}</td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      )}
    </div>
  );
};

export default CityKpiList;
