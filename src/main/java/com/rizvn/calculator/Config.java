package com.rizvn.calculator;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
@ComponentScan({ "com.rizvn.*" })
public class Config {

	@Bean
	public CacheManager cacheManager() {
    EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
    ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
    ehCacheManagerFactoryBean.setShared(true);

		return new EhCacheCacheManager(ehCacheManagerFactoryBean.getObject());
	}


	/*@Bean
    public CacheManager defaultCacheManager() {
        return new ConcurrentMapCacheManager("books");
    }*/
	
}