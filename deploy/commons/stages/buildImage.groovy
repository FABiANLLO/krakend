
def loadEnvVars() {
    load './deploy/commons/utils/buildImageVars.groovy'
}

def buildStage(imageFullName, dockerFile, dockerContext) {
    echo "Building image: ${imageFullName}"
    sh "${DOCKER_COMMAND} images | grep devopsfaith/krakend"
    sh "${DOCKER_COMMAND} build -t $imageFullName -f $dockerFile $dockerContext"
    echo 'Build completed successfully'
}

def pushStage(imageFullName) {
    echo "Pushing image: ${APP_DOCKER_REGISTRY}/${imageFullName}"
    sh "${DOCKER_COMMAND} tag $imageFullName ${APP_DOCKER_REGISTRY}/$imageFullName"
    sh "${DOCKER_COMMAND} push ${APP_DOCKER_REGISTRY}/$imageFullName"
}

def tagPromotionImage() {
    sh "${DOCKER_COMMAND} pull ${APP_DOCKER_REGISTRY}/$APP_SOURCE_IMAGE_FULL_NAME"
    sh "${DOCKER_COMMAND} tag ${APP_DOCKER_REGISTRY}/$APP_SOURCE_IMAGE_FULL_NAME ${APP_DOCKER_REGISTRY}/$APP_IMAGE_FULL_NAME"
    sh "${DOCKER_COMMAND} push ${APP_DOCKER_REGISTRY}/$APP_IMAGE_FULL_NAME"
}

return this
