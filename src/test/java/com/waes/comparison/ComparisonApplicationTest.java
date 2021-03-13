package com.waes.comparison;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waes.comparison.core.repositories.ComparisonCustomRepository;
import com.waes.comparison.core.repositories.ComparisonRepository;
import com.waes.comparison.core.usecases.ComparisonUseCases;
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
	protected ComparisonRepository comparisonRepository;

	@Autowired
	protected ComparisonUseCases comparisonUseCases;

	@Autowired
	protected ComparisonCustomRepository comparisonCustomRepository;

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
