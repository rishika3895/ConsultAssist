// API configuration
const API_BASE_URL = process.env.NODE_ENV === 'production' 
  ? 'https://your-backend-url.railway.app' // Replace with your actual backend URL
  : 'http://localhost:8082';

export default API_BASE_URL;