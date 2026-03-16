import React from 'react'
import { Routes, Route } from 'react-router-dom'
import { Provider } from 'react-redux'
import store from './redux/store'
import Home from './screens/Home'
import Service from './screens/Service'
import NetBanking from './screens/NetBanking'
import About from './screens/About'
import Contact from './screens/Contact'
import Header from './shared/Header'
import Footer from './shared/Footer'
import './index.css'
function App() {
  

  return (
  <Provider store={store}>
  <div className="app">
  <Header ></Header>
  <hr></hr>
  <Routes>
    <Route path="/" element={<Home />} />
    <Route path="/service" element={<Service />} />
    <Route path="/netbanking" element={<NetBanking />} />
    <Route path="/about" element={<About />} />
    <Route path="/contact" element={<Contact />} />
  </Routes>
  <hr></hr>
  <Footer></Footer>
  </div>
  </Provider>
  )
}

export default App
