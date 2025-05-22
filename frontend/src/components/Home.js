import React from 'react';
import { Card, Container, Row, Col, Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const Home = () => {
  return (
    <Container className="mt-4">
      <Row className="mb-4">
        <Col>
          <div className="jumbotron bg-light p-5 rounded shadow-sm">
            <h1 className="display-4">SmartCity KPI Dashboard</h1>
            <p className="lead">
              Panel de control para monitorear indicadores clave de rendimiento (KPI) de ciudades inteligentes.
            </p>
            <hr className="my-4" />
            <p>
              Este dashboard permite visualizar datos en tiempo real sobre contaminación, congestión de tráfico 
              y consumo eléctrico en diferentes ciudades.
            </p>
          </div>
        </Col>
      </Row>
      
      <Row>
        <Col md={4} className="mb-4">
          <Card className="h-100 shadow-sm">
            <Card.Body>
              <Card.Title>Prueba de Conexión</Card.Title>
              <Card.Text>
                Verifica la conexión con la base de datos Oracle y comprueba que el backend está funcionando correctamente.
              </Card.Text>
              <Link to="/test-connection">
                <Button variant="primary">Probar Conexión</Button>
              </Link>
            </Card.Body>
          </Card>
        </Col>
        
        <Col md={4} className="mb-4">
          <Card className="h-100 shadow-sm">
            <Card.Body>
              <Card.Title>Lista de KPIs</Card.Title>
              <Card.Text>
                Visualiza todos los indicadores de rendimiento de las ciudades registradas en el sistema.
              </Card.Text>
              <Link to="/city-kpis">
                <Button variant="primary">Ver KPIs</Button>
              </Link>
            </Card.Body>
          </Card>
        </Col>
        
        <Col md={4} className="mb-4">
          <Card className="h-100 shadow-sm">
            <Card.Body>
              <Card.Title>Mayor Contaminación</Card.Title>
              <Card.Text>
                Identifica la ciudad con el mayor índice de contaminación para tomar medidas preventivas.
              </Card.Text>
              <Link to="/highest-pollution">
                <Button variant="primary">Ver Alerta</Button>
              </Link>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container>
  );
};

export default Home;
