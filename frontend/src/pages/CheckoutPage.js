import React, { useState, useEffect } from 'react';
import axios from 'axios';
import jwt_decode from 'jwt-decode';
import { Link, useNavigate } from 'react-router-dom';

const UpdateCustomerInfoPage = () => {
  const token = localStorage.getItem('token');
  const decodedToken = jwt_decode(token);
  const navigate = useNavigate();
  const customerId = decodedToken.id;

  const currentDate = new Date();
  const year = currentDate.getFullYear();
  const month = String(currentDate.getMonth() + 1).padStart(2, '0');
  const day = String(currentDate.getDate()).padStart(2, '0');
  const formattedDate = `${year}-${month}-${day}`;

  const [total, setTotal] = useState(0);

  const fetchTotal = async () => {
    try {
      let response = await axios.get(`http://localhost:8080/orderItems/total/${decodedToken.id}`);
      console.log("Here->" + response.data);
      setTotal(response.data);
    } catch (error) {
      console.error('Error fetching total:', error);
    }
  };

  useEffect(() => {
    fetchTotal();
  }, []);

  useEffect(() => {
    const fetchCustomerData = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/customer/${customerId}`);
        const customerData = response.data;
        const totalValue = customerData;
        setTotal(totalValue);
      } catch (error) {
        console.error('Error fetching total:', error);
      }
    };

    fetchCustomerData();
  }, [customerId]);

  const [orderItems, setOrderItems] = useState({
    orderItemsId: "",
    name: "",
    productId: "",
    customerId: "",
    quantity: "",
    price: ""
  });

  const [customer, setCustomer] = useState({
    name: decodedToken.name,
    surname: decodedToken.surname,
    email: decodedToken.email,
    password: decodedToken.password,
    admin: decodedToken.admin,
    cardNumber: decodedToken.cardNumber,
    expiry: decodedToken.expiry,
    address: decodedToken.address,
    postcode: decodedToken.postcode
  });

  console.log(total);

  const [order, setOrder] = useState({
    customerId: decodedToken.id,
    orderItemsId: '20',
    date: formattedDate,
    paymentStatus: 'Paid',
    deliveryAddress: customer.address + ' ' + customer.postcode,
    totalPrice: fetchTotal()
  });

  const handleUpdate = async () => {
    try {
      let response = await axios.put(`http://localhost:8080/customers/${customerId}`, customer);
      console.log(customer);
      console.log(decodedToken.id);
      console.log(formattedDate);


      if (response.status === 200) {
        console.log('Customer information updated successfully');
      } else {
        console.error('Error updating customer information:', response.status);
      }

      const updatedOrder = {
        ...order,
        totalPrice: total !== null ? total : 0
      };

      updatedOrder.deliveryAddress = customer.address + ' ' + customer.postcode; // Set the delivery address

      response = await axios.post(`http://localhost:8080/orders/`, updatedOrder);

      if (response.status === 201) {
        console.log('Order information updated successfully');
        navigate('/orderComplete');
      } else {
        console.error('Error updating order information:', response.status);
      }
    } catch (error) {
      console.error('Error updating order information:', error);
    }
  };

  return (
    <div>
      <h2>Update Customer Information</h2>
      <div>
        <label htmlFor="address">Address:</label>
        <input
          type="text"
          id="address"
          value={customer.address}
          onChange={(e) => setCustomer(prevCustomer => ({ ...prevCustomer, address: e.target.value }))}
          placeholder={customer.address}
        />
      </div>
      <div>
        <label htmlFor="cardNumber">Card Number:</label>
        <input
          type="text"
          id="cardNumber"
          value={customer.cardNumber}
          onChange={(e) => setCustomer(prevCustomer => ({ ...prevCustomer, cardNumber: e.target.value }))}
          placeholder={customer.cardNumber}
        />
      </div>
      <div>
        <label htmlFor="expiry">Expiry:</label>
        <input
          type="text"
          id="expiry"
          value={customer.expiry}
          onChange={(e) => setCustomer(prevCustomer => ({ ...prevCustomer, expiry: e.target.value }))}
          placeholder={customer.expiry}
        />
      </div>
      <div>
        <label htmlFor="postcode">Postcode:</label>
        <input
          type="text"
          id="postcode"
          value={customer.postcode}
          onChange={(e) => setCustomer(prevCustomer => ({ ...prevCustomer, postcode: e.target.value }))}
          placeholder={customer.postcode}
        />
      </div>
      <p>Total price: £{total}</p>
      <button onClick={handleUpdate}>Place Order</button>
    </div>
  );
};

export default UpdateCustomerInfoPage;