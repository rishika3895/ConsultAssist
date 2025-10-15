import React from 'react';
import { useNavigate, useLocation } from 'react-router-dom';

const Navbar = () => {
  const navigate = useNavigate();
  const location = useLocation();
  
  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    navigate('/login');
  };
  
  const isLoggedIn = localStorage.getItem('token');
  const userRole = localStorage.getItem('role');
  
  return (
    <nav className="bg-white shadow-sm">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between h-16">
          <div className="flex">
            <div className="flex-shrink-0 flex items-center">
              <h1 className="text-xl font-bold text-indigo-600">ConsultAssist</h1>
            </div>
            {isLoggedIn && (
              <div className="hidden sm:ml-6 sm:flex sm:space-x-8">
                {userRole === 'PATIENT' ? (
                  <button
                    onClick={() => navigate('/patient')}
                    className={`border-transparent text-gray-500 hover:border-gray-300 hover:text-gray-700 inline-flex items-center px-1 pt-1 border-b-2 text-sm font-medium ${
                      location.pathname === '/patient' ? 'border-indigo-500 text-gray-900' : ''
                    }`}
                  >
                    Dashboard
                  </button>
                ) : (
                  <button
                    onClick={() => navigate('/doctor')}
                    className={`border-transparent text-gray-500 hover:border-gray-300 hover:text-gray-700 inline-flex items-center px-1 pt-1 border-b-2 text-sm font-medium ${
                      location.pathname === '/doctor' ? 'border-indigo-500 text-gray-900' : ''
                    }`}
                  >
                    Patient Records
                  </button>
                )}
              </div>
            )}
          </div>
          {isLoggedIn && (
            <div className="flex items-center">
              <span className="text-sm text-gray-700 mr-4">
                {userRole === 'PATIENT' ? 'Patient' : 'Doctor'} Portal
              </span>
              <button
                onClick={handleLogout}
                className="ml-4 inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
              >
                Logout
              </button>
            </div>
          )}
        </div>
      </div>
    </nav>
  );
};

export default Navbar;