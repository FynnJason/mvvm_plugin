package com.fynnjason.mvvmtemplate.services

import com.intellij.openapi.project.Project
import com.fynnjason.mvvmtemplate.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
