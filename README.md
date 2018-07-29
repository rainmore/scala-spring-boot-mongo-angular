# Centus

## Startup

Initial Gradle Wrapper
```
gradle wrapper
```

Setup Mongodb

```
// access to mongo
mongo

// create db user
use admin
db.createUser({user: "centus", pwd: "centus", roles:[{ role: "readWrite", db: "centus" }]})

db.auth('centus', 'centus')

// create db
use centus

```




## UI

### angular-6-registration-login-example

Angular 6 User Registration and Login Example with Webpack 4

Full tutorial with example available at http://jasonwatmore.com/post/2018/05/16/angular-6-user-registration-and-login-example-tutorial

https://github.com/cornflourblue/angular-6-registration-login-example