package mk.iwec.bookshelf.test.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mk.iwec.bookshelf.dto.PublisherDto;
import mk.iwec.bookshelf.infrastucture.Endpoints;
import mk.iwec.bookshelf.service.impl.PublisherServiceImpl;
import mk.iwec.bookshelf.test.utils.PublisherTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "spring.jpa.hibernate.ddl-auto=none")
@AutoConfigureMockMvc
public class PublisherControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    PublisherServiceImpl publisherService;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(mockMvc).isNotNull();
        assertThat(objectMapper).isNotNull();
    }

    @Test
    public void getAllPublishersTest_success() {
        List<PublisherDto> mockPublisherDtoList = new ArrayList<>();
        PublisherDto mock1 = PublisherTestUtil.createMockPublisherDto("mock name 1", "mock country 1");
        PublisherDto mock2 = PublisherTestUtil.createMockPublisherDto("mock name 2", "mock country 2");

        mockPublisherDtoList.add(mock1);
        mockPublisherDtoList.add(mock2);

        when(this.publisherService.findAll()).thenReturn(mockPublisherDtoList);

        try {
            this.mockMvc.perform(get(Endpoints.PUBLISHERS))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].name").value(mock1.getName()))
                    .andExpect(jsonPath("$[0].country").value(mock1.getCountry()))
                    .andExpect(jsonPath("$[1].name").value(mock2.getName()))
                    .andExpect(jsonPath("$[1].country").value(mock2.getCountry()));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

    }

    @Test
    public void createPublisherTest_success() {
        PublisherDto mock = PublisherTestUtil.createMockPublisherDto("mock name 1", "mock country 1");
        PublisherDto createdMock = PublisherTestUtil.createMockPublisherDto("mock name 1", "mock country 1");

        when(this.publisherService.create(mock)).thenReturn(createdMock);

        try {
            String jsonBodyPayload = objectMapper.writer().writeValueAsString(mock);

            this.mockMvc.perform(post(Endpoints.PUBLISHERS)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonBodyPayload))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.name").value(createdMock.getName()))
                    .andExpect(jsonPath("$.country").value(createdMock.getCountry()));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

    }

    @Test
    public void updatePublisherTest_success() {
        Integer id = 1;
        PublisherDto mock = PublisherTestUtil.createMockPublisherDto("mock name 1", "mock country 1");
        PublisherDto updatedMock = PublisherTestUtil.createMockPublisherDto("mock name 1", "mock country 1");

        when(this.publisherService.update(id, mock)).thenReturn(updatedMock);

        try {
            String jsonBodyPayload = objectMapper.writer().writeValueAsString(mock);

            this.mockMvc.perform(put(Endpoints.PUBLISHERS + id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonBodyPayload))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.name").value(updatedMock.getName()))
                    .andExpect(jsonPath("$.country").value(updatedMock.getCountry()));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

    }

}
