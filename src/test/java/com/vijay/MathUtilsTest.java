package com.vijay;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("When running MathUtils")
class MathUtilsTest {

	private MathUtils mathUtils;

	@BeforeAll
	// @BeforeAll method must be static unless the test class is annotated with
	// @TestInstance(Lifecycle.PER_CLASS).
	static void beforeAllInit() {
		System.out.println("This need to run before All");
	}

	@BeforeEach
	void initEach(TestInfo testInfo, TestReporter testReporter) {
		mathUtils = new MathUtils();
		testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with Tags :" + testInfo.getTags());
	}

	@AfterEach
	void afterEach() {
		System.out.println("Cleaning up...");
	}

	@AfterAll
	static void afterAll() {
		System.out.println("This need to run after All");
	}

	@Nested
	@DisplayName("add method")
	@Tag("Math")
	class AddTest {
		@Test
		@DisplayName("when adding two positive numbers correctly")
		void testAddingTwoPositives() {
			assertEquals(2, mathUtils.add(1, 1), "Add method should return the sum of two numbers");
		}

		@Test
		@DisplayName("when adding two negetive numbers correctly")
		void testAddingTwoNegatives() {
			assertEquals(-2, mathUtils.add(-1, -1), "Add method should return the sum of two numbers");
		}
	}

	@Test
	@Tag("Math")
	@DisplayName("multiply method")
	void testMultiply() {
		assertAll( () -> assertEquals(0, mathUtils.multiply(1, 0)),
				   () -> assertEquals(1, mathUtils.multiply(1, 1)),
				   () -> assertEquals(6, mathUtils.multiply(2, 3)));
	}

	@Test
	@DisplayName("compute Circle area")
	void computeCircleArea() {
		assertEquals(314.1592653589793, mathUtils.computeCircleArea(10), () -> "Should return right circle area");
	}

	@Test
	@DisplayName("testing divide method")
	void testDivide() {
		assertThrows(ArithmeticException.class, () -> mathUtils.divide(1, 0),
				"Divide should throw ArithmeticException when denominator is zero");
	}

	@Test
	@EnabledOnJre(JRE.JAVA_8)
	@EnabledOnOs(OS.WINDOWS)
	@DisplayName("conditional execution test case")
	// @EnabledIf
	// @EnabledIfEnvironmentVariable
	// @EnabledIfSystemProperty
	void method1() {
		System.out.println("Conditional execution");
	}

	@Test
	@DisplayName("test assumption")
	void testAssumption() {
		boolean isServerUp = false;
		assumeTrue(isServerUp);
		System.out.println("assumption test");
	}

	@RepeatedTest(3)
	@DisplayName("test repeated add method")
	void testRepeatedAddMethod(RepetitionInfo repetitionInfo) {
		System.out.println("Total Repetition : " + repetitionInfo.getTotalRepetitions());
		System.out.println("Current Repetition : " + repetitionInfo.getCurrentRepetition());
		assertEquals(2, mathUtils.add(1, 1));
	}

	@Test
	@Disabled
	@DisplayName("test disabled")
	void testDisabled() {
		fail("This method should not run");
	}

}