import axios from 'axios';
import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import jwt_decode from 'jwt-decode';

const ProductManagementPage = () => {
  const [products, setProducts] = useState([]);
  const [successMessage, setSuccessMessage] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const checkAdminAccess = async () => {
      try {
        const token = localStorage.getItem('token');
        if (token) {
          const decodedToken = jwt_decode(token);
          if (!decodedToken.admin) {
            // Redirect to a page indicating unauthorized access
            navigate('/unauthorized');
          }
        } else {
          // Redirect to the login page if no token is found
          navigate('/signUp');
        }
      } catch (error) {
        console.error('Error checking admin access:', error);
      }
    };

    checkAdminAccess();
  }, []);

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      const response = await axios.get('http://localhost:8080/products');
      setProducts(response.data);
    } catch (error) {
      console.error('Error fetching products:', error);
    }
  };

  const handleDeleteProduct = async (product) => {
    try {
      const response = await axios.delete(`http://localhost:8080/products/${product.productId}`);

      if (response.status === 204) {
        setSuccessMessage('Product deleted successfully!');
        setProducts((prevProducts) => prevProducts.filter((p) => p.productId !== product.productId));
      } else {
        setSuccessMessage('Error deleting product');
        // Handle error
      }
    } catch (error) {
      setSuccessMessage('Error deleting product');
      // Handle error
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
    <div className="container">
    <h2>Product Management</h2>
    {successMessage && <p className="success-message">{successMessage}</p>}
    <Link to="/addProduct">Add Product</Link>
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Price</th>
          <th>Stock</th>
          <th>Description</th>
          <th>Image</th>
          <th>Product Type</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        {products.map((product) => (
          <tr key={product.productId}>
            <td>{product.productId}</td>
            <td>{product.name}</td>
            <td>{product.price}</td>
            <td>{product.stock}</td>
            <td>{product.description}</td>
            <td>{product.image}</td>
            <td>{product.productType}</td>
            <td>
              <button onClick={() => handleDeleteProduct(product)}>Delete</button>
              <Link to={`/updateProduct/${product.productId}`}>Update</Link>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  </div>
  );
};

export default ProductManagementPage;
