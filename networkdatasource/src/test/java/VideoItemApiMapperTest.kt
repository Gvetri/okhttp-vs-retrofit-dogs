import com.google.common.truth.Truth.assertThat
import dev.gvetri.apimodel.VideoItemApi
import dev.gvetri.model.NON_AVAILABLE
import dev.gvetri.model.VideoItem
import dev.gvetri.networkdatasource.videoItemApiMapper
import org.junit.Test

class VideoItemApiMapperTest {

    private val channel = "news"
    private val id = "x7xm1ex"
    private val owner = "x28sc85"
    private val title = "Por qué Luis renunció a convertirse en el doctor Piedrahita"
    private val thumbnailUrl = "https://s1.dmcdn.net/v/Sb-vF1Vk1IqseOxdr/x240"

    @Test
    fun `Given a non existent VideoItemApi should return null`() {
        //given
        val videoItemApi = null
        //when
        val actual = videoItemApiMapper(videoItemApi)
        //then
        val expected = null

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Given a VideoItemApi without a channel should return VideoItem with NonAvailable as channel value`() {
        //given
        val videoItemApi = VideoItemApi(
            channel = null,
            id = id,
            owner = owner,
            title = title,
            thumbnail_240_url = thumbnailUrl
        )
        //when
        val actual = videoItemApiMapper(videoItemApi)
        //then
        val expected = VideoItem(
            channel = NON_AVAILABLE,
            id = id,
            owner = owner,
            title = title,
            thumbnailUrl = thumbnailUrl
        )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Given a VideoItemApi without a owner should return VideoItem with NonAvailable as owner value`() {
        //given
        val videoItemApi = VideoItemApi(
            channel = channel,
            id = id,
            owner = null,
            title = title,
            thumbnail_240_url = thumbnailUrl
        )
        //when
        val actual = videoItemApiMapper(videoItemApi)
        //then
        val expected = VideoItem(
            channel = channel,
            id = id,
            owner = NON_AVAILABLE,
            title = title,
            thumbnailUrl = thumbnailUrl
        )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Given a VideoItemApi without a title should return VideoItem with NonAvailable as title value`() {
        //given
        val videoItemApi = VideoItemApi(
            channel = channel,
            id = id,
            owner = owner,
            title = null,
            thumbnail_240_url = thumbnailUrl
        )
        //when
        val actual = videoItemApiMapper(videoItemApi)
        //then
        val expected = VideoItem(
            channel = channel,
            id = id,
            owner = owner,
            title = NON_AVAILABLE,
            thumbnailUrl = thumbnailUrl
        )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Given a VideoItemApi without a thumbnail url should return VideoItem with thumbnail url null value`() {
        //given
        val videoItemApi = VideoItemApi(
            channel = channel,
            id = id,
            owner = owner,
            title = title,
            thumbnail_240_url = null
        )
        //when
        val actual = videoItemApiMapper(videoItemApi)
        //then
        val expected = VideoItem(
            channel = channel,
            id = id,
            owner = owner,
            title = title,
            thumbnailUrl = null
        )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Given a VideoItemApi without an id should return a null VideoItem`() {
        //given
        val videoItemApi = VideoItemApi(
            channel = channel,
            id = null,
            owner = owner,
            title = title,
            thumbnail_240_url = thumbnailUrl
        )
        //when
        val actual = videoItemApiMapper(videoItemApi)
        //then
        val expected = null

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Given a VideoItemApi should return a VideoItem`() {
        //given
        val videoItemApi = VideoItemApi(
            channel = channel,
            id = id,
            owner = owner,
            title = title,
            thumbnail_240_url = thumbnailUrl
        )
        //when
        val actual = videoItemApiMapper(videoItemApi)
        //then
        val expected = VideoItem(
            channel = channel,
            id = id,
            owner = owner,
            title = title,
            thumbnailUrl = thumbnailUrl
        )

        assertThat(actual).isEqualTo(expected)
    }
}