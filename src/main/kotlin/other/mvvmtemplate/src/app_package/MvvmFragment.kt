package other.mvvmtemplate.src.app_package
import com.android.tools.idea.wizard.template.ProjectTemplateData

fun mvvmFragment(
    pageName: String,
    packageName: String,
    fragmentLayoutName: String,
    data: ProjectTemplateData
) = """
package $packageName

import android.os.Bundle
import ${data.applicationPackage}.R
import ${data.applicationPackage}.base.BaseFragment
import ${data.applicationPackage}.databinding.Fragment${pageName}Binding

class ${pageName}Fragment : BaseFragment<Fragment${pageName}Binding, ${pageName}ViewModel>() {
    private var param1: String? = null
    private var param2: String? = null
    
    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ${pageName}Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    
    override fun layoutResID(): Int = R.layout.${fragmentLayoutName}

    override fun initView() {
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun initListener() {

    }

    override fun loadData() {

    }

    override fun eventBus(code: Int, msg: Any) {

    }
}    
"""