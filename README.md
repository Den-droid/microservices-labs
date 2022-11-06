# Microservices labs repository

## Microservices Lab 2: Database
### Script for launching is located in "2" directory and called "start.cmd":
```CMD
minikube start
kubectl apply -f k8s/mysql
kubectl apply -f k8s/client
TIMEOUT /t 120
kubectl apply -f k8s/food-service
kubectl apply -f k8s/user-service
TIMEOUT /t 60
minikube tunnel
```

### API Endpoints
1. [User Interface](http://localhost:80/)
2. Users:
   * [Add User (POST)](http://localhost:80/api/users)
   * [Edit User (PUT)](http://localhost:80/api/users/1)
   * [Delete User (DELETE)](http://localhost:80/api/users/1)
   * [Get users' ids (GET)](http://localhost:80/api/users/ids)
   * [Get user by id (GET)](http://localhost:80/api/users/1)
3. Food:
   * [Add food (POST)](http://localhost:80/api/food)
   * [Edit food (PUT)](http://localhost:80/api/food/1)
   * [Delete food (DELETE)](http://localhost:80/api/food/1)
   * [Get food ids (GET)](http://localhost:80/api/food/ids)
   * [Get food by id (GET)](http://localhost:80/api/food/1)

### Additional information

For getting info about specific object you need to add at least 2 examples of these object.
You can remove first timeout from script if you already have mysql:8.0.28 (second timeout is for ingress configuring)


## Microservices Lab 3: Helm

We have got here the same services, we've just changed the way they are deploying, particularly, through HELM - packet manager for Cubernetes.
### Script for launching (you are in "3" folder): 
```CMD
helm install helm-demo helm
```

### API Endpoints
Look for the same title in Microservices Lab 2: Database 

### Additional information
Look for the same title in Microservices Lab 2: Database 