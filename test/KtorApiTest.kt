import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.TextContent
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class KtorApiTest {

    // Initialize the Ktor client with the coroutine-based CIO engine
    private val client = HttpClient(CIO)

    private val baseUrl = "https://jsonplaceholder.typicode.com/posts"

    @Test
    fun `GET - should fetch list of posts successfully`() = runBlocking {
        // 1. Execute the request
        val response = client.get(baseUrl)

        // 2. Ktor provides beautiful HttpStatusCode enums
        assertEquals(HttpStatusCode.OK, response.status)

        // 3. Extract the body as a String using a built-in suspend function
        val responseBody = response.bodyAsText()
        assertTrue(responseBody.contains("userId"), "Body should contain data")

        println("GET Success. Fetched data sample: ${responseBody.take(100)}...")
    }

    @Test
    fun `POST - should create a new post`() = runBlocking {
        val jsonPayload = """
            {
              "title": "Interview Practice",
              "body": "Ktor REST API testing",
              "userId": 1
            }
        """.trimIndent()

        // Pass a trailing lambda to the request to configure the body/headers
        val response = client.post(baseUrl) {
            setBody(TextContent(jsonPayload, ContentType.Application.Json))
        }

        // JSONPlaceholder returns 201 Created for a successful POST
        assertEquals(HttpStatusCode.Created, response.status)

        val responseBody = response.bodyAsText()
        assertTrue(responseBody.contains("Interview Practice"), "Response should contain the title")

        println("POST Success. Response: $responseBody")
    }

    @Test
    fun `PUT - should update an existing post`() = runBlocking {
        val targetUrl = "$baseUrl/1"

        val updatedJson = """
            {
              "id": 1,
              "title": "Updated Title",
              "body": "Updated body content",
              "userId": 1
            }
        """.trimIndent()

        val response = client.put(targetUrl) {
            setBody(TextContent(updatedJson, ContentType.Application.Json))
        }

        assertEquals(HttpStatusCode.OK, response.status)
        println("PUT Success. Response: ${response.bodyAsText()}")
    }

    @Test
    fun `DELETE - should remove a post`() = runBlocking {
        val targetUrl = "$baseUrl/1"

        val response = client.delete(targetUrl)

        assertEquals(HttpStatusCode.OK, response.status)
        println("DELETE Success. Status Code: ${response.status.value}")
    }
}
