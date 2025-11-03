import React, { useState, useContext } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { UserContext } from '../context/UserContext';
import { orderAPI } from '../services/api';
import OrderTracker from '../components/OrderTracker';

const OrderDetails = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const { user } = useContext(UserContext);
  const [paymentMethod, setPaymentMethod] = useState('MPESA');
  const [paymentProcessed, setPaymentProcessed] = useState(false);
  const [loading, setLoading] = useState(false);

  const handlePayment = async () => {
    setLoading(true);
    try {
      const response = await orderAPI.pay(id, { paymentMethod });
      setPaymentProcessed(true);
      alert(`Payment ${response.data.paymentStatus === 'SUCCESS' ? 'successful' : 'failed'}!`);
    } catch (error) {
      console.error('Payment error:', error);
      alert('Payment processing failed');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container mx-auto px-4 py-8">
      <button
        onClick={() => navigate('/restaurants')}
        className="mb-4 text-blue-500 hover:underline"
      >
        ← Back to Restaurants
      </button>

      <div className="grid grid-cols-1 lg:grid-cols-2 gap-8">
        <div>
          <OrderTracker orderId={id} />
        </div>

        <div>
          {!paymentProcessed && (
            <div className="bg-white rounded-lg shadow-md p-6">
              <h2 className="text-2xl font-bold mb-4">Payment</h2>
              
              <div className="mb-4">
                <label className="block text-gray-700 font-semibold mb-2">
                  Select Payment Method
                </label>
                <select
                  value={paymentMethod}
                  onChange={(e) => setPaymentMethod(e.target.value)}
                  className="w-full border rounded-lg px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
                >
                  <option value="MPESA">M-Pesa</option>
                  <option value="CARD">Credit/Debit Card</option>
                  <option value="COD">Cash on Delivery</option>
                </select>
              </div>

              <div className="bg-yellow-50 border border-yellow-200 rounded-lg p-4 mb-4">
                <p className="text-sm text-yellow-800">
                  ⚠️ This is a mock payment system. Payment success/failure is randomly simulated.
                </p>
              </div>

              <button
                onClick={handlePayment}
                disabled={loading}
                className="w-full bg-green-500 hover:bg-green-600 text-white font-semibold py-3 rounded-lg disabled:bg-gray-400"
              >
                {loading ? 'Processing Payment...' : 'Pay Now'}
              </button>
            </div>
          )}

          {paymentProcessed && (
            <div className="bg-white rounded-lg shadow-md p-6">
              <div className="text-center">
                <div className="text-6xl mb-4">✅</div>
                <h2 className="text-2xl font-bold mb-2">Payment Processed</h2>
                <p className="text-gray-600 mb-4">
                  Your order is being prepared. Track the status above.
                </p>
                <button
                  onClick={() => navigate('/restaurants')}
                  className="bg-blue-500 hover:bg-blue-600 text-white px-6 py-2 rounded-lg"
                >
                  Order More
                </button>
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default OrderDetails;