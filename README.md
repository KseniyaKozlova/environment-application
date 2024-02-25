# Environment Application Readme

This Java SpringBoot application is designed to manage users, tares, companies and coupons. Users can earn bonuses when they deposit tare in postamat. Each tare category has own cost (bonuses to accounting). User can use their bonuses to purchase coupons. Coupon can give user a possibility to have discount in different companies.

## Installation

1. Clone the repository:
```bash
git clone https://github.com/KseniyaKozlova/environment-application.git
```

## Usage

### Users

- Users can be created, updated and deleted.
- Each user has a unique identifier, login and password for personal account, name, role(default is consumer) and bonus balance.

### Tares

- Rewards can be deposited by user and deleted.
- Each tare has a unique identifier, category, volume in litres and bonuses to accounting.

### Coupons

- Coupons can be created by company, purchased (using bonuses earned by users), returned (if they become invalid) and used by user in company.
- Each coupon has a unique identifier, description (how you can use this coupon), cost and company (coupon owner company).

### Companies

- Company can be created, updated and deleted.
- Each coupon has a unique identifier, description (what possibilities can give this coupon), cost and company (coupon owner company).

## API Endpoints

- **GET /user/{id}**: Get user by ID
- **GET /user/{login}**: Get user by login
- **POST /user**: Create new user
- **PUT /user/{id}**: Update user by ID
- **DELETE /user/{id}**: Delete user by ID


- **GET /tares**: Get all tares
- **GET /tare/{id}**: Get tare by ID
- **POST /tare**: Create new tare
- **PUT /tare/{id}**: Update tare by ID
- **DELETE /tare/{id}**: Delete tare by ID


- **GET /coupons**: Get all coupons
- **GET /coupon/{id}**: Get coupon by ID
- **POST /coupon**: Create new coupon
- **PUT /coupon/{id}**: Update coupon by ID
- **DELETE /coupon/{id}**: Delete coupon by ID


- **GET /companies**: Get all coupons
- **GET /company/{id}**: Get company by ID
- **POST /company**: Create new company
- **PUT /company/{id}**: Update company by ID
- **DELETE /company/{id}**: Delete company by ID


- **POST /user/{userId}/coupon/{couponId}**: User buy coupon by ID
- **POST /user/{userId}/coupon/{couponId}/without_bonuses_back**: User use coupon by ID
- **POST /user/{userId}/coupon/{couponId}/with_bonuses_back**: User return coupon by ID

## Technologies Used

- Java 17
- SpringBoot
- PostgreSQL
- Maven

## Remote API

- [Addresses](https://github.com/KseniyaKozlova/addresses) - see company addresses
- [Bonuses](https://github.com/KseniyaKozlova/bonuses-count) - get bonuses count by tare category to accounting
