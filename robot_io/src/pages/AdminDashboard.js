import React, { useState } from 'react';
import apiClient from '../apis/Robot_io_API'; // Axios instance
import '../styles/AdminDashboard.css'
const AdminDashboard = () => {
  const [blastPower, setBlastPower] = useState(1);
  const [speed, setSpeed] = useState(1);
  const [bazooka, setBazooka] = useState('NORMAL');
  const [color, setColor] = useState('blue');
  const [successMessage, setSuccessMessage] = useState('');

  const handleCreateRobot = async (e) => {
    e.preventDefault();

    try {
      const response = await apiClient.post('/api/robots', {
        blastPower,
        speed,
        bazooka,
        color,
      });

      if (response.status === 200) {
        setSuccessMessage('Robot created successfully!');
        // Reset the form
        setBlastPower(1);
        setSpeed(1);
        setBazooka('NORMAL');
        setColor('blue');
      } else {
        alert('Failed to create robot.');
      }
    } catch (error) {
      console.error('Error creating robot:', error);
      alert('An error occurred while creating the robot.');
    }
  };

  return (
    <div className="admin-container">
      <h2 className="admin-title">Admin Dashboard: Create Robot</h2>
      {successMessage && <p className="success-message">{successMessage}</p>}
      <form className="admin-form" onSubmit={handleCreateRobot}>
        <div className="form-group">
          <label>Blast Power:</label>
          <input type="number" value={blastPower} onChange={(e) => setBlastPower(e.target.value)} min="1" required />
        </div>

        <div className="form-group">
          <label>Speed:</label>
          <input type="number" value={speed} onChange={(e) => setSpeed(e.target.value)} min="1" required />
        </div>

        <div className="form-group">
          <label>Bazooka Type:</label>
          <select value={bazooka} onChange={(e) => setBazooka(e.target.value)}>
            <option value="NORMAL">Normal</option>
            <option value="DOUBLE">Double</option>
            <option value="BIGGER">Bigger</option>
          </select>
        </div>

        <div className="form-group">
          <label>Team Color:</label>
          <select value={color} onChange={(e) => setColor(e.target.value)}>
            <option value="blue">Blue</option>
            <option value="red">Red</option>
            <option value="green">Green</option>
          </select>
        </div>

        <button className="create-button" type="submit">Create Robot</button>
      </form>
    </div>
  );
};

export default AdminDashboard;
