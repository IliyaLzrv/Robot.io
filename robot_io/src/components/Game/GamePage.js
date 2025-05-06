import React from "react";
import { useLocation } from 'react-router-dom';

const GamePage = () => {
    const location = useLocation();
    const params = new URLSearchParams(location.search);

    const sessionId = params.get('sessionId');
    const robotId = params.get('robotId');
    const username = params.get('username');

    console.log("Parsed query parameters:", { sessionId, robotId, username });

    return (
        <div>
            <h1>Game Page</h1>
            <p>Session ID: {sessionId || "Not provided"}</p>
            <p>Robot ID: {robotId || "Not provided"}</p>
            <p>Username: {username || "Not provided"}</p>
        </div>
    );
};

export default GamePage;

