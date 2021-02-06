package com.anesabml

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    val client = HttpClient(CIO) {
        install(HttpTimeout) {
        }
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
        install(Logging) {
            level = LogLevel.BODY
        }
    }
    runBlocking {
        val message = client.get<JsonSampleClass> {
            url("http://127.0.0.1:8080/json/gson")
            timeout {
                requestTimeoutMillis = 30_000
            }
        }
    }
}

data class JsonSampleClass(val hello: String)

