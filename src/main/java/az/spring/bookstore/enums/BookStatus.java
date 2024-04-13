package az.spring.bookstore.enums;

import lombok.Getter;

@Getter
public enum BookStatus {

    ACTIVE("active"),INACTIVE("inactive"),FALSE("false");

    private String value;

     BookStatus(String value) {
        this.value = value;
    }
}
