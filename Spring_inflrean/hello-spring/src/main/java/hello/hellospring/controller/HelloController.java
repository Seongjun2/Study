package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "Hello!");
        return "hello";//return hello 를 하면 viewResolver 가 화면을 찾아 처리 -> hello.html
    }


    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    //api 방식에서는 데이터를 그냥 넘겨줄 때 사용한다고 보면 됨.
    @GetMapping("hello-string")
    @ResponseBody//body 부에 직접 넣어 주겠다.
    public String helloString(@RequestParam("name") String name){
        return "hello" + name;//hello spring
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);

        return hello;// 객체로 리턴시 json 형식 으로 출력
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

