#!/bin/bash

if [ -d $DEPLOY_DIR ]; then
    rm -rf $DEPLOY_DIR
fi

mkdir -p $DEPLOY_DIR


# Node affinity para la aplicación
affinity=()

envsubst < "manifests/affinity-app.yaml" > $DEPLOY_DIR/affinity-app.yaml
affinity+=$(cat $DEPLOY_DIR/affinity-app.yaml)

export NODE_AFFINITY=$(IFS=; echo "${affinity[*]}")

# Creación deployment
envsubst < "manifests/deployment.yaml" > $DEPLOY_DIR/deployment.yaml

envsubst < "manifests/service-nodeport.yaml" > $DEPLOY_DIR/service-nodeport.yaml
envsubst < "manifests/service.yaml" > $DEPLOY_DIR/service.yaml


echo "El deployment resultante"
cat $DEPLOY_DIR/deployment.yaml
