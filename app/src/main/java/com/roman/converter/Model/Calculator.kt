package com.roman.converter.Model

import com.roman.converter.Contracts.CalcContract
import com.roman.converter.ValuteList

class Calculator:CalcContract.Model {

    override fun calculate(set: ValuteSet): ValuteSet {
        val curs1: Float = ValuteList.getNumValue(set.getCode1()) ?: 1f
        val curs2: Float = ValuteList.getNumValue(set.getCode2()) ?: 1f
        val targetValue: Float
        if (set.getPos() == ValuteSet.Pos.DOWN)
            targetValue = curs1/curs2*set.getVal()
        else
            targetValue = curs2/curs1*set.getVal()

        return ValuteSet(targetValue,set.getCode1(),set.getCode2(),set.getPos())
    }

}