package com.waes.comparison;

import com.waes.comparison.core.repositories.JsonFileCustomRepository;
import com.waes.comparison.core.repositories.JsonFileRepository;
import com.waes.comparison.core.usecases.JsonFileUseCases;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public abstract class ComparisonApplicationTests {

	@Autowired
	protected JsonFileRepository jsonFileRepository;

	@Autowired
	protected JsonFileUseCases jsonFileUseCases;

	@Autowired
	protected JsonFileCustomRepository jsonFileCustomRepository;
}
