INSERT INTO management_users (
    organization_id, role_id, username, password_hash, enabled, created_at, updated_at
)
SELECT NULL, roles.id, 'platform-admin',
       '$2a$12$VW6sUxadpSMiPPQhIFBZjetXxeOQnk4hmn3kyzkJ5aopDOrsqjAEW',
       TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
FROM roles
WHERE roles.name = 'PLATFORM_ADMIN';


INSERT INTO management_users (
    organization_id, role_id, username, password_hash, enabled, created_at, updated_at
)
SELECT NULL, roles.id, 'gateway-service',
       '$2a$12$BXwwlpjUHESPwx/0tBpWGO3B17vDADyVvhk/9cvUHq5xk/bsqGSuu',
       TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
FROM roles
WHERE roles.name = 'GATEWAY_SERVICE';