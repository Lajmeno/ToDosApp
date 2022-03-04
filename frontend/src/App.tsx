
import { Suspense } from 'react';
import { BrowserRouter, Outlet, Route, Routes } from 'react-router-dom';
import './App.css';
import ToDosGallery from './Components/ToDosGallery';

function App() {
  return (
    <Suspense fallback="loading..">
    <div className="App">
      <Outlet/>
    </div>
    </Suspense>
  );
}

export default App;
