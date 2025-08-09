def functions = load "./deploy/commons/utils/functions.groovy"

load "./deploy/commons/utils/envVars.groovy"

env.DB_DEPLOY_DIR = functions.defineValue(env.DB_DEPLOY_DIR, "./deploy-files")
