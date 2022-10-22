REM minikube start
kubectl apply -f k8s/mysql
kubectl apply -f k8s/client
TIMEOUT /t 120
kubectl apply -f k8s/food-service
kubectl apply -f k8s/user-service
TIMEOUT /t 60
minikube tunnel