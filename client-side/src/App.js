import React from 'react';
import './App.css';

import Teacher from './components/Pages/Teacher';
import Lecturer from './components/Pages/lecturer';
import  Student  from './components/Pages/student';
import Home from './components/Pages/home';
//import { UpdateLecturer } from './components/UpdateLecturer';


import Nav from 'react-bootstrap/Nav';
import {BrowserRouter as Router,  Routes,  Route } from 'react-router-dom'

import NavBar from './components/Navbar';
import UpdateLecturer from './components/Pages/UpdateLecturer';


function App() {

 return (
 <>
<NavBar/>
<div className='container'>
  <Routes>
    <Route path="/" element={<Home/>}/>
    <Route path="/lecturer" element={<Lecturer/>}/>
    <Route path="/student" element={<Student/>}/>
    <Route path="/updateLecturer/:id" element={<UpdateLecturer/>}/>
  </Routes>
</div>

</> )
}

export default App;
