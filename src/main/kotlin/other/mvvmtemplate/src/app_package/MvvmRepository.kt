package other.mvvmtemplate.src.app_package

fun mvvmRepository(
    pageName: String,
    packageName: String
) = """
package $packageName

object ${pageName}Repository {

}
"""