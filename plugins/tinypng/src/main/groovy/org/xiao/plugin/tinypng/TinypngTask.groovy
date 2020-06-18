package org.xiao.plugin.tinypng

import com.tinify.Source
import com.tinify.Tinify
import groovy.io.FileType
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.xiao.plugin.tinypng.compressor.ICompressor
import org.xiao.plugin.tinypng.compressor.TinypngCompressor

import javax.imageio.ImageIO

class TinypngTask extends DefaultTask {

    TinypngTask() {
        group = "tinypng"
    }

    @TaskAction
    void compress() {
        TinypngExtension config = project.extensions.findByName('tinypngExtension')
        if (config == null) {
            println("Failed! have no tinypngExtension configuration!")
            return
        }

        ICompressor compressor = new TinypngCompressor(config.key)
        Tinify.setKey(config.key)

        if (TinypngExtension.TYPE_TINYPNG == config.compressType) {
            project.getRootDir().eachFileRecurse(FileType.FILES) { file ->
                if (file.name.endsWith(".png")
                        && !file.name.endsWith(".9.png")
                        && !file.name.contains("build")) {
                    int bit = ImageIO.read(file).colorModel.pixelSize
                    println("$file bit:$bit")
                    if (bit > 8) {
                        compressor.compress(file.absolutePath, "${file.absolutePath}")
                    }
                }
            }
        } else {
            println("Failed! Unknow compress type")
        }
    }
}