def functions = load './deploy/commons/utils/functions.groovy'

env.APP_DOCKER_REGISTRY = functions.defineValue(env.APP_DOCKER_REGISTRY, 'registry.tigo.com.co:5000')
env.DOCKER_HOST = functions.defineValue(env.DOCKER_HOST, 'tcp://10.28.20.78:8182')
env.DOCKER_SKIP_CONFIG = 1
env.DOCKER_COMMAND = functions.defineValue(env.DOCKER_COMMAND, 'docker')
env.KUBECONFIG = functions.defineValue(env.KUBECONFIG, '/usr/local/bin/QA/kubeconfig')
