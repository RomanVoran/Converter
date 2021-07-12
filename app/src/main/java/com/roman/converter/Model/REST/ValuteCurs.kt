package com.roman.converter.Model.REST

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name= "ValCurs", strict = false)
data class ValuteCurs @JvmOverloads constructor(

    @field:ElementList(name = "Valute",inline = true)
    @param:ElementList(name = "Valute",inline = true)
    var valuteList:ArrayList<Valute>? = null

)