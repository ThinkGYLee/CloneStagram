package com.gyleedev.clonestagram.ui.public

data class ItemData(
    val icon: UserIconImageType.IconFromUrlType,
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
        val initialItem = listOf(
            ItemData(
                icon = UserIconImageType.IconFromUrlType(
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f9/Android_logo_2.svg/2048px-Android_logo_2.svg.png"
                ),
                ownerId = "android",
                music = "♫ 뎁트・타이밍 (feat 김뮤지엄 & 에이민)",
                image = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgX98TKIsaJF7D4wnq7YBOuMjtYH-6D5Kgm7m7VbRek7cQIGN7TNVtJMDIbSiEG5KgcGyGpgGxEOz7u9v-WhQASrQrjvCQF8-RQ7PsZpA6djqK7RA7mXrnt6aYiac8voLef_mhP-s_TucPVEP1vvmUBjspmjA2RdrbvIqVwYXQJZ1fwPyamJIxXTrgMVmg/s1600/image1.png",
                heartBoolean = false,
                bookMarkBoolean = false,
                heartCount = 3638,
                content = "android 15 is coming",
                writtenTimeAgo = 2
            ),
            ItemData(
                icon = UserIconImageType.IconFromUrlType("https://velog.velcdn.com/images/cocoder/post/86fa8f97-d9aa-4f02-98e3-7d8a540f9227/image.png"),
                ownerId = "kotlin",
                music = "♫ 뎁트・타이밍 (feat 김뮤지엄 & 에이민)",
                image = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgX98TKIsaJF7D4wnq7YBOuMjtYH-6D5Kgm7m7VbRek7cQIGN7TNVtJMDIbSiEG5KgcGyGpgGxEOz7u9v-WhQASrQrjvCQF8-RQ7PsZpA6djqK7RA7mXrnt6aYiac8voLef_mhP-s_TucPVEP1vvmUBjspmjA2RdrbvIqVwYXQJZ1fwPyamJIxXTrgMVmg/s1600/image1.png",
                heartBoolean = false,
                bookMarkBoolean = false,
                heartCount = 3638,
                content = "android 15 is coming",
                writtenTimeAgo = 2
            ),
            ItemData(
                icon = UserIconImageType.IconFromUrlType(
                    "https://download.logo.wine/logo/JetBrains/JetBrains-Logo.wine.png"
                ),
                ownerId = "jetbrains",
                music = "♫ 뎁트・타이밍 (feat 김뮤지엄 & 에이민)",
                image = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgX98TKIsaJF7D4wnq7YBOuMjtYH-6D5Kgm7m7VbRek7cQIGN7TNVtJMDIbSiEG5KgcGyGpgGxEOz7u9v-WhQASrQrjvCQF8-RQ7PsZpA6djqK7RA7mXrnt6aYiac8voLef_mhP-s_TucPVEP1vvmUBjspmjA2RdrbvIqVwYXQJZ1fwPyamJIxXTrgMVmg/s1600/image1.png",
                heartBoolean = false,
                bookMarkBoolean = false,
                heartCount = 3638,
                content = "android 15 is coming",
                writtenTimeAgo = 2
            ),
            ItemData(
                icon = UserIconImageType.IconFromUrlType(
                    "https://developer.android.com/static/codelabs/jetpack-compose-animation/img/jetpack_compose_logo_with_rocket.png"
                ),
                ownerId = "compose",
                music = "♫ 뎁트・타이밍 (feat 김뮤지엄 & 에이민)",
                image = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgX98TKIsaJF7D4wnq7YBOuMjtYH-6D5Kgm7m7VbRek7cQIGN7TNVtJMDIbSiEG5KgcGyGpgGxEOz7u9v-WhQASrQrjvCQF8-RQ7PsZpA6djqK7RA7mXrnt6aYiac8voLef_mhP-s_TucPVEP1vvmUBjspmjA2RdrbvIqVwYXQJZ1fwPyamJIxXTrgMVmg/s1600/image1.png",
                heartBoolean = false,
                bookMarkBoolean = false,
                heartCount = 3638,
                content = "android 15 is coming",
                writtenTimeAgo = 2
            ),
            ItemData(
                icon = UserIconImageType.IconFromUrlType(
                    "https://miro.medium.com/v2/resize:fit:1024/1*Kxa_1fuHF4BMBQkq8Uyz0Q.jpeg"
                ),
                ownerId = "droid_bot",
                music = "♫ 뎁트・타이밍 (feat 김뮤지엄 & 에이민)",
                image = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgX98TKIsaJF7D4wnq7YBOuMjtYH-6D5Kgm7m7VbRek7cQIGN7TNVtJMDIbSiEG5KgcGyGpgGxEOz7u9v-WhQASrQrjvCQF8-RQ7PsZpA6djqK7RA7mXrnt6aYiac8voLef_mhP-s_TucPVEP1vvmUBjspmjA2RdrbvIqVwYXQJZ1fwPyamJIxXTrgMVmg/s1600/image1.png",
                heartBoolean = false,
                bookMarkBoolean = false,
                heartCount = 3638,
                content = "android 15 is coming",
                writtenTimeAgo = 2
            ),
            ItemData(
                icon = UserIconImageType.IconFromUrlType(
                    "https://cdn.logojoy.com/wp-content/uploads/20230801145608/Current-Google-logo-2015-2023-600x203.png"
                ),
                ownerId = "google",
                music = "♫ 뎁트・타이밍 (feat 김뮤지엄 & 에이민)",
                image = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgX98TKIsaJF7D4wnq7YBOuMjtYH-6D5Kgm7m7VbRek7cQIGN7TNVtJMDIbSiEG5KgcGyGpgGxEOz7u9v-WhQASrQrjvCQF8-RQ7PsZpA6djqK7RA7mXrnt6aYiac8voLef_mhP-s_TucPVEP1vvmUBjspmjA2RdrbvIqVwYXQJZ1fwPyamJIxXTrgMVmg/s1600/image1.png",
                heartBoolean = false,
                bookMarkBoolean = false,
                heartCount = 3638,
                content = "android 15 is coming",
                writtenTimeAgo = 2
            ),
            ItemData(
                icon = UserIconImageType.IconFromUrlType(
                    "https://upload.wikimedia.org/wikipedia/commons/f/f1/Samsung_logo_blue.png"
                ),
                ownerId = "samsung",
                music = "♫ 뎁트・타이밍 (feat 김뮤지엄 & 에이민)",
                image = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgX98TKIsaJF7D4wnq7YBOuMjtYH-6D5Kgm7m7VbRek7cQIGN7TNVtJMDIbSiEG5KgcGyGpgGxEOz7u9v-WhQASrQrjvCQF8-RQ7PsZpA6djqK7RA7mXrnt6aYiac8voLef_mhP-s_TucPVEP1vvmUBjspmjA2RdrbvIqVwYXQJZ1fwPyamJIxXTrgMVmg/s1600/image1.png",
                heartBoolean = false,
                bookMarkBoolean = false,
                heartCount = 3638,
                content = "android 15 is coming",
                writtenTimeAgo = 2
            ),
            ItemData(
                icon = UserIconImageType.IconFromUrlType(
                    "https://upload.wikimedia.org/wikipedia/commons/9/91/Samsung_Galaxy_Logo.png"
                ),
                ownerId = "galaxy",
                music = "♫ 뎁트・타이밍 (feat 김뮤지엄 & 에이민)",
                image = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgX98TKIsaJF7D4wnq7YBOuMjtYH-6D5Kgm7m7VbRek7cQIGN7TNVtJMDIbSiEG5KgcGyGpgGxEOz7u9v-WhQASrQrjvCQF8-RQ7PsZpA6djqK7RA7mXrnt6aYiac8voLef_mhP-s_TucPVEP1vvmUBjspmjA2RdrbvIqVwYXQJZ1fwPyamJIxXTrgMVmg/s1600/image1.png",
                heartBoolean = false,
                bookMarkBoolean = false,
                heartCount = 3638,
                content = "android 15 is coming",
                writtenTimeAgo = 2
            ),
            ItemData(
                icon = UserIconImageType.IconFromUrlType(
                    "https://seeklogo.com/images/L/lg-electronics-logo-5E5F0D42EB-seeklogo.com.png"
                ),
                ownerId = "lg",
                music = "♫ 뎁트・타이밍 (feat 김뮤지엄 & 에이민)",
                image = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgX98TKIsaJF7D4wnq7YBOuMjtYH-6D5Kgm7m7VbRek7cQIGN7TNVtJMDIbSiEG5KgcGyGpgGxEOz7u9v-WhQASrQrjvCQF8-RQ7PsZpA6djqK7RA7mXrnt6aYiac8voLef_mhP-s_TucPVEP1vvmUBjspmjA2RdrbvIqVwYXQJZ1fwPyamJIxXTrgMVmg/s1600/image1.png",
                heartBoolean = false,
                bookMarkBoolean = false,
                heartCount = 3638,
                content = "android 15 is coming",
                writtenTimeAgo = 2
            ),
            ItemData(
                icon = UserIconImageType.IconFromUrlType(
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e5/Motorola_logo.svg/2560px-Motorola_logo.svg.png"
                ),
                ownerId = "motorola",
                music = "♫ 뎁트・타이밍 (feat 김뮤지엄 & 에이민)",
                image = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgX98TKIsaJF7D4wnq7YBOuMjtYH-6D5Kgm7m7VbRek7cQIGN7TNVtJMDIbSiEG5KgcGyGpgGxEOz7u9v-WhQASrQrjvCQF8-RQ7PsZpA6djqK7RA7mXrnt6aYiac8voLef_mhP-s_TucPVEP1vvmUBjspmjA2RdrbvIqVwYXQJZ1fwPyamJIxXTrgMVmg/s1600/image1.png",
                heartBoolean = false,
                bookMarkBoolean = false,
                heartCount = 3638,
                content = "android 15 is coming",
                writtenTimeAgo = 2
            )
        )
    }
}
