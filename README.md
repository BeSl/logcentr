# logcentr
просмотр логов систем из одного окна

# цель проекта 
 - изучение kotlin
 - разобраться с jmix
 - сделать полезный инструмент

# Итоговый проект:
 - содержит параметры подключения к серверам ClickHouse
 - дает возможность просмотра таблиц по заданным отборам
 - для некоторых таблиц свои вьюхи и графики (н-р, замеры маркировки)

# Задачи:
1. добавить скрипты создания таблиц
2. добавить скрипты заполнения таблиц
3. добавить докер-композ для кликхауса(КХ)
4. определить роли в системе
5. реализовать хранение редактирование подключений к серверам КХ
6. ДТО для загрузки таблицы журнала



# Подготовка окружения

## Установка ClickHouse

- Пример docker-compose.yml
```yaml
version: '3.7'
services:
 ClickHouse-Server:
   image: yandex/clickhouse-server
   container_name: ClickHouse
   ports:
     - "8123:8123"
     - "9000:9000"
   volumes:
     - ./db:/var/lib/clickhouse

```

- Запросы для создания таблиц
```sql
CREATE DATABASE IF NOT EXISTS ch_test1_base;
 
CREATE TABLE IF NOT EXISTS ch_test1_base.eventlog_error(
   `timestamp` DateTime,
   `source_type` String,
   `host` String,
   `message` String,
   `component_id` String,
   `component_kind` String,
   `component_type` String,
   `reason` String,
   `error` String
) ENGINE = MergeTree() PARTITION BY toYYYYMM(timestamp)
ORDER BY (timestamp, source_type, host);
 
CREATE TABLE IF NOT EXISTS ch_test1_base.eventlog(
    `DateTime` DateTime,
    `TransactionStatus` String,
    `TransactionDate` DateTime,
    `TransactionNumber` Int32,
    `User` String,
    `UserUuid` String,
    `Computer` String,
    `Application` String,
    `Connection` Int32,
    `Event` String,
    `Severity` String,
    `Comment` String,
    `Metadata` String,
    `MetadataUuid` String,
    `Data` String,
    `DataPresentation` String,
    `Server` String,
    `MainPort` Int16,
    `AddPort` Int16,
    `Session` Int32
) ENGINE = MergeTree() PARTITION BY toYYYYMM(DateTime)
ORDER BY (DateTime);

```
Структура взята как образец хранилища логов журналов регистраций из 1С
Под эту структуру таблиц ориентируемся при разработке DTO объектов и просмотре журналов.
