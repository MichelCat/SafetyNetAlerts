/* Setting up SafetyNetAlertsProd DB */
drop database if exists SafetyNetAlertsProd;

create database SafetyNetAlertsProd;
use SafetyNetAlertsProd;

create table T_FIRE_STATION_FIR(
	fir_station int NOT NULL,
	fir_address varchar(50) NOT NULL,
	PRIMARY KEY (fir_station),
	UNIQUE KEY (fir_address)
);

create table T_PERSON_PER(
	id int PRIMARY KEY AUTO_INCREMENT,
	per_first_name varchar(50) NOT NULL,
	per_last_name varchar(50) NOT NULL,
	per_address varchar(50) NOT NULL,
	per_city varchar(50) NOT NULL,
	per_zip varchar(5) NOT NULL,
	per_phone_number varchar(15) NOT NULL,
	per_email varchar(255) NOT NULL,
	per_birthdate date,
	UNIQUE KEY (per_first_name, per_last_name)
);

create table T_MEDICAL_RECORD_MER(
	id int PRIMARY KEY AUTO_INCREMENT,
	mer_id_person int NOT NULL,
	UNIQUE KEY (mer_id_person),
	FOREIGN KEY (mer_id_person) REFERENCES T_PERSON_PER (id)
);

create table TR_ALLERGY_ALL(
	id int PRIMARY KEY AUTO_INCREMENT,
	all_allergy varchar(50) NOT NULL,
	UNIQUE KEY (all_allergy)
);

create table TR_MEDICATION_MED(
	id int PRIMARY KEY AUTO_INCREMENT,
	med_medication varchar(50) NOT NULL,
	UNIQUE KEY (med_medication)
);

create table TJ_MEDICAL_RECORD_ALLERGY_MRA(
	mra_id_medical_record int NOT NULL,
	mra_id_allergy int NOT NULL,
    PRIMARY KEY (mra_id_medical_record, mra_id_allergy),
	FOREIGN KEY (mra_id_medical_record) REFERENCES T_MEDICAL_RECORD_MER (id)
        on delete CASCADE
        on update CASCADE,
	FOREIGN KEY (mra_id_allergy) REFERENCES TR_ALLERGY_ALL (id)
        on delete CASCADE
        on update CASCADE
);

create table TJ_MEDICAL_RECORD_MEDICATION_MRM(
	mrm_id_medical_record int NOT NULL,
	mrm_id_medication int NOT NULL,
	mrm_dosage varchar(20),
    PRIMARY KEY (mrm_id_medical_record, mrm_id_medication),
	FOREIGN KEY (mrm_id_medical_record) REFERENCES T_MEDICAL_RECORD_MER (id)
        on delete CASCADE
        on update CASCADE,
	FOREIGN KEY (mrm_id_medication) REFERENCES TR_MEDICATION_MED (id)
        on delete CASCADE
        on update CASCADE
);
