package com.abhishek.gatryx.management.service;

import com.abhishek.gatryx.management.entity.Organization;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditService {

    private final JdbcTemplate jdbcTemplate;

    public AuditService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void record(Organization organization,
                       String action,
                       String resourceType,
                       String resourceId,
                       long startedAt,
                       String details) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String actor = authentication == null ? "SYSTEM" : authentication.getName();
        if (actor == null || actor.isBlank()) {
            actor = "SYSTEM";
        }
        if (actor.length() > 100) {
            actor = actor.substring(0, 100);
        }

        Long organizationId = organization == null ? null : organization.getId();
        long durationMs = Math.max(0L, (System.nanoTime() - startedAt) / 1_000_000L);

        jdbcTemplate.update("""
                INSERT INTO control_plane_audit_records
                    (organization_id, actor, action, resource_type, resource_id,
                     correlation_id, duration_ms, result, details, created_at)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """,
                organizationId,
                actor,
                action,
                resourceType,
                resourceId,
                null,
                durationMs,
                "SUCCESS",
                details,
                LocalDateTime.now());
    }
}
