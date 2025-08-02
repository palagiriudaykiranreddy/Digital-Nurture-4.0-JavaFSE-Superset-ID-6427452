import React, { useState } from 'react';
import BookDetails from './components/BookDetails';
import BlogDetails from './components/BlogDetails';
import CourseDetails from './components/CourseDetails';

function App() {
  const [selected, setSelected] = useState("book");

  let output;
  if (selected === "book") {
    output = <BookDetails />;
  } else if (selected === "blog") {
    output = <BlogDetails />;
  } else {
    output = <CourseDetails />;
  }

  return (
    <div style={{ padding: 20 }}>
      <h1>BloggerApp</h1>
      <button onClick={() => setSelected("book")}>Book</button>
      <button onClick={() => setSelected("blog")}>Blog</button>
      <button onClick={() => setSelected("course")}>Course</button>

      <div style={{ marginTop: 20 }}>
        {output}
      </div>
    </div>
  );
}

export default App;
