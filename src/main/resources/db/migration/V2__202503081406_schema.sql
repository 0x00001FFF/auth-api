CREATE INDEX idx_roles_deleted_at ON roles (deleted_at);
CREATE INDEX idx_permissions_deleted_at ON permissions (deleted_at);
CREATE INDEX idx_users_deleted_at ON users(deleted_at);
CREATE INDEX idx_user_auth_deleted_at ON user_auth(deleted_at);
CREATE INDEX idx_role_permissions_role_permission_id ON role_permission(role_id, permission_id);
CREATE INDEX idx_user_roles_role_user_id ON user_roles(role_id, user_id);