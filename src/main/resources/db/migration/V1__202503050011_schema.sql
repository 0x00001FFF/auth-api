CREATE TABLE users (
id UUID PRIMARY KEY,
user_name VARCHAR(255) UNIQUE NOT NULL,
email VARCHAR(255) UNIQUE NOT NULL,
is_active BOOL DEFAULT FALSE,
email_verified BOOL DEFAULT FALSE
);

CREATE TABLE user_auth (
	id UUID PRIMARY KEY,
	user_id UUID UNIQUE NOT NULL REFERENCES
	users(id) ON
DELETE
	CASCADE,
	password_hash VARCHAR(255),
	last_login TIMESTAMP,
	failed_login_attempts INT DEFAULT 0,
	password_reset_token CHAR(6),
	password_reset_expiry TIMESTAMP,
	is_locked BOOLEAN DEFAULT FALSE
);

CREATE TABLE roles (
	id UUID PRIMARY KEY,
	name VARCHAR(50) UNIQUE NOT NULL,
	description VARCHAR(255)
);

CREATE TABLE permissions (
	id UUID PRIMARY KEY,
	name VARCHAR(50) UNIQUE NOT NULL
);