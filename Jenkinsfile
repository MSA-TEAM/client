node {

    stage ('소스체크아웃') {
        checkout([$class: 'GitSCM',
            branches: [[name: '*/master']],
            doGenerateSubmoduleConfigurations: false, extensions: [],
            submoduleCfg: [],
            userRemoteConfigs: [[credentialsId: 'DevPro', url: 'https://devpro.ktds.co.kr/msa/client.git']]])
    }

    stage ('빌드') {
        sh './gradlew clean build'
    }

    stage ('스크립팅'){
        parallel client1: {
            sh 'ssh ec2-user@ip-172-31-14-215 "mkdir -p /home/ec2-user/client/log"'
            sh 'scp ./start.sh ec2-user@ip-172-31-14-215:/home/ec2-user/client'
            sh 'scp ./shutdown.sh ec2-user@ip-172-31-14-215:/home/ec2-user/client'
            sh 'ssh ec2-user@ip-172-31-14-215 "chmod a+x /home/ec2-user/client/*.sh"'
        },
        client2: {
            sh 'ssh ec2-user@ip-172-31-0-235 "mkdir -p /home/ec2-user/client/log"'
            sh 'scp ./start.sh ec2-user@ip-172-31-0-235:/home/ec2-user/client'
            sh 'scp ./shutdown.sh ec2-user@ip-172-31-0-235:/home/ec2-user/client'
            sh 'ssh ec2-user@ip-172-31-0-235 "chmod a+x /home/ec2-user/client/*.sh"'
        }

    }

    stage ('서버 중지'){
        parallel client1: {
            sh 'ssh ec2-user@ip-172-31-14-215 "/home/ec2-user/client/shutdown.sh || true"'
        },
        client2: {
            sh 'ssh ec2-user@ip-172-31-0-235 "/home/ec2-user/client/shutdown.sh || true"'
        }

    }

    stage ('배포') {
        parallel client1: {
            sh 'scp build/libs/client-1.0.0-RELEASE.jar ec2-user@ip-172-31-14-215:/home/ec2-user/client'
        },
        client2: {
            sh 'scp build/libs/client-1.0.0-RELEASE.jar ec2-user@ip-172-31-0-235:/home/ec2-user/client'
        }
    }

    stage ('서버 시작') {
        parallel client1: {
            sh 'ssh ec2-user@ip-172-31-14-215 "/home/ec2-user/client/start.sh /home/ec2-user/client/client-1.0.0-RELEASE.jar prd"'
        },
        client2: {
            sh 'ssh ec2-user@ip-172-31-0-235 "/home/ec2-user/client/start.sh /home/ec2-user/client/client-1.0.0-RELEASE.jar prd"'
        }
    }

}