package az.spring.bookstore.service.libraryService;

import az.spring.bookstore.entity.Library;
import az.spring.bookstore.exception.LibraryNotFoundException;
import az.spring.bookstore.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LibraryReadService {

    private final LibraryRepository libraryRepository;

    public Library findLibrary(Long id) {
        return libraryRepository.findById(id).orElseThrow(LibraryNotFoundException::new);
    }
}
