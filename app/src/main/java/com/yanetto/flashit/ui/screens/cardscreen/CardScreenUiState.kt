package com.yanetto.flashit.ui.screens.cardscreen

data class CardScreenUiState (
    val question: String = "Extension-функции в Kotlin",
    val answer: String = "Extension-функции в Kotlin позволяют " +
            "добавлять новые функции к существующим " +
            "классам без изменения их кода. Эти функции выглядят как методы класса, но определяются вне его. Для создания extension-функции нужно объявить функцию с именем класса перед именем функции, например:\n" +
            "fun String.firstChar(): Char = this[0]\nТеперь firstChar можно использовать на любом объекте типа String как обычный метод: \n" +
            "val text = \"Hello\" println(text.firstChar()) // Выведет 'H' \n" +
            "Extension-функции полезны для добавления функционала к классам, доступ к коду которых ограничен."
)