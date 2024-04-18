package az.spring.bookstore.service.libraryService;

import az.spring.bookstore.dto.request.library.LibraryUpdateRequest;
import az.spring.bookstore.mapper.LibraryMapper;
import az.spring.bookstore.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibraryUpdateService {

    private final LibraryRepository libraryRepository;

    private final LibraryReadService readService;

    private final LibraryMapper libraryMapper;

    public void updateLibrary(LibraryUpdateRequest updateRequest) {
        libraryMapper.updateLibraryFromUpdateRequest(updateRequest, readService.findLibrary(updateRequest.getLibraryId()));
    }

}
