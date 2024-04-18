package az.spring.bookstore.service.libraryService;

import az.spring.bookstore.dto.request.library.LibraryReadRequest;
import az.spring.bookstore.dto.response.library.LibraryReadResponse;
import az.spring.bookstore.entity.Library;
import az.spring.bookstore.exception.LibraryNotFoundException;
import az.spring.bookstore.mapper.LibraryMapper;
import az.spring.bookstore.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LibraryReadService {

    private final LibraryRepository libraryRepository;

    private final LibraryMapper libraryMapper;

    public Library findLibrary(Long id) {
        return libraryRepository.findById(id).orElseThrow(LibraryNotFoundException::new);
    }

    public LibraryReadResponse getLibrary(LibraryReadRequest readRequest) {
        return libraryMapper.mapEntityToReadResponse(findLibrary(readRequest.getLibraryId()));
    }

}
