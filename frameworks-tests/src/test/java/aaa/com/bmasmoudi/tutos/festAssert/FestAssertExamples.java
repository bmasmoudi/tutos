package aaa.com.bmasmoudi.tutos.festAssert;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class FestAssertExamples {

	@Test
	public void use_of_assert_that() throws Exception {
		assertThat(1).isEqualTo(1);
		assertThat(1).isNotEqualTo(2);
	}

}
