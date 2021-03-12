package com.waes.comparison;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waes.comparison.core.repositories.JsonFileCustomRepository;
import com.waes.comparison.core.repositories.JsonFileRepository;
import com.waes.comparison.core.usecases.JsonFileUseCases;
import com.waes.comparison.infrastructure.facade.JsonFileFacade;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public abstract class ComparisonApplicationTest {

	@Autowired
	protected JsonFileRepository jsonFileRepository;

	@Autowired
	protected JsonFileUseCases jsonFileUseCases;

	@Autowired
	protected JsonFileCustomRepository jsonFileCustomRepository;

	@Autowired
	protected JsonFileFacade jsonFileFacade;

	@Autowired
	protected MockMvc mvc;

	protected String mapToJson(Object obj) throws JsonProcessingException
	{
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}
}
