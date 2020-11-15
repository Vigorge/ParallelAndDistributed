# ParallelAndDistributed

#### Содержание
[Описание проекта](#description)  
[Лабораторная работа 1 (_сдана_)](#lab1)  
[Лабораторная работа 2 (_сдана_)](#lab2)  
[Лабораторная работа 3 (_сдана_)](#lab3)  
[Лабораторная работа 4 (_в работе_)](#lab4)  

<a name="description"><h2>Описание проекта</h2></a>
В проекте представленны лабораторные работы по предмету "Разработка параллельных и распределенных программ". Для сборки проекта используется фреймворк Apache Maven и каждая работа расположена в соответствующем пакете в директории src/main/java. Файлы, необходимые для некоторых лабораторных работ, находятся в директории inputFiles.  
Также лабораторные работы 1-3 выполняются с использованием Hadoop.  
Для отслеживание процесса написания кода используется скрипт gitwatch, автоматически создающий коммиты с изменениемя в файлах проекта. (Т.к. данный репозиторий был создан после сдачи лабораторных работа с первой по третью, в случае необходимости историю коммитов для второй и третей можно посмотреть в репозиториях https://github.com/Vigorge/hadoopLab1 (вторая) и https://github.com/Vigorge/hadoopLab2 (третья)).

## Лабораторные работы
<a name="lab1"><h3>Лабораторная работа 1</h3></a>
Статус: _сдана_  
Цель: _знакомство с системой Hadoop_  
Описание работы:  
_Программа формирует частотный словарь для текста книги "Война и мир"._  
Package: `lab1`  
Необходимые файлы: `warandpeace1.txt`  
Запуск программы:

    export HADOOP_CLASSPATH=target/PDlabs-1.0.jar
    hadoop lab1.WordCountApp warandpeace1.txt outputLab1

<a name="lab2"><h3>Лабораторная работа 2</h3></a>
Статус: _сдана_  
Цель: _разработка программы, реализующей задачу связывания наборов данных по ключу методом Reduse side join_  
Описание работы:  
_Программа связывает две таблицы (таблицы сопоставления кодов аэропортов их названиям и таблицы с информацией о совершенных рейсах) по коду аэропорта прибытия (DEST_AEROPORT_ID) и подсчитывает для каждого минимальное, максимальное и среднее время задержки._  
Package: `lab2`  
Необходимые файлы: `airports.csv` `flights.csv`  
Запуск программы:

    export HADOOP_CLASSPATH=target/PDlabs-1.0.jar
    hadoop lab2.AirportApp airports.csv flights.csv outputLab2
    
<a name="lab3"><h3>Лабораторная работа 3</h3></a>
Статус: _сдана_  
Цель: _знакомство с работой Apache Spark_  
Описание работы:  
_На основании данных двух таблиц программа подсчитывает для каждой пары <аэропорт вылета, аэропорт прибытия> максимальное время опаздания и проценты опоздавших и отмененных рейсов. В каждой паре коды аэропортов заменяются названиями._  
Package: `lab3`  
Необходимые файлы: `airports.csv` `flights.csv`  
Запуск программы:

    spark-submit --class lab3.CountDelayApp --master yarn-client --num-executors target/PDlabs-1.0.jar airports.csv flights.csv outputLab3
    
<a name="lab4"><h3>Лабораторная работа 4</h3></a>
Статус: _в работе_  
Цель: _знакомство с Actor based framework_  

