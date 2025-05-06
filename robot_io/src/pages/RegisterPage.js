import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import apiClient from '../apis/Robot_io_API'; // Import the Axios instance
import '../styles/RegisterPage.css';

const RegisterPage = () => {
  const [email, setEmail] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // Send the registration data to the backend API
      const response = await apiClient.post('/api/users/register', {
        email,
        username,
        password,
      });

      if (response.status === 200 || response.status === 201) {
        // Save user data in localStorage
        localStorage.setItem('user', JSON.stringify(response.data));

        // Registration successful, navigate to the lobby page
        navigate('/lobby');
      } else {
        alert('Registration failed. Please try again.');
      }
    } catch (error) {
      console.error('Registration error:', error);
      alert('An error occurred during registration. Please try again.');
    }
  };

  return (
    <div className="register-container">
      <form className="register-form" onSubmit={handleSubmit}>
        <h2>Register</h2>
        <div>
          <label>Email:</label>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Username:</label>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Password:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button type="submit">Register</button>
        <p>
          Already a user? <Link to="/login"> Login here</Link>
        </p>
      </form>
    </div>
  );
};

export default RegisterPage;
