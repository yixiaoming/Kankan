package org.xiao.plugin.tinypng


import org.gradle.api.Plugin
import org.gradle.api.Project

class TinypngPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println("-------------TinypngPlugin start-------------")
        project.extensions.create("tinypngExtension", TinypngExtension)
        project.getTasks().create("compress", TinypngTask.class)
        println("-------------TinypngPlugin end-------------")
    }
}