#!/bin/bash

echo "Starting Pulsar Manager..."
docker-compose -f pulsar-compose.yaml up -d
echo "Waiting for Pulsar Manager to start..."
# Wait for Pulsar Manager to be ready
until curl -s http://localhost:7750/pulsar-manager/ > /dev/null; do
    echo "Waiting for Pulsar Manager to be ready..."
    sleep 5
done    
echo "Pulsar Manager is ready."
echo "Creating superuser 'admin' with password 'apachepulsar'..."

CSRF_TOKEN=$(curl http://localhost:7750/pulsar-manager/csrf-token)
curl \
    -H "X-XSRF-TOKEN: $CSRF_TOKEN" \
    -H "Cookie: XSRF-TOKEN=$CSRF_TOKEN;" \
    -H 'Content-Type: application/json' \
    -X PUT http://localhost:7750/pulsar-manager/users/superuser \
    -d '{"name": "admin", "password": "apachepulsar", "description": "test", "email": "username@test.org"}'
