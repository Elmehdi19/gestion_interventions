package com.pgi.pgic.util;

import org.springframework.stereotype.Component;
import java.time.Year;

@Component
public class NumeroGenerator {
    private static long counter = 0;
    
    public synchronized String genererNumeroReclamation() {
        counter++;
        return String.format("REC-%d-%05d", Year.now().getValue(), counter);
    }
    
    public synchronized String genererNumeroIntervention() {
        return String.format("INT-%d-%05d", Year.now().getValue(), counter);
    }
}