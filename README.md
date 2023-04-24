
# BIRD MDM Provider

This service provides an endpoint for the BIRD project that delivers course 
data from wlo and supplements it with additional information and maps it in BIRD format

## Docker
``` bash
docker run docker.edu-sharing.com/projects/wlo/bird-mdm-provider \
-e EDU_SHARING_USER=admin\
-e EDU_SHARING_PASSWORD=admin\
-e EDU_SHARING_BASEPATH=http://repository.127.0.0.1.nip.io:8100/edu-sharing/rest
```