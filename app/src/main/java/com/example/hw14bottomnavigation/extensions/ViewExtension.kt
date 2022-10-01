package com.example.hw14bottomnavigation.extensions

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.getTextOrSetError(): String? {
    return editText?.text?.toString()
        ?.ifBlank {
            error = "field is empty"
            null
        }
}