import React from 'react';
import './HomePage.css';
import '../resources/keyboard.png'
import '../resources/headset.jpg'

const HomePage = () => {
    return (
        <div className="home-container">
            <div className="image-container">
                <img src={require('../resources/keyboard.png')} alt="Image 1" />
                <img src={require('../resources/headset.jpg')} alt="Image 2" />
                <img src={require('../resources/monitor.jpg')} alt="Image 3" />
            </div>
            <h1 className="title">The Latest And Greatest {"\n"} Gaming Gear</h1>
        </div>
    );
};

export default HomePage;