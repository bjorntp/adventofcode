import React, { useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import './DynamicSubmitPage.css';

const DynamicSubmitPage = () => {
  const { year, id } = useParams(); // Get the dynamic `id` from the route
  const navigate = useNavigate();
  const [answerTwo, setAnswerTwo] = useState(<></>)
  const [answerOne, setAnswerOne] = useState(<></>)

  const handleSubmit = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const data = {
      id,
      message: formData.get("message"),
    };

    let api = "http://localhost:8080/solutions/"
    console.log(JSON.stringify(data))
    fetch(api + year + "/" + id, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    })
      .then((response) => response.json())
      .then((result) => {
        setAnswerOne(
          <div className="AnswerBox">
            <p className="AnswerHeader">Task 1</p>
            <p className="AnswerText">{result[0]}</p>
          </div>
        );
        setAnswerTwo(
          <div className="AnswerBox">
            <p className="AnswerHeader">Task 2</p>
            <p className="AnswerText">{result[1]}</p>
          </div>
        );
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  };

  return (
    <div className='MainBox'>
      <h1>Solution {id}</h1>
      <form onSubmit={handleSubmit} style={{ marginTop: "20px" }}>
        <div>
          <label htmlFor="message" style={{ fontSize: "18px" }}>
            Enter your message:
          </label>
          <br />
          <div className="TextAndAnswer">
            {answerOne}
            <div>
              <textarea
                id="message"
                name="message"
                rows="4"
                cols="30"
                required
                style={{ margin: "10px 0", padding: "10px", borderRadius: "5px" }}
              />
            </div>
            {answerTwo}
          </div>
        </div>
        <button
          className='SubmitButton'
          type="submit"
        >
          Submit
        </button>
      </form>
      <button
        className='BackToHomeButton'
        onClick={() => navigate("/")}
      >
        Back to Home
      </button>
    </div >
  );
};

export default DynamicSubmitPage;
