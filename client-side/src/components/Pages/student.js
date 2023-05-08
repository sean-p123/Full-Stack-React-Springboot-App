import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
   
    export function Student() {
        const [students, setStudents] = useState([]);
      
      
          useEffect(()=>{
              fetch('http://localhost:8080/students')
              .then(res=>res.json())
              .then((result)=>(
                  setStudents(result)
              ))
          })
      
          return (
            <div>
                 <h1>Students</h1>
      <table className='lecturer-table'>
        <thead>
          <tr>
            <th>SID</th>
            <th>Name</th>
           
            <th>Modules</th>
          </tr>
        </thead>
        <tbody>
          {students.map(student => (
              <tr key={student.id}>
              <td>{student.sid}</td>
              <td>{student.name}</td>
              <td>
            <ul>
              {student.modules.map(module => (
                <li key={module.id}>{module.name}</li>
              ))}
            </ul>
          </td>
          <td>
       
      </td>
                </tr>
          ))}
        </tbody>
      </table>
            </div>
          );
        }

        export default Student;