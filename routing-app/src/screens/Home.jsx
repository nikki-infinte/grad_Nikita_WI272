import React from 'react'
import Menu from '../shared/Menu'
import { useNavigate } from 'react-router-dom'


function Home() {
  const [count, setCount] = React.useState(0);
  const navigate = useNavigate();

  const handleClick = () => {
    const newCount = count + 1;

    if (newCount === 5) {
      navigate("/about");
    }

    setCount(newCount);
  };
  return (


    <div>
        <h1>Home Page</h1>
        <p>Welcome to Wissen Bank. We are here to serve you with the best banking experience. Please explore our services and enjoy banking with us.</p>
        <center>
          <p color ='red'>You have clicked the button {count} times.</p>
          <button onClick={handleClick}>About Us</button> 
        </center>
    </div>
  )
}

export default Home