def functions = load './deploy/commons/utils/functions.groovy'

env.APP_DOCKER_REGISTRY = functions.defineValue(env.APP_DOCKER_REGISTRY, 'gatewaydevhome.tigo.com.co:30083')
env.APP_IMAGE_BASE_NAME = functions.defineValue(env.APP_IMAGE_BASE_NAME, 'custom-krakend')
// Nombre del pipeline
env.PIPELINE_NAME = functions.defineValue(env.PIPELINE_NAME, env.JOB_BASE_NAME)
env.APP_CODE_PATH = functions.defineValue(env.APP_CODE_PATH, 'app')
env.DEPLOY_DIR = functions.defineValue(env.DEPLOY_DIR, './deploy-files')

// Configuración de comandos
if (!env.DOCKER_SKIP_CONFIG) {
    env.DOCKER_HOST = functions.defineValue(env.DOCKER_HOST, 'tcp://10.28.20.130:8182')
    env.DOCKER_TLS_VERIFY = functions.defineValue(env.DOCKER_TLS_VERIFY, 0)
    env.DOCKER_CERT_PATH = functions.defineValue(env.DOCKER_CERT_PATH, '/usr/local/bin/docker/certs')
}
env.DOCKER_COMMAND = functions.defineValue(env.DOCKER_COMMAND,
    'docker --tls=false --tlsverify=false -H tcp://10.28.20.130:8182')
env.KUBECTL_COMMAND = functions.defineValue(env.KUBECTL_COMMAND, '/usr/local/bin/kubectl')
env.KUBECONFIG = functions.defineValue(env.KUBECONFIG, '/usr/local/bin/Dev/config')
env.DOCKER_COMPOSE_COMMAND = functions.defineValue(env.DOCKER_COMPOSE_COMMAND, 'docker compose')

// Script paths
env.APP_SH_SCRIPTS_WAIT_PATH = functions.defineValue(env.APP_SH_SCRIPTS_WAIT_PATH,
    './commons/scripts/sh/wait.sh')
env.APP_SH_SCRIPTS_FUNCTIONS_PATH = functions.defineValue(env.APP_SH_SCRIPTS_FUNCTIONS_PATH,
    './commons/scripts/sh/functions.sh')
env.APP_SH_SCRIPTS_ENCRYPT_PATH = functions.defineValue(env.APP_SH_SCRIPTS_ENCRYPT_PATH,
    './commons/scripts/sh/encrypt.sh')

// Jasypt
env.JASYPT_CONFIG = functions.defineValue(env.JASYPT_CONFIG, 'saltGeneratorClassName=org.jasypt.salt.RandomSaltGenerator stringOutputType=base64 algorithm=PBEWITHHMACSHA512ANDAES_256 ivGeneratorClassName=org.jasypt.iv.RandomIvGenerator')
env.JASYPT_SCRIPT = functions.defineValue(env.JASYPT_SCRIPT, '/usr/bin/jasypt/bin/encrypt.sh')
env.JASYPT_DECRYPT = functions.defineValue(env.JASYPT_DECRYPT, '/usr/bin/jasypt/bin/decrypt.sh')
