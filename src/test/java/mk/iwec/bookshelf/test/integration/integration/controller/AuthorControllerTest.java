package mk.iwec.bookshelf.test.integration.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mk.iwec.bookshelf.domain.Author;
import mk.iwec.bookshelf.dto.AuthorDto;
import mk.iwec.bookshelf.dto.AuthorInfoDto;
import mk.iwec.bookshelf.infrastucture.Endpoints;
import mk.iwec.bookshelf.service.impl.AuthorServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import mk.iwec.bookshelf.test.integration.utils.AuthorTestUtil;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "spring.jpa.hibernate.ddl-auto=none")
@AutoConfigureMockMvc
public class AuthorControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AuthorServiceImpl authorService;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(mockMvc).isNotNull();
        assertThat(objectMapper).isNotNull();
    }

    @Test
    public void getAllAuthorsTest_success() {
        List<AuthorDto> mockAuthorDtoList = new ArrayList<>();
        AuthorDto mock1 = AuthorTestUtil.createMockAuthorDto("mock firstname 1", "mock lastname 1");
        AuthorDto mock2 = AuthorTestUtil.createMockAuthorDto("mock firstname 2", "mock lastname 2");

        mockAuthorDtoList.add(mock1);
        mockAuthorDtoList.add(mock2);

        when(this.authorService.findAll()).thenReturn(mockAuthorDtoList);

        try {
            this.mockMvc.perform(get(Endpoints.AUTHORS))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].firstName").value(mock1.getFirstName()))
                    .andExpect(jsonPath("$[0].lastName").value(mock1.getLastName()))
                    .andExpect(jsonPath("$[1].firstName").value(mock2.getFirstName()))
                    .andExpect(jsonPath("$[1].lastName").value(mock2.getLastName()));

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void createAuthorTest_success() {
        AuthorDto mock = AuthorTestUtil.createMockAuthorDto("mock firstname 1", "mock lastname 1");
        AuthorDto createdMock = AuthorTestUtil.createMockAuthorDto("mock firstname 1", "mock lastname 1");

        when(this.authorService.create(mock)).thenReturn(createdMock);

        try {
            String jsonBodyPayload = objectMapper.writer().writeValueAsString(mock);

            this.mockMvc.perform(post(Endpoints.AUTHORS)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonBodyPayload))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.firstName").value(createdMock.getFirstName()))
                    .andExpect(jsonPath("$.lastName").value(createdMock.getLastName()));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void updateAuthorTest_success() {
        Integer id = 1;
        AuthorDto mock = AuthorTestUtil.createMockAuthorDto("mock firstname 1", "mock lastname 1");
        AuthorDto updatedMock = AuthorTestUtil.createMockAuthorDto("mock firstname 1", "mock lastname 1");

        when(this.authorService.update(id, mock)).thenReturn(updatedMock);

        try {
            String jsonBodyPayload = objectMapper.writer().writeValueAsString(mock);

            this.mockMvc.perform(put(Endpoints.AUTHORS + id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonBodyPayload))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.firstName").value(updatedMock.getFirstName()))
                    .andExpect(jsonPath("$.lastName").value(updatedMock.getLastName()));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

}
