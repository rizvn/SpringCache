package com.rizvn.calculator;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@CacheConfig(cacheNames = "calculator_cache")
public class SlowCalculator {

	@Cacheable
	public Double calculateHypotenuse(int a, int b)
	{
		System.out.println("Calculating hypotenuse...");
		sleep(5000L);
		return Math.sqrt(Math.pow(a,2) + Math.pow(b,2));
	}
	
	private void sleep(long seconds){
		try {
				Thread.sleep(seconds);
		} catch (Exception e) {
				throw new IllegalStateException(e);
		}
	}
	
}