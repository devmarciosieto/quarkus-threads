package br.com.mmmsieto.controller.dtos;

public class ClientEntity {

    private Long id;
    private String name;

    private String cep;

    public ClientEntity() {}
    public ClientEntity(Long id, String name, String cep) {
        this.id = id;
        this.name = name;
        this.cep = cep;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
