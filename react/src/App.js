import './App.css';
import './components/DynamicSubmitPage';
import { Link, Routes, BrowserRouter as Router, Route } from 'react-router-dom';
import DynamicSubmitPage from './components/DynamicSubmitPage';
import React, { useState } from 'react';
function App() {
  const [year, selectYear] = useState(2023);
  const buttons = Array.from({ length: 25 }, (_, i) => i + 1);
  const years = Array.from({ length: 10 }, (_, j) => j + 1);
  return (
    <div>
      <Router>
        <Routes>
          <Route
            path="/"
            element={
              <div className='App'>

                <div className='YearContainer'>
                  {years.map((num, index) => (
                    <React.Fragment key={index}>
                      <p
                        className={`Year${year === (num + 2014) ? "Selected" : ""}`}
                        onClick={() => selectYear(num + 2014)}>
                        {num + 2014}
                      </p>
                      {index < years.length - 1 && <p className='YearSeperator'> / </p>}
                    </React.Fragment>
                  ))}
                </div>
                <div className='ButtonContainer'>
                  {buttons.map((num) => (
                    <Link
                      key={num}
                      to={`/solution/${year}/${num}`}
                    >
                      <button className='Button'>
                        {num}
                      </button>

                    </Link>
                  ))}

                </div>
              </div>
            }
          />
          <Route path="/solution/:year/:id" element={<DynamicSubmitPage />} />
        </Routes>
      </Router>
    </div >
  );
}

export default App;
