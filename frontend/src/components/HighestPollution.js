import React, { useEffect, useState } from 'react';
import CityKpiService from '../services/CityKpiService';
import { Alert, Card, Container, Spinner } from 'react-bootstrap';

const HighestPollution = () => {
  const [cityKpi, setCityKpi] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchHighestPollution();
  }, []);

  const fetchHighestPollution = () => {
    setLoading(true);
    setError(null);

    CityKpiService.getCityWithHighestPollution()
      .then(response => {
        setCityKpi(response.data);
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
    <Container className="mt-4">
      <Card className="shadow-sm">
        <Card.Header as="h4">Ciudad con Mayor Índice de Contaminación</Card.Header>
        <Card.Body>
          {loading ? (
            <div className="text-center my-4">
              <Spinner animation="border" variant="primary" />
              <p className="mt-2">Cargando datos...</p>
            </div>
          ) : error ? (
            <Alert variant="danger">
              Error al cargar los datos: {error}
            </Alert>
          ) : !cityKpi ? (
            <Alert variant="info">
              No se encontraron datos de KPIs de ciudades.
            </Alert>
          ) : (
            <div>
              <Alert variant="warning" className="mb-4">
                <h5>¡Alerta de Contaminación!</h5>
                <p className="mb-0">
                  La ciudad de <strong>{cityKpi.cityName}</strong> presenta el nivel más alto de contaminación
                  con un índice de <strong>{cityKpi.pollutionIndex}</strong>.
                </p>
              </Alert>
              
              <div className="row">
                <div className="col-md-6">
                  <Card className="mb-3">
                    <Card.Header>Detalles de la Ciudad</Card.Header>
                    <Card.Body>
                      <p><strong>ID:</strong> {cityKpi.id}</p>
                      <p><strong>Nombre:</strong> {cityKpi.cityName}</p>
                      <p><strong>Fecha y Hora:</strong> {formatDateTime(cityKpi.kpiTimestamp)}</p>
                    </Card.Body>
                  </Card>
                </div>
                <div className="col-md-6">
                  <Card>
                    <Card.Header>Métricas</Card.Header>
                    <Card.Body>
                      <p><strong>Índice de Contaminación:</strong> {cityKpi.pollutionIndex}</p>
                      <p><strong>Congestión de Tráfico:</strong> {cityKpi.trafficCongestion}</p>
                      <p><strong>Consumo Eléctrico:</strong> {cityKpi.electricConsumption}</p>
                    </Card.Body>
                  </Card>
                </div>
              </div>
            </div>
          )}
        </Card.Body>
      </Card>
    </Container>
  );
};

export default HighestPollution;
