
package br.com.marcelo.springboot.model;

public class Language {

    private Integer id;
    private String name;

    public Language(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Language() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
