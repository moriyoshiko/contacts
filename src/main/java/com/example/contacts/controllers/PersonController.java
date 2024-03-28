package com.example.contacts.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.contacts.models.Person;
import com.example.contacts.repository.PersonRepository;

import jakarta.annotation.PostConstruct;



@Controller
public class PersonController {

    private final PersonRepository repository;
    public PersonController(PersonRepository repository){
        this.repository = repository;
        System.out.println("コンストラクタが呼ばれた");
    }
    
    @GetMapping("/")
    public String index(@ModelAttribute Person person, Model model) {
        model.addAttribute("people",repository.findAll());
        System.out.println("indexメソッドが呼ばれた");
        return "person/index";
        
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute Person person, BindingResult result, Model model) { 
        if(result.hasErrors()){
            model.addAttribute("people", repository.findAll());
            return "person/index";
        }
        repository.saveAndFlush(person);
        return "redirect:/";
    }

    //削除
    @GetMapping("/delete/{id}")
    public String remove(@PathVariable long id) {
        repository.deleteById(id);
        return "redirect:/";
    }

    //編集
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("person", repository.findById(id));
        return "person/edit";
    }

    //更新
    @PostMapping("/update/{id}")
    public String update(@PathVariable long id, @Validated @ModelAttribute Person person, BindingResult result) {
        if(result.hasErrors()){
            return "person/edit";
        }
        repository.save(person);
        return "redirect:/";
    }
    
    
    
    //初期データ投入
    @PostConstruct
    public void dataInt() {
        Person suzuki = new Person();
        suzuki.setName("鈴木");
        suzuki.setAge(23);
        suzuki.setEmail("suzuki@email.com");
        repository.saveAndFlush(suzuki);

        Person sato = new Person();
        sato.setName("佐藤");
        sato.setAge(20);
        sato.setEmail("sato@email.com");
        repository.saveAndFlush(sato);

        System.out.println("初期データ投入が呼ばれた");
    }
    
    
}
