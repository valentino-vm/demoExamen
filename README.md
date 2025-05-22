# SmartCity KPI Dashboard

Este proyecto es un dashboard para monitorear KPIs de ciudades inteligentes, construido con Spring Boot (backend) y React (frontend).

## Requisitos Previos

- Java 17 o superior
- Node.js 14 o superior
- npm (viene con Node.js)
- Oracle Database (configurada según las especificaciones del proyecto)

## Estructura del Proyecto

- `backend/`: API REST con Spring Boot
- `frontend/`: Aplicación React
- `wallet/`: Configuración de la base de datos Oracle

## Instrucciones de Ejecución

### 1. Iniciar el Backend (Spring Boot)

Abre una terminal y ejecuta:

```powershell
cd "ruta_del_proyecto\demoExamen"
.\mvnw spring-boot:run
```

El backend se iniciará en `http://localhost:8091`

### 2. Configurar el Frontend

En una nueva terminal, instala las dependencias del frontend:

```powershell
cd "ruta_del_proyecto\demoExamen\frontend"
npm install
```

### 3. Iniciar el Frontend

En la misma terminal del frontend, ejecuta:

```powershell
npm start
```

El frontend se iniciará en `http://localhost:3000`

## Verificación

1. Abre tu navegador y visita `http://localhost:3000`
2. Deberías ver la interfaz del SmartCity KPI Dashboard
3. Usa el botón "Probar Conexión" para verificar la conexión con la base de datos
4. Explora la lista de KPIs de las ciudades

## Funcionalidades

- Test de conexión a la base de datos
- Visualización de KPIs de ciudades
- Actualización de datos en tiempo real
- Interfaz de usuario intuitiva y responsive

## Solución de Problemas

Si encuentras algún error:

1. Verifica que el backend esté corriendo en `http://localhost:8091`
2. Asegúrate de que todas las dependencias del frontend estén instaladas
3. Comprueba la conexión a la base de datos Oracle
4. Revisa los logs del backend para más detalles

## Puertos Utilizados

- Backend: 8091
- Frontend: 3000

Asegúrate de que estos puertos estén disponibles antes de iniciar la aplicación.
