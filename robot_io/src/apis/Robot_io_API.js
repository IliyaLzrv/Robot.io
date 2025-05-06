import axios from "axios";

const apiClient = axios.create({
    baseURL: process.env.Robot_io_API_BASE_URL || 'http://localhost:8080', // Use the environment variable or fallback to localhost
    headers: {
      'Content-Type': 'application/json',
    },
  });
  
  apiClient.interceptors.request.use((config) => {
    const token = localStorage.getItem('token'); // Retrieve token from localStorage
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
  }, (error) => {
    return Promise.reject(error);
  });
  
  export default apiClient;