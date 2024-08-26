package br.com.monitodehabitos.monitodehabitos.domain;

public class Address {

    private String cep;
    private String street;
    private String city;
    private String state;
    private String neighborhood;
    private Integer number;
    private String complement;

    public Address(String cep, String street, String city, String state, String neighborhood, Integer number, String complement) {
        this.cep = cep;
        this.street = street;
        this.city = city;
        this.state = state;
        this.neighborhood = neighborhood;
        this.number = number;
        this.complement = complement;
    }


    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }
}
