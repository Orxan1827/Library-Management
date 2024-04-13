package az.spring.bookstore.enums;

public enum Role {

    ADMIN("ADMIN"), USER("USER");

    private String value;

    Role(String value) {
        this.value = value;
    }
}
