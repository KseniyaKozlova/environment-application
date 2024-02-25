# Environment Application Readme

This Java SpringBoot application is designed to manage users, rewards, and coupons. Users can earn rewards, which can be used to purchase coupons.

## Installation

1. Clone the repository:
```bash
git clone https://github.com/your_repository.git
```

2. Navigate to the project directory:
```bash
cd your_project_directory
```

3. Build the project using Maven:
```bash
mvn clean package
```

4. Run the application:
```bash
java -jar target/your_application.jar
```

## Usage

### Users

- Users can be created, updated, and deleted.
- Each user has a unique identifier, name, email address, and bonus balance.

### Rewards

- Rewards can be earned by users and used to purchase coupons.
- Each reward has a unique identifier, name, value, and expiry date.

### Coupons

- Coupons can be purchased using rewards earned by users.
- Each coupon has a unique identifier, name, discount value, and expiry date.

## API Endpoints

- **GET /users**: Get all users
- **GET /users/{id}**: Get user by ID
- **POST /users**: Create a new user
- **PUT /users/{id}**: Update user by ID
- **DELETE /users/{id}**: Delete user by ID

- **GET /rewards**: Get all rewards
- **GET /rewards/{id}**: Get reward by ID
- **POST /rewards**: Create a new reward
- **PUT /rewards/{id}**: Update reward by ID
- **DELETE /rewards/{id}**: Delete reward by ID

- **GET /coupons**: Get all coupons
- **GET /coupons/{id}**: Get coupon by ID
- **POST /coupons**: Create a new coupon
- **PUT /coupons/{id}**: Update coupon by ID
- **DELETE /coupons/{id}**: Delete coupon by ID

## Technologies Used

- Java
- SpringBoot
- Maven