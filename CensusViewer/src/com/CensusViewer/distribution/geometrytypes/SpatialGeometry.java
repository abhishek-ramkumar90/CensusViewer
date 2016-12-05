package com.CensusViewer.distribution.geometrytypes;

import java.io.Serializable;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateFilter;
import com.vividsolutions.jts.geom.CoordinateSequenceComparator;
import com.vividsolutions.jts.geom.CoordinateSequenceFilter;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryComponentFilter;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.GeometryFilter;

public class SpatialGeometry extends Geometry implements Serializable {
	   public SpatialGeometry() {
	       super(null);
	    }
	
	public SpatialGeometry(GeometryFactory factory) {
		super(factory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void apply(CoordinateFilter arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void apply(CoordinateSequenceFilter arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void apply(GeometryFilter arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void apply(GeometryComponentFilter arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected int compareToSameClass(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int compareToSameClass(Object arg0,
			CoordinateSequenceComparator arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected Envelope computeEnvelopeInternal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equalsExact(Geometry arg0, double arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Geometry getBoundary() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getBoundaryDimension() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Coordinate getCoordinate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Coordinate[] getCoordinates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDimension() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getGeometryType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumPoints() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void normalize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Geometry reverse() {
		// TODO Auto-generated method stub
		return null;
	}

}
