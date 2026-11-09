package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        ExecutorService workers = Executors.newFixedThreadPool(4);
        ScheduledExecutorService scheduledWorkes = Executors.newScheduledThreadPool(1);
        AtomicInteger valorTotal = new AtomicInteger(0);
        List<Integer> quantidadeTotal = Collections.synchronizedList(new ArrayList<>());


        Voo v1 = new Voo("USA",21);

        for (int i = 1;i <= 25;i++){

            CompletableFuture.supplyAsync(()->{
                 return v1.comprarPassagem();
                 },workers).thenApply(valorPassado ->{
                     if (valorPassado){
                         return 500;
                     }else{
                         throw new RuntimeException("Tickets esgotados!");
                     }
                 }).exceptionally(excecao ->{
                     System.out.println(excecao.getCause().getMessage());
                     return 0;
                 }).thenAccept(valorPassado ->{
                     if (valorPassado > 0){
                         valorTotal.getAndAdd(valorPassado);
                         quantidadeTotal.add(1);
                     }
                 });
        }
        scheduledWorkes.scheduleWithFixedDelay(() ->{
            try {
                System.out.println("O valor total das compras dos voos foi de R$"+valorTotal.get());
                System.out.println("Quantidade total de passagens compradas até agora:"+quantidadeTotal.size());
            }catch (Exception e){
                System.out.println();
            }
        },1,1, TimeUnit.SECONDS);

        Utilitarios.fecharPools(workers,scheduledWorkes,valorTotal,quantidadeTotal);
        System.out.println("Fazendo outras coisas...");
    }
}