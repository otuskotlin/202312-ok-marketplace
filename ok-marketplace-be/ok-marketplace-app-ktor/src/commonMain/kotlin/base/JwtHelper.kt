package ru.otus.otuskotlin.marketplace.app.ktor.base

import io.ktor.server.request.*
import ru.otus.otuskotlin.marketplace.app.common.AUTH_HEADER
import ru.otus.otuskotlin.marketplace.app.common.jwt2principal
import ru.otus.otuskotlin.marketplace.common.permissions.MkplPrincipalModel

fun ApplicationRequest.jwt2principal(): MkplPrincipalModel = this.header(AUTH_HEADER).jwt2principal()
