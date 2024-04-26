package az.spring.bookstore.service.libraryService;

import az.spring.bookstore.dto.request.library.LibraryDeleteRequest;
import az.spring.bookstore.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibraryDeleteService {

    private final LibraryRepository libraryRepository;

    private final LibraryReadService readService;

    public void deleteLibrary(LibraryDeleteRequest deleteRequest) {
        libraryRepository.delete(readService.findLibrary(deleteRequest.getLibraryId()));
        log.info("Library is deleted");
    }

}
