package au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import au.gov.vic.ecodev.mrt.model.vgp.hydro.Observation;
import au.gov.vic.ecodev.mrt.model.vgp.hydro.SampleAnalysis;

public class VgpHydroObservationsDaoImplTest {

	private static EmbeddedDatabase db;
	private static JdbcTemplate jdbcTemplate;
	
	private VgpHydroObservationsDaoImpl testInstance;
	
	@BeforeClass
	public static void setUpDb() {
		db = new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript("db/sql/create_observations.sql")
				.addScript("db/sql/insert_observations.sql")
				.build();
		jdbcTemplate = new JdbcTemplate(db);
	}
	
	@Before
	public void setUp() {
		testInstance = new VgpHydroObservationsDaoImpl();
	}
	
	@Test
	public void shouldAddAnewRecord() {
		//Given
		Timestamp  timestamp = new Timestamp(System.currentTimeMillis());
		Observation observation = getObservation(timestamp);
		testInstance.setJdbcTemplate(jdbcTemplate);
		//When
		boolean flag = testInstance.updateOrSave(observation);
		//Then
		assertThat(flag, is(true));
	}
	
	@Test
	public void shouldReturnARecord() {
		//Given
		long id = 1;
		testInstance.setJdbcTemplate(jdbcTemplate);
		//When
		Observation observation = (Observation) testInstance.get(id);
		//Then
		assertThat(observation, is(notNullValue()));
		assertThat(observation.getId(), is(equalTo(1l)));
		assertThat(observation.getLoaderId(), is(equalTo(1l)));
		assertThat(observation.getSampleId(), is(equalTo(1l)));
		assertThat(observation.getIgsn(), is(equalTo("IGSN")));
		assertThat(observation.getOccurTime(), is(notNullValue()));
		assertThat(observation.getParameter(), is(equalTo("abc")));
		assertThat(observation.getDepthFrom(), is(equalTo(new BigDecimal("5"))));
		assertThat(observation.getDepthTo(), is(equalTo(new BigDecimal("7"))));
		assertThat(observation.getResult(), is(equalTo("Result")));
		assertThat(observation.getObserver(), is(equalTo("Observer")));
		assertThat(observation.getType(), is(equalTo("Type")));
	}

	@Test
	public void shouldPopulateJdbcTemplate() {
		//Given
		//When
		testInstance.setJdbcTemplate(jdbcTemplate);
		//Then
		JdbcTemplate retrievedJdbcTemplate = Whitebox.getInternalState(testInstance, 
				"jdbcTemplate");
		assertThat(retrievedJdbcTemplate, is(equalTo(jdbcTemplate)));
	}
	
	private Observation getObservation(Timestamp timestamp) {
		Observation observation = new Observation();
		observation.setId(12l);
		observation.setLoaderId(34l);
		observation.setSiteId(56l);
		observation.setSampleId(78l);
		observation.setIgsn("IGSN");
		observation.setOccurTime(timestamp);
		observation.setParameter("Parameter");
		observation.setDepthFrom(BigDecimal.ONE);
		observation.setDepthTo(BigDecimal.TEN);
		observation.setResult("Result");
		observation.setObserver("Observer");
		observation.setType("type");
		return observation;
	}
}
