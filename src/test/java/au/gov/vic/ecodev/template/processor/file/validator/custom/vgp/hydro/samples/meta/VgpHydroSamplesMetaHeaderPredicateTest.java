package au.gov.vic.ecodev.template.processor.file.validator.custom.vgp.hydro.samples.meta;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class VgpHydroSamplesMetaHeaderPredicateTest {

	@Test
	public void shouldReturnTrueWhenExactMatched() {
		//Given
		String label = "Site_ID";
		String testData = label;
		//When
		boolean flag = VgpHydroSamplesMetaHeaderPredicate.isMatched(label).test(testData);
		//Then
		assertThat(flag, is(true));
	}
	
	@Test
	public void shouldReturnTrueWhenMatchedWitUom() {
		//Given
		String label = "Site_ID";
		String testData = label + " (m)";
		//When
		boolean flag = VgpHydroSamplesMetaHeaderPredicate.isMatched(label).test(testData);
		//Then
		assertThat(flag, is(true));
	}
	
	@Test
	public void shouldReturnTrueWhenMatchedWitVariation() {
		//Given
		String label = "Site_ID";
		String testData = "Site ID";
		//When
		boolean flag = VgpHydroSamplesMetaHeaderPredicate.isMatched(label).test(testData);
		//Then
		assertThat(flag, is(true));
	}
	
	@Test
	public void shouldReturnTrueWhenMatchedWitVariationHypen() {
		//Given
		String label = "Site_ID";
		String testData = "Site-ID";
		//When
		boolean flag = VgpHydroSamplesMetaHeaderPredicate.isMatched(label).test(testData);
		//Then
		assertThat(flag, is(true));
	}
	
	@Test
	public void shouldReturnFalseWhenMatchedWitVariationRubbish() {
		//Given
		String label = "Site_ID";
		String testData = "Site-IDxx";
		//When
		boolean flag = VgpHydroSamplesMetaHeaderPredicate.isMatched(label).test(testData);
		//Then
		assertThat(flag, is(false));
	}
	
	@Test
	public void shouldReturnFalseWhenMatchedWitVariationUomRubbish() {
		//Given
		String label = "Site_ID";
		String testData = "Site-ID (m)xx";
		//When
		boolean flag = VgpHydroSamplesMetaHeaderPredicate.isMatched(label).test(testData);
		//Then
		assertThat(flag, is(false));
	}
}
