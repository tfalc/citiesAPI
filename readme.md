# Geolocation API

[TOC]

## Requirements

- Git
- Java 8
  - Spring Boot
    - Spring WEB
    - Spring Data JPA
    - PostgreSQL Driver
- Docker
- Heroku CLI

------

### Database

###### PostgresSQL

To run on docker:

```
docker run --name cities-db -d -p 5432:5432 -e POSTGRES_USER=postgres_user_city -e POSTGRES_PASSWORD=super_password -e POSTGRES_DB=cities postgres
```

### To populate DB

[PostreSQL files](https://github.com/chinnonsantos/sql-paises-estados-cidades/tree/master/PostgreSQL)

Change directory to where the files where unziped. 

<u>***Example: cd C:\CitiesData\\***</u>

Then populate using the commands below:

```
docker run -it --rm --net=host -v C:/CitiesData¹:/tmp postgres /bin/bash

psql -h localhost -U postgres_user_city cities -f /tmp/pais.sql
psql -h localhost -U postgres_user_city cities -f /tmp/estado.sql
psql -h localhost -U postgres_user_city cities -f /tmp/cidade.sql

psql -h localhost -U postgres_user_city cities
```

[^1]: In windows you should use the absolute path to the directory in order to make a pseudodir accessing the docker /tmp directory. When using Linux you could use "[...] -v $PWD:/tmp postgre /bin/bash" instead.

- [EarthDistance PostgreSQL documentation](https://www.postgresql.org/docs/current/earthdistance.html)
- [Earth Distance SQL to populate DB](https://github.com/postgres/postgres/blob/master/contrib/earthdistance/earthdistance--1.0--1.1.sql)
- [Operator <@>](https://github.com/postgres/postgres/blob/master/contrib/earthdistance/earthdistance--1.1.sql)
- [Postgres Cheat Sheet](https://postgrescheatsheet.com/#/tables)
- [Datatype Geometric](https://www.postgresql.org/docs/current/datatype-geometric.html)



##### Creating the extensions to use the Earth Distance feature:

```
CREATE EXTENSION cube; 
CREATE EXTENSION earthdistance;
```

##### Acessing the database

```
docker exec -it cities-db /bin/bash

psql -U postgres_user_city cities
```

##### Query for Earth distance

###### Point

```
select ((select lat_lon from cidade where id = 4929) <@> (select lat_lon from cidade where id=5254)) as distance;
```

###### Cube

```
select earth_distance(
    ll_to_earth(-21.95840072631836,-47.98820114135742), 
    ll_to_earth(-22.01740074157715,-47.88600158691406)
) as distance;
```

### Spring Boot

------

Make the project using: [Spring.io](https://start.spring.io/)

Choose to use:

- [x] Java 8
- [x] Gradle Project
- [x] Jar file

On dependencies:

- [x] Spring Web
- [x] Spring Data JPA
- [x] PostgreSQL Driver

#### Types

- [JsonTypes](https://github.com/vladmihalcea/hibernate-types)
- [UserType](https://docs.jboss.org/hibernate/orm/3.5/api/org/hibernate/usertype/UserType.html)

## Heroku

