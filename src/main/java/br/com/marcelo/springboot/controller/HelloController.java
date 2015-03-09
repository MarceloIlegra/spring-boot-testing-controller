
package br.com.marcelo.springboot.controller;

import br.com.marcelo.springboot.model.Language;
import br.com.marcelo.springboot.validation.LanguageValidation;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(new LanguageValidation());
    }

    @RequestMapping("hello")
    public String sayHello(@RequestParam String name){
        return "hello " + name + "!";
    }

    @RequestMapping(value="language", method = RequestMethod.POST)
    public ResponseEntity getLanguage(@Valid @ModelAttribute Language language, BindingResult errors){
        return errors.hasErrors()
                ? new ResponseEntity(errors.getAllErrors(), HttpStatus.BAD_REQUEST)
                : new ResponseEntity(language, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("badroute")
    public ResponseEntity sayHello(){
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="search", headers = "Accept=application/json")
    public ResponseEntity findOne(@RequestParam Integer id){
        List<Language> languages = Arrays.asList(new Language(1, "Java"), new Language(2, "Scala"), new Language(3, "Ruby"));
        Optional<Language> languageOptional = languages.stream().filter((language)->language.getId() == id).findFirst();
        return languageOptional.map( language -> new ResponseEntity(language, HttpStatus.OK))
                .orElse(new ResponseEntity("Not found...", HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value="calculateAge", method=RequestMethod.POST)
    public Integer getAge(@RequestParam String birthdate){
        LocalDate localDate = LocalDate.parse(birthdate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return Period.between(localDate, LocalDate.now()).getYears();
    }
}
