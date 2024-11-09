    //tentando fazer com base no jantar dos filosofos

public class Leitor implements Runnable {

    private String name;

    private Livro livro;

    private int numero_Pausas_café = 0;
    
    private int numero_Leituras = 0;

    private final static int MAX_PAUSAS = 10;

    private String state = "Acordando";

    public Leitor(String name, Livro livro) {
        this.name = name;
        this.livro = livro;
    }

    public String getName() {
        return this.name;
    }

    public String getState() {
        return this.state;
    }

    public int getNumeroPausasCafé() {
        return this.numero_Pausas_café;
    }

    public int getNumeroLeituras() {
        return this.numero_Leituras;
    }

    public boolean possuiOLivro() {
        return this.livro.estaSendoLidoPor(this);
    }

    // public boolean hasBothForks() {
    //     return this.hasLeftFork() && this.hasRightFork();
    // }

    public void descansar() {
        this.state = "Tomando café...";
        this.numero_Pausas_café++;
        this.descansando();
    }

    public void descansando() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
    }

    private void ler() throws InterruptedException {

        this.state = "Tentando começar a Ler...";

        while (!this.possuiOLivro()) {
            
            if (!this.livro.estaSendoLido()) {
                this.livro.pegandoLivro(this);
                this.state = "Pegando o livro (" + this.livro.getName() + ") livro pego";
            } 
        }

        this.state = "Lendo...";
        this.lendo();

        this.state = "Devolvendo o Livro";
        this.livro.devolvendoLivro(this);
    }

    public void lendo() {
        this.numero_Leituras++;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
    }

    @Override
    public void run() {
        try {
            while (this.numero_Pausas_café < MAX_PAUSAS) {
                this.descansar();
                this.ler();
            }

            this.state = "DONE";

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("!!!!!!!!!!!!!!!!!");
        }
    }
}
