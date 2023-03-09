package com.example.uiexample.common

interface EventHandler<E> {

    fun obtainEvent(event: E)
}