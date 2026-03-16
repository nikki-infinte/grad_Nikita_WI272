import React from 'react'
import { useSelector, useDispatch } from 'react-redux'
import { setService } from '../redux/serviceActions'
import Loan from '../components/Loan'
import Deposit from '../components/Deposit'

function Service() {

  const currentService = useSelector(state => state.currentService)
  const dispatch = useDispatch()

  const toggleService = () => {
    const newService = currentService === "loan" ? "deposit" : "loan"
    dispatch(setService(newService))
  }

  return (
    <>
      <h1>Services</h1>
      <h3>We offer the following services</h3>

      <div style={{ display: "flex", gap: "15px" }}>
        
        <button
          onClick={toggleService}
          style={{
            padding: '10px 20px',
            fontSize: '16px',
            backgroundColor: currentService === "loan" ? '#7ec290' : '#d8fde2',
            border: 'none',
            borderRadius: '5px',
            cursor: 'pointer'
          }}
        >
          {currentService === "loan" ? "Switch to DEPOSIT" : "Switch to LOAN"}
        </button>

      </div>

      {/* Render component here */}
      {currentService === "loan" && <Loan />}
      {currentService === "deposit" && <Deposit />}
    </>
  )
}

export default Service