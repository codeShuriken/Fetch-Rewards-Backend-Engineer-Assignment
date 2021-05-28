# Fetch-Rewards-Backend-Engineer-Assignment
Fetch Rewards take home assignment for backend engineer and backend engineer intern. This assignment requires us to:

 * Add transactions for a specific payer and date.
 * Spend points using the rules above and return a list of { "payer": <string>, "points": <integer> } for each call.
 * Return all payer point balances.

### Prerequisites
* Install Java 11 or higher version

### Running the Application

#### Windows
* Download this project.
* From inside the root directory of the project, run the command:

    ``` ./mvnw clean spring-boot:run ```

### Testing the REST API

* ADD POINTS
   
``` POST /api/v1/points/addPoints ```
   curl -X POST localhost:8080/employees -H 'Content-type:application/json' -d '{"name": "Samwise Gamgee", "role": "gardener"}'
Ex: ``` curl -X POST localhost:8080/api/v1/points/addPoints -H 'Content-Type: application/json' -d '{"payer": "DANNON", "points": 1000, "timestamp": "2020-11-02T14:00:00Z"}' ```

* SPEND POINTS
   
``` POST /api/v1/points/spendPoints ```
   
Ex: ``` curl -X POST localhost:8080/api/v1/points/spendPoints -H 'Content-Type: application/json' -d  '{"points": 1000}' ```

* GET BALANCE
   
``` GET /api/v1/points/payersBalance ```
   
Ex: ```  curl -v localhost:8080/api/v1/points/payersBalance ```

