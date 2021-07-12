package com.roman.converter.Contracts

interface LoadContract {

    interface LoadModel {
        fun load()
    }

    interface LoadPresenter{
        fun load()
        fun endOfLoad(successLoad:Boolean)
        fun detachView()
    }

    interface LoadView {
        fun showErrorMessage()
        fun nextActivity()
    }
}