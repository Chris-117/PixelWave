import React, { useContext } from 'react';
import { Route, Navigate } from 'react-router-dom';


const PrivateRoute = ({ component: Component, ...rest }) => {
  const { user, loading } = useContext();

  if (loading) {
    return <div>Loading...</div>; // Show loading indicator while checking authentication state
  }

  return (
    <Route
      {...rest}
      render={(props) =>
        user ? (
          <Component {...props} />
        ) : (
          <Navigate to="/login" />
        )
      }
    />
  );
};

export default PrivateRoute;