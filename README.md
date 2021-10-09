# gRPC Hello World Demo
A simple gRPC Spring Boot application to demo the functionality
of gRPC and Spring Boot. This application also leverages the
gRPC Spring Boot Starter: https://github.com/LogNet/grpc-spring-boot-starter.

## Usage

### Building
`./gradlew build`

### Running Locally
`./gradlew :grpc-helloworld-server:bootRun`

### Build/Run Container Using Docker
`cd grpc-helloworld-server`

`docker build -t grpc-demo .`

`docker run -p 5000:5000 grpc-demo`

### Build/Run Container Using Lima/Nerdctl 
https://github.com/lima-vm/lima

`limactl start`

`cd grpc-helloworld-server`

`lima nerdctl build -t grpc-demo .`

`lima nerdctl run -p 127.0.0.1:5000:5000 grpc-demo`