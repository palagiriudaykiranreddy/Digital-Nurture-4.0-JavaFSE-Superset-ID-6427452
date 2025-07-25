import React from "react";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import Home from "./Home";
import TrainersList from "./TrainerList";
import TrainerDetail from "./TrainerDetails";
import { trainers } from "./TrainersMock";

function App() {
  return (
    <BrowserRouter>
      <nav>
        <ul>
          <li><Link to="/">Home</Link></li>
          <li><Link to="/trainers">Trainers List</Link></li>
        </ul>
      </nav>

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/trainers" element={<TrainersList data={trainers} />} />
        <Route path="/trainer/:id" element={<TrainerDetail />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
