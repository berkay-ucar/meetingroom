Meeting Room

A simple meeting room reservation system that allows users to manage and book meeting rooms.
This project demonstrates a full-stack application using a modern frontend and a RESTful backend.

🚀 Features

📅 Create and manage meeting reservations

🏢 View available meeting rooms

⏱️ Prevent overlapping reservations

🔍 List existing bookings

⚡ REST API based architecture

🛠️ Technologies
Backend

Spring Boot 4

Java

REST API

Frontend

Vite 8

React Router 6

React

JavaScript / HTML / CSS

📂 Project Structure
meetingroom/
│
├── backend/        # Spring Boot backend
│   ├── controller
│   ├── service
│   ├── model
│   └── repository
│
├── frontend/       # Vite + React frontend
│   ├── src
│   ├── components
│   ├── pages
│   └── router
│
└── README.md
⚙️ Installation
1. Clone the repository
git clone https://github.com/berkay-ucar/meetingroom.git
2. Backend Setup
cd backend
./mvnw spring-boot:run

Backend will start on:

http://localhost:8080
3. Frontend Setup
cd frontend
npm install
npm run dev

Frontend will start on:

http://localhost:5173
📖 Usage

Start the backend server.

Run the frontend development server.

Open the frontend in your browser.

Create or manage meeting room reservations.

🔮 Future Improvements

User authentication

Role-based access (admin / user)

Email notifications

UI improvements

## Screenshots

<img src="/day.png" width="800"/>

<img src="/week.png" width="800"/>

<img src="/month.png" width="800"/>


