🍔 Food Delivery Web Application
A complete full-stack food delivery platform built with Spring Boot 3 (Java 21) and React + Vite. This application demonstrates modern web development practices with JWT authentication, real-time order tracking, and a robust mock payment system.

📋 Table of Contents
✨ Features
🛠 Tech Stack
🏗 Architecture
📋 Prerequisites
📥 Installation
🚀 Running the Application
📚 API Documentation
🧪 Testing Guide
📁 Project Structure
📊 Sample Data
🔧 Troubleshooting
🚀 Deployment
🤝 Contributing
📜 License
👥 Authors
✨ Features
🔐 Authentication & Authorization
JWT-based authentication
Role-based access control (Customer, Restaurant, Admin)
Secure password hashing with BCrypt
Protected routes and API endpoints
🍕 Restaurant Management
Browse available restaurants
View detailed restaurant information and menus
Menu items with descriptions and pricing
Discount system for promotional items
🛒 Shopping Cart
Add/remove items from cart and update quantities
Real-time price calculations
Cart persistence across sessions
📦 Order Management
Place orders with multiple items
Real-time order tracking
Order status updates (Placed → Preparing → En Route → Delivered)
Order history for customers
💳 Payment System
Mock payment gateway simulation
Multiple payment methods: M-Pesa, Credit/Debit Card, Cash on Delivery (COD)
Payment success/failure simulation (80% success rate)
⭐ Feedback System
Customer reviews and ratings (1-5 stars)
Comment system
View restaurant feedback history
📱 Responsive Design
Mobile-first approach
Clean and modern UI with Tailwind CSS
Intuitive user experience and real-time updates
🛠 Tech Stack
Backend
Technology	Version	Description
Java	21 (LTS)	Core language
Spring Boot	3.2.0	Application framework
Spring Data JPA		Data persistence
Spring Security		Authentication & authorization
JWT	jjwt 0.12.3	Token-based security
H2 Database		In-memory database for development
Maven		Dependency management
Frontend
Technology	Version	Description
React	18.2.0	UI library
Vite	5.0.8	Build tool and dev server
React Router DOM	6.20.1	Client-side routing
Axios	1.6.2	HTTP client
Tailwind CSS	3.3.6	Utility-first CSS framework
Context API		State management
🏗 Architecture
Visual Diagram
┌─────────────────────────────────────────────────────────────┐
│ Frontend (React) │
│ ┌──────────────┐ ┌──────────────┐ ┌──────────────┐ │
│ │ Pages │ │ Components │ │ Context │ │
│ │ - Auth │ │ - Navbar │ │ - User │ │
│ │ - Dashboard │ │ - Cards │ │ - Cart │ │
│ └──────────────┘ └──────────────┘ └──────────────┘ │
│ │ │
│ ┌──────▼──────┐ │
│ │ Axios API │ │
│ └──────┬──────┘ │
└───────────────────────────┼─────────────────────────────────┘
│ HTTP/REST
│ JWT Token
┌───────────────────────────▼─────────────────────────────────┐
│ Backend (Spring Boot) │
│ ┌──────────────┐ ┌──────────────┐ ┌──────────────┐ │
│ │ Controllers │ │ Services │ │ Repositories │ │
│ │ - Auth │─▶│ - User │─▶│ - JPA │ │
│ │ - Restaurant │ │ - Order │ │ - CRUD │ │
│ └──────────────┘ └──────────────┘ └──────┬───────┘ │
│ │ │ │
│ ┌──────▼──────────┐ ┌─────────▼────────┐ │
│ │ JWT Security │ │ H2 Database │ │
│ │ Filter Chain │ │ (In-Memory) │ │
│ └─────────────────┘ └──────────────────┘ │
└─────────────────────────────────────────────────────────────┘

Layered Architecture
Backend Layers:

Controller Layer: REST endpoints, request/response handling.
Service Layer: Business logic, transaction management.
Repository Layer: Data access, JPA operations.
Model Layer: Entity definitions, relationships.
Security Layer: JWT authentication, authorization.
Frontend Structure:

