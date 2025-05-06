import React, { useState, useEffect } from 'react';
import apiClient from '../apis/Robot_io_API';
import '../styles/upgrades.css';

const RobotUpgradePage = ({ robotId }) => {
  const [upgrades, setUpgrades] = useState([]);

  useEffect(() => {
    const fetchUpgrades = async () => {
      const response = await apiClient.get(`/robots/upgrades/${robotId}`);
      setUpgrades(response.data);
    };
    fetchUpgrades();
  }, [robotId]);

  const handleUpgrade = async (upgradeType) => {
    try {
      await apiClient.post(`/robots/upgrades/${robotId}/upgrade`, { upgradeType });
      alert('Upgrade successful!');
    } catch (error) {
      console.error('Error upgrading robot:', error);
    }
  };

  return (
    <div className="upgrade-container">
      <h2>Robot Upgrades</h2>
      <ul>
        {upgrades.map((upgrade) => (
          <li key={upgrade.upgradeType}>
            {upgrade.upgradeType} - Level: {upgrade.level}
            <button onClick={() => handleUpgrade(upgrade.upgradeType)}>Upgrade</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default RobotUpgradePage;
