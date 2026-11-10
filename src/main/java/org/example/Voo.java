package org.example;

public class Voo {
    private String destino;
    private Integer assentosDisponiveis;

    public Voo(String destino,Integer assentosDisponiveis){
        this.destino = destino;
        this.assentosDisponiveis = assentosDisponiveis;

    }

    public synchronized Boolean comprarPassagem(){
        if (Thread.currentThread().isInterrupted()){
            return false;
            //Aqui retorna um false pois quando essa atividade for interrompida pelo awaitTermination() e o shutdownNow().
            //A condição será acionado retornando um false e impedindo que as threads processem dados errados lá no CompletableFuture
            //Pois os métodos CompletableFuture dependem do valor boolean que sair daqui para saber se processam ou não os valores.
            //Dessa forma, processando de forma correta os dados e fazendo a atividade da forma correta.
            //Você também pode fazer essa verificação dentro do próprio CompletableFuture, sem problema
        }

        if (this.assentosDisponiveis > 0){
            this.assentosDisponiveis--;
            return true;
        }else{
            return false;
        }
    }
}
