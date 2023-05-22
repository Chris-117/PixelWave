import React from 'react';
import './Footer.css'; // Import the CSS file for styling
import logo from '../resources/logo.png';


const Footer = () => {
  return (
    <footer className="footer-container">
      <div className="footer-left">
        <img src={logo} alt="Company Logo" className="footer-logo" />
        <p className="footer-info">Pixelwave.inc</p>
        <p className="footer-info">Newbury, UK</p>
        <p className="footer-info">Instagram: @PixelwaveUK</p>
        <p className="footer-info">Email: Pixelwave.cb@gmail.com</p>
      </div>
    </footer>
  );
};

export default Footer;