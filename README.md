## Домашнее задание к занятию «3.2. Coroutines: Scopes, Cancellation, Supervision»
### Вопросы: Cancellation
#### Вопрос №1 - не отработает, т.к. стоит cancelAndJoin(), которая отменяет задание и ждет его завершения.
#### Вопрос №2 - строка не отработает, т.к. стоит .cancel() которая отменяет задание

### Вопросы: Exception Handling
#### Вопрос №1 - нет не отработает, т.к. выкидывается первое исключение.
#### Вопрос №2 - отработает, т.к. coroutineScope находится внутри блока try/catch
#### Вопрос №3 - отработает, т.к. supervisorScope находится внутри блока try/catch
#### Вопрос №4 - не отработает, т.к. задержка и исключение первым выкинет следующий launch
#### Вопрос №5 - отработает, т.к. supervisorScope и выполнение дочерних элементов не влияет друг на друга
#### Вопрос №6 - не работает, т.к. первее выкинется Exception, чем отработают запущенные корутины
#### Вопрос №7 - не отработает, т.к. первее выкинется Exception, чем отработают запущенные корутины

