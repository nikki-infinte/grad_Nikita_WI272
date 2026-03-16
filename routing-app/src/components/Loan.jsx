import React, { useState } from 'react'

function Loan() {
  const [aName, setAName] = useState('')
  const [loanType, setLoanType] = useState('choose')
  const [iRate, setIRate] = useState('')
  const [amount, setAmount] = useState('')
  const [duration, setDuration] = useState('')
  const [result, setResult] = useState('')
  const [amountError, setAmountError] = useState(false)
  const [durationError, setDurationError] = useState(false)

  const setInterest = (type) => {
    if (type === "home") {
      setIRate(9)
    } else if (type === "car") {
      setIRate(12)
    } else if (type === "personal") {
      setIRate(15)
    } else {
      setIRate('')
    }
  }

  const handleLoanTypeChange = (e) => {
    const type = e.target.value
    setLoanType(type)
    setInterest(type)
    setAmountError(false)
    setDurationError(false)
  }

  const validInput = () => {
    const amt = parseFloat(amount)
    const dur = parseInt(duration)

    let isValid = true

    setAmountError(false)
    setDurationError(false)

    if (loanType === "choose") {
      alert("Please select loan type!")
      return false
    }

    if (isNaN(amt) || isNaN(dur)) {
      return false
    }

    if (loanType === "home") {
      if (amt < 500000) {
        setAmountError(true)
        isValid = false
      }
      if (dur > 30) {
        setDurationError(true)
        isValid = false
      }
    } else if (loanType === "car") {
      if (amt < 100000) {
        setAmountError(true)
        isValid = false
      }
      if (dur > 7) {
        setDurationError(true)
        isValid = false
      }
    } else if (loanType === "personal") {
      if (amt < 10000) {
        setAmountError(true)
        isValid = false
      }
      if (dur > 5) {
        setDurationError(true)
        isValid = false
      }
    }

    return isValid
  }

  const calculateEMI = () => {
    if (!validInput()) {
      alert("Invalid Input! Please check RED highlighted fields.")
      return
    }
    const p = parseFloat(amount)
    const r = parseFloat(iRate) / 12 / 100
    const n = parseInt(duration) * 12

    const emi = (p * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1)

    setResult("Your EMI is: " + emi.toFixed(2))
  }

  return (
    <div>
      <h1>LOAN Application Form</h1>
      <hr />

      <form>
        Applicant Name <input type="text" value={aName} onChange={(e) => setAName(e.target.value)} /><br /><br />
        Type of Loan
        <select value={loanType} onChange={handleLoanTypeChange}>
          <option value="choose">Choose Loan Type</option>
          <option value="home">Home Loan</option>
          <option value="car">Car Loan</option>
          <option value="personal">Personal Loan</option>
        </select><br /><br />
        Interest Rate <input type="text" value={iRate} disabled /><br /><br />
        Amount <input
          type="text"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
          style={{ border: amountError ? '2px solid red' : '2px solid black' }}
        /><br /><br />
        Duration <input
          type="number"
          placeholder="in years"
          value={duration}
          onChange={(e) => setDuration(e.target.value)}
          style={{ border: durationError ? '2px solid red' : '2px solid black' }}
        /><br /><br />
        <input type="button" value="Calculate EMI" onClick={calculateEMI} />
        <div>{result}</div>
      </form>
    </div>
  )
}

export default Loan