# Домашнее задание к уроку 25: War. Spring MVC

## Веб-приложение на Spring MVC

Цель: Нучиться создавать war-пакеты и запускать их в TomCat.

Научиться пользоваться Thymeleaf.
- Собрать war для приложения из ДЗ про Web Server
- Создавать основные классы приложения, как Spring beans (Кэш, Dao, DBService)
- Настройку зависимостей выполнить с помощью Java/Annotation based конфигурации
- Для обработки запросов использовать @Controller и/или @RestController
- В качестве движка шаблонов использовать Thymeleaf
- Запустить веб приложение во внешнем веб сервере

Авторизацию и аутентификацию делать не надо.

## Запуск приложения
Для запуска приложения необходимо:
1. Поднять TomCat
2. Запустить Docker, поднять Postgresql, воспользовавшись скриптом в папке `docker`
3. Собрать war-файл при помощи gradle
4. Задеплоить war-файл в TomCat

Приложение будет доступно по ссылке: `http://localhost:8080/app/`
