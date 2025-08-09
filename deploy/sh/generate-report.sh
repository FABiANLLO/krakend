#!/bin/bash

envsubst < "deploy/html/report.html" > "$DEPLOY_DIR/report.html"
