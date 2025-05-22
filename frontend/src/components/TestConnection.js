import React, { useState } from 'react';
import CityKpiService from '../services/CityKpiService';
import { Alert, Button, Spinner } from 'react-bootstrap';

const TestConnection = () => {
  const [connectionStatus, setConnectionStatus] = useState({
    tested: false,
    success: false,
    message: '',
    loading: false
  });

  const testConnection = () => {
    setConnectionStatus({
      tested: false,
      success: false,
      message: '',
      loading: true
    });

    CityKpiService.testConnection()
      .then(response => {
        setConnectionStatus({
          tested: true,
          success: true,
          message: response.data,
          loading: false
        });
      })
      .catch(error => {
        setConnectionStatus({
          tested: true,
          success: false,
          message: error.response ? error.response.data : error.message,
          loading: false
        });
      });
  };

  return (
    <div>
      <p className="mb-3">
        Haga clic en el botón para probar la conexión a la base de datos Oracle.
      </p>
      <Button 
        variant="primary" 
        onClick={testConnection} 
        disabled={connectionStatus.loading}
      >
        {connectionStatus.loading ? (
          <>
            <Spinner
              as="span"
              animation="border"
              size="sm"
              role="status"
              aria-hidden="true"
              className="me-2"
            />
            Probando...
          </>
        ) : (
          'Probar Conexión'
        )}
      </Button>

      {connectionStatus.tested && (
        <Alert 
          variant={connectionStatus.success ? 'success' : 'danger'} 
          className="mt-3"
        >
          {connectionStatus.message}
        </Alert>
      )}
    </div>  );
};

export default TestConnection;
