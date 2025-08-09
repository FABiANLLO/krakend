def describePods(deployment, namespace, service) {
    sh 'chmod +x ./deploy/commons/sh/describe-pods.sh'
    sh './deploy/commons/sh/describe-pods.sh -d "' + deployment + '" -n "' + namespace + '" -s "' + service + '"'
}
def getDeploymentImage(deploymentName, namespace) {
    def image = sh(
        script: """
            $KUBECTL_COMMAND get deployment ${deploymentName} -n ${namespace} -o jsonpath='{.spec.template.spec.containers[*].image}'
        """,
        returnStdout: true
    ).trim()

    def imageName = image.tokenize('/').last()
    return imageName
}
def defineDeploymentImage(deploymentName, namespace) {
    env.APP_IMAGE_FULL_NAME = getDeploymentImage(deploymentName, namespace)
    echo "La imagen definida APP_IMAGE_FULL_NAME: $APP_IMAGE_FULL_NAME"
}

return this
