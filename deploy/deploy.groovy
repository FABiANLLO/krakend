load "./deploy/utils/envVars.groovy"
def customDeployStages = load "./deploy/stages/customDeploy.groovy"
def buildImageStages = load "./deploy/commons/stages/buildImage.groovy"
def kubeStages = load "./deploy/commons/stages/kube.groovy"
def gitStages = load "./deploy/commons/stages/gitStages.groovy"

try {

    stage('Initialize') {
        gitStages.calculateCommitHash()
        buildImageStages.loadEnvVars()
        sh '''
        #!/bin/bash
        chmod +x deploy/sh/deploy-01-environment.sh
        deploy/sh/deploy-01-environment.sh
        '''  
    }

    stage('Build Docker Image') {
        buildImageStages.buildStage(env.APP_IMAGE_FULL_NAME, "Dockerfile", ".")
    }

    stage('Push Docker Image') {
        buildImageStages.pushStage(env.APP_IMAGE_FULL_NAME)
    }

    stage('Create deploy files') {
        customDeployStages.createDeployFilesStage()
    }


    stage('Deploy to Kubernetes') {
        customDeployStages.deployStage()
    }

    stage('Describe pods') {
        kubeStages.describePods("$APP_PROJECT_NAME-deployment", env.APP_KUBE_NAMESPACE, "$APP_PROJECT_NAME-loadbalancer")
    }

    stage('Publish Report') {
        sh '''
        #!/bin/bash
        chmod +x deploy/sh/generate-report.sh
        deploy/sh/generate-report.sh
        '''        
        publishHTML(target: [
            reportName: "Reporte ${APP_KUBE_NAMESPACE}",
            reportDir: '.',
            reportFiles: "$DEPLOY_DIR/report.html",
            keepAll: true,
            alwaysLinkToLastBuild: true,
            allowMissing: false
        ])
    }

} catch (Exception e) {
    echo "Error: ${e.message}"
    currentBuild.result = 'FAILURE'
    throw e
} finally {
    echo 'Finish!'
}