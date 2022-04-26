#!/usr/bin/env bash

CAMUNDA_API_PATH="http://localhost:8080/engine-rest/"

deployment_list_json=$(curl -s $CAMUNDA_API_PATH"deployment")

echo ${deployment_list_json}
for deployment_id in $(echo ${deployment_list_json} | sort -r | jq -r '.[].id'); do
curl -sX DELETE ${CAMUNDA_API_PATH}"deployment/"${deployment_id}"?cascade=true"
if [[ $(echo $?) -eq 0 ]]
then
echo "Deployment with id: "$deployment_id" and its instances were successfully deleted"
else
echo "Deployment with id: ""$deployment_id"" and its instances failed to be deleted"
fi
done
