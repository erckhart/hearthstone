import GameInfoTestMockFactory.mockServerResponse
import com.voidx.data.network.deserializer.GameInfoDeserializer
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GameInfoDeserializerTest {

    private lateinit var deserializer: GameInfoDeserializer

    @Before
    fun setup() {
        deserializer = GameInfoDeserializer()
    }

    @Test
    fun `test successfully deserialization`() {

        val key = "hearthStone"
        val values = listOf("Death Knight", "Druid", "Hunter")

        val json = mockServerResponse(key, values)

        val gameInfo = deserializer.deserialize(json, null, null)

        assertEquals(1, gameInfo.options.size)
        assertEquals(values.size, gameInfo.options[0].values.size)
        assertEquals(key.capitalize(), gameInfo.options[0].title)
        assertEquals(values[0], gameInfo.options[0].values[0])
    }

}