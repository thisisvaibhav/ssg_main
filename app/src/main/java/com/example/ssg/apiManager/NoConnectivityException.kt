package com.example.ssg.apiManager

import java.io.IOException

class NoConnectivityException : IOException() {

    override val message: String?
        get() = "No connectivity exception"
}