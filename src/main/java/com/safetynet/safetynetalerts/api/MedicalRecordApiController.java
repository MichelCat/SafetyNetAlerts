package com.safetynet.safetynetalerts.api;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.safetynet.safetynetalerts.business.MedicalRecordBusiness;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import javax.validation.Valid;
import java.net.URI;

/**
 * MedicalRecordApiController is the Endpoint will perform the following actions via Post/Put/Delete with HTTP on medical records.
 * 
 * @author MC
 * @version 1.0
 */
@RestController
public class MedicalRecordApiController implements MedicalRecordApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalRecordApiController.class);

    @Autowired
    private MedicalRecordBusiness medicalRecordBusiness;

    /**
     * Create - Add a new medical record
     * 
     * @param body An object medical record
     * @return The medical record object saved
     */
    public ResponseEntity<MedicalRecord> postMedicalRecord(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody MedicalRecord body) {
      LOGGER.debug("HTTP POST, Add a medical record ({}, {}).", body.getFirstName(), body.getLastName());
      MedicalRecord medicalRecord = medicalRecordBusiness.saveMedicalRecord(body);
      if (medicalRecord == null) {
        LOGGER.debug("HTTP POST, BAD_REQUEST ({}, {}).", body.getFirstName(), body.getLastName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
      }
      URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(medicalRecord.getId())
                    .toUri();
      LOGGER.debug("HTTP POST, CREATED ({}, {}).", body.getFirstName(), body.getLastName());
      return ResponseEntity.created(location).body(medicalRecord);
    }

    /**
     * Update - Update an existing medical record
     * 
     * @param body An object medical record
     * @return The medical record object updated
     */
    public ResponseEntity<MedicalRecord> putMedicalRecord(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody MedicalRecord body) {
      LOGGER.debug("HTTP PUT, Update medical record ({}, {}).", body.getFirstName(), body.getLastName());
      MedicalRecord medicalRecord = medicalRecordBusiness.updateMedicalRecord(body);
      if (medicalRecord == null) {
        LOGGER.debug("HTTP PUT, BAD_REQUEST ({}, {}).", body.getFirstName(), body.getLastName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
      }
      LOGGER.debug("HTTP PUT, SUCCESSFUL ({}, {}).", body.getFirstName(), body.getLastName());
      return ResponseEntity.ok().body(medicalRecord);
    }

    /**
     * Delete - Delete an medical record
     * 
     * @param firstName - The first name of the medical record to delete
     * @param lastName - The last name of the medical record to delete
     */
    public ResponseEntity<Void> deleteMedicalRecord(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "firstName", required = false) String firstName,@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "lastName", required = false) String lastName) {
      LOGGER.debug("HTTP DELETE, Delete a medical record ({}, {}).", firstName, lastName);
      if (medicalRecordBusiness.deleteMedicalRecord(firstName, lastName) == false) {
        LOGGER.debug("HTTP DELETE, BAD_REQUEST ({}, {}).", firstName, lastName);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
      }
      LOGGER.debug("HTTP DELETE, NO_CONTENT ({}, {}).", firstName, lastName);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
