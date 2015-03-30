CREATE SCHEMA Shoe_Runner;
USE Shoe_Runner;




CREATE TABLE Shoe_Runner.Request_Status(
	id				int NOT NULL AUTO_INCREMENT,
	name			nvarchar(50),
	description		nvarchar(50),
	created_time 	DATETIME,
	created_by 		nvarchar(50),
	modified_time 	DATETIME,
	modified_by 	nvarchar(50),

	PRIMARY KEY(id),
	UNIQUE INDEX `id_UNIQUE` (`id` ASC)
);

DELIMITER //
CREATE TRIGGER before_insert_request_status
	BEFORE INSERT ON Shoe_Runner.Request_Status
	FOR EACH ROW
		BEGIN
        SET new.created_by = USER(),
			new.modified_by = USER(),
			new.created_time = UTC_TIMESTAMP(),
			new.modified_time = UTC_TIMESTAMP();
        IF new.id = "" OR new.id IS NULL THEN
			SET new.id = UUID();
        END IF;
  END//

DELIMITER ;

CREATE TRIGGER before_modify_request_status
	BEFORE UPDATE ON Shoe_Runner.Request_Status
	FOR EACH ROW
		SET new.modified_by = USER(),
		new.modified_time = UTC_TIMESTAMP();

CREATE TABLE Shoe_Runner.Shoerunner_Request(
	id						nvarchar(36) NOT NULL,
	employee_id				int(10) unsigned,
	shoerunner_id				nvarchar(36),
	request_status_id			int,
	created_time 			DATETIME,
	created_by 				nvarchar(50),
	modified_time 			DATETIME,
	modified_by 			nvarchar(50),

	PRIMARY KEY(id),
	FOREIGN KEY (request_status_id) REFERENCES Shoe_Runner.Request_Status(id),
	UNIQUE INDEX `id_UNIQUE` (`id` ASC)
);

DELIMITER //
CREATE TRIGGER before_insert_shoerunner_request
	BEFORE INSERT ON Shoe_Runner.Shoerunner_Request
	FOR EACH ROW
		BEGIN
        SET new.created_by = USER(),
			new.modified_by = USER(),
			new.created_time = UTC_TIMESTAMP(),
			new.modified_time = UTC_TIMESTAMP();
        IF new.id = "" OR new.id IS NULL THEN
			SET new.id = UUID();
        END IF;
  END//

DELIMITER ;


CREATE TRIGGER before_modify_shoerunner_request
	BEFORE UPDATE ON Shoe_Runner.Shoerunner_Request
	FOR EACH ROW
		SET new.modified_by = USER(),
		new.modified_time = UTC_TIMESTAMP();

CREATE TABLE Shoe_Runner.Request_Item_Map (
    request_id nvarchar(36) NOT NULL,
    item_sku nvarchar(50) NOT NULL,
    item_upc nvarchar(50),
    description nvarchar(100) DEFAULT NULL,
    item_image_url nvarchar(50),
	color nvarchar(50),
	size nvarchar(50),
    created_time DATETIME,
    created_by nvarchar(50),
    modified_time DATETIME,
    modified_by nvarchar(50),
    PRIMARY KEY (request_id , item_upc),
    FOREIGN KEY (request_id)
        REFERENCES Shoe_Runner.Shoerunner_Request (id)
);

CREATE TRIGGER before_insert_request_item_map
	BEFORE INSERT ON Shoe_Runner.Request_Item_Map
	FOR EACH ROW
	SET new.created_by = USER(), 
		new.modified_by = USER(),
		new.created_time = UTC_TIMESTAMP(),
		new.modified_time = UTC_TIMESTAMP();

CREATE TRIGGER before_modify_request_item_map
	BEFORE UPDATE ON Shoe_Runner.Request_Item_Map
	FOR EACH ROW
		SET new.modified_by = USER(),
		new.modified_time = UTC_TIMESTAMP();