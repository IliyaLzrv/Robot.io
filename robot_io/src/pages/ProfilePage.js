import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../styles/ProfilePage.css';

const ProfilePage = () => {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [level, setLevel] = useState('1');
  const [gamesPlayed, setGamesPlayed] = useState('0');
  const [wins, setWins] = useState('0');
  const [userId, setUserId] = useState(null); // Store the user's ID

  const navigate = useNavigate();

  useEffect(() => {
    // Get the user data from localStorage when the component mounts
    const userData = JSON.parse(localStorage.getItem('user'));
    if (userData) {
      setUsername(userData.username);
      setEmail(userData.email);
      setUserId(userData.id); // Set the user ID for updates and deletion
    }
  }, []);

  // Handle profile updates
  const handleUpdate = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.put(`http://localhost:8080/api/users/${userId}`, {
        username,
        email,
        // Add more fields as needed
      });

      if (response.status === 200) {
        alert('Profile updated successfully');
      } else {
        alert('Profile update failed');
      }
    } catch (error) {
      console.error('Error updating profile:', error);
      alert('An error occurred while updating the profile.');
    }
  };

  // Handle account deletion
  const handleDelete = async () => {
    if (window.confirm('Are you sure you want to delete your account? This action is irreversible.')) {
      try {
        const response = await axios.delete(`http://localhost:8080/api/users/${userId}`);
        
        if (response.status === 204) {
          alert('Account deleted successfully');
          localStorage.removeItem('user');  // Remove the user data from localStorage
          navigate('/register');  // Redirect to the register page after deletion
        } else {
          alert('Account deletion failed');
        }
      } catch (error) {
        console.error('Error deleting account:', error);
        alert('An error occurred while deleting the account.');
      }
    }
  };

  return (
    <div className="profile-container">
      <div className="profile-card">
        <div className="profile-section">
          <h3>Profile</h3>
          <div className="profile-field">
            <label>Username:</label>
            <input
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
          </div>
          <div className="profile-field">
            <label>Email:</label>
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>
          <button className="save-button" onClick={handleUpdate}>
            Save Changes
          </button>
          <button className="delete-button" onClick={handleDelete}>
            Delete Account
          </button>
        </div>
        <div className="stats-section">
          <h3>Stats</h3>
          <div className="stats-field">
            <label>Level:</label>
            <input type="text" value={level} readOnly />
          </div>
          <div className="stats-field">
            <label>Games Played:</label>
            <input type="text" value={gamesPlayed} readOnly />
          </div>
          <div className="stats-field">
            <label>Wins:</label>
            <input type="text" value={wins} readOnly />
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProfilePage;
