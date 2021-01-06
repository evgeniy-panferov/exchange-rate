## Инструкция по запуску

- Открыть среду разработки
- Зайти в файл application.properties, в строках где написано - example, now using new token. Need pass your token,
необходимо удалить символы до знака равно и установить свой токен, либо попросить у меня. 
- После этого нажать Run в среде разработке, установка переменных окружения не требуется.

### Что за что отвечает в application.properties

- feign.giphy.name= название сервиса, при интеграции с eureka 
- feign.giphy.url= url, по-которому отправляем запросы 
- giphy.search= urn, для вышеуказанного url (endpoint)
- giphy.api_key= токен для доступа к api
- giphy.q.rich= тут указно слово, по которому ищем, когда курс растет 
- giphy.q.broke= тут указно слово, по которому ищем, когда курс падает  
- 
- feign.openexchangerates.name= название сервиса, при интеграции с eureka 
- feign.openexchangerates.url= url, по-которому отправляем запросы 
- feign.openexchangerates.historical= urn, для вышеуказанного url
- feign.openexchangerates.symbols= urn, для вышеуказанного url (endpoint)
- feign.openexchangerates.base= базовая валюта, от которой измеряем отношение - usd, 
так как api платное 
- feign.openexchangerates.authorization= токен для доступа к api