package other.mvvmtemplate.src

import com.android.tools.idea.wizard.template.ProjectTemplateData

fun mvvmManifest(
    pageName: String,
    activityPackageName: String,
    data: ProjectTemplateData
) = """
    
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
package="${data.applicationPackage}">
    <application>
        <activity
	        android:name="${activityPackageName}.${pageName}Activity"
            android:exported="false" />
    </application>
</manifest>
"""