package other.mvvmtemplate

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import java.io.File
import javax.lang.model.type.TypeVariable

class MvvmPluginTemplateProviderImpl : WizardTemplateProvider() {
    override fun getTemplates(): List<Template> = listOf(
        mvvmTemplate
    )

    val mvvmTemplate: Template
        get() = template {
            name = "FynnJason MVVM"
            description = "Generate MVVM plugin"
            minApi = MIN_API
            category = Category.Other
            formFactor = FormFactor.Mobile
            screens = listOf(
                WizardUiContext.ActivityGallery,
                WizardUiContext.MenuEntry,
                WizardUiContext.NewProject,
                WizardUiContext.NewModule
            )
            thumb { File("template_blank_activity.png") }

            val pageName = stringParameter {
                name = "Page Name"
                constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY, Constraint.STRING)
                default = "Main"
                help = ""
            }

            val packageName = stringParameter {
                name = "Package Name"
                constraints = listOf(Constraint.PACKAGE)
                default = "com.mycompany.myapp"
                help = ""
            }

            val activityOrFragment = enumParameter<MyEnum> {
                name = "Generate Activity Or Fragment"
                default = MyEnum.Activity
                help = ""
            }

            val activityLayoutName = stringParameter {
                name = "Activity Layout Name"
                constraints = listOf(Constraint.LAYOUT, Constraint.NONEMPTY)
                suggest = { activityToLayout(pageName.value) }
                default = "activity_main"
                visible = { activityOrFragment.value.name == "Activity" || activityOrFragment.value.name == "ToolbarActivity"}
                help = ""
            }

            val fragmentLayoutName = stringParameter {
                name = "Fragment Layout Name"
                constraints = listOf(Constraint.LAYOUT, Constraint.NONEMPTY)
                suggest = { "fragment_${classToResource(pageName.value)}" }
                default = "fragment_main"
                visible = { activityOrFragment.value.name == "Fragment" }
                help = ""
            }

            widgets(
                TextFieldWidget(pageName),
                PackageNameWidget(packageName),
                EnumWidget(activityOrFragment),
                TextFieldWidget(activityLayoutName),
                TextFieldWidget(fragmentLayoutName)
            )

            recipe = { te ->
                mvvmRecipe(
                    pageName.value,
                    packageName.value,
                    activityOrFragment.value,
                    activityLayoutName.value,
                    fragmentLayoutName.value,
                    (te as ModuleTemplateData)
                )
            }

        }

    enum class MyEnum {
        Activity {
            override fun toString(): String {
                return "Activity"
            }
        },
        ToolbarActivity{
            override fun toString(): String {
                return "ToolbarActivity"
            }
        },
        Fragment {
            override fun toString(): String {
                return "Fragment"
            }
        }
    }


//    val pageName = stringParameter {
//        name = "Page Name"
//        constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY, Constraint.STRING)
//        default = "Main"
//        help = ""
//    }


    // 包名
//    val appPackageName = stringParameter {
//        name = "包名"
//        constraints = listOf(Constraint.PACKAGE)
//        default = "com.mycompany.myapp"
//        help = "文件生成的位置"
//    }
//
//    //是否需要
//    val needActivity = booleanParameter {
//        name = "Generate Activity"
//        default = true
//        help = "是否需要生成 Activity ? 不勾选则不生成"
//    }
//
//    //组件化相关
//    val isModule = booleanParameter {
//        name = "Is Module"
//        default = true
//        help = "是否是组件化模块，如果是就会在两个AndroidManifest.xml都加上activity标签"
//    }
//
//    //layout xml 文件
//    val activityLayoutName = stringParameter {
//        name = "Activity Layout Name"
//        constraints = listOf(Constraint.LAYOUT, Constraint.NONEMPTY)
//        suggest = { activityToLayout(pageName.value) }
//        default = "activity_main"
//        visible = { needActivity.value }
//        help = "Activity 创建之前需要填写 Activity 的布局名,若布局已创建就直接填写此布局名,若还没创建此布局,请勾选下面的单选框"
//    }

    //是否需要 layout xml 文件
