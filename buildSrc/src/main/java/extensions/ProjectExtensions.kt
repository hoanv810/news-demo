package extensions

import org.gradle.api.Project
import getLocalProperty

/**
 * @author hoanv
 * @since 11/12/20
 */

/**
 * Obtain property declared on `$projectRoot/local.properties` file.
 *
 * @param propertyName the name of declared property
 */
fun Project.getLocalProperty(propertyName: String): String {
    return getLocalProperty(propertyName, this)
}