import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) throws Exception {
        Grafo<Integer> grafo = new Grafo<>();
        //Map<String, String> cadaLinha = new HashMap<String, String>();
        //Nome do arquivo passado na linha de comando
        //Descompressor descompressor = new Descompressor();
        try (Stream<String> stream = Files.lines(Paths.get("C:/Users/Luciano/Documents/T2Alest/T2Alest/src/caso01.txt"))) {
            stream.forEach(linha -> {
                String replaceRegex = linha.replaceAll("\\B|\\b", " ");
                String[] splitArray = replaceRegex.split(" ");
                grafo.adicionarVertice(Grafo.count,splitArray[1]);
                for(int i = 1;i<splitArray.length-1;i++){
                    grafo.adicionarVertice(Grafo.count+1,splitArray[i+1]);
                    grafo.adicionarAresta(Grafo.count,Grafo.count+1);
                    grafo.adicionarAresta(Grafo.count+1,Grafo.count);
                    if(Grafo.count>64){
                        grafo.adicionarAresta(Grafo.count, Grafo.count-65);
                        grafo.adicionarAresta(Grafo.count-65, Grafo.count);
                    }
                    if(i % 64==0){
                        Grafo.contador();
                    }
                    Grafo.contador();
                }
            });
        } catch (Exception e) {
            System.out.println("Problemas no processamento do arquivo de entrada");
        }
        grafo.buscaEmLargura();
    }
}
