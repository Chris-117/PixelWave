import React, { useState } from 'react';
import axios from 'axios';
import './SignUpPage.css';
import SignUpPageComp from './SignUpPageComp';
import SignUpPageFail from './SignUpPageFail';
import { Link, useNavigate } from 'react-router-dom';

const SignUpPage = () => {
    const [customer, setCustomer] = useState({
        name: '',
        surname: '',
        email: '',
        password: '',
        admin: false,
        cardNumber: '',
        expiry: '',
        address: '',
        postcode: ''
    });

    const navigate = useNavigate();

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setCustomer((prevCustomer) => ({ ...prevCustomer, [name]: value }));
    };

    const handleSignUpSubmit = async (event) => {
        event.preventDefault();
        try {
            // Send a POST request to the server with the customer data
            await axios.post('http://localhost:8080/customers/signUp', customer);
            // Redirect to the success page
            navigate('/signUpComp');
        } catch (error) {
            // Redirect to the failure page
            navigate('/signUpFail');
            console.error('Error signing up:', error);
        }
    };

    const handleLoginSubmit = async (event) => {
        event.preventDefault();
        try {
            // Send a POST request to the server with the user credentials
            const response = await axios.post('http://localhost:8080/customers/login', {
                email: customer.email,
                password: customer.password
            });
            console.log('Login successful!');
            const { token } = response.data; //getting the token back 
            localStorage.setItem('token', token);// saving it in local storage 
            navigate("/")
        } catch (error) {
            // Handle login error
            console.error('Error logging in:', error);
        }
    };

    return (
        <div>
            <div className="login-section">
                <h2>Login</h2>
                <form onSubmit={handleLoginSubmit}>
                    <label htmlFor="email">Email:</label>
                    <input
                        type="email"
                        id="email"
                        name="email"
                        value={customer.email}
                        onChange={handleInputChange}
                        required
                    />

                    <label htmlFor="password">Password:</label>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        value={customer.password}
                        onChange={handleInputChange}
                        required
                    />

                    <button type="submit">Login</button>
                </form>
            </div>
            <div className="submit-section">
            <h2>Sign Up</h2>
            <form onSubmit={handleSignUpSubmit}>
                <label htmlFor="name">Name:</label>
                <input type="text" id="name" name="name" value={customer.name} onChange={handleInputChange} />

                <label htmlFor="surname">Surname:</label>
                <input type="text" id="surname" name="surname" value={customer.surname} onChange={handleInputChange} />

                <label htmlFor="email">Email:</label>
                <input type="email" id="email" name="email" value={customer.email} onChange={handleInputChange} />

                <label htmlFor="password">Password:</label>
                <input type="password" id="password" name="password" value={customer.password} onChange={handleInputChange} />

                <label htmlFor="cardNumber">Card Number:</label>
                <input type="text" id="cardNumber" name="cardNumber" value={customer.cardNumber} onChange={handleInputChange} />

                <label htmlFor="expiry">Expiry:</label>
                <input type="text" id="expiry" name="expiry" value={customer.expiry} onChange={handleInputChange} />

                <label htmlFor="address">Address:</label>
                <input type="text" id="address" name="address" value={customer.address} onChange={handleInputChange} />

                <label htmlFor="postcode">Postcode:</label>
                <input type="text" id="postcode" name="postcode" value={customer.postcode} onChange={handleInputChange} />

                <button type="submit">Sign Up</button>

            </form>
            </div>
        </div>
    );
};

export default SignUpPage;