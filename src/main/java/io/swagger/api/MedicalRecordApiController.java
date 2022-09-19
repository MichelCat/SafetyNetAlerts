package io.swagger.api;

import io.swagger.business.MedicalRecordBusiness;
import io.swagger.model.MedicalRecord;
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
import javax.validation.Valid;
import java.net.URI;

@RestController
public class MedicalRecordApiController implements MedicalRecordApi {

    private static final Logger log = LoggerFactory.getLogger(MedicalRecordApiController.class);

    private final MedicalRecordBusiness medicalRecordBusiness;

    @Autowired
    public MedicalRecordApiController(MedicalRecordBusiness medicalRecordBusiness) {
        this.medicalRecordBusiness = medicalRecordBusiness;
    }

    public ResponseEntity<Void> deleteMedicalRecord(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "firstName", required = false) String firstName,@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "lastName", required = false) String lastName) {
      try {
        medicalRecordBusiness.deleteMedicalRecord(firstName, lastName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
      } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
      }
    }

    public ResponseEntity<MedicalRecord> postMedicalRecord(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody MedicalRecord body) {
      MedicalRecord medicalRecord = medicalRecordBusiness.saveMedicalRecord(body);
      if (medicalRecord == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
      }
      URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(medicalRecord.getId())
                    .toUri();
      return ResponseEntity.created(location).body(medicalRecord);
    }

    public ResponseEntity<MedicalRecord> putMedicalRecord(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody MedicalRecord body) {
      MedicalRecord medicalRecord = medicalRecordBusiness.updateMedicalRecord(body);
      if (medicalRecord == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
      }
      return ResponseEntity.ok().body(medicalRecord);
    }

}