Pages: Route components, main views.
Components: Reusable UI elements.
Context: Global state management.
Services: API integration, HTTP requests.
📋 Prerequisites
Before you begin, ensure you have the following installed:

Java Development Kit (JDK) 21 or higher
java -version
# Should show: java version "21.x.x"

Apache Maven 3.8+
mvn -version
# Should show: Apache Maven 3.8.x or higher

Node.js 18+ and npm 9+
node -version
# Should show: v18.x.x or higher
npm -version
# Should show: 9.x.x or higher

Git (for cloning the repository)
git --version

📥 Installation
1. Clone the Repository
git clone [https://github.com/yourusername/food-delivery-app.git\](https://github.com/yourusername/food-delivery-app.git)
cd food-delivery-app

2. Backend Setup
# Navigate to backend directory
cd food-delivery-backend

# Install dependencies and build
mvn clean install
# This will: download dependencies, compile Java code, and package the application.

3. Frontend Setup
# Navigate to frontend directory (from root)
cd ../food-delivery-frontend

# Install dependencies
npm install

🚀 Running the Application
Start Backend Server
# From food-delivery-backend directory
mvn spring-boot:run

Backend will start on: http://localhost:8080

Start Frontend Development Server
# From food-delivery-frontend directory
npm run dev

Frontend will start on: http://localhost:5173

Access the Application
Frontend: Open your browser and navigate to http://localhost:5173
Backend API: http://localhost:8080/api/v1
H2 Database Console: http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:testdb, Username: sa, Password: leave blank)
📚 API Documentation
Base URL
http://localhost:8080/api/v1

Authentication Endpoints
Method	Endpoint	Description
POST	/auth/register	Register a new user (CUSTOMER, RESTAURANT)
POST	/auth/login	Authenticate and receive JWT token
Register User Example

POST /auth/register
Content-Type: application/json

{
"name": "John Doe",
"email": "john@example.com",
"password": "password123",
"role": "CUSTOMER",
"address": "123 Main St",
"phoneNumber": "+254712345678"
}

Restaurant Endpoints
Method	Endpoint	Description
GET	/restaurants	Get all available restaurants
GET	/restaurants/{id}/menu	Get the menu for a specific restaurant
Order Endpoints
Method	Endpoint	Description
POST	/orders	Create a new order (Requires Auth)
GET	/orders/{id}/track	Get real-time status of an order (Requires Auth)
POST	/orders/{id}/pay	Process payment for a placed order (Requires Auth)
Feedback Endpoints
Method	Endpoint	Description
POST	/feedback	Submit feedback/rating for a restaurant (Requires Auth)
GET	/feedback/restaurant/{restaurantId}	Get all feedback for a restaurant
🧪 Testing Guide
Complete Testing Workflow (Customer)
Customer Registration & Login

Test Account (Pre-loaded): Email: john@example.com, Password: password123
Or Register a new account at http://localhost:5173/auth.
Browse Restaurants

After login, you'll see a list of pre-loaded restaurants (Pizza Palace, Burger House, Sushi World).
View Menu & Add to Cart

Click on a restaurant card to view the menu.
Add items to the cart. The cart icon (🛒) will update.
(Note: Adding items from a different restaurant will prompt to clear the existing cart.)
Review Cart & Place Order

Click the cart icon. Review and adjust items.
Check the order summary (Subtotal, Delivery Fee, Total).
Click "Place Order".
Process Payment

Select a payment method (M-Pesa, Credit/Debit Card, or COD).
Click "Pay Now". Payment is simulated with an 80% success rate.
Track Order Status

The order status updates automatically, progressing through:
📝 PLACED → 👨‍🍳 PREPARING → 🚗 EN_ROUTE → ✅ DELIVERED
Leave Feedback

