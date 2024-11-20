# TaskManagementSystem

## Развёртка проекта

### Описание application.properties по настройки базы данных

| Ключ поля                                        | Описание                                                                       | Обязательность |
|--------------------------------------------------|--------------------------------------------------------------------------------|----------------|
| spring.datasource.url                            | Заполняется адрес БД используемой приложением                                  | Обязателен     |
| spring.datasource.username                       | Заполняет логин авторизации БД используемой приложением                        | Обязателен     |
| spring.datasource.password                       | Заполняет пароль авторизации БД используемой приложением                       | Обязателен     |
### Инструкция по развёртке

1) Клонировать этот репозиторий
2) Создать локальную БД Postgres SQL (Название БД task_management 
или создать свою и поменять в [application.properties](src%2Fmain%2Fresources%2Fapplication.properties) поля для вашей локальной машины)
3) Запустить проект, командой docker-compose up
4) Зайти в браузере на localhost:8080

Для работы с задачами созданы учетные записи. Логины и пароли для получения access и refresh токенов представлены в таблице ниже:

|     username      | password |
|:-----------------:|:--------:|
| user@example.com  |  123456  |
| admin@example.com |  123456  |


В качестве базы данных использован PostgreSQL.
Для управления миграциями базы данных использована Liquibase.

Протестировать работу сервиса можно с помощью postman.

Документация API создана с использованием swagger и представлена по ссылке: http://localhost:8080/swagger-ui/index.html