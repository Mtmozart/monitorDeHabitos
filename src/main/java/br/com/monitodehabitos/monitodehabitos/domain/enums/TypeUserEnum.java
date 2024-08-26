package br.com.monitodehabitos.monitodehabitos.domain.enums;

public enum TypeUserEnum {
    ADMIN("admin"),
    CLIENT("client");

    private final String value;

    TypeUserEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TypeUserEnum fromValue(String value) {
        for (TypeUserEnum type : TypeUserEnum.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