Navigate back to the restaurant menu, scroll down, select a rating, and write a comment.
Restaurant/Admin Dashboard Testing
Admin Account: Email: admin@example.com, Password: admin123
Order Status Update: As a Restaurant Owner or Admin, you can manually update the order status through the Restaurant Dashboard: "Start Preparing" → "Out for Delivery" → "Mark as Delivered" or "Cancel Order". The customer's tracker updates in real-time.
API Testing with cURL
Test Login (Retrieve JWT Token)

curl -X POST http://localhost:8080/api/v1/auth/login \
-H "Content-Type: application/json" \
-d '{
"email": "john@example.com",
"password": "password123"
}'

Test Create Order (Replace YOUR_JWT_TOKEN)

curl -X POST http://localhost:8080/api/v1/orders \
-H "Content-Type: application/json" \
-H "Authorization: Bearer YOUR_JWT_TOKEN" \
-d '{
"restaurantId": 1,
"items": [
{
"menuItemId": 1,
"quantity": 2
}
]
}'

📁 Project Structure
Backend Structure
food-delivery-backend/
├── src/
│ ├── main/
│ │ ├── java/com/example/fooddelivery/
│ │ │ ├── FoodDeliveryApplication.java
│ │ │ ├── config/ (SecurityConfig, DataLoader)
│ │ │ ├── controller/ (Auth, Restaurant, Order, Feedback)
│ │ │ ├── dto/ (Request/Response objects)
│ │ │ ├── exception/ (GlobalExceptionHandler, ResourceNotFoundException)
│ │ │ ├── model/ (User, Order, Restaurant, Enums)
│ │ │ ├── repository/ (JPA Repositories)
│ │ │ ├── security/ (JwtUtil, Filter, UserDetailsService)
│ │ │ └── service/ (Business Logic, MockPaymentService)
│ │ └── resources/
│ │ └── application.properties
│ └── test/
└── pom.xml

Frontend Structure
food-delivery-frontend/
├── src/
│ ├── components/
│ │ ├── Navbar.jsx
│ │ ├── RestaurantCard.jsx
│ │ ├── MenuItemCard.jsx
│ │ └── ProtectedRoute.jsx
│ ├── pages/
│ │ ├── AuthPage.jsx
│ │ ├── CustomerDashboard.jsx
│ │ ├── Cart.jsx
│ │ └── OrderDetails.jsx
│ ├── context/
│ │ ├── UserContext.jsx
│ │ └── CartContext.jsx
│ ├── services/
│ │ └── api.js
│ ├── App.jsx
│ ├── main.jsx
│ └── index.css
├── .env
├── package.json
└── tailwind.config.js

📊 Sample Data
The application comes pre-loaded with sample data for testing, injected via DataLoader.java.

Users
Role	Email	Password	Name
Customer	john@example.com	password123	John Doe
Customer	jane@example.com	password123	Jane Smith
Admin	admin@example.com	admin123	Admin User
Restaurants
ID	Name	Location	Contact
1	Pizza Palace	Downtown Nairobi	+254701234567
2	Burger House	Westlands, Nairobi	+254702345678
3	Sushi World	Karen, Nairobi	+254703456789
📸 Screenshots
(You can replace the ASCII representations below with actual image links if you host them.)

Login Page
┌─────────────────────────────────────┐
│ 🍔 FoodDelivery │
├─────────────────────────────────────┤
│ │
│ Login │
│ │
│ Email: [________________] │
│ Password: [________________] │
│ │
│ [ Login ] │
│ │
│ Don't have an account? Register │
│ │
│ Test Accounts: │
│ Customer: john@example.com │
│ Password: password123 │
└─────────────────────────────────────┘

Order Tracking
┌─────────────────────────────────────┐
│ Order Status │
├─────────────────────────────────────┤
│ │
│ ┌─────────────────────────────┐ │
│ │ 👨‍🍳 PREPARING │ │
│ └─────────────────────────────┘ │
│ │
│ Order ID: #123 │
│ Restaurant: Pizza Palace │
│ Order Date: Jan 15, 2024 10:30 │
│ │
│ Order Items: │
│ • Margherita Pizza x2 $25.98 │
│ • Pepperoni Pizza x1 $14.39 │
│ │
│ Payment Details: │
│ Method: MPESA │
│ Status: ✅ SUCCESS │
│ Amount: $42.37 │
└─────────────────────────────────────┘

