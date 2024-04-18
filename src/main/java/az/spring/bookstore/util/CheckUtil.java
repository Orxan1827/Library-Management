package az.spring.bookstore.util;

import az.spring.bookstore.entity.Library;
import az.spring.bookstore.entity.User;
import az.spring.bookstore.exception.GenericException;
import java.util.Objects;

import static org.springframework.http.HttpStatus.FOUND;

public class CheckUtil {

    protected static void checkLibraryExisting(Library library) {
        if (Objects.nonNull(library)) {
            throw GenericException.builder()
                    .httpStatus(FOUND)
                    .errorMessage("Library: " + library.getName() + " is already exists")
                    .errorCode(FOUND.value()).build();
        }
    }

    public static void checkBookExisting(Long bookId, User user, Library library) {
        if (library.getFkBookIds().contains(bookId)) {
            throw GenericException.builder()
                    .httpStatus(FOUND)
                    .errorCode(FOUND.value())
                    .errorMessage("Number of book purchases is limited")
                    .build();
        }
    }

//    private void checkLibrary(Long fkUserId) {
//        boolean isAllReadyExists = libraryRepository.existsLibraryByFkUserId(fkUserId);
//        if (isAllReadyExists) {
//            throw GenericException.builder()
//                    .httpStatus(FOUND)
//                    .errorMessage("Library: " + fkUserId + " is already exists")
//                    .errorCode(FOUND.value()).build();
//        }
//    }
}
