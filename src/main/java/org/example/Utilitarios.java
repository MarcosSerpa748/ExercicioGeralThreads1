package org.example;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Utilitarios {


    public static void fecharPools(ExecutorService workers,ScheduledExecutorService scheduledWorkers,AtomicInteger valorTotalVoos, List<Integer> quantidadePassagensCompradas){
        workers.shutdown();
        try {
            if (!workers.awaitTermination(5, TimeUnit.SECONDS)){
                workers.shutdownNow();
            }
            scheduledWorkers.shutdownNow();
            System.out.println("Valor total de compras das passagens R$"+valorTotalVoos.get());
            System.out.println("Quantidade total de passagens comprados:"+quantidadePassagensCompradas.size());

        }catch(InterruptedException e){
            scheduledWorkers.shutdownNow();
            workers.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
