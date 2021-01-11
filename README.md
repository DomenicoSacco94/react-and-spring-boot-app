INSTRUCTIONS:

The project is deployed through 3 containers:

- postgres-db (the chosen database)
- message-api (the spring backend, using the embedded tomcat server)
- webapp (with the React frontend, generated with create-react-app)

To build the required docker containers, use the following commands in the **message-backend** directory:

**docker-compose build**

Start docker Hiking API containers (must be in project working directory):

**docker-compose up**

Stop the containers:

**docker-compose down**

Remove containers for instance (in case a total reset a is needed):

**docker-compose rm ${docker ps -a}**

