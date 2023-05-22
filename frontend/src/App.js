import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import Footer from './components/Footer';
import Home from './pages/HomePage';
import ProductsPage from './pages/ProductsPage';
import CheckoutPage from './pages/CheckoutPage';
import BasketPage from './pages/BasketPage';
import SignUpPage from './pages/SignUpPage';
import SignUpPageComp from './pages/SignUpPageComp';
import SignUpPageFail from './pages/SignUpPageFail';
import PrivateRoute from './components/PrivateRoute';
import UpdateProductPage from './pages/UpdateProductPage';
import AdminPage from './pages/ProductChange';
import OrderComplete from './pages/OrderComplete';
import UnauthorizedPage from './pages/Unauthorized';
import AddProductPage from './pages/AddProductPage';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import ErrorNotification from './components/ErrorNotification';
import './App.css';

function App() {
  const handleApiError = (error) => {
    if (error.response) {
      const errorMessage = error.response.data.message;
      toast.error(<ErrorNotification message={errorMessage} />, {
        position: toast.POSITION.TOP_CENTER,
      });
    } else {
      toast.error(<ErrorNotification message="Network Error" />, {
        position: toast.POSITION.TOP_CENTER,
      });
    }
  };

  return (
    <Router>
      <div className="App">
        <Header />

        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/products" element={<ProductsPage />} />
          <Route path="/basket" element={<BasketPage />} />
          <Route path="/signUp" element={<SignUpPage />} />
          <Route path="/signUpComp" element={<SignUpPageComp />} />
          <Route path="/signUpFail" element={<SignUpPageFail />} />
          <Route path="/admin" element={<AdminPage />} />
          <Route path="/checkout" element={<CheckoutPage />} />
          <Route path="/unauthorized" element={<UnauthorizedPage />} />
          <Route path="/orderComplete" element={<OrderComplete />} />
          <Route path="/updateProduct/:productId" element={<UpdateProductPage />} />
          <Route path="/addProduct" element={<AddProductPage/>} />
        </Routes>

        <ToastContainer />

        <Footer />
      </div>
    </Router>
  );
}

export default App;
