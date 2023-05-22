import React, { useState, useEffect } from 'react';
import axios from 'axios';
import jwt_decode from 'jwt-decode';
import './Name.css'

const Name = () => {
  const [customer, setCustomer] = useState({});

  useEffect(() => {
    fetchCustomer();
  }, []);

  const fetchCustomer = async () => {
    const token = localStorage.getItem('token');

    if (token) {
      const decodedToken = jwt_decode(token);
      const customerId = decodedToken.id;

      try {
        const response = await axios.get(`http://localhost:8080/customers/${customerId}`);
        const customerData = response.data;
        setCustomer(customerData);
      } catch (error) {
        console.error('Error fetching customer:', error);
      }
    }
  };

  return (
    <div className="name">
      {customer.name ? (
        <div>
          <p>Hi, {customer.name} {customer.surname}</p>
        </div>
      ) : (
        <p>Welcome!</p> // No signed-in user
      )}
    </div>
  );
};


export default Name;