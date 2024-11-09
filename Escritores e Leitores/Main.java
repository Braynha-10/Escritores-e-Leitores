import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String ... args) {

        final int NUMBER_OF_Leitores_escritores_AND_Livros = 5;

        final List<Livro> livros = new ArrayList<Livro>();

        for (int i = 0; i < NUMBER_OF_Leitores_escritores_AND_Livros; i++) {
            String name = "Livro " + new Integer(i + 1).toString();
            livros.add(new Livro(name));
        }

        final List<Leitor> leitores = new ArrayList<Leitor>();

        final List<Escritor> escritores = new ArrayList<Escritor>();

        for (int i = 0; i < NUMBER_OF_Leitores_escritores_AND_Livros; i++) {
            
            String name = "P" + new Integer(i + 1).toString();
            
            Livro livro = livros.get(i);
            // Livro livro = forks.get((i + 1) % NUMBER_OF_Leitores_AND_Livros);

            Leitor leitor = new Leitor(name, livro);

            leitores.add(leitor);

            new Thread(leitor).start();
        }

        for (int i = 0; i < NUMBER_OF_Leitores_escritores_AND_Livros; i++) {
            
            String name = "P" + new Integer(i + 1).toString();
            
            Livro livro = livros.get(i);
            // Livro livro = forks.get((i + 1) % NUMBER_OF_Leitores_AND_Livros);

            Escritor escritor = new Escritor(name, livro);

            escritores.add(escritor);

            new Thread(escritor).start();
        }

        boolean hasRunningThreads;

        do {

            hasRunningThreads = false;

            for (Leitor p: leitores) {

                String messageLog = p.getName() + ": " + p.getState();
                messageLog += " | Pausas = " + p.getNumeroPausasCafé();
                messageLog += " | Leituras: " + p.getNumeroLeituras();

                System.out.println(messageLog);

                hasRunningThreads |= p.getState() != "DONE";
            }
            
            System.out.println("********************************************\n\n");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
        
            }

        } while(hasRunningThreads);
    }
}