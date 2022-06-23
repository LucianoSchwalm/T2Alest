import java.util.ArrayList;

public class Vertice<TIPO> {
    private String valor;
    private Integer dado;
    private ArrayList<Aresta<TIPO>> arestas;
    
    public Vertice(Integer dado,String valor){
        this.valor = valor;
        this.dado = dado;
        this.arestas = new ArrayList<Aresta<TIPO>>();
    }

    public Integer getDado() {
        return dado;
    }


    public String getValor() {
        return valor;
    }
    
    public void adicionarAresta(Aresta<TIPO> aresta){
        this.arestas.add(aresta);
    }

    public ArrayList<Aresta<TIPO>> getArestas() {
        return arestas;
    }

    public String toString(){
        return "Dado: "+ dado;
    }
}