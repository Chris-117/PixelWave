
import Name from '../components/Name';
import axios from 'axios';
import jwt_decode from 'jwt-decode';
import React, { useState, useEffect } from 'react';

const SignUpPageComp = () => {
    const [customer, setCustomer] = useState({});
    const token = localStorage.getItem('token');

    if (token) {
      const decodedToken = jwt_decode(token);
      const customerId = decodedToken.id;

      try {
        const response = axios.get(`http://localhost:8080/customers/${customerId}`);
        const customerData = response.data;
        setCustomer(customerData);
      } catch (error) {
        console.error('Error fetching customer:', error);
      }
    
  };
    return (
        <div className = "comp">
            
            <h1> Account Successfully registed!</h1>
            <h2>Welcome! {customer.name}  {customer.surname}</h2>
            
        </div>
    )
};
export default SignUpPageComp;