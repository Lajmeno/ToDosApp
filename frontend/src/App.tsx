import { Suspense } from 'react';
import { Outlet } from 'react-router-dom';
import './App.css';
import Header from './Components/Header';

function App() {
  return (
    <Suspense fallback="loading..">
     < Header />
    <div className="App">
      <Outlet/>
    </div>
    </Suspense>
  );
}

export default App;
