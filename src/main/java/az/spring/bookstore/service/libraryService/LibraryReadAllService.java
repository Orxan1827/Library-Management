package az.spring.bookstore.service.libraryService;

import az.spring.bookstore.dto.request.library.LibraryReadBookIdRequest;
import az.spring.bookstore.dto.response.library.LibraryReadResponse;
import az.spring.bookstore.mapper.LibraryMapper;
import az.spring.bookstore.repository.LibraryRepository;
import az.spring.bookstore.wrapper.LibraryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibraryReadAllService {

    private final LibraryRepository libraryRepository;

    private final LibraryMapper libraryMapper;

    public List<LibraryReadResponse> getAllLibrary() {
        return libraryRepository.findAll().stream()
                .map(libraryMapper::mapEntityToReadResponse)
                .toList();
    }

    public List<LibraryWrapper> findByLibraryStatusA() {
        return libraryRepository.findByLibraryStatusA();
    }

}
