#!/bin/bash

$KUBECTL_COMMAND apply -f $DEPLOY_DIR/deployment.yaml -n "$APP_KUBE_NAMESPACE"

$KUBECTL_COMMAND apply -f $DEPLOY_DIR/service.yaml -n "$APP_KUBE_NAMESPACE"
$KUBECTL_COMMAND apply -f $DEPLOY_DIR/service-nodeport.yaml -n "$APP_KUBE_NAMESPACE"