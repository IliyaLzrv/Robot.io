import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import '../styles/Navbar.css'; // Assuming you have a CSS file for styling

const Navbar = () => {
  const navigate = useNavigate();

  const logout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    navigate('/login'); // Redirect to login page
  };

  return (
    <nav className="navbar">
      <div className="navbar-left">
        <Link to="/lobby">Robot.io</Link>
        <Link to="/lobby">Lobby</Link>
        <Link to="/leaderboards">Leaderboards</Link>
        <Link to="/settings">Settings</Link>
      </div>
      <div className="navbar-right">
        <Link to="/profile">Profile</Link>
        <button onClick={logout} className="logout-button">
          Logout
        </button>
      </div>
    </nav>
  );
};

export default Navbar;
