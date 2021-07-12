package com.roman.converter.Contracts

import com.roman.converter.Model.ValuteSet

interface CalcContract {

    interface View{
        fun changeValues(set:ValuteSet)
    }

    interface Presenter{
        fun recalculate(set: ValuteSet)
    }

    interface Model {
        fun calculate(set:ValuteSet):ValuteSet
    }
}