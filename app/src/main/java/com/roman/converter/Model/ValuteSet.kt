package com.roman.converter.Model

class ValuteSet {

    // target position of valute converter
    public enum class Pos{
        UP,
        DOWN
    }

    private var pos: Pos
    private var value:Float
    private var code1: String
    private var code2: String

    constructor(value: String, code1: String, code2: String, pos: Pos) {
        try {
            this.value = value.replace(",", ".").toFloat()
        }catch (e:Exception){
            this.value = -1f
            e.printStackTrace()
        }
        this.code1 = code1
        this.code2 = code2
        this.pos = pos
    }
    constructor(value: Float, code1: String, code2: String, pos: Pos) {
        this.value = value
        this.code1 = code1
        this.code2 = code2
        this.pos = pos
    }

    fun getVal():Float = value

    fun getCode1():String = code1

    fun getCode2():String = code2

    fun getPos():Pos = pos

}