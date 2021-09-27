package org.oagBlog.blogapp.config.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.oagBlog.blogapp.entity.user.UserEntity;

import java.util.Optional;
import java.util.UUID;

/**
 * Bu class auditing uchun hizmat qiladi
 * Sistemada kim borligini qaytaradi
 */
public class SpringSecurityAuditAwareImpl implements AuditorAware<UUID> {
    @Override
    public Optional<UUID> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null
                && authentication.isAuthenticated()
                && !authentication.getPrincipal().equals("anonymousUser")) {
            UserEntity user = (UserEntity) authentication.getPrincipal();
            return Optional.of(user.getId());
        }
        return Optional.empty();
    }
}
