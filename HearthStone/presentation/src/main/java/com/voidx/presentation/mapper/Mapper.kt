package com.voidx.presentation.mapper

interface Mapper<FROM, TO> {

    fun map(from: FROM): TO

}