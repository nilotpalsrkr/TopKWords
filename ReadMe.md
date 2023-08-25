# Top K Words

## Tech Stack and Illustration
- Java 17 (Used records and api response models)
- Springboot 3.0
- maven
- Top K words algorithm using heap (Priority Queue)
- redis cache
- Docker for spawning the redis cluster


## Start the application:
### Method - 1:  Download code from github, build the project, start the project with single docker-compose
1. Git clone
   ```bash
   git clone git@github.com:nilotpalsrkr/TopKWords.git
   ```
2. cd to the repository.
   ```bash
   cd TopKWords
   git checkout master # The latest code is present in master branchg and not in main.
   ```

3. build the TopKWords springboot application
    ```bash
   mvn clean install 
   ```
   Or, use intellij's maven plugin to build the application.
4. Run the redis cluster using - Docker Compose.
```bash
  docker-compose up -d # This step is required for caching the api response. This would run the Redis cluster.
```
5. Test the application -
   ** Postman **

Import the postman json, placed in the repository
```
src/resources/postman # The file to be tested would require to be updated in the postman collection
Two sample files are also located in test/resources directory.
```



##Steps to create docker image:
```bash
docker build -t nilotpals92/top-k-words:1.0 .
```
Steps to push the image to docker public repo -
```bash
docker push nilotpals92/top-k-words:1.0
```