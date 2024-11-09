    //tentando fazer com base no jantar dos filosofos

public class Escritor implements Runnable {

    private String name;

    private Livro livro;

    private int numeroPausasLanche = 0;

    private int numeroLivrosEscritos = 0;

    private final static int MAX_PAUSAS = 10;

    private String state = "Acordando";

    public Escritor(String name, Livro livro) {
        this.name = name;
        this.livro = livro;
    }

    public String getName() {
        return this.name;
    }

    public String getState() {
        return this.state;
    }

    public int getNumeroPausasLanche() {
        return this.numeroPausasLanche;
    }

    public int getNumeroEscritas() {
        return this.numeroLivrosEscritos;
    }

    public boolean possuiOLivro() {
        return this.livro.estaSendoEscritoPor(this);
    }

    public void descansar() {
        this.state = "descansando";
        this.numeroPausasLanche++;
        this.descansando();
    }

    public void descansando() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {

        }
    }

    private void escrever() throws InterruptedException {

        this.state = "Tentando começar a escrever...";

        while (!this.possuiOLivro()) {
            
            if (!this.livro.estaSendoLido()) {
                this.livro.pegandoLivro(this);
                this.state = "Pegando o livro (" + this.livro.getName() + ") livro pego";
            } 
        }

        this.state = "Lendo...";
        this.escrevendo();

        this.state = "Devolvendo o Livro";
        this.livro.devolvendoLivro(this);

        this.state = "Escrevendo";
        this.escrevendo();

        this.state = "Devolvendo Livros";
        this.livro.devolvendoLivro(this);

    }

    public void escrevendo() {
        this.numeroLivrosEscritos++;

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {

        }
    }

    @Override
    public void run() {
        try {
            while (this.numberOfThoughts < MAX_THOUGHTS) {
                this.think();
                this.eat();
            }

            this.state = "DONE";

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("!!!!!!!!!!!!!!!!!");
        }
    }
}
