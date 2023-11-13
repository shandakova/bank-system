Как можно производить проверку


#### Как можно проверить работу:

После запуска сваггер доступен по ссылке:
`http://localhost:8080/swagger-ui.html`

1. Создать банковский аккаунт

Выполнить POST http://localhost:8080/accounts/new
Получить в ответ (здесь нам понадобится uuid):

`
{
 "value": {
   "uuid": "96761cfa-ef84-48a2-ae2e-e557217485ae",
   "balance": 0
 },
 "success": true,
 "message": null
}
`

2. Загрузить в базу список курсов валют

Выполнить POST http://localhost:8080/currency/upload

Получить ответ:
`
{
  "value": null,
  "success": true,
  "message": null
}
`
3. Можно проверить загруженное содержимое

Выполнить GET http://localhost:8080/currency

Получить ответ:
`
{
  "value": [
    {
      "name": "USD",
      "rate": 92.0535
    },
    {
      "name": "EUR",
      "rate": 98.3155
    }
  ],
  "success": true,
  "message": null
}
`

4. Далее выполнить, например, операцию пополнения аккаунта

Выполнить POST http://localhost:8080/operation/new

В аккаунт подставляем полученный ранее uuid.

`
{
  "account": "96761cfa-ef84-48a2-ae2e-e557217485ae",
  "type": "DEPOSIT",
  "description": "Тестовое пополнение",
  "amount": 1000
}
`

5. Получить список операций в валюте за этот год

Выполнить POST http://localhost:8080/operation/search

`
{
  "filter": {
    "currency": "USD",
    "start": "2023-01-01T00:00:00.000Z",
    "end": "2023-12-31T00:00:00.000Z"
  },
  "page": 0,
  "size": 10
}
`

Получить ответ:

`
{
  "value": [
    {
      "account": "96761cfa-ef84-48a2-ae2e-e557217485ae",
      "type": "DEPOSIT",
      "description": "Тестовое пополнение",
      "amount": 10.86,
      "id": 1,
      "dateTime": "2023-11-13T12:36:34.819212142",
      "currency": "USD"
    }
  ],
  "success": true,
  "message": null,
  "page": 0,
  "totalPage": 1,
  "size": 0
}
`
