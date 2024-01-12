# CSCB869 Java Web Services

## Assignment

The vehicle service project aims to streamline the management of service details for garages offering vehicle repairs and maintenance services. These service stations provide a range of services, such as chassis work, engine maintenance, brake system checks, and consumables replacement. To ensure quality service, qualified employees are essential, and some workshops may specialize in specific vehicle brands.

Clients book appointments in advance, selecting their preferred workshop for servicing. Essential vehicle information, including registration number, make, model, and year of manufacture, is stored. During each visit, records are maintained on the serviced car and the corresponding payment.

Within the service station, employees have access to a comprehensive vehicle service history, offering insights into individual vehicles and summarizing services by category, brand, and year of manufacture. Customers also have the convenience of viewing the complete service history of their vehicles across all service stations.

## Technologies: 
Java(Spring Boot), JS, Thymeleaf and mySQL.

## Roles:
### - Admin:
  + CRUD type of service
  + CRUD type of qualification
  + CRUD employee
  + CRUD car service
  + Manage car service (add/remove services from it's pricelist)
### - Employee:
  + View -> Accept reservations(made from users)
  + CRUD repair
  + Complete repairs
  + View statistics about the car service in which he is working
### - Customer:
  + CRUD vehicles
  + search car services based on required brand and service
  + make reservation

## How to use:
1. Clone the repository.
2. Configure the database settings in the application properties file.
3. Build and run the application using Spring Boot.
4. Access the web application through the provided URL.
5. Use admin/admin to login and create DB from scratch.
