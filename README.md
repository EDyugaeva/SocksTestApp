# Автоматизация учета склада носков

### О приложении
Приложение, тестовое задание, предназначено для автоматизации ведения учета поступлений и отпуска носков со склада.

### Используются
* база данных PostgreSQL с применением Liquibase для версионирования схемы
* RestAPI (только post и get запросы - по заданию)
* Mokito для тестирование Controller
* применен Controller advisor(@RestControllerAdvice) для управления исключениями


### Функциональность
Приложение состоит из одной модели (таблицы), репозитория, сервиса и контроллера.
В записи таблицы учитывается id записи (автоматически), цвет, количество,
процент хлопка носков, а также дата создания записи и тип записи. Дата внесения записи не требуется
по заданию, но кажется необходимой для более качественного функционирования склада.

Тип записи
 возможен только двух видов: отпуск или приход носков. От этого зависит значение
количества носков (положительное или отрицательное). Тип записи выполнен в контроллерами использованием
POST запросов: /api/socks/outcome и /api/socks/income
 
Также создан GET запрос, возвращающий общее количество носков на складе, соответствующих
переданным в параметрах критериям запроса: цвету, значению хлопка и оператору сравнения.


### Будущее
При наличии времени данное приложение планируется расширить, например такими способами:
* добавив другие типы товаров
* расширить сведения о текущем товаре (например, добавить производителя)
* добавить другие запросы (на удаление/изменение записей, дополнительные get запросы для понимания статистики по производителю)
* добавить пользователей с различными правами на добавление (для всех) и удаление записей (для пользователей с полными правами)
* подключить телеграм бота
* выложить на heroku

### Обо мне
Екатерина, начинающий Java-разработчик

# Automation of stock accounting for socks

### О приложении
The application, a test task, is designed to automate the accounting of receipts and issues of socks from a warehouse.

### Uses
* database PostgreSQL with Liquibase for schema versioning
* RestAPI (only post and get requests)
* Mokito for test controller 
* controller advisor (@RestControllerAdvice) for hadling exceptions


### FUNCTIONALITY
The application consists of one model (table), repository, service and controller.
The table has columns: id (automatically), a sock color,a socks' quantity, a
percentage of cotton, a date of the entry and a type of entry. An entry date does not require
on assignment, but seems necessary for the better functioning of the warehouse.

Record Type can be only in two types: income or outcome of socks. This depends on the quantity
number of socks (positive or negative). This is implemented in controllers using
POST requests: /api/socks/outcome and /api/socks/income

Also  GET request is created for returning the total number of socks in stock that match
the query criteria passed in the parameters: color, percentage of cotton and comparison operator.

### Future

If there is time, this application is planned to be expanded, for example in the following ways:
* by adding other types of items
* expand information about the current product (for example, add a manufacturer)
* add other requests (for deleting/changing records, additional get requests for understanding statistics)
* add users with different access rights to add (for everyone) and delete records (for users with full rights)
* connect with telegram bot
* deploy on heroku

### Developers
Ekaterina, junior Java developer