package com.example.sb.exercise;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/ex")
public class BasicController {
	private final Logger log =LoggerFactory.getLogger(getClass());  
	//  log 사용하려고 선언한 코드 org.slf4j

	@GetMapping("/hello")  //< 중요 //localhost:8090/sb/ex/hello
	public String hello() {
		return "exercise/hello";        // hello.html
	}
	
	@ResponseBody       // html 파일을 찾지 말고, 데이터를 직접 전송
	@GetMapping("/noHtml")    // 에이젝스를 사용할때 바뀌는 부분만 바꿀때 사용 
	public String noHtml() {
		return"<h1>Hello Spring Boot!!!</h1>";    // 전송되는 값
	}
	
	@GetMapping("/redirect")
	public String redirect() {
		return "redirect:/ex/hello";   // Redirection,Web 페이지 보여주는 3가지
	}
	
	@GetMapping("/params")
	public String params(Model model) {
		model.addAttribute("name","제임스");
		return "exercise/params";
	}
	// 파일을 업로드하는 경우 사용
	@GetMapping("/params2")
	public String params2(Model model, HttpServletRequest req) {
		String name = req.getParameter("name"); // 파라메타로 값을 받아서 
		model.addAttribute("name",name);//name값을 html로 전송
		return "exercise/params";
	}
	// 자주 사용하는경우
	@GetMapping("/params3")  //String name 선언만 해주면 나머지는 Spring이 처리해줌
	public String params3(Model model,String name,int count) { // int도 자동 변환해줌
		model.addAttribute("name",name+count);
		return "exercise/params";
	}
	
	@GetMapping("/memberForm")
	public String memberForm() {
		return "exercise/memberForm";
	}
					
	@PostMapping("/memberProc")
	public String memberProc(Member member, Model model) {
		log.info(member.toString());
		model.addAttribute("name",member.getName());
		return "exercise/params";
	}
	
}
