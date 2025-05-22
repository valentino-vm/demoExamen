import axios from 'axios';

const API_URL = 'http://localhost:8091/api/city-kpi';

class CityKpiService {
    // Probar la conexión a la base de datos
    testConnection() {
        return axios.get(`${API_URL}/test`);
    }

    // Obtener todos los KPIs de ciudades
    getAllCityKpis() {
        return axios.get(API_URL);
    }

    // Obtener un KPI de ciudad por ID
    getCityKpiById(id) {
        return axios.get(`${API_URL}/${id}`);
    }

    // Crear un nuevo KPI de ciudad
    createCityKpi(cityKpi) {
        return axios.post(API_URL, cityKpi);
    }

    // Actualizar un KPI de ciudad existente
    updateCityKpi(id, cityKpi) {
        return axios.put(`${API_URL}/${id}`, cityKpi);
    }

    // Eliminar un KPI de ciudad
    deleteCityKpi(id) {
        return axios.delete(`${API_URL}/${id}`);
    }

    // Obtener KPIs de una ciudad específica
    getCityKpisByCity(cityName) {
        return axios.get(`${API_URL}/city/${cityName}`);
    }

    // Obtener ciudades con un índice de contaminación por encima de un umbral
    getCityKpisWithPollutionAbove(threshold) {
        return axios.get(`${API_URL}/pollution/above?threshold=${threshold}`);
    }

    // Obtener la ciudad con mayor índice de contaminación
    getCityWithHighestPollution() {
        return axios.get(`${API_URL}/highest-pollution`);
    }
}

export default new CityKpiService();
