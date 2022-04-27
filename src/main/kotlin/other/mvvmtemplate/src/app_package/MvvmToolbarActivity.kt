package other.mvvmtemplate.src.app_package

import com.android.tools.idea.wizard.template.ProjectTemplateData

fun mvvmToolbarActivity(
    pageName: String,
    packageName: String,
    activityLayoutName: String,
    data: ProjectTemplateData
) = """
package $packageName

import android.content.Context
import android.content.Intent
import ${data.applicationPackage}.R
import ${data.applicationPackage}.base.BaseToolbarActivity
import ${data.applicationPackage}.databinding.Activity${pageName}Binding

class ${pageName}Activity : BaseToolbarActivity<Activity${pageName}Binding, ${pageName}ViewModel>() {
    companion object {
        @JvmStatic
        fun newInstance(context: Context) {
            context.startActivity(Intent(context, ${pageName}Activity::class.java))
        }
    }
    
    override fun layoutResID(): Int = R.layout.${activityLayoutName}

    override fun initView() {
        
    }

    override fun initListener() {
        
    }

    override fun loadData() {
        
    }

    override fun eventBus(code: Int, msg: Any) {

    }
    
    override fun onBack() {
        finish()
    }

    override fun title(): String = "标题"
}    
"""