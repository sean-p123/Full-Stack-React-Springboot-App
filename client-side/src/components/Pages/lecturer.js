import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

function Lecturer() {
  const [lecturers, setLecturers] = useState([]);


    useEffect(()=>{
        fetch('http://localhost:8080/allLecturers')
        .then(res=>res.json())
        .then((result)=>(
            setLecturers(result)
        ))
    })

  return (
    <div>
      <h1>Lecturers</h1>
      <table className='lecturer-table'>
        <thead>
          <tr>
            <th>LID</th>
            <th>Name</th>
            <th>Tax Band</th>
            <th>Salary Scale</th>
            <th>Modules</th>
          </tr>
        </thead>
        <tbody>
          {lecturers.map(lecturer => (
              <tr key={lecturer.lid}>
              <td>{lecturer.lid}</td>
              <td>{lecturer.name}</td>
              <td>{lecturer.taxBand}</td>
              <td>{lecturer.salaryScale}</td>
              <td>
            <ul>
              {lecturer.modules.map(module => (
                <li key={module.id}>{module.name}</li>
              ))}
            </ul>
          </td>
          <td>
        <Link to={`/updateLecturer/${lecturer.lid}`} className="update-button">
          Update
        </Link>
      </td>
                </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Lecturer;
