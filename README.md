# Find Country by Phone Number
    Это приложение позволяет определить страну по номеру телефона.
    Оно состоит из Spring Boot backend и статического фронтенда (HTML), который обслуживается Spring Boot.

# Технологии:
    Backend: Spring Boot (Java 11)
    Frontend: Статический HTML (обслуживается Spring Boot)
    База данных: PostgreSQL
    Сборка: Gradle
    Контейнеризация: Docker и Docker Compose

# Требования:
    Docker и Docker Compose установлены на вашей машине.
    Java 11 (если вы хотите запускать приложение без Docker).

# Быстрый старт:
1. Клонируйте репозиторий git clone https://github.com/wezde/FindCountryByPhoneNumber.git
                          cd <ваш-репозиторий>
2. Запустите приложение с помощью Docker Compose " docker-compose up --build "
3. Откройте приложение в браузере http://localhost:8088

# Сборка и запуск без Docker
1.Укажите в файле application.property ваши переменные окружения
2.Соберите проект с помощью Gradle ./gradlew clean build
3.Запустите приложение java -jar build/libs/FindCountryByPhoneNumber-0.0.1-SNAPSHOT.jar

# Тестирование
1. ./gradlew test
2. Просмотр отчетов: Откройте файл build/reports/tests/test/index.html в браузере.


# API
    Получить страну по номеру телефона
    Метод: GET
    URL: /api/country?phone=<номер телефона>

    Пример запроса:
    curl http://localhost:8088/api/country?phone=79123456789

    Пример ответа:
    {
    "country": "Russia"
    }
