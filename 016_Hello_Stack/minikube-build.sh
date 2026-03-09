#!/usr/bin/env bash

eval "$(minikube docker-env)"
#set -euo pipefail

cd backend-quarkus
docker build -t quarkus-hello:java25 .
cd ..

cd frontend-angular
docker build -t angular-hello:ng21 .
