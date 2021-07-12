package com.roman.converter

import com.roman.converter.Model.REST.Valute

object ValuteList {

    lateinit var list: ArrayList<Valute>

    fun getValue(charCode: String): String? {
        if (list == null) {
            return null
        }
        for (item in list!!) {
            if (item.charCode.equals(charCode)) {
                return item.value
            }
        }
        return null
    }


    fun getNumValue(charCode: String): Float? {
        if (list == null) {
            return null
        }
        for (item in list!!) {
            if (item.charCode.equals(charCode)) {
                return (item.value?.replace(",", ".")?.toFloat() ?: 0) as Float?
            }
        }
        return null
    }


    fun getName(charCode: String): String? {
        if (list == null) {
            return null
        }
        for (item in list!!) {
            if (item.charCode.equals(charCode)) {
                return item.name
            }
        }
        return null
    }


}