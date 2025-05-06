import React from 'react';
import { useNavigate } from 'react-router-dom';
import apiClient from '../apis/Robot_io_API';
import '../styles/game.css';

const MatchmakingPage = () => {
  const navigate = useNavigate();

  const handleJoinQueue = async () => {
    try {
      await apiClient.post('/matchmaking/queue', { playerId: 1 }); // Replace 1 with actual player ID
      alert('You have joined the queue!');
      navigate('/game'); // Redirect to game page
    } catch (error) {
      console.error('Error joining queue:', error);
    }
  };

  return (
    <div className="matchmaking-container">
      <h1>Matchmaking</h1>
      <button onClick={handleJoinQueue}>Join Queue</button>
    </div>
  );
};

export default MatchmakingPage;
