## Bier Geek

The application connects to an external API which gives information about biers. There are currently 3 features the project provides:
* Get a random bier
* Search a specific bear by name
* Save a bier to your session

The DB is generated at runtime based on the Bier.java entity and then populated with users and authorities using the data.sql file. This resets each time the application is launched.

### BASE URL
http://localhost:8080

### User and password
* user 
* 12345 

### Download and install the application
* Clone/Download the project
* Run "mvn spring-boot:run" in the project directory 

### Reported bug
The save button does not work correctly from the "Search" page. Steps to reproduce:
* Open the application and log in
* Navigate to the "Search" page
* Search a bier such as "IPA"
* Attempt to save one of the  results
* Error page appears

### Requested features


1. Currently we only save the name, tagline and abv of a bier. The "description" field should also be saved in the DB
    * The description column should be able to hold up to 1024 characters
    * The description column is optional
    
2. Implement a new endpoint that will search and return a JSON representation of a list of Bier objects that have the alcohol by volume(abv) between a "min" and "max" parameters.
    * Only the min parameter is mandatory. If max is not specified then return all biers with abv > min
    * The user interaction with the endpoint is not important, only the backend code (we can test by editing the URL in the browser manually)


