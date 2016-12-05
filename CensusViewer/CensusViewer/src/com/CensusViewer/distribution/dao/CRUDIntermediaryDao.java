package com.mars.distribution.dao;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;

import com.mars.distribution.model.IntermediaryFreeFlow;

public interface CRUDIntermediaryDao {

void saveIntermediary(String jsonString);
void UpdateIntermediary(String updateDetails ) throws JsonParseException, JsonMappingException, IOException;
void DeleteIntermediary(String DeleteDetails) throws JSONException;
IntermediaryFreeFlow getIntermediary(String InterDetails);

}
