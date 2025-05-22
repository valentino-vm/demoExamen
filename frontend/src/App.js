import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';

// Components
import TestConnection from './components/TestConnection';
import CityKpiList from './components/CityKpiList';
import { Container, Row, Col, Card } from 'react-bootstrap';

function App() {
  return (
    <div className="App">
      <header className="bg-dark text-white py-3">
        <Container>
          <h1 className="mb-0">SmartCity KPI Dashboard</h1>
        </Container>
      </header>
      <main className="py-4">
        <Container>
          <Row>
            <Col>
              <Card className="mb-4">
                <Card.Header as="h4">Validación de Conexión a la Base de Datos</Card.Header>
                <Card.Body>
                  <TestConnection />
                </Card.Body>
              </Card>
            </Col>
          </Row>
          <Row>
            <Col>
              <Card>
                <Card.Header as="h4">Lista de KPIs de Ciudades</Card.Header>
                <Card.Body>
                  <CityKpiList />
                </Card.Body>
              </Card>
            </Col>
          </Row>
        </Container>
      </main>
      <footer className="bg-dark text-white text-center py-3 mt-5">
        <Container>
          <p className="mb-0">SmartCity KPI Dashboard &copy; {new Date().getFullYear()}</p>
        </Container>
      </footer>
    </div>
  );
}

export default App;