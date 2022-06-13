package github.mewgrammer.shopbuddy

import github.mewgrammer.shopbuddy.api.controller.ListController
import github.mewgrammer.shopbuddy.testcontainer.TestContainerBaseTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.get

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
}