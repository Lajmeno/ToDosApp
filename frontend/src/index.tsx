
import './i18n';

import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import ToDosGallery from './Components/ToDosGallery';
import ToDo from './Components/ToDo';
import ToDoDetails from './Components/ToDoDetails';

ReactDOM.render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element = {<App/>} >
          <Route path="/ToDosGallery" element={<ToDosGallery />} />
          <Route path="ToDosGallery/:id" element={<ToDoDetails  />} />
        </Route>
      </Routes>
    </BrowserRouter>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
