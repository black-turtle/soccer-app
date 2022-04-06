# Overview

This software is a web platform where users can buy/sell soccer players. 

Upon register user gets a team with 5 million budget and 20 players. User can add his team's player in the selling/transfer list. Also he/she can buy players from the transfer list if his/her team has enough budget. Player value is increased randomly by system upon successful transaction.

For More details please check the `REQUIREMENT.md` file

# Technical Specification
1. UI is built with ReactJS
2. For styling Tailwind css is used
3. Backend created with Java Spring boot microservice (On OpenJdk 8)
4. For deploying docker & docker-compose is used.


For more details check the `frontend/README.md` and `backend/README.md`

# How to Setup
## setup using docker
First, get [Docker](https://docs.docker.com/get-docker/) in your machine. 

Then just run `docker compose up` from root folder to start both front-end and backend server. Front end will run on port 3000 and backend will run in port 8080 by default. you can change ports from .env file.

After that you should be able to access soccer game app using this url: http://localhost:3000

To stop servers just run `docker compose down`

## Manual setup
### Start backend
1. First go to backend folder and build the project using maven. Use this command `mvn clean package` to build.
2. Then go to target folder and run the jar using this command `java -jar soccer-1.0.0-SNAPSHOT.jar`

### Start frontend
1. Go to front end folder. Then first run `npm install` to install all dependencies.
2. After that run `npm start` to start the frontend server on port 3000.

After that you should be able to access soccer game app using this url: http://localhost:3000


## Currently supported operations
1. Login
2. SignUp
3. View and edit team information
4. View and edit player information
5. Add player to selling/transfer list
6. View Transfer list
7. Buy player from selling/transfer list


## Operations for future considerations
1. User email verfication
2. A Player database to choose player from
3. Search & filter in transfer list page