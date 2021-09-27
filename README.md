# URL shortener

HTTP service for account registration, URL shortening & statistic retrieval.

The app is deployed to <a href="https://t-u-s.herokuapp.com/">https://t-u-s.herokuapp.com and examples below can be done with the deployed app to instead of running it locally. 

### 1. Run the app

`./mvnw spring-boot:run`

## Examples

Create an account (provide `<ACCOUNT_ID>`)
```
curl --header "Content-Type: application/json" -X POST -d '{"AccountId":"<ACCOUNT_ID>"}' http://localhost:8080/account
```

Generate basic authentication token for the created account (provide `<ACCOUNT_ID>` & `<PASSWORD>`)

```
echo -ne "<ACCOUNT_ID>:<PASSWORD>" | base64 --wrap 0
```

Use the token for URL registration (shortening) (provide `<TOKEN>` and replace `url` if wanted)

```
curl -H "Content-Type: application/json" -H "Authorization: Basic <TOKEN>" -X POST -d '{"url":"https://stackoverflow.com/questions/44472730/defining-a-variable-from-a-value-in-a-json-array-in-bash", "redirectType": 302}' http://localhost:8080/register
```

Test the generated short URL (provide `<TOKEN>` & `<SHORT_URL>`)

```
curl -H "Authorization: Basic <TOKEN>" <SHORT_URL>
```

Retrieve the stats for the created account (provide `<TOKEN>` & `<ACCOUNT_ID>`)

```
curl -H "Authorization: Basic <TOKEN>" http://localhost:8080/statistic/<ACCOUNT_ID>
```