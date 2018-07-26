package au.gov.vic.ecodev.template.processor.persistent.custom.vgp.hydro;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import au.gov.vic.ecodev.template.constants.Constants.Numerals;
import au.gov.vic.ecodev.mrt.model.sl4.Site;
import au.gov.vic.ecodev.mrt.template.processor.model.Entity;
import au.gov.vic.ecodev.template.processor.model.custom.vgp.hydro.VgpHydroEntity;

public class VgpHydroLocationMetaDaoImpl implements VgpHydroLocationMetaDao {
	
	private static final Logger LOGGER = Logger.getLogger(VgpHydroLocationMetaDaoImpl.class);
	
	private static final String INSERT_SQL = "INSERT INTO lOC_SITE(LOADER_ID, SITE_ID, UWI, LOCN_NAME, LOCN_DESC, STATE, AMG_ZONE, EASTING, NORTHING, LOCN_DATUM_CD, LATITUDE, LONGITUDE, KB, ELEVATION_GL, BORE_DIAMETER, TD, TVD, DEPTH_DATUM) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String COUNT_SQL = "SELECT COUNT(LOADER_ID) FROM lOC_SITE WHERE LOADER_ID = ? AND SITE_ID = ?";

	private static final String UPDATE_SQL = "UPDATE lOC_SITE SET UWI=?, LOCN_NAME=?, LOCN_DESC=?, STATE=?, AMG_ZONE=?, EASTING=?, NORTHING=?, LOCN_DATUM_CD=?, LATITUDE=?, LONGITUDE=?, KB=?,  ELEVATION_GL=?, BORE_DIAMETER=?, TD=?, TVD=?,  DEPTH_DATUM= ? WHERE LOADER_ID = ? AND SITE_ID = ?";
	
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean updateOrSave(Entity entity) {
		LOGGER.info("Inside VgpHydroLocationMetaDaoImpl.updateOrSave");
		
		Site site = (Site) entity;
		SiteIdRepository.INSTANCE.add(site.getSiteId());
		return true;

		//TODO -- This part of code will be needed once the location meta table requirements are confirmed.
//		
//		int count = jdbcTemplate.queryForObject(COUNT_SQL, Integer.class, site.getLoaderId(), site.getSiteId());
//		if (Numerals.ZERO == count) {
//			int rows = jdbcTemplate.update(INSERT_SQL, new Object[] {site.getLoaderId(), site.getSiteId(),
//					site.getUwi(), site.getLocalName(), site.getLocalDescription(), site.getState(), site.getAmgZone(),
//					site.getEasting(), site.getNorthing(), site.getLocnDatumCd(), site.getLatitude(), 
//					site.getLongitude(), site.getKb(), site.getElevationGl(), site.getBoreDiameter(),
//					site.getTd(), site.getTvd(), site.getDepthDatum()});
//			return Numerals.ONE == rows;
//		} else {
//			int rows = jdbcTemplate.update(UPDATE_SQL, new Object[] {
//					site.getUwi(), site.getLocalName(), site.getLocalDescription(), site.getState(), site.getAmgZone(),
//					site.getEasting(), site.getNorthing(), site.getLocnDatumCd(), site.getLatitude(), 
//					site.getLongitude(), site.getKb(), site.getElevationGl(), site.getBoreDiameter(),
//					site.getTd(), site.getTvd(), site.getDepthDatum(),
//					site.getLoaderId(), site.getSiteId()});
//			return Numerals.ONE == rows;
//		}
	}

	@Override
	public Entity get(long id) {
		LOGGER.info("Inside VgpHydroLocationMetaDaoImpl.get");
		return null;
	}

	@Override
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		LOGGER.info("Inside VgpHydroLocationMetaDaoImpl.setJdbcTemplate: " + jdbcTemplate);
		this.jdbcTemplate = jdbcTemplate;
	}
}
