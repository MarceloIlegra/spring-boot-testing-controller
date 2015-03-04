
package br.com.marcelo.springboot.validation;

import br.com.marcelo.springboot.model.Language;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class LanguageValidation implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return Language.class.equals(clazz);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Language language = (Language) object;
        if(language.getName().length() > 10){
            errors.rejectValue("name", "limit", "The limit of characters is 10.");
        }

        if(language.getName().length() < 2){
            errors.rejectValue("name", "limit", "The minimum of characters is 3.");
        }

        if(language.getId() == null){
            errors.rejectValue("id", "required", "Required field.");
        }
    }

}
