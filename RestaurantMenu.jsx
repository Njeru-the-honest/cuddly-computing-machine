import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { restaurantAPI, feedbackAPI } from '../services/api';
import MenuItemCard from '../components/MenuItemCard';
import FeedbackForm from '../components/FeedbackForm';

const RestaurantMenu = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [restaurant, setRestaurant] = useState(null);
  const [menuItems, setMenuItems] = useState([]);
  const [feedbacks, setFeedbacks] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchData();
  }, [id]);

  const fetchData = async () => {
    try {
      const [restaurantRes, menuRes, feedbackRes] = await Promise.all([
        restaurantAPI.getById(id),
        restaurantAPI.getMenu(id),
        feedbackAPI.getByRestaurant(id),
      ]);
      
      setRestaurant(restaurantRes.data);
      setMenuItems(menuRes.data);
      setFeedbacks(feedbackRes.data);
      setLoading(false);
    } catch (error) {
      console.error('Error fetching data:', error);
      setLoading(false);
    }
  };

  if (loading) {
    return (
      <div className="flex justify-center items-center h-screen">
        <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-500"></div>
      </div>
    );
  }

  if (!restaurant) {
    return <div className="text-center py-8">Restaurant not found</div>;
  }

  return (
    <div className="container mx-auto px-4 py-8">
      <button
        onClick={() => navigate('/restaurants')}
        className="mb-4 text-blue-500 hover:underline"
      >
        ← Back to Restaurants
      </button>

      <div className="bg-white rounded-lg shadow-md p-6 mb-8">
        <h1 className="text-3xl font-bold mb-2">{restaurant.name}</h1>
        <p className="text-gray-600">📍 {restaurant.location}</p>
        <p className="text-gray-600">📞 {restaurant.contactInfo}</p>
      </div>

      <h2 className="text-2xl font-bold mb-4">Menu</h2>
      {menuItems.length === 0 ? (
        <p className="text-center text-gray-500">No menu items available</p>
      ) : (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mb-8">
          {menuItems.map((item) => (
            <MenuItemCard key={item.id} item={item} restaurant={restaurant} />
          ))}
        </div>
      )}

      <div className="grid grid-cols-1 lg:grid-cols-2 gap-8 mt-8">
        <div>
          <h2 className="text-2xl font-bold mb-4">Customer Reviews</h2>
          {feedbacks.length === 0 ? (
            <p className="text-gray-500">No reviews yet</p>
          ) : (
            <div className="space-y-4">
              {feedbacks.map((feedback) => (
                <div key={feedback.id} className="bg-white rounded-lg shadow-md p-4">
                  <div className="flex items-center mb-2">
                    <div className="text-yellow-400">
                      {'⭐'.repeat(feedback.rating)}
                    </div>
                    <span className="ml-2 text-gray-600 text-sm">
                      {new Date(feedback.date).toLocaleDateString()}
                    </span>
                  </div>
                  <p className="text-gray-700">{feedback.comment}</p>
                  <p className="text-gray-500 text-sm mt-2">
                    - {feedback.customer?.name}
                  </p>
                </div>
              ))}
            </div>
          )}
        </div>

        <div>
          <FeedbackForm restaurantId={parseInt(id)} onSuccess={fetchData} />
        </div>
      </div>
    </div>
  );
};

export default RestaurantMenu;