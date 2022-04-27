package other.mvvmtemplate.src.app_package

import com.android.tools.idea.wizard.template.ProjectTemplateData

fun mvvmViewModel(
    pageName: String,
    packageName: String,
    data: ProjectTemplateData
) = """
package $packageName

import ${data.applicationPackage}.base.BaseViewModel

class ${pageName}ViewModel : BaseViewModel() {

}
"""