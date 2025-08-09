#!/bin/bash

if [ -d $DEPLOY_DIR ]; then
    rm -rf $DEPLOY_DIR
fi

mkdir -p $DEPLOY_DIR

$KUBECTL_COMMAND delete -f $DEPLOY_DIR/service.yaml -n "$APP_KUBE_NAMESPACE"
$KUBECTL_COMMAND delete -f $DEPLOY_DIR/service-nodeport.yaml -n "$APP_KUBE_NAMESPACE"
$KUBECTL_COMMAND delete -f $DEPLOY_DIR/deployment.yaml -n "$APP_KUBE_NAMESPACE"
