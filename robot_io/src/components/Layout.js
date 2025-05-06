import React from 'react';
import { useLocation } from 'react-router-dom';
import Navbar from '../components/Navbar';


const Layout = ({ children }) => {
  const location = useLocation();

  // Check if the current path is either '/login' or '/register'
  const hideNavbar = location.pathname === '/login' || location.pathname === '/register';

  return (
    <div>
      {/* Only render the Navbar if the current path is not login or register */}
      {!hideNavbar && <Navbar />}
      <div>{children}</div> {/* Render the page content */}
    </div>
  );;
};

export default Layout;

