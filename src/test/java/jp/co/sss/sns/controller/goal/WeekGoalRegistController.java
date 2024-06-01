package jp.co.sss.sns.controller.goal;

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
public class WeekGoalRegistController {

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
	  @DisplayName("weekGoalRegistGet_正常系")
	  void weekGoalRegistGet() throws Exception {
	    mockMvc.perform(get("/sns/weekGoal/input"))
	    .andExpect(status().isOk()); 
	  }
	  
	  @Test
	  @DisplayName("weekGoalRegistCheck_正常系")
	  void weekGoalRegistCheck() throws Exception {
	    mockMvc.perform(get("/sns/weekGoal/check"))
	    .andExpect(status().isOk()); 
	  }
	  @Test
	  @DisplayName("weekGoalRegistComplete_正常系")
	  void weekGoalRegistComplete() throws Exception {
	    mockMvc.perform(get("/sns/weekGoal/complete"))
	    .andExpect(status().isOk()); 
	  }
	  
	  
	  
	  
	  
	  
	  
	
	
}
