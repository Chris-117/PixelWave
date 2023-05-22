import axios from 'axios';
import React, { useState, useEffect } from 'react';
import './UpdateProductPage.css';
import { useParams, useNavigate } from 'react-router-dom';

const UpdateProductPage = () => {
  const { productId } = useParams();
  const navigate = useNavigate();

  const [product, setProduct] = useState({
    name: '',
    price: '',
    stock: '',
    description: '',
    image: '',
    productType: '',
  });

  const [successMessage, setSuccessMessage] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  useEffect(() => {
    fetchProduct();
  }, []);

  const fetchProduct = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/products/${productId}`);
      setProduct(response.data);
    } catch (error) {
      console.error('Error fetching product:', error);
      setErrorMessage('Error fetching product');
    }
  };

  const handleFormSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.put(`http://localhost:8080/products/${productId}`, product);

      if (response.status === 200) {
        setSuccessMessage('Product updated successfully!');
        navigate('/admin');
      } else {
        setErrorMessage('Error updating product');
        // Handle error
      }
    } catch (error) {
      console.error('Error updating product:', error);
      setErrorMessage('Error updating product');
      // Handle error
    }
  };

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setProduct((prevProduct) => ({ ...prevProduct, [name]: value }));
  };

  return (
    <div>
      <h2>Update Product</h2>
      {successMessage && <p>{successMessage}</p>}
      {errorMessage && <p>{errorMessage}</p>}
      <form onSubmit={handleFormSubmit}>
        <label htmlFor="name">Name:</label>
        <input type="text" id="name" name="name" value={product.name} onChange={handleInputChange} />

        <label htmlFor="price">Price:</label>
        <input type="text" id="price" name="price" value={product.price} onChange={handleInputChange} />

        <label htmlFor="strock">Stock:</label>
        <input type="text" id="stock" name="stock" value={product.stock} onChange={handleInputChange} />

        <label htmlFor="description">Description:</label>
        <textarea id="description" name="description" value={product.description} onChange={handleInputChange} />

        <label htmlFor="image">Image:</label>
        <input type="text" id="image" name="image" value={product.image} onChange={handleInputChange} />

        <label htmlFor="productType">Product Type:</label>
        <input type="text" id="productType" name="productType" value={product.productType} onChange={handleInputChange} />

        <button type="submit">Update Product</button>
      </form>
    </div>
  );
};

export default UpdateProductPage;
