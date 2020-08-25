/* MySQL */

-- db
CREATE DATABASE db;

USE db;


-- pojo
CREATE TABLE pojo (
	id INT AUTO_INCREMENT,

	name VARCHAR(255),

	date DATETIME,

	--
	PRIMARY KEY (id)
) ENGINE = InnoDB;


-- keygen
CREATE TABLE keygen (
	name VARCHAR(255),
	value INT,

	--
	PRIMARY KEY (name)
) ENGINE = InnoDB;


-- admin
CREATE TABLE admin (
	adminid INT,

	username VARCHAR(255),
	password VARCHAR(255),

	status INT,

	createdate DATETIME,

	--
	PRIMARY KEY (adminid),
	UNIQUE INDEX (username)
) ENGINE = InnoDB;


-- log
CREATE TABLE log (
	logid INT,

	adminid INT,

	module VARCHAR(255),

	action VARCHAR(255),
	message VARCHAR(4000),

	ip VARCHAR(255),

	status INT,

	createdate DATETIME,

	--
	PRIMARY KEY (logid)
) ENGINE = InnoDB;


-- [session]
CREATE TABLE session (
	sessionid INT,

	adminid INT,

	value VARCHAR(255),

	status INT,

	createdate DATETIME,

	--
	PRIMARY KEY (sessionid)
) ENGINE = InnoDB;


--
INSERT INTO admin VALUES (1, 'admin', '', 0, '1970-01-01 00:00:00');

INSERT INTO keygen VALUES ('Admin', 1);


--
CREATE PROCEDURE p(n INT)

BEGIN

	DECLARE counter INT DEFAULT 0;

	WHILE counter < n DO

		INSERT INTO `pojo` (`name`, `date`)
			VALUES (
				CONCAT('n', (SELECT LAST_INSERT_ID()) + 1),
				DATE_ADD('1970-01-01 00:00:00', INTERVAL counter SECOND)
			);

		SET counter = counter + 1;

	END WHILE;

END;
