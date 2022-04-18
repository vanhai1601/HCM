package vn.com.mbbank.dto;

import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.CoreMatchers.allOf;
import static com.google.code.beanmatchers.BeanMatchers.*;
import org.junit.jupiter.api.Test;

class DTOTest {

	@BeforeEach
	void setUp() {
	}

	@Test
	void FileDTO (){
		assertThat(FileDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
	}
}