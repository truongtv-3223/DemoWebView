package com.lime.demowebview

import java.util.regex.Pattern


fun String.endWithHTML() : Boolean{
    val pattern = Pattern.compile(Constant.REGEX_URL_EXTENSION)
    return pattern.matcher(this).find()
}

fun String.isTab() : Boolean{
    val pattern = Pattern.compile(Constant.REGEX_URL_TAB)
    return pattern.matcher(this).find()
}
