package com.roman.converter.Presenter

import com.roman.converter.Contracts.CalcContract
import com.roman.converter.Model.Calculator
import com.roman.converter.Model.ValuteSet

class CalcPresenter(val view: CalcContract.View): CalcContract.Presenter {

    private val model : CalcContract.Model = Calculator()

    override fun recalculate(set: ValuteSet) {
        if ( set.getVal() != -1f )

            view.changeValues(model.calculate(set))
    }
}