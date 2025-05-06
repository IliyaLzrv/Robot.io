import React, { useState } from 'react';
import axios from "axios";
import { useNavigate, Link } from 'react-router-dom';
import apiClient from '../apis/Robot_io_API'; // Import the Axios instance
import '../styles/LoginPage.css';

const LoginPage = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/api/users/login", {
        email,
        password,
      });
  
      console.log("Login successful:", response.data);
      localStorage.setItem("token", response.data.token); // Save the token
      navigate("/lobby");
    } catch (error) {
      if (error.response) {
        if (error.response.status === 401) {
          alert("Invalid email or password.");
        } else if (error.response.status === 403) {
          alert("This account is suspended.");
        } else {
          alert("An error occurred. Please try again.");
        }
      } else {
        console.error("Login error:", error);
        alert("Server error. Please try again later.");
      }
    }
  };
  
  

  return (
    <div className="login-container">
      <div className="login-form">
        <h2>Login</h2>
        <form onSubmit={handleSubmit}>
          <input
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          <button type="submit">Login</button>
        </form>
        <p>
          New user? <Link to="/register">Register here</Link>
        </p>
      </div>
    </div>
  );
};

export default LoginPage;
