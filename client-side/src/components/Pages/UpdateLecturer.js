import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';

export function UpdateLecturer() {
  const { id } = useParams();

  const [lecturer, setLecturer] = useState(null);
  const [name, setName] = useState('');
  const [taxband, setTaxband] = useState('');
  const [salaryScale, setSalaryScale] = useState('');

  
    console.log(taxband)
  const lid = id;
 
  useEffect(() => {
    const fetchLecturer = async () => {
      try {
        const response = await fetch(`http://localhost:8080/lecturer/${lid}`);
        const data = await response.json();
        setLecturer(data);
        setName(data.name);
        setTaxband(data.taxband);
        setSalaryScale(data.salaryScale);
      } catch (error) {
        console.error('Error fetching lecturer:', error);
      }
    };

    // Fetch the lecturer data based on the lid parameter
    fetchLecturer();
  }, [lid]);

  console.log(lid);

  const handleNameChange = (e) => {
    setName(e.target.value);
  };

  const handleTaxbandChange = (e) => {
    setTaxband(e.target.value);
  };

  const handleSalaryScaleChange = (e) => {
    setSalaryScale(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      // Update the lecturer data
      const response = await fetch(`http://localhost:8080/lecturers/${lid}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name, taxband, salaryScale }),
      });
      // Handle the response or perform any other necessary actions
    } catch (error) {
      console.error('Error updating lecturer:', error);
    }
  };


  if (!lecturer) {
    return <div>Loading...</div>;
  }

  

  return (
    <div>
       <div>
      <h1>Update Lecturer</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="name">Name:</label>
          <input
            type="text"
            id="name"
            value={name}
            onChange={handleNameChange}
          />
        </div>
        <div>
          <label htmlFor="taxband">Tax Band:</label>
          <input
            type="text"
            id="taxband"
            value={taxband}
            onChange={handleTaxbandChange}
          />
        </div>
        <div>
          <label htmlFor="salaryScale">Salary Scale:</label>
          <input
            type="text"
            id="salaryScale"
            value={salaryScale}
            onChange={handleSalaryScaleChange}
          />
        </div>
        <button type="submit">Update</button>
      </form>
    </div>
    </div>
  );
}

export default UpdateLecturer;