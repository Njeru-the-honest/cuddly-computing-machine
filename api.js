import axios from 'axios';

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080';

const api = axios.create({
  baseURL: `${API_BASE_URL}/api/v1`,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Add token to requests
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Auth API
export const authAPI = {
  register: (data) => api.post('/auth/register', data),
  login: (data) => api.post('/auth/login', data),
};

// Restaurant API
export const restaurantAPI = {
  getAll: () => api.get('/restaurants'),
  getById: (id) => api.get(`/restaurants/${id}`),
  getMenu: (id) => api.get(`/restaurants/${id}/menu`),
  getOrders: (id) => api.get(`/restaurants/${id}/orders`),
  updateOrderStatus: (orderId, status) => api.put(`/restaurants/orders/${orderId}/status`, { status }),
  updateMenuItem: (restaurantId, itemId, data) => api.put(`/restaurants/${restaurantId}/menu/${itemId}`, data),
};

// Order API
export const orderAPI = {
  create: (data) => api.post('/orders', data),
  getById: (id) => api.get(`/orders/${id}`),
  track: (id) => api.get(`/orders/${id}/track`),
  pay: (id, data) => api.post(`/orders/${id}/pay`, data),
  getPayment: (id) => api.get(`/orders/${id}/payment`),
};

// Feedback API
export const feedbackAPI = {
  create: (data) => api.post('/feedback', data),
  getByRestaurant: (restaurantId) => api.get(`/feedback/restaurant/${restaurantId}`),
};

export default api;