minikube start
kubectl apply -f k8s/mysql
kubectl apply -f k8s/food-service
kubectl apply -f k8s/client
kubectl apply -f k8s/user-service
minikube tunnel