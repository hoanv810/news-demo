import org.gradle.api.NamedDomainObjectContainer
import com.android.build.gradle.internal.dsl.ProductFlavor
import extensions.buildConfigStringField

/**
 * @author hoanv
 * @since 11/3/20
 */

interface BuildProductFlavor {
    val name: String

    fun libraryCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>
    ): ProductFlavor

    fun appCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>
    ): ProductFlavor
}

object ProductFlavorDevelop : BuildProductFlavor {
    override val name = "dev"

    override fun appCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>
    ): ProductFlavor {
        return namedDomainObjectContainer.create(name) {
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            dimension = BuildProductDimensions.ENVIRONMENT
        }
    }

    override fun libraryCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>
    ): ProductFlavor {
        return namedDomainObjectContainer.create(name) {
            versionNameSuffix = "-dev"
            dimension = BuildProductDimensions.ENVIRONMENT
            buildConfigStringField("API_BASE_URL", "https://newsapi.org/v2/")
            buildConfigStringField("API_KEY", "ce4e4f8fd47b4fbea20529a5b9dadc3e")
            buildConfigStringField("DOMAINS", "bbc.co.uk,techcrunch.com,engadget.com,theverge.com,mashable.com")
            buildConfigStringField("CATEGORY", "general")
            buildConfigStringField("COUNTRY", "us")
        }
    }
}