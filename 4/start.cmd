kubectl label namespace default --overwrite istio-injection=enabled 
kubectl apply -f k8s_v1
kubectl apply -f k8s_v2_retry
minikube tunnel
REM Send request to api/food/bad
REM Start test script
kubectl apply -f k8s_v3_circuit
minikube tunnel
REM Start test script