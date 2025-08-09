def createDeployFilesStage() {
    sh '''
        #!/bin/bash
        chmod +x deploy/sh/deploy-02-create-deploy-files.sh
        deploy/sh/deploy-02-create-deploy-files.sh
    '''
}

def deployStage() {
    sh '''
        #!/bin/bash
        chmod +x deploy/sh/deploy-03-deploy.sh
        deploy/sh/deploy-03-deploy.sh
    '''
}

def undeployStage() {
    sh '''
        #!/bin/bash
        chmod +x ./deploy/sh/undeploy.sh
        ./deploy/sh/undeploy.sh
    '''
}

def testStage() {
    sh '''
        #!/bin/bash
        chmod +x ./deploy/scripts/sh/test.sh
        ./deploy/scripts/sh/test.sh
    '''
}

return this