🔧 Troubleshooting
Common Backend Issues
Issue	Solution
Port 8080 Already in Use	Kill the process using port 8080 or change server.port in application.properties to 8081.
H2 Console Not Accessible	Ensure spring.h2.console.enabled=true in properties. Access at http://localhost:8080/h2-console.
JWT Token Errors	Ensure jwt.secret in application.properties is at least 32 characters long for HS256 algorithm.
Maven Build Failures	Run mvn clean install or use mvn clean install -DskipTests to skip tests.
Common Frontend Issues
Issue	Solution
CORS Errors	Verify SecurityConfig.java in the backend has http://localhost:5173 listed in setAllowedOrigins.
API Connection Failed	Ensure the backend is running and the VITE_API_BASE_URL in your .env file points to the correct backend address (http://localhost:8080).
Tailwind Styles Not Applied	Verify your tailwind.config.js content paths are correctly configured, and that src/index.css includes the three Tailwind directives.
🚀 Deployment
Backend Deployment
Render Deployment File (render.yaml):
services:
- type: web
name: food-delivery-backend
env: java
buildCommand: mvn clean install -DskipTests
startCommand: java -jar target/food-delivery-backend-1.0.0.jar
envVars:
- key: JAVA_VERSION
value: 21
- key: JWT_SECRET
generateValue: true
- key: JWT_EXPIRATION
value: 86400000
Frontend Deployment
Environment Variables for Production (.env.production):
VITE_API_BASE_URL=[https://your-backend-url.com\](https://your-backend-url.com)

Deployment Platforms: Netlify or Vercel are recommended. Remember to run npm run build and set the VITE_API_BASE_URL environment variable on the platform.

Production Checklist
 Update CORS origins in SecurityConfig.java.
 Use a production database (PostgreSQL/MySQL instead of H2).
 Set a secure JWT secret (minimum 256 bits).
 Enable HTTPS.
 Configure proper logging and error monitoring.
 Add health check endpoints.
🤝 Contributing
Contributions are welcome! Please follow these steps:

Fork the Repository: Click "Fork" on GitHub and clone your fork.

Create a Feature Branch: git checkout -b feature/your-feature-name

Make Your Changes: Follow the development guidelines below.

Commit Your Changes: Use the Conventional Commits style (e.g., feat: add payment logging).
git commit -m "feat: add your feature description"

Push and Create Pull Request: Push your branch and open a PR on GitHub.

📝 Development Guidelines
Backend Development
Adding a New Entity: Requires creating an Entity Class (in model/), a corresponding Repository (in repository/), a Service (in service/), and a Controller (in controller/).
Frontend Development
Adding a New Page: Create the Page Component (in pages/) and add its route mapping to App.jsx.
Adding API Service: Define new API functions in services/api.js using axios.
🧪 Testing
Backend: Use Maven commands to run unit and integration tests.
mvn test # Run all tests
mvn test jacoco:report # Run with coverage

Frontend: Use npm commands for component testing (e.g., with React Testing Library).
npm test # Run tests
npm test -- --coverage # Run with coverage

📜 License
This project is licensed under the MIT License. See the LICENSE file for details.

MIT License

Copyright (c) 2025 Ted M. Njeru

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

👥 Authors
Your Name
GitHub: @Njeru-the-honest
LinkedIn: Ted Njeru
Email: tednjeru1@gmail.com
⭐ Show Your Support
Give a ⭐️ if this project helped you!

🗺 Roadmap
Version 1.1.0 (Planned)
 Real-time notifications with WebSocket
 Email notifications
 Order history page
Version 1.2.0 (Planned)
 Multi-language support
 Dark mode
 Mobile app (React Native)
<p align="center">
Built with ❤️ for learning and demonstration purposes | Last Updated: November 2025
</p>
