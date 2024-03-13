import io.kotest.assertions.assertSoftly
import io.kotest.common.ExperimentalKotest
import io.kotest.common.Platform
import io.kotest.common.platform
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.core.spec.style.describeSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldMatch

class KotestWithParams : ShouldSpec({
    withData(
        mapOf(
            "10x2" to Triple(10, 2, 20),
            "20x2" to Triple(20, 2, 40),
            "30x2" to Triple(30, 2, 60),
        )
    ) { (a, b, c) ->
        a * b shouldBe c
    }
})

@OptIn(ExperimentalKotest::class)
class EmailTest : DescribeSpec({
    include(emailValidation)
})

@ExperimentalKotest
val emailValidation = describeSpec {

//    describe("Registration").config(enabled = platform != Platform.JS) {
    describe("Registration").config(enabled = platform != Platform.JS) {
        context("Checking user's mail") {
            forAll(
                row("test@test.com"),
                row("simple-user@gmail.com"),
            ) {
                assertSoftly {
                    it shouldMatch "^(.+)@(.+)\$"
                }
            }
        }
    }
}
