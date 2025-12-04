package org.msd.ebankingbackend.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration du cache Spring.
 * Utilise le cache en mémoire par défaut (ConcurrentHashMap).
 */
@Configuration
@EnableCaching
public class CacheConfig {
    // Configuration par défaut, peut être personnalisée plus tard avec Caffeine ou Redis
}
