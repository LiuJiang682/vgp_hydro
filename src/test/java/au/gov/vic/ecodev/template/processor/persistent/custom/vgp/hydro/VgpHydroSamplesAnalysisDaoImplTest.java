package au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import au.gov.vic.ecodev.mrt.model.vgp.hydro.SampleAnalysis;

public class VgpHydroSamplesAnalysisDaoImplTest {

	private static EmbeddedDatabase db;
	private static JdbcTemplate jdbcTemplate;
	
	private VgpHydroSamplesAnalysisDaoImpl testInstance;
	
	@BeforeClass
	public static void setUpDb() {
		db = new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript("db/sql/create_sample_analysis.sql")
				.addScript("db/sql/insert_sample_analysis.sql")
				.build();
		jdbcTemplate = new JdbcTemplate(db);
	}
	
	@Before
	public void setUp() {
		testInstance = new VgpHydroSamplesAnalysisDaoImpl();
	}
	
	@Test
	public void shouldAddAnewRecord() {
		//Given
		Timestamp  timestamp = new Timestamp(System.currentTimeMillis());
		SampleAnalysis sampleAnalysis = getSampleAnalysis(timestamp);
		testInstance.setJdbcTemplate(jdbcTemplate);
		//When
		boolean flag = testInstance.updateOrSave(sampleAnalysis);
		//Then
		assertThat(flag, is(true));
	}
	
	@Test
	public void shouldReturnARecord() {
		//Given
		long id = 1;
		testInstance.setJdbcTemplate(jdbcTemplate);
		//When
		SampleAnalysis sampleAnalysis = (SampleAnalysis) testInstance.get(id);
		//Then
		assertThat(sampleAnalysis, is(notNullValue()));
		assertThat(sampleAnalysis.getId(), is(equalTo(1l)));
		assertThat(sampleAnalysis.getLoaderId(), is(equalTo(1l)));
		assertThat(sampleAnalysis.getSampleId(), is(equalTo(1l)));
		assertThat(sampleAnalysis.getParameter(), is(equalTo("abc")));
		assertThat(sampleAnalysis.getUom(), is(equalTo("5")));
		assertThat(sampleAnalysis.getResult(), is(equalTo("7")));
		assertThat(sampleAnalysis.getAnanlysisMethod(), is(equalTo("ref1")));
		assertThat(sampleAnalysis.getLor(), is(equalTo("1")));
	}

	@Test
	public void shouldPopulateJdbcTemplate() {
		//Given
		//When
		testInstance.setJdbcTemplate(jdbcTemplate);
		//Then
		JdbcTemplate retrievedJdbcTemplate = Whitebox.getInternalState(testInstance, "jdbcTemplate");
		assertThat(retrievedJdbcTemplate, is(equalTo(jdbcTemplate)));
	}
	
	private SampleAnalysis getSampleAnalysis(Timestamp timestamp) {
		SampleAnalysis sampleAnalysis = new SampleAnalysis();
		sampleAnalysis.setId(System.currentTimeMillis());
		sampleAnalysis.setLoaderId(100l);
		sampleAnalysis.setSampleId(123l);
		sampleAnalysis.setIgsn("IGSN");
		sampleAnalysis.setLabSampleNo("LabSampleNo");
		sampleAnalysis.setAnalysisDate(timestamp);
		sampleAnalysis.setParameter("Parameter");
		sampleAnalysis.setUom("UOM");
		sampleAnalysis.setResult("Result");
		sampleAnalysis.setAnanlysisMethod("Anal_Meth");
		sampleAnalysis.setLor("LOR");
		return sampleAnalysis;
	}
}
