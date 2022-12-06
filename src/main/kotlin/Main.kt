import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext

//Домашнее задание к занятию «3.2. Coroutines: Scopes, Cancellation, Supervision»
//Выполненное задание прикрепите ссылкой на ваши GitHub-проекты в личном кабинете студента на сайте netology.ru.

//Вопросы: Cancellation
//Вопрос №1
//Отработает ли в этом коде строка <--? Поясните, почему да или нет.

//fun main() = runBlocking {
//    val job = CoroutineScope(EmptyCoroutineContext).launch {
//        launch {
//            delay(500)
//            println("ok") // <-- не отработает, т.к. стоит cancelAndJoin(), которая отменяет задание и ждет его завершения.
//        }
//        launch {
//            delay(500)
//            println("ok")
//        }
//    }
//    delay(100)
////    job.join()
//    job.cancelAndJoin()
//}

//Вопрос №2
//Отработает ли в этом коде строка <--. Поясните, почему да или нет.

//fun main() = runBlocking {
//    val job = CoroutineScope(EmptyCoroutineContext).launch {
//        val child = launch {
//            delay(5000)
//            println("ok 1") // <-- Строка не отработает, т.к. стоит .cancel() которая отменяет задание
//        }
//        launch {
//            delay(500)
//            println("ok 2")
//        }
//        delay(100)
////        child.cancel()
//        child.join()
//    }
//    delay(100)
//    job.join()
//}


//Вопросы: Exception Handling
//Вопрос №1
//Отработает ли в этом коде строка <--. Поясните, почему да или нет.

//fun main() {
//    with(CoroutineScope(EmptyCoroutineContext)) {
//        try {
//            launch {
//                println("Начало работы")
//                throw Exception("something bad happened")
//            }
//        } catch (e: Exception) {
//            e.printStackTrace() // <-- нет не отработает, т.к. выкидывается первое исключение.
//        }
//    }
//    Thread.sleep(1000)
//}

//Вопрос №2
//Отработает ли в этом коде строка <--. Поясните, почему да или нет.

//fun main() {
//    CoroutineScope(EmptyCoroutineContext).launch {
//        try {
//            coroutineScope {
//                throw Exception("something bad happened")
//            }
//        } catch (e: Exception) {
//            println("Отработал!")
//            e.printStackTrace() // <-- отработает, т.к. coroutineScope находится внутри блока try/catch
//        }
//    }
//    Thread.sleep(1000)
//}


//Вопрос №3
//Отработает ли в этом коде строка <--. Поясните, почему да или нет.

//fun main() {
//    CoroutineScope(EmptyCoroutineContext).launch {
//        try {
//            supervisorScope {
//                throw Exception("something bad happened")
//            }
//        } catch (e: Exception) {
//            println("Отработал!")
//            e.printStackTrace() // <-- отработает, т.к. supervisorScope находится внутри блока try/catch
//        }
//    }
//    Thread.sleep(1000)
//}

//Вопрос №4
//Отработает ли в этом коде строка <--. Поясните, почему да или нет.

//fun main() {
//    CoroutineScope(EmptyCoroutineContext).launch {
//        try {
//            coroutineScope {
//                launch {
//                    delay(500)
//                    println("Отработал!")
//                    throw Exception("something bad happened") // <-- не отработает, т.к. задержка и исключение первым выкинет следующий launch
//                }
//                launch {
//                    throw Exception("something bad happened")
//                }
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//    Thread.sleep(1000)
//}

//Вопрос №5
//Отработает ли в этом коде строка <--. Поясните, почему да или нет.

//fun main() {
//    CoroutineScope(EmptyCoroutineContext).launch {
//        try {
//            supervisorScope {
//                launch {
//                    delay(500)
//                    println("Отработал!")
//                    throw Exception("something bad happened") // <-- отработает, т.к. supervisorScope и выполнение дочерних элементов не влияет друг на друга
//                }
//                launch {
//                    throw Exception("something bad happened")
//                }
//            }
//        } catch (e: Exception) {
//            e.printStackTrace() // <--
//        }
//    }
//    Thread.sleep(1000)
//}

//Вопрос №6
//Отработает ли в этом коде строка <--. Поясните, почему да или нет.

//fun main() {
//    CoroutineScope(EmptyCoroutineContext).launch {
//        CoroutineScope(EmptyCoroutineContext).launch {
//            launch {
//                delay(1000)
//                println("Отработал!")
//                println("ok 1") // <-- не работает, т.к. первее выкинеться Exception, чем отработают запущенные корутины
//            }
//            launch {
////                delay(500)
//                println("ok 2")
//            }
//            throw Exception("something bad happened")
//        }
//    }
//    Thread.sleep(1000)
//}

//Вопрос №7
//Отработает ли в этом коде строка <--. Поясните, почему да или нет.

fun main() {
    CoroutineScope(EmptyCoroutineContext).launch {
        CoroutineScope(EmptyCoroutineContext + SupervisorJob()).launch {
            launch {
                delay(1000)
                println("Отработал!")
                println("ok 2") // <-- не отработает, т.к. первее выкинеться Exception, чем отработают запущенные корутины
            }
            launch {
                delay(500)
                println("ok 1")
            }
            throw Exception("something bad happened")
        }
//        throw Exception("something bad happened")
    }
    Thread.sleep(1000)
}
