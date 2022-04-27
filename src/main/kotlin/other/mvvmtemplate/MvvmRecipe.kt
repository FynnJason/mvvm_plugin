package other.mvvmtemplate

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import other.mvvmtemplate.res.layout.mvvmLayout
import other.mvvmtemplate.src.app_package.*
import other.mvvmtemplate.src.mvvmManifest
import java.io.File

fun RecipeExecutor.mvvmRecipe(
    pageName: String,
    packageName: String,
    activityOrFragment: MvvmPluginTemplateProviderImpl.MyEnum,
    activityLayoutName: String,
    fragmentLayoutName: String,
    moduleData: ModuleTemplateData
) {
    val (projectData) = moduleData
    // 创建viewModel
    val viewModelFile = File(
        moduleData.rootDir,
        "${fFmSlashedPackageName(packageName)}/${pageName}ViewModel.kt"
    )
    save(mvvmViewModel(pageName, packageName, projectData), viewModelFile)
    // 判断是activity还是fragment
    if (activityOrFragment.name == "Activity" || activityOrFragment.name == "ToolbarActivity") {
        val activityPackageName =
            packageName.replace(projectData.applicationPackage!!.toString(), "")
        // 创建注册清单
        mergeXml(
            mvvmManifest(pageName, activityPackageName, projectData),
            File(moduleData.manifestDir, "AndroidManifest.xml")
        )
        // 创建xml
        save(mvvmLayout(pageName,packageName), File(moduleData.resDir,"layout/${activityLayoutName}.xml" ))
        // 创建activity
        val activityFile = File(moduleData.rootDir,"${fFmSlashedPackageName(packageName)}/${pageName}Activity.kt")
        if (activityOrFragment.name == "Activity"){
            save(mvvmActivity(pageName,packageName,activityLayoutName,projectData),activityFile)
        }else{
            save(mvvmToolbarActivity(pageName,packageName,activityLayoutName,projectData),activityFile)
        }
        open(activityFile)
    } else {
        // 创建xml
        save(mvvmLayout(pageName,packageName), File(moduleData.resDir,"layout/${fragmentLayoutName}.xml" ))
        // 创建activity
        val fragmentFile = File(moduleData.rootDir,"${fFmSlashedPackageName(packageName)}/${pageName}Fragment.kt")
        save(mvvmFragment(pageName,packageName,fragmentLayoutName,projectData),fragmentFile)
        open(fragmentFile)
    }
}

fun fFmSlashedPackageName(oVar: String): String {
    return "src/main/java/${oVar.replace('.', '/')}"
}

