## RUN DEV
./mvnw quarkus:dev or .\mvnw quarkus:dev

## RUN PROD
./mvnw quarkus:build or .\mvnw quarkus:build
java -jar ./target/quarkus-app/quarkus-run.jar

## Generate public and private key (OPTIONAL)
1. openssl genrsa -out rsaPrivateKey.pem 2048
2. openssl rsa -pubout -in rsaPrivateKey.pem -out publicKey.pem
3. openssl pkcs8 -topk8 -nocrypt -inform pem -in rsaPrivateKey.pem -outform pem -out privateKey.pem

## Sample CURL
1. Get token -> curl --location 'http://localhost:8080/token' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "bsatmoko@gmail.com",
    "roles": ["Admin", "User"],
    "birthdate": "09-12-1996"
}'
2. Upload CSV -> curl -X POST -H "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczovL2V4YW1wbGUuY29tL2lzc3VlciIsInVwbiI6ImJzYXRtb2tvQGdtYWlsLmNvbSIsImdyb3VwcyI6WyJVc2VyIiwiQWRtaW4iXSwiYmlydGhkYXRlIjoiMDktMTItMTk5NiIsImlhdCI6MTcwNzIyNDQxMywiZXhwIjoxNzA3MjI0NzEzLCJqdGkiOiI1Zjc1MGJmMS1jNGNkLTRlMDYtOGE1Mi05ZGJmYjFiOGUyOTQifQ.YJS1YgcGeRLNK61iiWGUx6HA5LG7NMHfPjKgqEMRfTdCaAuQbptvPGtBVELDQF_tXUnScL_lJakXpOpa6sRPOUgOBEv2w_xJuQvvACN8W0tofUhBjy0KnGh6BrQRpr_OvGDNQmcWiX5AtS26oCqsk7csW1_1xwKkOivzJbRv7k7J-Gj2qgKAjIBaIMNmYOdtHEAaIGzI-4kZoqn4ex5jC5qodl51F_3MNVnBce5_45jxyWg3rLiwkDk1VcI-3SCwbA8QnfKEOeK-urI8xkHx-dsQSsGIY5qeC_LwtPrZ_UlFO8w8FS84xCQXtEBD0XC-5v7jFqLM3cYGfviiniIJNA" -F "file=@test.csv" http://localhost:8080/upload/csv

## Quarkus REST
http://localhost:8080

## Kafka UI
http://localhost:8085