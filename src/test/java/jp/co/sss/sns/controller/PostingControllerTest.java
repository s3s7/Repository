package jp.co.sss.sns.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootTest
@EnableWebMvc
public class PostingControllerTest {

	
	 MockMvc mockMvc;

	  @Autowired WebApplicationContext webApplicationContext;
	 // @Autowired LogFilter logFilter;
	  
	  // モックの設定と解放
	  private AutoCloseable closeable;
	  
	  @BeforeEach public void openMocks() {
		    closeable = MockitoAnnotations.openMocks(this);
		  }
		  @AfterEach public void releaseMocks() throws Exception {
		    closeable.close();
		  }
	  @BeforeEach
	  void beforeEach() {
	    mockMvc =
	        MockMvcBuilders.webAppContextSetup(webApplicationContext)     // MockMVCをセットアップ
	     //       .addFilter(logFilter, "/*")                               // ただしfilterは手動で追加が必要
	            .build();
	  }
	  
	  @Test
	  @DisplayName("showList_正常系")
	  void showList() throws Exception {
	    mockMvc.perform(get("/snssns/findAll"))
	    .andExpect(status().isOk()); 
	  }
	  
	  @Test
	  @DisplayName("postInput_正常系")
	  void postInput() throws Exception {
	    mockMvc.perform(get("/snssns/posts"))
	    .andExpect(status().isOk()); 
	  }
	  
	  @Test
	  @DisplayName("postCheck_正常系")
	  void postCheck() throws Exception {
	    mockMvc.perform(get("/snssns/posting"))
	    .andExpect(status().isOk()); 
	  }
	  
	  @Test
	  @DisplayName("postRegist_正常系")
	  void postRegist() throws Exception {
	    mockMvc.perform(get("/snssns/dopost"))
	    .andExpect(status().isOk()); 
	  }
	  
	  @Test
	  @DisplayName("postComplete_正常系")
	  void postComplete() throws Exception {
	    mockMvc.perform(get("/snssns/complete"))
	    .andExpect(status().isOk()); 
	  }
	  
	  @Test
	  @DisplayName("newSort_正常系")
	  void newSort() throws Exception {
	    mockMvc.perform(get("/sns/newSort"))
	    .andExpect(status().isOk()); 
	  }
	  
	  @Test
	  @DisplayName("oldSort_正常系")
	  void oldSort() throws Exception {
	    mockMvc.perform(get("/sns/oldSort"))
	    .andExpect(status().isOk()); 
	  }
	  
	  @Test
	  @DisplayName("commentManySort_正常系")
	  void commentManySort() throws Exception {
	    mockMvc.perform(get("/sns/commentManySort"))
	    .andExpect(status().isOk()); 
	  }
	  
	  @Test
	  @DisplayName("commentLessSort_正常系")
	  void commentLessSort() throws Exception {
	    mockMvc.perform(get("/sns/commentLessSort"))
	    .andExpect(status().isOk()); 
	  }
	  
	  @Test
	  @DisplayName("searchPostingTitle_正常系")
	  void searchPostingTitle() throws Exception {
	    mockMvc.perform(get("/postingTitle/search"))
	    .andExpect(status().isOk()); 
	  }
	  
}
