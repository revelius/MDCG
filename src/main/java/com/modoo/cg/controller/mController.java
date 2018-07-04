package com.modoo.cg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.modoo.cg.limit.minutes;
import com.modoo.cg.util.Constant;
import com.naver.naverlogintutorial.oauth.bo.NaverLoginBO;

@Controller
public class mController {
	HttpSession hs;

	Command command;
	
	public JdbcTemplate template;
	
	
	
	@Autowired
	public void setTemplat(JdbcTemplate template) {
		System.out.println("template생성이 언제니?()");
		
		/*CustomSession cs=new CustomSession();
		hs=cs.addSession();*/
		
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
		System.out.println("home(Model model) ");
		
		return "index";
	}
	
	
	//회원가입
	@RequestMapping("/register")
	public String register(Model model) {
		System.out.println("register()");
		
		command=new ListCommand();
		command.execute(model);
		
		return "register";
	}
	
	@RequestMapping("/list/{curPage}")
	public String list(@RequestParam(defaultValue="title") String searchOption ,
			@RequestParam(defaultValue="") String keyword, 
			@PathVariable("curPage") int curPage ,@RequestParam(defaultValue="")String sc,
			Model model,HttpSession session,@RequestParam(defaultValue="") String limit) {
		
		System.out.println("list()");
		
		String a =(String) session.getAttribute("writeTime");
		
		System.out.println(a);
		model.addAttribute("sessionNull", a);
		model.addAttribute("curPage", curPage);
		model.addAttribute("keyword", keyword);
		model.addAttribute("searchOption", searchOption);
		model.addAttribute("limit", limit);
		
		command=new ListCommand();
		command.execute(model);
		
		return "list";
	}
	
	@RequestMapping("/write_view")
	public String write_view(Model model,HttpSession session) throws java.text.ParseException {
		minutes m = new minutes();
		String time = (String) session.getAttribute("writeTime");
		System.out.println(time);
		
		if(time == null || time.equals("") ) {
			
			return "write_view";
			
		}else {
			
		
			if(!m.equalsTime(time)) {
				
				return "redirect:list/1?limit=2";
				
			}else {
				
				session.removeAttribute("writeTime");
				
				return "write_view";
			}
		}
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model,HttpSession session) {
		System.out.println("write()");
		//
		minutes m = new minutes();
		
		session.setAttribute("writeTime",m.getTime());
		System.out.println(m.getTime());
		
		
		model.addAttribute("request", request);
		command = new WriteCommand();
		command.execute(model);
		
		
		return "redirect:list/1";
	}
	
	@RequestMapping("/content_view/{curPage}/{bID}")
	public String content_view(@PathVariable("curPage") int curPage ,@PathVariable("bID") int bID ,
			HttpServletRequest request, Model model,HttpSession session,
			@RequestParam(defaultValue="") String limit ,@RequestParam(defaultValue="title") String searchOption ,
			@RequestParam(defaultValue="") String keyword,@RequestParam(defaultValue="")String sc) {
		System.out.println("content_view()");
		
		String a =(String) session.getAttribute("writeTime");
		
		System.out.println(a);
		model.addAttribute("sessionNull", a);
		model.addAttribute("request", request);
		model.addAttribute("bID",bID);
		model.addAttribute("curPage", curPage);
		model.addAttribute("limit", limit);
		model.addAttribute("curPage", curPage);
		model.addAttribute("keyword", keyword);
		model.addAttribute("searchOption", searchOption);
		
		
		
		
		
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
		
		return "redirect:list/1";
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
		
		return "redirect:list/1";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("delete()");
		
		model.addAttribute("request", request);
		command = new DeleteCommand();
		command.execute(model);
		
		return "redirect:list/1";
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
		
		naverLoginBO.naverprofile(session);
		
		naverLoginBO.refresh(session);
		return new ModelAndView("callback", "result", apiResult);
	}
    
    /*=========================================*/
    /*여기서부터는 쪽지함입니다*/
    /*=========================================*/
    @RequestMapping("/mailList/{curPage}")
	public String mailList(	@PathVariable("curPage") int curPage, Model model) {
		System.out.println("mailList()");
		model.addAttribute("curPage", curPage);
		
		command=new MailListCom();
		command.execute(model);
		
		return "mailList";
	}
    
	@RequestMapping("/mailWrite_view")
	public String mailWrite_view(Model model) {
		System.out.println("write_view()");
		
		return "mailWrite_view";
	}
    
    
	@RequestMapping("/mailWrite")
	public String mailWrite(HttpServletRequest request, Model model) {
		System.out.println("mailWrite(start)");
		
		model.addAttribute("request", request);
		command = new MailWriteCom();
		command.execute(model);
		
		return "redirect:mailList/1";
	}
    /*=========================================*/
    /*여기까지가 쪽지함입니다*/
    /*=========================================*/
	
    /*=========================================*/
    /*콜로세움*/
    /*=========================================*/	
	@RequestMapping("/coloseumList/{curPage}")
	public String coloseumlist(@RequestParam(defaultValue="title") String searchOption ,
			@RequestParam(defaultValue="") String keyword, 
			@PathVariable("curPage") int curPage ,@RequestParam(defaultValue="")String sc,
			Model model) {
		
		System.out.println("coloseumList()");
		
		
		model.addAttribute("curPage", curPage);
		model.addAttribute("keyword", keyword);
		model.addAttribute("searchOption", searchOption);
		
		command=new ListCommand();
		command.execute(model);
		
		return "coloseumList";
	}
    /*=========================================*/
    /*콜로세움 끝*/
    /*=========================================*/	
    /*=========================================*/
    /*회원정보 조회  / 수정 시작*/
    /*=========================================*/	
	
	@RequestMapping("/myinfo")
	public String myinfo(Model model) {//, HttpSession session) {
		System.out.println("myinfo()");
		String id;
		//id=(String)session.getAttribute("id");
		//model.addAttribute("id", id);
		command=new MyInfoCom();
		command.execute(model);
		
		return "myinfo";
	}
		
	
    /*=========================================*/
    /*회원정보 조회  / 수정 시작 ㅃㄴㅃㄴ*/
    /*=========================================*/	
	
    
   @RequestMapping("/logout")
   	public ModelAndView logout(HttpSession session) throws IOException {
	   
	   
	  
	   String oauthToken = naverLoginBO.logout(session);
	   
	   
	   
   		return new ModelAndView("logout", "result", "네아로 연동 해제");
   	}
}
