import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './BasketPage.css';
import jwt_decode from 'jwt-decode';
import { useNavigate } from 'react-router-dom';


const BasketPage = () => {
  const [basketItems, setBasketItems] = useState([]);
  const navigate = useNavigate();

  const [orderItems, setCustomer] = useState({
    orderItemsId : "",
    name : "",
    productId : "",
    customerId : "",
    quantity : "",
    price : ""

});
  useEffect(() => {
    const fetchBasketItems = async () => {
      try {
        const token = localStorage.getItem('token');
        let customerId = null;

        if (token) {
          const decodedToken = jwt_decode(token);
          customerId = decodedToken.id;
        }

        const response = await axios.get(`http://localhost:8080/orderItems/customer/${customerId}`);
        const basketItemsData = response.data;
        setBasketItems(basketItemsData);
        console.log("here"+basketItemsData)
      } catch (error) {
        console.error('Error fetching basket items:', error);
      }
    };

    fetchBasketItems();
  }, []);

  const removeFromBasket = async (product) => {
    try {
        console.log(product)
      const response = await axios.delete(`http://localhost:8080/orderItems/${product.orderItemsId}`);

      if (response.status === 204) {
        window.location.reload();
      } else {
        console.error('Error removing item from basket:', response.status);
      }
    } catch (error) {
      console.error('Error removing item from basket:', error);
    }
  };

  const updateQuantity = (itemId, quantity) => {
    // Find the item in the basketItems array and update its quantity
    const updatedItems = basketItems.map((item) => {
      if (item.id === itemId) {
        return {
          ...item,
          quantity: quantity,
        };
      }
      return item;
    });

    setBasketItems(updatedItems);
  };

  const handleUpdate = async (product) => {
    try {
      const response = await axios.put(`http://localhost:8080/orderItems/${product.orderItemsId}` , product);

      if (response.status === 200) {
        window.location.reload();
        console.log('Item quantity updated successfully');
      } else {
        console.error('Error updating item quantity:', response.status);
      }
    } catch (error) {
      console.error('Error updating item quantity:', error);
    }
  };

  const handleCheckout = () => {
    navigate('/checkout');
    console.log('Checkout clicked');
  };

  return (
    <div className="basket-container">
      <h2>Your Basket</h2>
      {basketItems.length > 0 ? (
        <ul className="basket-items">
          {basketItems.map((product) => (
            <li key={product.orderItemsId} className="basket-item">
              <div className="item-details">
                <h3>{product.name}</h3>
                <p>Price: £{product.price}</p>
                <p>
                  Quantity:
                  <input
                    type="number"
                    value={product.quantity}
                    onChange={(e) => updateQuantity(product.id, e.target.value)}
                  />
                </p>
              </div>
              <button onClick={() => removeFromBasket(product)}>Remove</button>
              <button onClick={() => handleUpdate(product)}>Update</button>
            </li>
          ))}
        </ul>
      ) : (
        <p>Your basket is empty.</p>
      )}
      <button onClick={handleCheckout}>Checkout</button>
    </div>
  );
};

export default BasketPage;