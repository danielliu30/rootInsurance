# rootInsurance

Starting the application will prompt for a text file. (ex. src\\main\\resources\\input.txt)

Approach was to abstract the creation of trips and drivers from the user. Used a utility class
to allow the application to interface a read functionality and a display result functionality.

Following TDD, I created the methods for managing the actual creation of the trip and drivers, which
provides another layer of abstraction and separates logic from each component

Drivers continously keep track of their trip totals to prevent constant recalculation of all trips
Trips precalculate speed to abstract the calculation from the service layer.
 
The design for this is to allow ease of switching from a console application to a web application.
The distinction between the models and services allow use to stub in a controller to allow requests
for creating trips and drivers