//    val generateActivityLayout = booleanParameter {
//        name = "Generate Activity Layout"
//        default = true
//        visible = { needActivity.value }
//        help = "是否需要给 Activity 生成布局? 若勾选,则使用上面的布局名给此 Activity 创建默认的布局"
//    }
//
//    //Activity 路径
//    val activityPackageName = stringParameter {
//        name = "Ativity Package Name"
//        constraints = listOf(Constraint.STRING)
//        suggest = { "${appPackageName.value}.mvp.ui.activity" }
//        visible = { needActivity.value }
//        default = "${appPackageName.value}.mvp.ui.activity"
//        help = "Activity 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
//    }

    //是否需要生成 Fragment
//    val needFragment = booleanParameter {
//        name = "Generate Fragment"
//        default = false
//        help = "是否需要生成 Fragment ? 不勾选则不生成"
//    }
//
//    //Fragment xml 文件
//    val fragmentLayoutName = stringParameter {
//        name = "Fragment Layout Name"
//        constraints = listOf(Constraint.LAYOUT, Constraint.NONEMPTY)
//        suggest = { "fragment_${classToResource(pageName.value)}" }
//        default = "fragment_main"
//        visible = { needFragment.value }
//        help = "Fragment 创建之前需要填写 Fragment 的布局名,若布局已创建就直接填写此布局名,若还没创建此布局,请勾选下面的单选框"
//    }

    //是否需要生成 Fragment layout 文件
//    val generateFragmentLayout = booleanParameter {
//        name = "Generate Fragment Layout"
//        default = true
//        visible = { needFragment.value }
//        help = "是否需要给 Fragment 生成布局? 若勾选,则使用上面的布局名给此 Fragment 创建默认的布局"
//    }
//
//    //fragment 路径
//    val fragmentPackageName = stringParameter {
//        name = "Fragment Package Name"
//        constraints = listOf(Constraint.STRING)
//        suggest = { "${appPackageName.value}.mvp.ui.fragment" }
//        visible = { needFragment.value }
//        default = "${appPackageName.value}.mvp.ui.fragment"
//        help = "Fragment 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
//    }


    // mvp 相关
//    val needContract = booleanParameter {
//        name = "Generate Contract"
//        default = true
//        help = "是否需要生成 Contract ? 不勾选则不生成"
//    }
//
//    val contractPackageName = stringParameter {
//        name = "Contract Package Name"
//        constraints = listOf(Constraint.STRING)
//        suggest = { "${appPackageName.value}.mvp.contract" }
//        visible = { needContract.value }
//        default = "${appPackageName.value}.mvp.contract"
//        help = "Contract 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
//    }

//    val needPresenter = booleanParameter {
//        name = "Generate Presenter"
//        default = true
//        help = "是否需要生成 Presenter ? 不勾选则不生成"
//    }
//
//    val presenterPackageName = stringParameter {
//        name = "Presenter Package Name"
//        constraints = listOf(Constraint.STRING)
//        suggest = { "${appPackageName.value}.mvp.presenter" }
//        visible = { needPresenter.value }
//        default = "${appPackageName.value}.mvp.presenter"
//        help = "Presenter 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
//    }
//
//    val needModel = booleanParameter {
//        name = "Generate Model"
//        default = true
//        help = "是否需要生成 Model ? 不勾选则不生成"
//    }
//
//    val modelPackageName = stringParameter {
//        name = "Model Package Name"
//        constraints = listOf(Constraint.STRING)
//        suggest = { "${appPackageName.value}.mvp.model" }
//        visible = { needModel.value }
//        default = "${appPackageName.value}.mvp.model"
//        help = "Model 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
//
//    }

    //dagger 相关
//    val needDagger = booleanParameter {
//        name = "Generate Dagger (Moudle And Component)"
//        default = true
//        help = "是否需要生成 Dagger 组件? 不勾选则不生成"
//    }
//    val componentPackageName = stringParameter {
//        name = "Component Package Name"
//        constraints = listOf(Constraint.STRING)
//        suggest = { "${appPackageName.value}.di.component" }
//        visible = { needDagger.value }
//        default = "${appPackageName.value}.di.component"
//        help = "Component 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
//    }
//    val moudlePackageName = stringParameter {
//        name = "Moudle Package Name"
//        constraints = listOf(Constraint.STRING)
//        suggest = { "${appPackageName.value}.di.module" }
//        visible = { needDagger.value }
//        default = "${appPackageName.value}.di.module"
//        help = "Moudle 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名"
//    }

}