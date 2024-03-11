package ru.otus.otuskotlin.marketplace.api.v2.mappers

import ru.otus.otuskotlin.marketplace.api.v2.models.*
import ru.otus.otuskotlin.marketplace.common.MkplContext
import ru.otus.otuskotlin.marketplace.common.models.*
import ru.otus.otuskotlin.marketplace.common.stubs.MkplStubs
import kotlin.test.assertEquals
import kotlin.test.Test

class MapperUpdateTest {
    @Test
    fun fromTransport() {
        val req = AdUpdateRequest(
            debug = AdDebug(
                mode = AdRequestDebugMode.STUB,
                stub = AdRequestDebugStubs.SUCCESS,
            ),
            ad = AdUpdateObject(
                id = "12345",
                title = "title",
                description = "desc",
                adType = DealSide.DEMAND,
                visibility = AdVisibility.PUBLIC,
                lock = "456789",
            ),
        )

        val context = MkplContext()
        context.fromTransport(req)

        assertEquals(MkplStubs.SUCCESS, context.stubCase)
        assertEquals(MkplWorkMode.STUB, context.workMode)
        assertEquals("12345", context.adRequest.id.asString())
        assertEquals("456789", context.adRequest.lock.asString())
        assertEquals("title", context.adRequest.title)
        assertEquals(MkplVisibility.VISIBLE_PUBLIC, context.adRequest.visibility)
        assertEquals(MkplDealSide.DEMAND, context.adRequest.adType)
    }

    @Test
    fun toTransport() {
        val context = MkplContext(
            requestId = MkplRequestId("1234"),
            command = MkplCommand.UPDATE,
            adResponse = MkplAd(
                id = MkplAdId("12345"),
                title = "title",
                description = "desc",
                adType = MkplDealSide.DEMAND,
                visibility = MkplVisibility.VISIBLE_PUBLIC,
                lock = MkplAdLock("456789"),
            ),
            errors = mutableListOf(
                MkplError(
                    code = "err",
                    group = "request",
                    field = "title",
                    message = "wrong title",
                )
            ),
            state = MkplState.RUNNING,
        )

        val req = context.toTransportAd() as AdUpdateResponse

        assertEquals("12345", req.ad?.id)
        assertEquals("456789", req.ad?.lock)
        assertEquals("title", req.ad?.title)
        assertEquals("desc", req.ad?.description)
        assertEquals(AdVisibility.PUBLIC, req.ad?.visibility)
        assertEquals(DealSide.DEMAND, req.ad?.adType)
        assertEquals(1, req.errors?.size)
        assertEquals("err", req.errors?.firstOrNull()?.code)
        assertEquals("request", req.errors?.firstOrNull()?.group)
        assertEquals("title", req.errors?.firstOrNull()?.field)
        assertEquals("wrong title", req.errors?.firstOrNull()?.message)
    }
}
