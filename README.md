<h1>Применённые разделы Java</h1>
<ul>
  <li>Collections</li>
  <li>I/O</li>
  <li>Exeptions</li>
  <li>Lambda Expressions</li>
  <li>Swing</li>
  <li>Annotations</li>
  <li>Maven</li>
</ul>

<h1>Применённые паттерны проектирования</h1>
<ul>
  <li>Компоновщик</li>
  <li>Наблюдатель</li>
  <li>Мост</li>
</ul>

<h1>ПО при разработке</h1>
<ul>
  <li>ОС Windows 10 22h2</li>
  <li>IDE - IntelliJ Idea CE 2025</li>
  <li>JDK 2025 <a href="https://download.oracle.com/java/25/latest/jdk-25_windows-x64_bin.msi">скачать</a></li>
</ul>

<h1>Быстрый просмотр возможностей приложений</h1>
<p>В корне репозитория лежит исполняемый <a href="TextDesign.jar">jar-файл</a>, который можно запустить имея на ПК установленное ПО Java (JRE/JDK). Если приложение не запускается, возможна неполная установка Java (например отсутвие в перменной среды Path значения с папкой bin от Java) или устаревшая версия установленного JRE/JDK.</p>

<h1>Описание приложения</h1>
<p>Приложение, которое позволяет посмотреть на внешний вид текста в виде строки при установленных метриках:
<ul>
  <li>Начертание</li>
  <li>Размер</li>
  <li>Цвет</li>
  <li>Гарнитура</li>
</ul>
</p>
<p>Есть возможность оценки размеров настроенной строки в пикселях: высоты и ширины. Для подбора цвета предусмотрено два типа окна:
<ul>
  <li>Наборное: когда пользователь может вручную набрать цвет в формате (R,G,B)</li>
  <li>Плиткой: когда пользователю предоставлены готовые цвета</li>
</ul></p>
<p>Окно приложение состоит из меню управления, панель с тестируемой строкой, строку ввода и панель с метриками введёной строки.</p>
<p></p>
<p>Выбор размера шрифта</p>
<img src="https://github.com/user-attachments/assets/a8290f01-9109-4487-98be-6ad8bad3ef2f">
<p></p>
<p>Выбор начертания шрифта</p>
<img src="https://github.com/user-attachments/assets/fa3abc8f-8e78-4372-a7bd-b359de27c2c1">
<p></p>
<p>Два разных окна выбора цвета</p>
<table>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/fe7f2d2e-e73c-4c80-b3bb-f9b5d8ebf302"></td>
    <td><img src="https://github.com/user-attachments/assets/caee7ed9-123f-45c5-aba5-8855d428eb5d"></td>
  </tr>
</table>
<p>Превышение допустимых размеров строки</p>
<img src="https://github.com/user-attachments/assets/8fe3f2e1-2c53-4c11-8030-ce7be6543e6e" />





