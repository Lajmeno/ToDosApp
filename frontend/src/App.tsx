
import { Suspense } from 'react';
import { Outlet } from 'react-router-dom';
import './App.css';

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
