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

//場所ミス
@SpringBootTest
@EnableWebMvc
public class dayGoalRegistControllerTest {

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
	  @DisplayName("dailyGoalRegistGet_正常系")
	  void dailyGoalRegistGet() throws Exception {
	    mockMvc.perform(get("/sns/dailyGoal/input"))
	    .andExpect(status().isOk()); 
	  }
	  
	  @Test
	  @DisplayName("dailyGoalRegistCheck_正常系")
	  void dailyGoalRegistCheck() throws Exception {
	    mockMvc.perform(get("/sns/dailyGoal/check"))
	    .andExpect(status().isOk()); 
	  }
	  @Test
	  @DisplayName("dailyGoalRegistComplete_正常系")
	  void dailyGoalRegistComplete() throws Exception {
	    mockMvc.perform(get("/sns/dailyGoal/complete"))
	    .andExpect(status().isOk()); 
	  }
	
}
