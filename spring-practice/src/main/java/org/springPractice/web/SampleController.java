package org.springPractice.web;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springPractice.domain.ProductVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
public class SampleController {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	
	@RequestMapping("doA")
	public void doA() {
		// return Type이 void인 경우 스프링 MVC는 현재 경로에 해당하는 JSP파일을 생성
		logger.info("doA called...............");
		
	}
	
	@RequestMapping("doB")
	public void doB() {
		logger.info("doB called................");
	}
	
	@RequestMapping("doC")
	public String doC(@ModelAttribute("msg") String msg) {
		// String Type은 return에 있는 값의 jsp파일을 찾아 호출한다 (WEB-INF/views/result.jsp)
		// ModelAttibute("msg")는 request시 msg이름의 파라미터를 문자열로 처리해 주고, 뷰에 전달하도록 함. 
		logger.info("doC called....................");
		return "result";
	}
	
	@RequestMapping("doD")
	public String doD(Model model) {
		
		// make Sample data
		ProductVO product = new ProductVO("Sample Product", 10000);
		
		logger.info("doD");
		
		model.addAttribute(product);
		return "productDetail";
	}
	
	@RequestMapping("doE")
	public String doE(RedirectAttributes rttr) {
		logger.info("doE called but redirect to /doF ............");
		
		rttr.addFlashAttribute("msg", "This is the Message");
		
		return "redirect:/doF";
	}
	
	@RequestMapping("doF")
	public void doF(@ModelAttribute String msg, HttpServletRequest request) {
		
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		
		logger.info("doF called............."+msg);
		logger.info("doF called............."+(String)flashMap.get("msg"));
	}
	
	@RequestMapping("/doJSON")
	public @ResponseBody ProductVO doJSON() {
		logger.info("...doJSON");
		ProductVO vo = new ProductVO("샘플상품", 30000);
		
		return vo;
	}
	
}
