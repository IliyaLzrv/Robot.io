import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import ProfilePage from './pages/ProfilePage';
import LobbyPage from './pages/LobbyPage';
import LeaderboardsPage from './pages/LeaderboardsPage';
import SettingsPage from './pages/SettingsPage';
import Layout from './components/Layout';
import AdminDashboard from './pages/AdminDashboard';
import GamePage from './components/Game/GamePage'; // Import GamePage

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  return (
    <Router>
      <Layout>
        {isLoggedIn && <Layout />}
        <Routes>
          <Route path="/login" element={<LoginPage setIsLoggedIn={setIsLoggedIn} />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/profile" element={<ProfilePage />} />
          <Route path="/leaderboards" element={<LeaderboardsPage />} />
          <Route path="/settings" element={<SettingsPage />} />
          <Route path="/lobby" element={<LobbyPage />} />
          <Route path="/admin" element={<AdminDashboard />} />
          <Route path="/game" element={<GamePage />} /> {/* Add this route */}
        </Routes>
      </Layout>
    </Router>
  );
}

export default App;
