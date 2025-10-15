import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import apiClient from '../config/axios';

const PatientDashboard = () => {
  const [formData, setFormData] = useState({
    name: '',
    age: '',
    symptoms: '',
    medicalHistory: ''
  });
  const [success, setSuccess] = useState(false);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  
  const navigate = useNavigate();
  
  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };
  
  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');
    setSuccess(false);
    
    try {
      const token = localStorage.getItem('token');
      console.log('Token from localStorage:', token);
      console.log('Form data being submitted:', formData);
      
      if (!token) {
        console.log('No token found, redirecting to login');
        navigate('/login');
        return;
      }
      
      // Convert age to integer before sending
      const dataToSend = {
        ...formData,
        age: parseInt(formData.age, 10)
      };
      
      const response = await apiClient.post('/api/patient/data', dataToSend);
      
      console.log('Response received:', response);
      setSuccess(true);
      // Reset form
      setFormData({
        name: '',
        age: '',
        symptoms: '',
        medicalHistory: ''
      });
    } catch (err) {
      console.error('Error submitting patient data:', err);
      console.error('Error response:', err.response);
      console.error('Error status:', err.response?.status);
      console.error('Error data:', err.response?.data);
      
      if (err.response && err.response.data) {
        setError(`Failed to submit data: ${err.response.data}`);
      } else if (err.message) {
        setError(`Failed to submit data: ${err.message}`);
      } else {
        setError('Failed to submit data. Please try again.');
      }
    } finally {
      setLoading(false);
    }
  };
  
  return (
    <div className="max-w-4xl mx-auto py-10 px-4 sm:px-6 lg:px-8">
      <div className="bg-white shadow overflow-hidden sm:rounded-lg">
        <div className="px-4 py-5 sm:px-6 border-b border-gray-200">
          <h3 className="text-lg leading-6 font-medium text-gray-900">
            Patient Health Information
          </h3>
          <p className="mt-1 max-w-2xl text-sm text-gray-500">
            Please fill in your health information before your appointment
          </p>
        </div>
        <div className="px-4 py-5 sm:p-6">
          {success && (
            <div className="rounded-md bg-green-50 p-4 mb-6">
              <div className="text-sm text-green-700">
                <p>Health information submitted successfully!</p>
              </div>
            </div>
          )}
          
          {error && (
            <div className="rounded-md bg-red-50 p-4 mb-6">
              <div className="text-sm text-red-700">
                {error}
              </div>
            </div>
          )}
          
          <form onSubmit={handleSubmit} className="space-y-6">
            <div className="grid grid-cols-1 gap-y-6 gap-x-4 sm:grid-cols-6">
              <div className="sm:col-span-3">
                <label htmlFor="name" className="block text-sm font-medium text-gray-700">
                  Full Name
                </label>
                <div className="mt-1">
                  <input
                    type="text"
                    name="name"
                    id="name"
                    value={formData.name}
                    onChange={handleChange}
                    required
                    className="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md"
                  />
                </div>
              </div>

              <div className="sm:col-span-3">
                <label htmlFor="age" className="block text-sm font-medium text-gray-700">
                  Age
                </label>
                <div className="mt-1">
                  <input
                    type="number"
                    name="age"
                    id="age"
                    value={formData.age}
                    onChange={handleChange}
                    required
                    min="1"
                    max="120"
                    className="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md"
                  />
                </div>
              </div>

              <div className="sm:col-span-6">
                <label htmlFor="symptoms" className="block text-sm font-medium text-gray-700">
                  Current Symptoms
                </label>
                <div className="mt-1">
                  <textarea
                    id="symptoms"
                    name="symptoms"
                    rows={3}
                    value={formData.symptoms}
                    onChange={handleChange}
                    required
                    className="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border border-gray-300 rounded-md"
                  />
                </div>
                <p className="mt-2 text-sm text-gray-500">
                  Please describe any symptoms you're currently experiencing
                </p>
              </div>

              <div className="sm:col-span-6">
                <label htmlFor="medicalHistory" className="block text-sm font-medium text-gray-700">
                  Medical History
                </label>
                <div className="mt-1">
                  <textarea
                    id="medicalHistory"
                    name="medicalHistory"
                    rows={4}
                    value={formData.medicalHistory}
                    onChange={handleChange}
                    required
                    className="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border border-gray-300 rounded-md"
                  />
                </div>
                <p className="mt-2 text-sm text-gray-500">
                  Please include any chronic conditions, allergies, medications, or previous surgeries
                </p>
              </div>
            </div>

            <div className="flex justify-end">
              <button
                type="submit"
                disabled={loading}
                className="ml-3 inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 disabled:opacity-50"
              >
                {loading ? 'Submitting...' : 'Submit Information'}
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default PatientDashboard;