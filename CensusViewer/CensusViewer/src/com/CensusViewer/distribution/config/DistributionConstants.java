package com.mars.distribution.config;

public class DistributionConstants {

	public static final int ONE = 1;
	public static final int ZERO = 0;
	public static final int TWO = 2;
	public static final int THREE = 3;
	public static final int FOUR = 4;
	public static final String CHANNEL_ID ="CH";
	public static final String INTERM_ID ="IN";
	public static final String CHANNEL_SEQUENCE ="channel_id";
	public static final String INTERM_SEQUENCE ="interm_id";
	public static final String BRANCH_CODE = "B";
	public static final String REGION_CODE = "R";
	public static final String TERRITORY_CODE = "T";
	public static final String DEFAULT_ORGID = "D1";
	public static final String DEFAULT_USER = "USR59";
	public static final String CREATED_ORGID = "ORG11";
	public static final String VILLAGE = "village";
	public static final String TOWN = "town"; 
	//branch geom club query.
	public static final String BRANCH_GEOM = "insert into distribution.district_test1(gid, the_geom, name, branch_id,status) VALUES (?,?,?,?,?)";
	//region geom club query
	public static final String REGION_GEOM = "insert into distribution.region_geom(gid, the_geom, name, region_id,status) VALUES (?,?,?,?,?)";
	public static final String TERRITORY_GEOM = "insert into distribution.territory_geom(gid, the_geom, name, territory_id,status) VALUES (?,?,?,?,?)";
	public static final String TERRITORY_DELETE_ALL="DELETE FROM distribution.territory_geom WHERE territory_id IN ";
	public static final String BRANCH_SINGLE_DELETE="DELETE FROM distribution.district_test1 WHERE branch_id=?";
	public static final String BRANCH_DELETE_ALL="DELETE FROM distribution.district_test1 WHERE branch_id IN ";
	public static final String REGION_SINGLE_DELETE="DELETE FROM distribution.region_geom WHERE region_id=?";
	public static final String REGION_DELETE_ALL="DELETE FROM distribution.region_geom WHERE region_id IN ";
	public static final String TERRITORY_SINGLE_DELETE="DELETE FROM distribution.territory_geom WHERE territory_id=?";
}
