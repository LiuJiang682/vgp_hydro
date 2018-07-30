package au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;
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

import au.gov.vic.ecodev.common.util.IDGenerator;
import au.gov.vic.ecodev.mrt.model.vgp.hydro.SampleMeta;

public class VgpHydroSamplesMetaDaoImplTest {

	private static EmbeddedDatabase db;
	private static JdbcTemplate jdbcTemplate;
	
	private VgpHydroSamplesMetaDaoImpl testInstance;
	
	@BeforeClass
	public static void setUpDb() {
		db = new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript("db/sql/create.sql")
				.addScript("db/sql/insert.sql")
				.build();
		jdbcTemplate = new JdbcTemplate(db);
	}
	
	@Before
	public void setUp() {
		testInstance = new VgpHydroSamplesMetaDaoImpl();
	}
	
	@Test
	public void shouldAddANewRecord() {
		//Given
		SampleMeta entity = givenSampleMeta(IDGenerator.getUID().longValue());
		testInstance.setJdbcTemplate(jdbcTemplate);
		//When
		boolean flag = testInstance.updateOrSave(entity);
		//Then
		assertThat(flag, is(true));
	}
	
	@Test
	public void shouldReturnASampleMetaRecord() {
		//Given
		long id = 1;
		testInstance.setJdbcTemplate(jdbcTemplate);
		//When
		SampleMeta sampleMeta = (SampleMeta) testInstance.get(id);
		//Then
		assertThat(sampleMeta, is(notNullValue()));
		assertThat(sampleMeta.getId(), is(equalTo(1l)));
		assertThat(sampleMeta.getLoaderId(), is(equalTo(1l)));
	}
	
	@Test
	public void shouldInsertASqlInjectedRecordWithoutAnyEffect() {
		//Given
		long id = IDGenerator.getUID().longValue();
		SampleMeta entity = givenSampleMeta(id);
		entity.setReference("Drill Faster Pty Ltd'; DROP TABLE DRILLING_DETAILS; -- '");
		testInstance.setJdbcTemplate(jdbcTemplate);
		//When
		boolean flag = testInstance.updateOrSave(entity);
		//Then
		assertThat(flag, is(true));
		SampleMeta retrieve = (SampleMeta) testInstance.get(id);
		assertThat(retrieve, is(notNullValue()));
		assertThat(retrieve.getLoaderId(), is(equalTo(entity.getLoaderId())));
		assertThat(retrieve.getReference(), is(equalTo(entity.getReference())));
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
	
	private SampleMeta givenSampleMeta(final long id) {
		SampleMeta entity = new SampleMeta();
		entity.setId(id);
		entity.setLoaderId(100);
		entity.setSiteId(123);
		entity.setSampleId(456);
		entity.setCoreId(789);
		entity.setLabCode("l");
		entity.setType("t1");
		entity.setPrepCode("p1");
		entity.setSampleDate(new Timestamp(System.currentTimeMillis()));
		entity.setIgsn("igsn");
		entity.setSampleTop(BigDecimal.ONE);
		entity.setSampleBottom(BigDecimal.TEN);
		entity.setStandardWaterLevel(new BigDecimal("6"));
		entity.setPumpingDepth(new BigDecimal("8"));
		entity.setReference("r1");
		entity.setSampleAreaDesc("desc");
		return entity;
	}
}
