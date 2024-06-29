package com.gyleedev.clonestagram.ui.public

import com.gyleedev.clonestagram.R

data class ItemData(
    val icon: Int,
    val ownerId: String,
    val music: String,
    val image: String,
    val heartBoolean: Boolean,
    val bookMarkBoolean: Boolean,
    val heartCount: Int,
    val content: String,
    val writtenTimeAgo: Int

) {
    companion object {
        val initialItem = ItemData(
            icon = R.drawable.icons8_test_account_96,
            ownerId = "think_gy_lee",
            music = "♫ 뎁트・타이밍 (feat 김뮤지엄 & 에이민)",
            image = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgX98TKIsaJF7D4wnq7YBOuMjtYH-6D5Kgm7m7VbRek7cQIGN7TNVtJMDIbSiEG5KgcGyGpgGxEOz7u9v-WhQASrQrjvCQF8-RQ7PsZpA6djqK7RA7mXrnt6aYiac8voLef_mhP-s_TucPVEP1vvmUBjspmjA2RdrbvIqVwYXQJZ1fwPyamJIxXTrgMVmg/s1600/image1.png",
            heartBoolean = false,
            bookMarkBoolean = false,
            heartCount = 3638,
            content = "android 15 is coming",
            writtenTimeAgo = 2
        )
    }
}
