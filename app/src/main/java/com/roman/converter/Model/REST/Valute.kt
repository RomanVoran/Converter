package com.roman.converter.Model.REST

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "Valute", strict = false)
data class Valute @JvmOverloads constructor(

    @field:Element(name = "NumCode")
    @param:Element(name = "NumCode")
    var numCode: String? = null,

    @field:Element(name = "CharCode")
    @param:Element(name = "CharCode")
    var charCode: String? = null,

    @field:Element(name = "Name")
    @param:Element(name = "Name")
    var name: String? = null,

    @field:Element(name = "Value")
    @param:Element(name = "Value")
    var value: String? = null



)