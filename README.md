# Приложение Monitor Sensors 

Проект представляет собой бэкенд часть приложения для работы пользователей с несколькими ролями (USER и ADMIN) с сущностью 
Sensor, датчика. Для работы необходимо авторизироваться под какой-либо из ролей. Неавторизированным пользователям запросы в приложение
недоступны.

#### Возможности пользователя ADMIN:
Создание сущности датчика, просмотр по id датчика, просмотр всех сущностей датчика, поиск по полям name и model сущности датчика,
обновление данных по id сущности, удаление по id сущности.
#### Возможности пользователя USER:
Просмотр по id датчика, просмотр всех сущностей датчика, поиск по полям name и model сущности датчика.

## Как развернуть локально
1) Необходимо иметь установленную базу данных PostgreSQL
2) Через pgAdmin выполнить первый скрипт из файла script.sql для создания
типа ENUM для ролей пользователя
3) Склонировать локально этот репозиторий
4) Указать в application.properties необходимые значения для spring.datasource.username,
   spring.datasource.password, server.port
5) Запустить приложение
6) Через pgAdmin выполнить второй и третий скрипты из файла script.sql, чтобы создать хардкодом двух пользователей
с разными ролями


## Как использовать
Необходимо использовать HTTP-клиент, например Postman, для того, чтобы отправлять http запросы для нашего приложения.
1) Авторизируемся через форму логина на http://localhost:8080/login, предоставляемую Spring Security под юзером с ролью ADMIN. В БД уже есть 
такой пользователь, его креды admin/admin. Далее всегда в почтовом клиенте при каждом заппосе необходимо указывать в Headers куки, которые
были получены пользователем при авторизации.
   ![Image](https://github.com/user-attachments/assets/ba02b50d-6e6d-4bc9-bab3-b26f2a90693d)
   ![Image](https://github.com/user-attachments/assets/5f536952-f41b-4822-8c78-65837ebda93f)
2) Создадим новую сущностю через POST http://localhost:8080/sensor/v1, также в body указать в JSON формате данные сущности. Согласно требованиям сущность Sensor содержит
такие поля как 
 - name, текстовое поле, должно быть не пустым и содержать не менее 3 и не более 30 символов
 - model, тексовое поле, должно быть не пустым и содержать не более 15 символов
 - range, объект с полями from и to, оба целочисленные и больше нуля, to имеет значение больше, чем from, обязательное
 - type, enum, может принимать значения PRESSURE, VOLTAGE, TEMPERATURE, HUMIDITY, обязательное поле
 - unit, enum, может принимать значения BAR, VOLTAGE, CELSIUS_DEGREES, PERCENT, не обязательное
 - location, текстовое поле, может содержать не более 40 символов, не обязательное
 - description, тексовое поле, может содержать не более 200 символов, не обязательное
   ![Image](https://github.com/user-attachments/assets/6f5a1861-b445-457a-a8ed-e5306c385510)
3) Просмотрим соданную сущность через GET http://localhost:8080/sensors/v1/{id}
   ![Image](https://github.com/user-attachments/assets/cf67ca0d-8937-4bea-8fc7-cb5dbe713184)
4) Просмотрим все сущности через GET http://localhost:8080/sensors/v1
   ![Image](https://github.com/user-attachments/assets/b8f40c47-d110-4071-a0a9-ba98c5f7d88f)
5) Просмотрим сущности по полному или частичному совпадению полей name и model через 
GET http://localhost:8080/sensors/v1/search?name={nameValue}&model={modelValue}
   ![Image](https://github.com/user-attachments/assets/dbbe6125-012b-431a-83de-0ae07b88143b)
6) Обновим данные сущности через PUT http://localhost:8080/sensors/v1/{id} здесь нужно указать в body такие же поля
как и в POST запросе
   ![Image](https://github.com/user-attachments/assets/914e079a-469c-4bdc-be33-fa27c104d077)
7) Удалим сущность через DELETE http://localhost:8080/sensors/v1/{id}
   ![Image](https://github.com/user-attachments/assets/9150c3be-9710-458f-87d2-a406d6676b56)
