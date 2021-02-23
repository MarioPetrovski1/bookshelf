package mk.iwec.bookshelf.test.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mk.iwec.bookshelf.dto.BookDto;
import mk.iwec.bookshelf.infrastucture.Endpoints;
import mk.iwec.bookshelf.service.impl.BookServiceImpl;
import mk.iwec.bookshelf.test.utils.BookTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "spring.jpa.hibernate.ddl-auto=none")
@AutoConfigureMockMvc
public class BookControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BookServiceImpl bookService;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(mockMvc).isNotNull();
        assertThat(objectMapper).isNotNull();
    }

    @Test
    public void getAllBooksTest_success() {
        List<BookDto> mockBookDtoList = new ArrayList<>();
        BookDto mock1 = BookTestUtil.createMockBookDto("mock title 1","mock isbn 1");
        BookDto mock2 = BookTestUtil.createMockBookDto("mock title 2","mock isbn 2");

        mockBookDtoList.add(mock1);
        mockBookDtoList.add(mock2);

        when(this.bookService.findAll()).thenReturn(mockBookDtoList);

        try {
            this.mockMvc.perform(get(Endpoints.BOOKS))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].title").value(mock1.getTitle()))
                    .andExpect(jsonPath("$[0].isbn").value(mock1.getIsbn()))
                    .andExpect(jsonPath("$[1].title").value(mock2.getTitle()))
                    .andExpect(jsonPath("$[1].isbn").value(mock2.getIsbn()));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void createBookTest_success() {
        BookDto mock = BookTestUtil.createMockBookDto("mock title 1","mock isbn 1");
        BookDto createdMock = BookTestUtil.createMockBookDto("mock title 1","mock isbn 1");

        when(this.bookService.create(mock)).thenReturn(createdMock);

        try {
            String jsonBodyPayload = objectMapper.writer().writeValueAsString(mock);

            this.mockMvc.perform(post(Endpoints.BOOKS)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonBodyPayload))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.title").value(createdMock.getTitle()))
                    .andExpect(jsonPath("$.isbn").value(createdMock.getIsbn()));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

    }

    @Test
    public void updateBookTest_success() {
        Integer id = 1;
        BookDto mock = BookTestUtil.createMockBookDto("mock title 1","mock isbn 1");
        BookDto updatedMock = BookTestUtil.createMockBookDto("mock title 1","mock isbn 1");

        when(this.bookService.update(id,mock)).thenReturn(updatedMock);

        try {
            String jsonBodyPayload = objectMapper.writer().writeValueAsString(mock);

            this.mockMvc.perform(put(Endpoints.BOOKS + id)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonBodyPayload))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.title").value(updatedMock.getTitle()))
                    .andExpect(jsonPath("$.isbn").value(updatedMock.getIsbn()));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

}
