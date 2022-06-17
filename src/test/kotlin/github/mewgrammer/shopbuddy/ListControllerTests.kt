package github.mewgrammer.shopbuddy

import github.mewgrammer.shopbuddy.api.controller.ListController
import github.mewgrammer.shopbuddy.api.model.request.CreateShoppingListDto
import github.mewgrammer.shopbuddy.testcontainer.TestContainerBaseTest
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

class ListControllerTests : TestContainerBaseTest() {

    @Autowired
    private lateinit var controller: ListController

    @Test
    fun `returns stored shopping lists`() {
        mockMvc.get("/api/list") {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
        }
    }

    @Test
    fun `creates a new shopping lists`() {
        val toCreate = CreateShoppingListDto("test-shopping-list", "", emptyList())
        mockMvc.post("/api/list") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(toCreate)
        }.andExpect {
            status { isCreated() }
            content {
                contentType(MediaType.APPLICATION_JSON)
                jsonPath("$.name", equalTo(toCreate.name))
            }
        }
    }
}