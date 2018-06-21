package com.modoo.cg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.modoo.cg.command.*;
import com.modoo.cg.util.Constant;
import com.naver.naverlogintutorial.oauth.bo.NaverLoginBO;

@Controller
public class mController {
	Command command;
	
	public JdbcTemplate template;
	
	/*
	친절한 영준이형이 주석으로 설명해준다
	JdbcTemplate는  servlet-context.xml에 지정되어 있다. 가서 보면 ref="dataSource"라고 되어 있는데
	dataSource가 뭐냐면 connection하고 root하고 등등 연결되어있는것들이 설정되어 있는 것이 보임 JdbcTemplate은 이런것들을 미리 가지고 있어서
	얘 불러다 쓰면 Connection, PreparedStatement, ResultSet을 쓸 필요가 없다.
	
	Dao.java 들어가보면
	template.qieryForObject(쿼리문,new BeanPropertyRowMapper<매핑타입>(매핑위지)) 라고 보일텐데
	이게 쿼리문 날려서 VO에 저장했다가 뽑아오는 역할을 전부 다하고 있는거임 게다가 mapper라서 "key",value로 값을 던지는 탓에 
	resultSet.get("key"); 으로 key를 던지지 않아도 해당하는 key에 맞는 VO에 값을 저장해주는 역할을 함
	
	한마디로 졸라 킹왕짱인데 
	if(connection!=null)connection.close(); 까지 해주는 막강한 능력을 가지고 있음
	*/
	
	@Autowired
	public void setTemplat(JdbcTemplate template) {
		this.template=template;
		Constant.template=this.template;
	}
	
	//로그인
	/*@RequestMapping("/login")
	public String login(Model model) {
		System.out.println("login()");
		
		return "login";
	}*/
	
	
	//메인페이지
	@RequestMapping("/")
	public String home(Model model) {
		
		
		
		return "index";
	}
	
	
	
	//mapping
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("list()");
		
		command=new ListCommand();
		command.execute(model);
		
		return "list";
	}
	
	@RequestMapping("/write_view")
	public String write_view(Model model) {
		System.out.println("write_view()");
		
		return "write_view";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		System.out.println("write()");
		
		model.addAttribute("request", request);
		command = new WriteCommand();
		command.execute(model);
		
		
		return "redirect:list";
	}
	
	@RequestMapping("content_view")
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("content_view()");
		
		model.addAttribute("request", request);
		command = new ContentCommand();
		command.execute(model);
		
		return "content_view";
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/modify")
	public String modify(HttpServletRequest request, Model model) {
		System.out.println("modify()");
		
		model.addAttribute("request", request);
		command = new ModifyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		System.out.println("reply_view()");
		
		model.addAttribute("request", request);
		command = new ReplyViewCommand();
		command.execute(model);
		
		return "reply_view";
	}
	
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("reply()");
		
		model.addAttribute("request", request);
		command = new ReplyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("delete()");
		
		model.addAttribute("request", request);
		command = new DeleteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	/* NaverLoginBO */
	private NaverLoginBO naverLoginBO;

	/* NaverLoginBO */
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO){
		this.naverLoginBO = naverLoginBO;
	}
	
	
    @RequestMapping("/login")
    public ModelAndView login(HttpSession session) {
        /* 네아로 인증 URL을 생성하기 위하여 getAuthorizationUrl을 호출 */
        String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
        
        /* 생성한 인증 URL을 View로 전달 */
        return new ModelAndView("login", "url", naverAuthUrl);
    }
    
    
    @RequestMapping("/callback")
	public ModelAndView callback(@RequestParam String code, @RequestParam String state, HttpSession session) throws IOException {
		OAuth2AccessToken oauthToken = naverLoginBO.getAccessToken(session, code, state);
		String apiResult = naverLoginBO.getUserProfile(oauthToken);
		
		
	
		return new ModelAndView("callback", "result", apiResult);
	}
	
    
   @RequestMapping("/logout")
   	public ModelAndView logout(HttpSession session) throws IOException {
	   String oauthToken = naverLoginBO.logout(session);
   		return new ModelAndView("logout", "result", "네아로 연동 해제");
   	}
}
