import React from 'react'
import Menu from './Menu'

function Header() {
  return (
    <div className="header">
        <h1>Welcome to Wissen Bank </h1>
        <h3><i>Banking at your Doorstep</i></h3>
      
        <Menu></Menu>
    </div>
  )
}

export default Header