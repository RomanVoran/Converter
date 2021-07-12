package com.roman.converter.Presenter

import com.roman.converter.Contracts.LoadContract
import com.roman.converter.Model.LoadModel

class LoadPresenter(var view:LoadContract.LoadView?):LoadContract.LoadPresenter {
    val model:LoadContract.LoadModel
    val callback = fun (successLoad:Boolean){
        if (successLoad)
            view!!.nextActivity()
        else
            view!! .showErrorMessage()
    }

    init {
        this.model = LoadModel(callback)
    }

    override fun load() {
        model.load()
    }

    override fun endOfLoad(successLoad:Boolean) {
        if (successLoad)
            view!!.nextActivity()
        else
            view!! .showErrorMessage()
    }

    override fun detachView() {
        view = null
    }


}