package org.xiao.plugin.tinypng.compressor

import com.tinify.Source
import com.tinify.Tinify

class TinypngCompressor implements ICompressor {

    TinypngCompressor(String key) {
        Tinify.setKey(key)
    }

    @Override
    boolean compress(String from, String to) {
        try {
            Source sourceFile = Tinify.fromFile(from)
            sourceFile.toFile(to)
        } catch (Exception e) {
            println("[compress -> from:$from   to:$to]     failed, e:$e")
            return false
        }
        println("[compress -> from:$from   to:$to]     success")
        return true
    }
}