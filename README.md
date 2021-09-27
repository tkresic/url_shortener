# URL Shortener

HTTP service for account registration, URL shortening & statistic retrieval.

The service is deployed at <a href="https://t-u-s.herokuapp.com/">https://t-u-s.herokuapp.com. 

## Docs

Docs are generated at compile time and can be accessed at `target/dokka/index.html`. 

## Running the service

`./mvnw spring-boot:run`

## Examples

Examples below can naturally be done locally by running the service and replacing `https://t-u-s.herokuapp.com` with `http://localhost:8080`.

1. Create an account (provide `<ACCOUNT_ID>`)
```
curl --header "Content-Type: application/json" -X POST -d '{"AccountId":"<ACCOUNT_ID>"}' https://t-u-s.herokuapp.com/account
```

2. Generate basic authentication token for the created account (provide `<ACCOUNT_ID>` & `<PASSWORD>`)

```
echo -ne "<ACCOUNT_ID>:<PASSWORD>" | base64 --wrap 0
```

3. Use the token for URL registration (shortening) (provide `<TOKEN>` and replace `url` if wanted)

```
curl -H "Content-Type: application/json" -H "Authorization: Basic <TOKEN>" -X POST -d '{"url":"https://stackoverflow.com/questions/44472730/defining-a-variable-from-a-value-in-a-json-array-in-bash", "redirectType": 302}' https://t-u-s.herokuapp.com/register
```

4. Test the generated short URL (provide `<TOKEN>` & `<SHORT_URL>`)

```
curl -H "Authorization: Basic <TOKEN>" <SHORT_URL>
```

5. Retrieve the stats for the created account (provide `<TOKEN>` & `<ACCOUNT_ID>`)

```
curl -H "Authorization: Basic <TOKEN>" https://t-u-s.herokuapp.com/statistic/<ACCOUNT_ID>
```