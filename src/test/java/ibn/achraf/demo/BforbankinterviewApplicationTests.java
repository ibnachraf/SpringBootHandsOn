package ibn.achraf.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class BforbankinterviewApplicationTests {

	@Autowired
	public MockMvc mockMVC;

	@Test
	void testHomePage() throws Exception {
		mockMVC.perform(get("/")).andExpect(status().isOk());
	}

}
