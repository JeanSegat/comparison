package com.waes.comparison;

import com.waes.comparison.infrastructure.repositories.JsonFileRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public abstract class ComparisonApplicationTests {

	@Autowired
	protected JsonFileRepository jsonFileRepository;
}
