package ru.vdsimako.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import ru.vdsimako.demo.model.dto.CreateUserDto;

import static org.springframework.restdocs.http.HttpDocumentation.httpRequest;
import static org.springframework.restdocs.http.HttpDocumentation.httpResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "spring.datasource.url=jdbc:tc:postgresql:11-alpine:///practice",
                "spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver"
        }
)
@Transactional
@Rollback
@AutoConfigureMockMvc
@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private RestDocumentationResultHandler documentationHandler;

    public static final String DEFAULT_REST_DOC_IDENTIFIER = "{class_name}/{method-name}/{step}";

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {

        this.documentationHandler = document(DEFAULT_REST_DOC_IDENTIFIER,
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())
        );

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(
                        documentationConfiguration(restDocumentation)
                                .snippets()
                                .withDefaults(httpRequest(), httpResponse())
                )
                .alwaysDo(documentationHandler)
                .build();
    }

    @DisplayName("createUser_whenCreateUserDto_thenUserDto")
    @ParameterizedTest(name = "[{index}]")
    @MethodSource("ru.vdsimako.demo.arguments.UserControllerTestArguments#createUser_whenCreateUserDto_thenUserDto")
    void createUser_whenCreateUserDto_thenUserDto(String requestBody) throws Exception {

        CreateUserDto createUserDto = objectMapper.readValue(requestBody, CreateUserDto.class);

        mockMvc.perform(post("/user")
                        .header("Content-Type", "Application/json")
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.email").value(createUserDto.email()))
                .andExpect(jsonPath("$.password").value(createUserDto.password()))
                .andDo(this.documentationHandler.document(
                        requestFields(
                                fieldWithPath("email").description("email пользователя"),
                                fieldWithPath("password").description("пароль пользователя")
                        ),
                        responseFields(
                                fieldWithPath("id").description("идентификатор пользователя"),
                                fieldWithPath("email").description("email пользователя"),
                                fieldWithPath("password").description("пароль пользователя")
                        )
                ))
        ;
    }
}