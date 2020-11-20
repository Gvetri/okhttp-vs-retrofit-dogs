import com.google.common.truth.Truth.assertThat
import dev.gvetri.apimodel.PaginatedVideoListApi
import dev.gvetri.apimodel.VideoItemApi
import dev.gvetri.model.PaginatedVideoList
import dev.gvetri.model.VideoItem
import dev.gvetri.networkdatasource.paginatedVideoListMapper
import org.junit.Test

class PaginatedVideoListMapperTest {

    @Test
    fun `Given an empty PaginatedVideoListApi mapper should return a PaginatedVideoList with the default options`() {
        //given
        val paginatedVideoListApi = null
        //when
        val actual = paginatedVideoListMapper(paginatedVideoListApi)
        //then
        val expected = PaginatedVideoList(
            explicit = false,
            hasMore = false,
            limit = 10,
            list = emptyList(),
            page = 0,
            total = 20
        )
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Given a PaginatedVideoListApi with emptyList videolist should return an empty PaginatedVideoList`() {
        //given
        val paginatedVideoListApi = PaginatedVideoListApi(
            explicit = false,
            hasMore = false,
            limit = 0,
            list = null,
            page = 0,
            total = 0,
        )
        //when
        val actual = paginatedVideoListMapper(paginatedVideoListApi)
        //then
        val expected = PaginatedVideoList(
            explicit = false,
            hasMore = false,
            limit = 0,
            list = emptyList(),
            page = 0,
            total = 0
        )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Given a PaginatedVideoListApi with all correct data should return a filled videoList`() {
        //given
        val channel = "news"
        val id = "x7xm1ex"
        val id1 = "x7xm1jj"
        val owner = "x28sc85"
        val owner1 = "x1d08d3"
        val title = "Por qué Luis renunció a convertirse en el doctor Piedrahita"
        val title1 = "Las malas noticias del Secretario General de la ONU"
        val thumbnailUrl = "https://s1.dmcdn.net/v/Sb-vF1Vk1IqseOxdr/x240"
        val thumbnailUrl1 = "https://s1.dmcdn.net/v/Sb-sf1Vk1JBEH4Mxd/x240"

        val videoItemApiList = listOf(
            VideoItemApi(
                channel = channel,
                id = id,
                owner = owner,
                title = title,
                thumbnail_240_url = thumbnailUrl
            ),
            VideoItemApi(
                channel = channel,
                id = id1,
                owner = owner1,
                title = title1,
                thumbnail_240_url = thumbnailUrl1
            )
        )

        val paginatedVideoListApi = PaginatedVideoListApi(
            explicit = false,
            hasMore = true,
            limit = 10,
            list = videoItemApiList,
            page = 1,
            total = 1000,
        )

        //when
        val actual = paginatedVideoListMapper(paginatedVideoListApi)

        //then
        val videoItemList = listOf(
            VideoItem(
                channel = channel,
                id = id,
                owner = owner,
                title = title,
                thumbnailUrl = thumbnailUrl
            ),
            VideoItem(
                channel = channel,
                id = id1,
                owner = owner1,
                title = title1,
                thumbnailUrl = thumbnailUrl1
            )

        )
        val expected = PaginatedVideoList(
            explicit = false,
            hasMore = true,
            limit = 10,
            list = videoItemList,
            page = 1,
            total = 1000
        )

        assertThat(actual).isEqualTo(expected)
    }
}