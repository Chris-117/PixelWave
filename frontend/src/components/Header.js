import React from 'react';
import './Header.css'; // Import the CSS file for styling
import Cart from '../resources/cart.png'
import Name from './Name';
import Account from '../resources/account.png'
import {
    BrowserRouter as Router,
    Switch,
    Link
} from 'react-router-dom';

const Header = () => {
    return (
        <header className="header-container">
            <h1 className="header-title">Pixelwave</h1>

            <nav className="header-nav">
                <ul className="nav-list">
                    <li className="nav-item">
                        <Link to="/" className="nav-link">Home</Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/products" className="nav-link">Products</Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/categories" className="nav-link">Categories</Link>
                    </li>
                </ul>
            </nav>

            <div className="header-icons">
                <Link to="/basket" className="header-link">
                    <img src={Cart} className="header-img" alt="Cart" />
                </Link>
                <Link to="/signUp" className="header-link">
                    <img src={Account} className="header-img" alt="Account" />
                </Link>
                <div className="customer">
                    <Name />
                </div>
            </div>
        </header>
    );
};

export default Header;