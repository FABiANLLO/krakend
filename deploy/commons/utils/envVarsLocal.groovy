def functions = load './deploy/commons/utils/functions.groovy'

env.APP_DOCKER_REGISTRY = functions.defineValue(env.APP_DOCKER_REGISTRY, 'registry.localhome:5000')
env.DOCKER_SKIP_CONFIG = 1
env.DOCKER_COMMAND = functions.defineValue(env.DOCKER_COMMAND, 'docker')
env.KUBECONFIG = functions.defineValue(env.KUBECONFIG, '/var/jenkins_home/.kube/config')
env.DOCKER_COMPOSE_COMMAND = functions.defineValue(env.KUBECONFIG, 'docker-compose')

env.DOCKER_COMMAND = 'docker'
env.KUBECTL_COMMAND = 'kubectl --insecure-skip-tls-verify=true'
env.DOCKER_SKIP_CONFIG = 1

sh 'kubectl config use-context k3d-cream-cluster'
