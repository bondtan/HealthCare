package com.example.uiexample.common

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun timeFormat (time: LocalTime): String = DateTimeFormatter
    .ofPattern("HH:mm")
    .format(time)

fun dateFormatSingleLine (date: LocalDate): String = DateTimeFormatter
    .ofPattern("dd.MM.yyyy")
    .format(date)

fun dateFormatTwoLines (date: LocalDate): String = DateTimeFormatter
    .ofPattern("dd.MM.\nyyyy")
    .format(date)

fun dateMonthFormat(date: LocalDate): String = DateTimeFormatter
    .ofPattern("dd.MM")
    .format(date)