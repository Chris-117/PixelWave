import React from 'react';
import { Link } from 'react-router-dom';

const OrderCompletePage = () => {
    return (
        <body>
            <div class="container">
                <h2>Order Complete</h2>
                <p>Thank you for your order! Your order has been successfully placed.</p>
                <p>An email confirmation with the order details has been sent to your registered email address.</p>
                <a href="/">Go back to homepage</a>
            </div>
        </body>
    );
};

export default OrderCompletePage;