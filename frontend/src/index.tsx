import './i18n';
import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import ToDosGallery from './Components/ToDosGallery';
import ToDoDetails from './Components/ToDoDetails';
import Login from './Login/Login';
import AuthProvider from './Login/AuthProvider';
import Register from './Login/Register';

ReactDOM.render(
  <React.StrictMode>
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/" element = {<App/>} >
            <Route path="/login" element = {<Login/>} />
            <Route path="/register" element = {<Register/>} />
            <Route path="/gallery" element={<ToDosGallery />} />
            <Route path="/gallery/:id" element={<ToDoDetails  />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
