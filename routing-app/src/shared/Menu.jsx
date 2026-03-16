import React from 'react'
import {Link} from 'react-router-dom'

function Menu() {
  return (
   <>
        <Link to={"/"}>Home</Link> |
        <Link to={"/about"}>About</Link>|
        <Link to={"/contact"}>Contact</Link>|
        <Link to={"/service"}>Service</Link>|
        <Link to={"/netbanking"}>NetBanking</Link>|
   </>
  )
}

export default Menu;