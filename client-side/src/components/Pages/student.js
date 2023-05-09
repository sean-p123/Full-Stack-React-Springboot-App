import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
   
    export function Student() {
        const [students, setStudents] = useState([]);
        const [deleteStatus, setdeleteStatus] = useState([]);
      
          useEffect(()=>{
              fetch('http://localhost:8080/students')
              .then(res=>res.json())
              .then((result)=>(
                  setStudents(result)
              ))
          })
      
          const handleDelete = async (id) => {
            try {
              const response = await fetch(`http://localhost:8080/students/${id}`, {
                method: 'DELETE',
              });
              setStudents((prevStudents) =>
                prevStudents.filter((student) => student.id !== id)
              )
              if (response.ok) {
                setdeleteStatus('Student deleted successfully');
              } else {
                setdeleteStatus('Failed to delete student, student must not have associated modules');
              }
            } catch (error) {
             
              console.error('Error deleting student:', error);
              
            }
          };

          return (
            <div>
              <h1>Students</h1>
              <table className="lecturer-table">
                <thead>
                  <tr>
                    <th>SID</th>
                    <th>Name</th>
                    <th>Modules</th>
                    <th>Action</th>
                  </tr>
                </thead>
                <tbody>
                  {students.map((student) => (
                    <tr key={student.id}>
                      <td>{student.sid}</td>
                      <td>{student.name}</td>
                      <td>
                        <ul>
                          {student.modules.map((module) => (
                            <li key={module.id}>{module.name}</li>
                          ))}
                        </ul>
                      </td>
                      <td>
                        <button onClick={() => handleDelete(student.sid)}>
                          Delete
                        </button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
              {deleteStatus && <p>{deleteStatus}</p>}
            </div>
          );
        };

        export default Student;