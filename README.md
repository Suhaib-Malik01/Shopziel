# ShopZiel - an E-Commerce Project

This is an e-commerce project that provides various API endpoints for managing customers, orders, sellers and products.

## Technologies Used

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- MySQL

## Usage

Follow the steps below to clone the repository, build, and run the project. You can access the API endpoints using a REST client or web browser.

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Maven

### Step 1: Clone the Repository

Clone the repository to your local machine using the following command: `git clone https://github.com/Suhaib-Malik01/Online-Shopping-Application`


### Step 2: Build and Run the Project

Navigate to the project directory and use Maven to build and run the project. Execute the following command:
`cd Online-Shopping-Application`
`mvn spring-boot:run`
The Maven command will build the project and start the Spring Boot application.

### Step 3: Access the API Endpoints

Once the application is running, you can access the API endpoints using a REST client or web browser. Here are a few options:

- Use a REST client like [Postman](https://www.postman.com/) or [Insomnia](https://insomnia.rest/).
- Use cURL command-line tool to make HTTP requests.
- Open a web browser and navigate to the desired endpoint URL.




## Controllers


### AppUserController

The `AppUserController` class handles API endpoints related to user registration and login.

#### Register a User

- **Endpoint**: POST /api/users/
- **Description**: Registers a new user.
- **Request Body**: UserDto
- **Response**: UserDto
- **HTTP Status**: 200 (OK)

#### Login

- **Endpoint**: GET /api/users/signIn
- **Description**: Retrieves details of the logged-in customer.
- **Response**: UserDto
- **HTTP Status**: 202 (Accepted)


### CustomerController

The `CustomerController` class handles API endpoints related to customer reviews.

#### Add a Review

- **Endpoint**: POST /api/customers/review/{id}
- **Description**: Adds a review for a product.
- **Request Body**: ReviewDto
- **Response**: ReviewDto
- **HTTP Status**: 200 (OK)
- **Throws**: ProductException, CustomerException

#### Update a Review

- **Endpoint**: PUT /api/customers/review/{id}
- **Description**: Updates a review for a product.
- **Request Body**: ReviewDto
- **Response**: ReviewDto
- **HTTP Status**: 202 (Accepted)
- **Throws**: CustomerException, ProductException, ReviewException

#### Delete a Review

- **Endpoint**: DELETE /api/customers/review/{id}
- **Description**: Deletes a review.
- **Response**: ReviewDto
- **HTTP Status**: 202 (Accepted)
- **Throws**: ReviewException

### OrderController

The `OrderController` class handles API endpoints related to orders.

#### Get All Orders of a Customer

- **Endpoint**: GET /api/orders/customer/{customerId}
- **Description**: Retrieves all orders of a specific customer.
- **Response**: List of OrderDto
- **HTTP Status**: 200 (OK)

#### Get Order by ID

- **Endpoint**: GET /api/orders/{orderId}
- **Description**: Retrieves an order by its ID.
- **Response**: OrderDto
- **HTTP Status**: 200 (OK)

#### Cancel an Order

- **Endpoint**: POST /api/orders/cancel/{orderId}
- **Description**: Cancels an order.
- **Response**: OrderDto
- **HTTP Status**: 200 (OK)

#### Create an Order

- **Endpoint**: POST /api/orders/create
- **Description**: Creates a new order.
- **Request Body**: OrderDto
- **Response**: OrderDto
- **HTTP Status**: 201 (Created)

#### Get All Orders of Logged-in Customer

- **Endpoint**: GET /api/orders/loggedin
- **Description**: Retrieves all orders of the currently logged-in customer.
- **Response**: List of OrderDto
- **HTTP Status**: 200 (OK)

### ProductController

The `ProductController` class handles API endpoints related to products.

#### Get All Products

- **Endpoint**: GET /api/products/
- **Description**: Retrieves all products.
- **Response**: List of ProductDto
- **HTTP Status**: 200 (OK)
- **Throws**: ProductException

#### Get Product by ID

- **Endpoint**: GET /api/products/{id}
- **Description**: Retrieves a product by its ID.
- **Response**: ProductDto
- **HTTP Status**: 200 (OK)
- **Throws**: ProductException


## ReturnRequestController

The `ReturnRequestController` class handles API endpoints related to return requests.

### Raise a Return Request

- **Endpoint**: POST /api/return-requests/
- **Description**: Raises a new return request.
- **Request Body**: ReturnRequestDto
- **Response**: ReturnRequestDto
- **HTTP Status**: 201 (Created)

### View Return Requests for Sellers

- **Endpoint**: GET /api/return-requests/seller
- **Description**: Retrieves return requests for sellers.
- **Response**: List of ReturnRequestDto
- **HTTP Status**: 200 (OK)

### View All Return Requests

- **Endpoint**: GET /api/return-requests/
- **Description**: Retrieves all return requests.
- **Query Parameters**:
  - pageSize: The number of return requests per page.
  - sortDirection: The sorting direction for return requests.
  - pageNo: The page number to retrieve.
- **Response**: Page of ReturnRequestDto
- **HTTP Status**: 200 (OK)

## SellerController

The `SellerController` class handles API endpoints related to sellers and products.

### Add a Product

- **Endpoint**: POST /api/sellers/products
- **Description**: Adds a new product.
- **Request Body**: ProductDto
- **Response**: ProductDto
- **HTTP Status**: 200 (OK)
- **Throws**: SellerException

### Update a Product

- **Endpoint**: PUT /api/sellers/products
- **Description**: Updates an existing product.
- **Request Body**: ProductDto
- **Response**: ProductDto
- **HTTP Status**: 202 (Accepted)
- **Throws**: ProductException, SellerException

### Delete a Product

- **Endpoint**: DELETE /api/sellers/products/{id}
- **Description**: Deletes a product by ID.
- **Response**: ProductDto
- **HTTP Status**: 200 (OK)
- **Throws**: ProductException, SellerException

## OrderItemController

The `OrderItemController` class handles API endpoints related to order items.

### Create an Order Item

- **Endpoint**: POST /api/order-items/
- **Description**: Creates a new order item.
- **Request Body**: OrderItemDto
- **Response**: OrderItemDto
- **HTTP Status**: 201 (Created)
- **Throws**: CustomerException, ProductException

### Get Order Item by ID

- **Endpoint**: GET /api/order-items/{orderItemId}
- **Description**: Retrieves an order item by its ID.
- **Response**: OrderItemDto
- **HTTP Status**: 200 (OK)

### Get Order Items by Product ID

- **Endpoint**: GET /api/order-items/product/{productId}
- **Description**: Retrieves order items by product ID.
- **Response**: List of OrderItemDto
- **HTTP Status**: 200 (OK)

### Get All Order Items by Order ID

- **Endpoint**: GET /api/order-items/order/{orderId}
- **Description**: Retrieves all order items associated with an order.
- **Response**: List of OrderItemDto
- **HTTP Status**: 200 (OK)

### Update an Order Item

- **Endpoint**: POST /api/order-items/update
- **Description**: Updates an existing order item.
- **Request Body**: OrderItemDto
- **Response**: OrderItemDto
- **HTTP Status**: 200 (OK)

### Cancel an Order Item

- **Endpoint**: POST /api/order-items/cancel/{orderItemId}
- **Description**: Cancels an order item by its ID.
- **Response**: OrderItemDto
- **HTTP Status**: 200 (OK)

### View Order Item Status

- **Endpoint**: GET /api/order-items/status/{orderItemId}
- **Description**: Retrieves the status of an order item.
- **Response**: OrderItemStatus
- **HTTP Status**: 200 (OK)

### Get All Order Items

- **Endpoint**: GET /api/order-items/
- **Description**: Retrieves all order items with pagination support.
- **Query Parameters**:
  - pageSize: The number of order items per page.
  - sortDirection: The sorting direction for order items.
  - pageNo: The page number to retrieve.
- **Response**: Page of OrderItemDto
- **HTTP Status**: 200 (OK)



