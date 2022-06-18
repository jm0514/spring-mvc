package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // 웹 어플리케이션에서 /hello가 들어오면 해당 매서드를 실행한다.
    @GetMapping("hello")
        public String hello(Model model){
            model.addAttribute("data", "hello!!");
            return "hello";
        }

    @GetMapping("hello-mvc")
    // 파라미터를 받을 때 @RequestParam을 사용한다.
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    // http body부에 data를 직접 넣는다.
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    // @ResponseBody에서 리턴값을 객체로 넘기면 HttpMessageConverter가 작동을 한다.
    // 객체는 JsonConverter가 작동, 문자열이면 StringConverter가 작동
    public Hello helloApi(@RequestParam("name") String name){
        // 객체를 받게 될 경우 기본값(Default)을 JSON방식으로 데이터를 만들어서 http응답에 반환한다.
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}

