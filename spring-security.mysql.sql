/* MySQL */

-- User Schema
CREATE TABLE users (
	username VARCHAR(50) NOT NULL PRIMARY KEY,
	password VARCHAR(50) NOT NULL,
	enabled TINYINT(1) NOT NULL
);

CREATE TABLE authorities (
	username VARCHAR(50) NOT NULL,
	authority VARCHAR(50) NOT NULL,
	FOREIGN KEY (username) REFERENCES users (username),
	UNIQUE INDEX (username, authority)
);


-- Group Authorities
CREATE TABLE groups (
	id BIGINT AUTO_INCREMENT PRIMARY KEY ,
	group_name VARCHAR(50) NOT NULL
);

CREATE TABLE group_authorities (
	group_id BIGINT NOT NULL,
	authority VARCHAR(50) NOT NULL,
	FOREIGN KEY (group_id) REFERENCES groups (id)
);

CREATE TABLE group_members (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(50) NOT NULL,
	group_id BIGINT NOT NULL,
	FOREIGN KEY (group_id) REFERENCES groups (id)
);


-- Persistent Login (Remember-Me) Schema
CREATE TABLE persistent_logins (
	username VARCHAR(64) NOT NULL,
	series VARCHAR(64) PRIMARY KEY,
	token VARCHAR(64) NOT NULL,
	last_used TIMESTAMP NOT NULL
);


-- [ACL Schema]
CREATE TABLE acl_sid (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	principal BOOLEAN NOT NULL,
	sid VARCHAR(100) NOT NULL,
	UNIQUE KEY unique_acl_sid (sid, principal)
) ENGINE=InnoDB;

CREATE TABLE acl_class (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	class VARCHAR(100) NOT NULL,
	UNIQUE KEY uk_acl_class (class)
) ENGINE=InnoDB;

CREATE TABLE acl_object_identity (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	object_id_class BIGINT UNSIGNED NOT NULL,
	object_id_identity BIGINT NOT NULL,
	parent_object BIGINT UNSIGNED,
	owner_sid BIGINT UNSIGNED,
	entries_inheriting BOOLEAN NOT NULL,
	UNIQUE KEY uk_acl_object_identity (object_id_class, object_id_identity),
	CONSTRAINT fk_acl_object_identity_parent FOREIGN KEY (parent_object) REFERENCES acl_object_identity (id),
	CONSTRAINT fk_acl_object_identity_class FOREIGN KEY (object_id_class) REFERENCES acl_class (id),
	CONSTRAINT fk_acl_object_identity_owner FOREIGN KEY (owner_sid) REFERENCES acl_sid (id)
) ENGINE=InnoDB;

CREATE TABLE acl_entry (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	acl_object_identity BIGINT UNSIGNED NOT NULL,
	ace_order INTEGER NOT NULL,
	sid BIGINT UNSIGNED NOT NULL,
	mask INTEGER UNSIGNED NOT NULL,
	granting BOOLEAN NOT NULL,
	audit_success BOOLEAN NOT NULL,
	audit_failure BOOLEAN NOT NULL,
	UNIQUE KEY unique_acl_entry (acl_object_identity, ace_order),
	CONSTRAINT fk_acl_entry_object FOREIGN KEY (acl_object_identity) REFERENCES acl_object_identity (id),
	CONSTRAINT fk_acl_entry_acl FOREIGN KEY (sid) REFERENCES acl_sid (id)
) ENGINE=InnoDB;


--
INSERT INTO users VALUES ('admin', '102382b79c4eeb26efb917fd38e4ddac72e9b2a8', 1);

INSERT INTO groups VALUES (1, 'GROUP_ADMIN');
INSERT INTO group_authorities VALUES (1, 'ROLE_ADMIN');
INSERT INTO group_members VALUES (1, 'admin', 1);
