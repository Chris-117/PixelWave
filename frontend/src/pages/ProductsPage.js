import React, { useState, useEffect } from 'react';
import './ProductPage.css'; // Import the CSS file
import axios from 'axios';
import Monitor from '../resources/monitor.jpg';
import Keyboard from '../resources/keyboard.png';
import Mouse from '../resources/mouse.webp';
import Headset from '../resources/headset.jpeg';
import jwt_decode from 'jwt-decode';

const ProductsPage = () => {
  const [products, setProducts] = useState([]);
  const [searchKeyword, setSearchKeyword] = useState('');
  const [selectedCategory, setSelectedCategory] = useState('');
  const [successMessage, setSuccessMessage] = useState('');

  const token = localStorage.getItem('token');
  let customerId = null;

  if (token) {
    const decodedToken = jwt_decode(token);
    customerId = decodedToken.id;
  }

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        let url = 'http://localhost:8080/products';
        if (searchKeyword) {
          url = `http://localhost:8080/products/search/${searchKeyword}`;
        }
        if (selectedCategory) {
          url = `http://localhost:8080/products/category/${selectedCategory}`;
        }
        const response = await fetch(url);
        const productsData = await response.json();
        setProducts(productsData);
      } catch (error) {
        console.error('Error fetching products data:', error);
      }
    };

    fetchProducts();
  }, [searchKeyword, selectedCategory]);

  const handleSearch = (e) => {
    e.preventDefault();
    setSearchKeyword(e.target.elements.search.value);
  };

  const handleCategoryChange = (e) => {
    setSelectedCategory(e.target.value);
  };

  const [orderItems, setOrderItems] = useState({
    productId: '',
    customerId: customerId,
    quantity: '1',
    price: ''
  });

  const handleAddToBasket = async (product) => {
    console.log(product)
    try {
      const newOrderItems = {
        productId: product.productId,
        customerId: customerId,
        quantity: '1',
        price: product.price
      };
      setOrderItems(newOrderItems)
      const response = await axios.post('http://localhost:8080/orderItems', orderItems);
      console.log(orderItems);

      if (response.status === 201) {
        console.log('Product added to basket!');
        setSuccessMessage('Product added to basket successfully!');
        // Handle success (e.g., show a notification)
      }
    } catch (error) {
      console.error('Error adding item to basket:', error);
    }
  };

  useEffect(() => {
    if (successMessage) {
      const timeout = setTimeout(() => {
        setSuccessMessage('');
      }, 3000);

      return () => clearTimeout(timeout);
    }
  }, [successMessage]);

  return (
    <div className="products-container">
      <h2>Products</h2>
      {successMessage && <p>{successMessage}</p>}
      <form className="search-form" onSubmit={handleSearch}>
        <input type="text" name="search" placeholder="Enter keyword" />
        <button type="submit">Search</button>
      </form>
      <div className="category-filter">
        <label htmlFor="category">Select Category:</label>
        <select id="category" value={selectedCategory} onChange={handleCategoryChange}>
          <option value="">All</option>
          <option value="KEYBOARD">Keyboard</option>
          <option value="MOUSE">Mouse</option>
          <option value="HEADSET">Headset</option>
          <option value="MONITOR">Monitor</option>
        </select>
      </div>
      <ul className="products-list">
        {products.map((product) => {
          let categoryImage;
          if (product.productType === 'MONITOR') {
            categoryImage = Monitor;
          } else if (product.productType === 'KEYBOARD') {
            categoryImage = Keyboard;
          } else if (product.productType === 'MOUSE') {
            categoryImage = Mouse;
          } else if (product.productType === 'HEADSET') {
            categoryImage = Headset;
          }

          return (
            <li key={product.id} className="product-item">
              <h3>{product.name}</h3>
              <p>{product.description}</p>
              <p>Price: £{product.price}</p>
              {product.stock > 0 ? (
                <button onClick={() => handleAddToBasket(product)}>Add to basket</button>
              ) : (
                <p className="out-of-stock">Out Of Stock</p>
              )}
              <img src={product.productType === 'MONITOR' ? Monitor : categoryImage} alt={product.name} />
              <p className={product.stock > 0 ? 'in-stock' : 'out-of-stock'}>
                Stock: {product.stock > 0 ? 'In Stock' : 'Out Of Stock'}
              </p>
            </li>
          );
        })}
      </ul>
    </div>
  );
};

export default ProductsPage;