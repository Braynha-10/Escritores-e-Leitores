public class Livro{
    //tentando fazer com base no jantar dos filosofos
    private String name;
    private Leitor user_L;
    private Escritor user_E;
    // private Boolean concluido;
    private int quantidadeLivros = 3;

    public Livro(String name){
        this.name=name;
    }

    public int getLivrosProntos() {
        return this.quantidadeLivros;
    }
    public String getName() {
        return this.name;
    }

    public Leitor getLeitor() {
        return this.user_L;
    }

    public Escritor getEscritor() {
        return this.user_E;
    }


    public synchronized void pegandoLivro(Leitor leitor) throws InterruptedException {
        while (this.user_L != null && !this.user_L.equals(leitor)) {
            wait();
        }

        this.user_L = leitor;
    }

    public synchronized void devolvendoLivro(Leitor leitor) {
        if (this.user_L.equals(leitor)) {
            this.user_L = null;
            notifyAll();
        }
    }

    public synchronized void pegandoLivro(Escritor escritor) throws InterruptedException {
        while (this.user_E != null && !this.user_E.equals(escritor)) {
            wait();
        }

        this.user_E = escritor;
    }

    public synchronized void devolvendoLivro(Escritor escritor) {
        if (this.user_E.equals(escritor)) {
            this.user_E = null;
            notifyAll();
        }
    }


    public boolean estaSendoEscrito() {
        return this.user_E != null;
    }

    public boolean estaSendoLido(){
        return this.user_L != null;
    }

    public boolean estaSendoLidoPor(Leitor leitor) {

        try {
            if (this.user_L == null)
                return false;

            if (this.user_L.equals(leitor))
                return true;

            return false;

        } catch (NullPointerException e) {
            return false;
        }
    }

    public boolean estaSendoEscritoPor(Escritor escritor) {

        try {
            if (this.user_E == null)
                return false;

            if (this.user_E.equals(escritor))
                return true;

            return false;

        } catch (NullPointerException e) {
            return false;
        }
    }

}