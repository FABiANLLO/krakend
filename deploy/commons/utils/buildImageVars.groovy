def functions = load './deploy/commons/utils/functions.groovy'

env.APP_PREFIX_BUILD_LENGTH = functions.defineValue(env.APP_PREFIX_BUILD_LENGTH, '5')
env.BUILD_NUMBER_WITH_PREFIX = functions.padNumber(env.BUILD_NUMBER, env.APP_PREFIX_BUILD_LENGTH)

def defineImageByVersion() {
    echo 'defineImageByVersion'

    env.APP_IMAGE_FULL_NAME = "$APP_IMAGE_BASE_NAME:$APP_VERSION"
    if (env.APP_VERSION_WITH_BUILD_NUMBER) {
        env.APP_IMAGE_FULL_NAME += "-$BUILD_NUMBER_WITH_PREFIX"
    }
    if (env.APP_VERSION_WITH_GIT_HASH) {
        env.APP_IMAGE_FULL_NAME += "-$GIT_COMMIT_HASH"
    }
    if (env.APP_SOURCE_IMAGE) {
        env.APP_SOURCE_IMAGE_FULL_NAME = "$APP_PROJECT_NAME:$APP_SOURCE_IMAGE"
        echo "Imagen fuente: $APP_SOURCE_IMAGE_FULL_NAME, imagen destino: $APP_IMAGE_FULL_NAME"
    }
    env.APP_IMAGE = "${APP_DOCKER_REGISTRY}/${APP_IMAGE_FULL_NAME}"
}

echo 'Definición de variables para la contrucción de la imagen'

defineImageByVersion()

echo "Nombre calculado de la imagen: $APP_IMAGE_FULL_NAME"
