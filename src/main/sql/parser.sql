CREATE TABLE tbl_esk363(
	id BIGINT NOT NULL AUTO_INCREMENT,
	rec_date DATETIME NOT NULL,
	rec_log_level VARCHAR(10) NOT NULL,
	rec_item_count INT NOT NULL,
	PRIMARY KEY(id)
) Engine InnoDB;
