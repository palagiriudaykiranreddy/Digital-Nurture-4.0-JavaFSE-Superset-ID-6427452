import React, { useState } from 'react';

const ComplaintRegister = () => {
  const [name, setName] = useState('');
  const [complaint, setComplaint] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    const referenceNumber = 'REF' + Math.floor(1000 + Math.random() * 9000);
    alert(
      `Complaint Submitted!\nName: ${name}\nComplaint: ${complaint}\nReference No: ${referenceNumber}`
    );
    setName('');
    setComplaint('');
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Employee Name: </label>
        <input
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
        />
      </div>
      <div>
        <label>Complaint: </label>
        <textarea
          value={complaint}
          onChange={(e) => setComplaint(e.target.value)}
          required
        ></textarea>
      </div>
      <button type="submit">Submit</button>
    </form>
  );
};

export default ComplaintRegister;
