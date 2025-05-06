import React, { useState, useEffect } from 'react';
import apiClient from '../apis/Robot_io_API';
import '../styles/powerup.css';

const PowerUpModal = ({ playerId, closeModal }) => {
  const [powerUps, setPowerUps] = useState([]);

  useEffect(() => {
    const fetchPowerUps = async () => {
      const response = await apiClient.get('/powerups/all');
      setPowerUps(response.data);
    };
    fetchPowerUps();
  }, []);

  const handleChoosePowerUp = async (powerUpId) => {
    try {
      await apiClient.post(`/player-power-ups/${playerId}/assign/${powerUpId}?duration=60`);
      alert('Power-up assigned!');
      closeModal();
    } catch (error) {
      console.error('Error assigning power-up:', error);
    }
  };

  return (
    <div className="powerup-modal">
      <h2>Choose Your Power-Up</h2>
      <ul>
        {powerUps.map((powerUp) => (
          <li key={powerUp.id}>
            {powerUp.type} - Duration: {powerUp.duration}s
            <button onClick={() => handleChoosePowerUp(powerUp.id)}>Select</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default PowerUpModal;
