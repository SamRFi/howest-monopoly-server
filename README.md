This project is a school assignment developed for educational purposes and is not intended for production use.

# Howest Monopoly Server

Welcome to the Howest Monopoly Server project! This server is designed to facilitate the classic Monopoly board game in a digital format, allowing multiple players to connect, interact, and enjoy the game remotely.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Features

- **Multiplayer Support**: Connect with friends and family to play Monopoly remotely.
- **Real-Time Gameplay**: Experience seamless, real-time interaction with other players.
- **Comprehensive Game Logic**: Implements all the traditional rules of Monopoly.
- **User Authentication**: Secure login and user management.
- **Scalable Architecture**: Designed to handle multiple concurrent games and players.

## Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

Before you begin, ensure you have the following installed on your system:

- Node.js
- npm (Node Package Manager)
- MongoDB (or any other database if specified)

### Installation

1. **Clone the Repository**

    ```sh
    git clone https://github.com/SamRFi/howest-monopoly-server.git
    ```

2. **Navigate to the Project Directory**

    ```sh
    cd howest-monopoly-server
    ```

3. **Install Dependencies**

    ```sh
    npm install
    ```

4. **Set Up Environment Variables**

    Create a `.env` file in the root directory and add the necessary environment variables. Refer to `.env.example` for the required variables.

5. **Run the Server**

    ```sh
    npm start
    ```

### Usage

Once the server is running, players can connect to the game via the client application. Ensure the client is properly configured to communicate with the server.
