import React from "react";
import { useParams } from "react-router-dom";
import { trainers } from "./TrainersMock";

function TrainerDetail() {
  const { id } = useParams();
  const trainer = trainers.find((t) => t.trainerId === id);

  return trainer ? (
    <div>
      <h2>Trainer Details</h2>
      <p><strong>ID:</strong> {trainer.trainerId}</p>
      <p><strong>Name:</strong> {trainer.name}</p>
      <p><strong>Phone:</strong> {trainer.phone}</p>
      <p><strong>Email:</strong> {trainer.email}</p>
      <p><strong>Stream:</strong> {trainer.stream}</p>
      <p><strong>Skills:</strong> {trainer.skills.join(", ")}</p>
    </div>
  ) : (
    <p>Trainer not found.</p>
  );
}

export default TrainerDetail;
