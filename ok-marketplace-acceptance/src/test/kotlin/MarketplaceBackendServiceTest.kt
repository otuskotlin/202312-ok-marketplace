import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

class AdCreationAndPostingTest : WordSpec({

    "Ad Creation and Posting (for Selling Products)" should {

        "succeed for a valid ad" {
            val adDetails = AdDetails("Sample Title", "Product description", "Electronics", listOf("image1.jpg", "image2.jpg"))
            val adManager = AdManager()

            val result = adManager.createAd(adDetails)

            result shouldBe AdCreationResult.Success("Ad is successfully created and visible in the marketplace.")
        }

        "allow preview before posting" {
            val adDetails = AdDetails("Sample Title", "Product description", "Electronics", listOf("image1.jpg", "image2.jpg"))
            val adManager = AdManager()

            val previewResult = adManager.previewAd(adDetails)

            previewResult shouldBe "Ad preview is available."
        }

        "fail for ad creation with missing title" {
            val adDetails = AdDetails("", "Product description", "Electronics", listOf("image1.jpg", "image2.jpg"))
            val adManager = AdManager()

            val result = adManager.createAd(adDetails)

            result shouldBe AdCreationResult.Failure("Ad creation fails with an error message: Title is required.")
        }

        "fail for ad creation with invalid images" {
            val adDetails = AdDetails("Sample Title", "Product description", "Electronics", listOf("invalidImage.pdf"))
            val adManager = AdManager()

            val result = adManager.createAd(adDetails)

            result shouldBe AdCreationResult.Failure("Ad creation fails with an error message: Invalid image format.")
        }
    }
})

data class AdDetails(val title: String, val description: String, val category: String, val images: List<String>)

sealed class AdCreationResult {
    data class Success(val message: String) : AdCreationResult()
    data class Failure(val errorMessage: String) : AdCreationResult()
}

class AdManager {
    fun createAd(adDetails: AdDetails): AdCreationResult {
        return when {
            adDetails.title.isBlank() -> AdCreationResult.Failure("Title is required.")
            adDetails.images.any { !isValidImageFormat(it) } -> AdCreationResult.Failure("Invalid image format.")
            else -> AdCreationResult.Success("Ad is successfully created and visible in the marketplace.")
        }
    }

    fun previewAd(adDetails: AdDetails): String {
        // Perform preview logic
        return "Ad preview is available."
    }

    private fun isValidImageFormat(image: String): Boolean {
        // Implement logic to check if the image format is valid
        return image.endsWith(".jpg") || image.endsWith(".png")
    }
}
