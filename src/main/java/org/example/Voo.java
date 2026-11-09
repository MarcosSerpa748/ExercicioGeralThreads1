package org.example;

public class Voo {
    private String destino;
    private Integer assentosDisponiveis;

    public Voo(String destino,Integer assentosDisponiveis){
        this.destino = destino;
        this.assentosDisponiveis = assentosDisponiveis;

    }

    public synchronized Boolean comprarPassagem(){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            return false;
            //Aqui retorna um false pois quando essa atividade for interrompida pelo awaitTermination() e o shutdownNow().
            //O catch será acionado retornando um false e impedindo que as threads processem dados errados lá no CompletableFuture
            //Pois os métodos CompletableFuture dependem do valor boolean que sair daqui para saber se processam ou não os valores.
            //Dessa forma, processando de forma correta os dados e fazendo a atividade da forma correta.
        }

        if (this.assentosDisponiveis > 0){
            this.assentosDisponiveis--;
            return true;
        }else{
            return false;
        }
    }
}
