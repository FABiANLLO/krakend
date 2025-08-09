def functions = load './deploy/commons/utils/functions.groovy'

env.APP_DOCKER_REGISTRY = functions.defineValue(env.APP_DOCKER_REGISTRY, 'registry.tigo.com.co:5000')
env.DOCKER_HOST = functions.defineValue(env.DOCKER_HOST, 'tcp://10.63.19.222:8182')
env.DOCKER_SKIP_CONFIG = 1
env.DOCKER_COMMAND = functions.defineValue(env.DOCKER_COMMAND, 'docker')
env.KUBECONFIG = functions.defineValue(env.KUBECONFIG, '/usr/local/bin/PDN/kubeconfig')

env.APP_APPROVED_USER_ID = functions.defineValue(env.APP_APPROVED_USER_ID, 'pmurillm')
env.APP_APPROVED_USER_EMAIL = functions.defineValue(env.APP_APPROVED_USER_EMAIL, 'psmurillom@indracompany.com')
