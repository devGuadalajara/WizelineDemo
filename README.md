# Wizeline Academy: QA Automation Bootcamp
### Technical Selection Challenge:  Automation - Front End Testing

# [Tests]:
- A) Login with a valid user ✔️
Expected: Validate the user navigates to the products page when logged in.
- B) Login with an invalid user ️✔️
Expected: Validate error message is displayed.
- C) Logout from the home page ✔️
Expected: Validate the user navigates to the login page.
- D) Sort products by Price (low to high) ✔️
Expected: Validate the products have been sorted by price correctly
- E) Add multiple items to the shopping cart ✔️
Expected: Validate all the items that have been added to the shopping cart.
- F) Add the specific product ‘Sauce Labs Onesie’ to the shopping cart ✔️
Expected: Validate the correct product was added to the cart.
- G) Complete a purchase ✔️
Expected: Validate the user navigates to the order confirmation page.


## Technologies used
- Java
- JUnit
- Maven
- Maven-site-plugin
- Maven-surefire-plugin

# Run tests & reports
```sh
mvn clean test site
```

# Chrome version 94.0.4606.81

# Reports location
```sh
\target\site\index.html
\target\site\surefire-report.html
```
