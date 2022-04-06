## Description

This is the backend for Soccer online manager game API.

### API Documentation

API documentation is done using <b> Swagger </b>. Can be accessed using this
url `http://{domain:port}/swagger-ui.html` <br/>

For localhost using default settings, you can access documentation via this: `http://localhost:8080/swagger-ui.html`

### Concurrent data update/write consideration

Database `Version No` is used to handle concurrent situations. Backend just catches the exception thrown by DB layer and
shows useful messages.

### Validation & query optimization via JWT token

Jwt token stores `user_id` and `team_id` in claims. So, user don't need to pass these for getting user and team
information. As jwt token already hold this information and backend verfies the token, so no extra validation required
for these.

Also Query become simple & efficient as no need to join multiple tables.

### Other important considerations

1. API versioning enabled. All api paths stats with prefix `api/v1`. For newer version we can just change the prefix
2. For player age `Date of birth` is used, so that age can be automatically calculated in runtime.

### Initial data

For testing purpose backend is automatically creating 10 dummy users & other data necessary for these users(eg: team,
players). Also, players who have even id is also inserted int transfer table.


