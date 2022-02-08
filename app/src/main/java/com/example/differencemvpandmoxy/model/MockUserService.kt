package com.example.differencemvpandmoxy.model

import androidx.annotation.IntRange
import com.example.differencemvpandmoxy.dto.User
import com.example.differencemvpandmoxy.model.UserService.Companion.FIRST_PAGE
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

class MockUserService : UserService {

    companion object {
        private const val DEFAULT_RESPONSE_DELAY = 3_000L
        private const val USER_PER_PAGE = 10
    }

    private val USERS = listOf(
        User(
            1,
            "Masha",
            "Macko",
            "https://w7.pngwing.com/pngs/193/660/png-transparent-computer-icons-woman-avatar-avatar-girl-thumbnail.png"
        ),
        User(
            2,
            "Yerdaulet",
            "Kenzhegazin",
            "https://e7.pngegg.com/pngimages/269/335/png-clipart-emote-emoticon-avatar-discord-avatar-white-mammal-thumbnail.png"
        ),
        User(
            3,
            "Valeriy",
            "Kim",
            "https://e7.pngegg.com/pngimages/550/997/png-clipart-user-icon-foreigners-avatar-child-face-thumbnail.png"
        ),
        User(
            4,
            "Leyli",
            "Ushurova",
            "https://e7.pngegg.com/pngimages/101/6/png-clipart-roblox-internet-forum-youtube-avatar-like-button-youtube-internet-forum-avatar-thumbnail.png"
        ),
        User(
            5,
            "Ramil",
            "Mukhoryapov",
            "https://e7.pngegg.com/pngimages/674/524/png-clipart-professional-computer-icons-avatar-job-avatar-heroes-computer-thumbnail.png"
        ),
        User(
            6,
            "Nariman",
            "Ermekov",
            "https://e7.pngegg.com/pngimages/670/509/png-clipart-avatar-female-girls-avatar-purple-face-thumbnail.png"
        ),
        User(
            7,
            "Nurdaulet",
            "Anefiyaev",
            "https://e7.pngegg.com/pngimages/203/674/png-clipart-black-and-blue-wolf-illustration-wolf-avatar-splash-animals-thumbnail.png"
        ),
        User(
            8,
            "Alena",
            "Nikolskaya",
            "https://e7.pngegg.com/pngimages/105/603/png-clipart-anime-avatar-desktop-anime-manga-head-thumbnail.png"
        ),
        User(
            9,
            "Nurken",
            "Rzaliev",
            "https://e7.pngegg.com/pngimages/499/739/png-clipart-roblox-avatar-character-summertime-2009-keyword-tool-avatar-heroes-fictional-character-thumbnail.png"
        ),
        User(
            10,
            "Azat",
            "Smagulov",
            "https://e7.pngegg.com/pngimages/347/200/png-clipart-roblox-avatar-rendering-character-avatar-heroes-fictional-character-thumbnail.png"
        ),
        User(
            11,
            "Ivan",
            "Makhambetov",
            "https://e7.pngegg.com/pngimages/456/700/png-clipart-computer-icons-avatar-user-profile-avatar-heroes-logo-thumbnail.png"
        ),
        User(
            12,
            "Loot",
            "Nurakhmetov",
            "https://w7.pngwing.com/pngs/193/660/png-transparent-computer-icons-woman-avatar-avatar-girl-thumbnail.png"
        ),
        User(
            13,
            "Maxim",
            "Bakiev",
            "https://e7.pngegg.com/pngimages/43/926/png-clipart-computer-icons-avatar-user-avatar-heroes-woman-thumbnail.png"
        ),
        User(
            14,
            "Anvar",
            "Bakiev",
            "https://e7.pngegg.com/pngimages/551/804/png-clipart-discord-logo-avatar-avatar-heroes-logo-thumbnail.png"
        ),
        User(
            15,
            "Olga",
            "Yegorova",
            "https://e7.pngegg.com/pngimages/340/946/png-clipart-avatar-user-computer-icons-software-developer-avatar-child-face-thumbnail.png"
        ),
        User(
            16,
            "Alexandr",
            "Pskovitin",
            "https://e7.pngegg.com/pngimages/799/987/png-clipart-computer-icons-avatar-icon-design-avatar-heroes-computer-wallpaper-thumbnail.png"
        ),
    )

    override suspend fun getUsers(
        @IntRange(from = FIRST_PAGE.toLong()) page: Int
    ): List<User> {
        delay(DEFAULT_RESPONSE_DELAY)
        val fromIndex = page * USER_PER_PAGE - USER_PER_PAGE
        val toIndex = (page * USER_PER_PAGE) % USERS.size
        return USERS.subList(fromIndex, toIndex)
    }

    override fun deleteUser(user: User): Flow<Int> {

    }
}