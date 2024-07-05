Stop EC2 Instances
------------------------------

import boto3
region = 'ap-northeast-1'
instances = ['i-09ac7b3314b8ea857']
ec2 = boto3.client('ec2' , region_name=region)

def lambda_handler(event, context):
    ec2.stop_instances(InstanceIds=instances)
    print('stopped your instances: ' + str(instances))


Start EC2 Instances
-----------------------------------

import boto3
region = 'ap-northeast-1'
instances = ['i-09ac7b3314b8ea857']
ec2 = boto3.client('ec2' , region_name=region)

def lambda_handler(event, context):
    ec2.start_instances(InstanceIds=instances)
    print('started your instances: ' + str(instances))
