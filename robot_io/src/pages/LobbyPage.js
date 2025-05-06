import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import Navbar from '../components/Navbar';
import '../styles/LobbyPage.css';

const LobbyPage = () => {
  const [robots, setRobots] = useState([]);
  const [selectedRobot, setSelectedRobot] = useState(null);
  const [lobbyId, setLobbyId] = useState(null);
  const navigate = useNavigate(); // For navigation to the game page
  const currentUser = { id: 1 }; // Replace with actual user ID from context or storage

  // Fetch the lobby for the current user when the component mounts
  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/lobbies/user/${currentUser.id}`)
      .then((response) => {
        if (response.data && response.data.id) {
          setLobbyId(response.data.id); // Assuming the response contains the lobby with an 'id' field
        } else {
          console.error('No lobby found for this user.');
        }
      })
      .catch((error) => {
        console.error('Error fetching lobby:', error);
      });
  }, [currentUser.id]);

  // Fetch robots from the backend
  useEffect(() => {
    axios
      .get("http://localhost:8080/api/robots")
      .then((response) => {
        console.log("Fetched robots:", response.data); // Log the response
        setRobots(response.data);
      })
      .catch((error) => {
        console.error("Error fetching robots:", error);
      });
  }, []);
  
  
  // Handle robot selection
  const handleSelectRobot = (robot) => {
    if (!lobbyId) {
      console.error('Lobby ID is not set');
      return;
    }

    axios
      .put(`http://localhost:8080/api/lobbies/${lobbyId}/selectedRobot/${robot.id}`)
      .then(() => {
        setSelectedRobot(robot); // Update selected robot in state
      })
      .catch((error) => {
        console.error('Error selecting robot:', error);
      });
  };

  // Handle starting the game
  const handleStartGame = async () => {
    try {
        if (!lobbyId) {
            alert("Lobby ID is missing!");
            return;
        }

        const response = await axios.post(
            'http://localhost:8080/api/games/start',
            null, // No body since it's using query params
            {
                params: {
                    lobbyId: lobbyId, // Make sure this value is correct
                },
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`, // Include token if required
                },
            }
        );

        // Extract response data
        const { sessionId, robotId, username } = response.data;

        // Log values for debugging
        console.log("Session ID:", sessionId);
        console.log("Robot ID:", robotId);
        console.log("Username:", username);

        // Navigate to the GamePage with query params
        navigate(`/game?sessionId=${sessionId}&robotId=${robotId}&username=${username}`);
    } catch (error) {
        console.error("Error starting game:", error);
        alert("Failed to start the game. Please try again.");
    }
};


  

  return (
    <div className="lobby-container">
      <h2 className="lobby-title">Select Your Robot</h2>
      <div className="robot-selection">
        {robots.map((robot) => (
          <div
            key={robot.id}
            className={`robot-card ${selectedRobot?.id === robot.id ? 'selected' : ''}`}
            onClick={() => handleSelectRobot(robot)}
          >
            <div className={`robot-icon ${robot.color.toLowerCase()}`}></div>
            <p className="robot-name">{robot.name}</p>
            <button className="select-button">
              {selectedRobot?.id === robot.id ? 'Selected' : 'Select'}
            </button>
          </div>
        ))}
      </div>
      <div className="action-buttons">
        <button
          className="play-button"
          onClick={handleStartGame}
          disabled={!selectedRobot}
        >
          Play
        </button>
        <button className="mode-button">Mode</button>
      </div>
    </div>
  );
};

export default LobbyPage;
