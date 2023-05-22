import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import jwt_decode from 'jwt-decode';
import './AddProductPage.css';

const AddProductPage = () => {
  const [product, setProduct] = useState({
    name: '',
    price: '',
    stock: '',
    description: '',
    image: '',
    productType: '',
  });
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  const handleFormSubmit = async (e) => {
    e.preventDefault();

    try {
      const token = localStorage.getItem('token');
      if (token) {
        const decodedToken = jwt_decode(token);
        if (!decodedToken.admin) {
          // Redirect to a page indicating unauthorized access
          navigate('/unauthorized');
        } else {
          const response = await axios.post('http://localhost:8080/products', product);
          if (response.status === 201) {
            navigate('/admin');
          } else {
            setErrorMessage('Error adding product');
          }
        }
      } else {
        // Redirect to the login page if no token is found
        navigate('/signUp');
      }
    } catch (error) {
      setErrorMessage('Error adding product');
    }
  };

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setProduct((prevProduct) => ({ ...prevProduct, [name]: value }));
  };

  return (
    <div className="container">
      <h2>Add Product</h2>
      {errorMessage && <p className="error-message">{errorMessage}</p>}
      <form onSubmit={handleFormSubmit}>
        <div className="form-group">
          <label htmlFor="name">Name:</label>
          <input
            type="text"
            id="name"
            name="name"
            value={product.name}
            onChange={handleInputChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="price">Price:</label>
          <input
            type="text"
            id="price"
            name="price"
            value={product.price}
            onChange={handleInputChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="stock">Stock:</label>
          <input
            type="text"
            id="stock"
            name="stock"
            value={product.stock}
            onChange={handleInputChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="description">Description:</label>
          <textarea
            id="description"
            name="description"
            value={product.description}
            onChange={handleInputChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="image">Image:</label>
          <input
            type="file"
            id="image"
            accept="image/*"
            onChange={(e) => setProduct((prevProduct) => ({ ...prevProduct, image: e.target.files[0] }))}
          />
        </div>
        <div className="form-group">
          <label htmlFor="productType">Product Type:</label>
          <select
            id="productType"
            name="productType"
            value={product.productType}
            onChange={handleInputChange}
          >
            <option value="">Select a product</option>
            <option value="HEADSET">Headset</option>
            <option value="KEYBOARD">Keyboard</option>
            <option value="MOUSE">Mouse</option>
            <option value="MONITOR">Monitor</option>
          </select>
        </div>
        <div className="btn-container">
          <button type="submit">Add Product</button>
        </div>
      </form>
    </div>
  );
};

export default AddProductPage;
