package ru.otus.otuskotlin.marketplace.backend.repo.tests

import ru.otus.otuskotlin.marketplace.common.models.*
import ru.otus.otuskotlin.marketplace.common.repo.DbAdRequest
import ru.otus.otuskotlin.marketplace.common.repo.DbAdResponseOk
import ru.otus.otuskotlin.marketplace.repo.common.IRepoAdInitializable
import kotlin.test.*


abstract class RepoAdCreateTest {
    abstract val repo: IRepoAdInitializable

    protected open val lockNew: MkplAdLock = MkplAdLock("20000000-0000-0000-0000-000000000002")

    private val createObj = MkplAd(
        title = "create object",
        description = "create object description",
        ownerId = MkplUserId("owner-123"),
        visibility = MkplVisibility.VISIBLE_TO_GROUP,
        adType = MkplDealSide.SUPPLY,
    )

    private val createBadObj = createObj.copy(title = "")

    @Test
    fun createSuccess() = runRepoTest {
        val result = repo.createAd(DbAdRequest(createObj))
        assertIs<DbAdResponseOk>(result)
        val expected = createObj.copy(id = result.data.id)
        assertEquals(expected.title, result.data.title)
        assertEquals(expected.description, result.data.description)
        assertEquals(expected.adType, result.data.adType)
        assertNotEquals(MkplAdId.NONE, result.data.id)
    }

    companion object : BaseInitAds("create") {
        override val initObjects: List<MkplAd> = emptyList()
    }
}
