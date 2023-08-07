# SWAPI_APP
##### SWAPI - Android-приложение, написанное на Kotlin с использованием Views, отображающее информацию о персонажах, звездолетах, планетах с одноименного API, посвященного Звездным войнам
Мною были выполнены все условия, бонусы, и пожелания, предъявляемые к тестовому заданию, а именно:
<p>1.Приложение содержит 2 страницы - домашняя страница, где осуществляется поиск нужно информации, и страница избранного, где отображаются карточки, помеченные как избранные.</p>
<p>2.На странице избранного отображаются фильмы, в которых появляются любимые персонажи,звездолеты, планеты. </p>
<p>3.Вызов API на домашней странице осуществляется только после ввода 2 и более символов в поле ввода (в противном случае кнопка поиска попросту отсутствует). Так же присутствует меню, позволяющее выбрать
категорию поиска(то есть среди чего искать по заданному названию), оно расположено сразу под строкой ввода и отображается как стрелка вниз.</p>
<p>4.На странице поиска и избранном отображаются имя, пол, звездолеты, которые пилотировал этот человек, для персонажей, для звездолетов отображаются имя, модель,
производитель, количество пассажиров, для планет отображаются название, диаметр, кол-во населения.</p>
<p>5.У каждой карточки отображаются не просто названия фильмов, в которых появляются персонажи, планеты, звездолеты, планеты, но и информация о режиссере и продюсере.</p>
<p>6.Хранилище данных для карточек в избранном является постоянным. Исходя из пожеланий к тестовому заданию (а именно - разработка как часть более крупного проекта)
было решено выбрать локальное хранилище ROOM, так как оно отлично подходит для хранения больших массивов данных.</p>
<p>7.В рамках выполнения задания были написаны тесты, которые проверяют работу компонентов приложения. для тестирования применял фреймворк Espresso, так как это является 
классикой UI-тестов.</p>
<p></p>
Дополнительно был реализован следующий функционал:
<p>Кастомный адаптер для карточек, позволяющий отображать на домашней странице и странице избранного любые виды сущностей(помимо персонажа,планеты,звездолета).
Решение с легкостью расширяется добавлением нескольких строк кода при появлении новой сущности.</p>
<p>Вспомогательный функционал, определяющий наличие подключение к интернету. Если подключения нет, то поиск прекращается, но приложение продолжает работать.</p>
<p>Эффект загрузки при нажатии на поиск ресурсов с API,при получении и отображении результата данный эффект пропадает.</p>
<p>В целях повышения удобства при нажатии на поиск, клавиатура скрывается с экрана для лучшего отображения результатов поиска.</p>
<p>Была полностью с нуля сделана тема приложения(как дневная, так и ночная) в соответствии со всеми требованиями Material Design от Google.</p>
<p>Изменена иконка приложения со стандартной на тематическую.</p>
<p>Кроме того была проведена работа с POSTMAN по проверке работспособности и быстродействия API, эмпирическим путем выяснилось, что сам запрос к API выполняется в разные промежутки времени с разной скоростью :
в районе полудня по МСК один запрос мог обрабатывать порядка 8 секунд(что соотвественно сказывалось на быстродействии приложения, ведь для получения информации о фильмах
  и звездолетах требуются вложенные запросы), в 8 часов по МСК запрос обрабатывался в среднем около 4 секунд, а значит и приложение отображало результат гораздо быстрее. </p>
  В репозитории содержится APK-файл приложения. Кроме того, присутствуют скриншоты.
 
