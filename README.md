# Microservices labs repository

## Microservices Lab 2: Database
### Script for launching is located in "2" directory and called "script.cmd":
```CMD
minikube start
kubectl apply -f k8s/mysql
kubectl apply -f k8s/food-service
kubectl apply -f k8s/client
kubectl apply -f k8s/user-service
minikube tunnel
```
You can remove minikube start if you have already started your cluster.

### API Endpoints
1. [User Interface](http://localhost:80/)
2. Users (CRUD is implemented here and MYSQL db is used here):
   * [Add User (POST)](http://localhost:80/api/users)
   * [Edit User (PUT)](http://localhost:80/api/users/1)
   * [Delete User (DELETE)](http://localhost:80/api/users/1)
   * [Get users' ids (GET)](http://localhost:80/api/users/ids)
   * [Get user by id (GET)](http://localhost:80/api/users/1)
3. Food:
   * [Get users' ids (GET)](http://localhost:80/api/food/ids)
   * [Get user by id (GET)](http://localhost:80/api/food/1)


### Additional information

For getting info about specific user you need to add at least 2 users.