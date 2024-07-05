node{
    stage('Scm Checkout'){    <git>
        git 'https://github.com/AwsAravind001/my-app.git'
    }
    stage('Mvn Package'){    <shell script>
        sh 'mvn clean package'
    }
    stage('Build Docker Image'){    <shell script>
        sh 'docker build -t devopsaravind/my-app:2.0.0 .'
    }
    stage('Push Docker Image'){   <withbind credentials select secret text> kind <secret text>
        withCredentials([string(credentialsId: 'docker-password', variable: 'DockerHubPwd')]) {
          sh "docker login -u devopsaravind -p ${DockerHubPwd}"
        }
        sh 'docker push devopsaravind/my-app:2.0.0'
    }
    stage('Run Container on Dev Server'){
      def dockerRun = 'docker run -p 80:8080 -d --name my-app devopsaravind/my-app:2.0.0'
      sshagent(['dev-server']) {
        sh "ssh -o StrictHostKeyChecking=no ec2-user@172.31.27.79 ${dockerRun}"
       }
    }
}

sudo usermod -a -G docker jenkins
sudo service jenkins restart
with credentials bind credentials to variables  --->secret text --->dockerHubPwd
Install sshAgentPlugin
sudo chmod 666 /var/run/docker.sock:wq!
