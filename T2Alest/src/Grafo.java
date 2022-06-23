import java.util.ArrayList;

public class Grafo<TIPO> {
    private ArrayList<Vertice<TIPO>> vertices;
    private ArrayList<Aresta<TIPO>> arestas;
    public static int count=0;
    public static int op=0;
    public static int looping=0;
    
    public Grafo(){
        this.vertices = new ArrayList<Vertice<TIPO>>();
        this.arestas = new ArrayList<Aresta<TIPO>>();
    }
    
    public void adicionarVertice(Integer dado,String a){
        Vertice<TIPO> novoVertice = new Vertice<TIPO>(dado,a);
        this.vertices.add(novoVertice);
    }
    
    public void adicionarAresta(Integer d1, Integer d2){
        Vertice<TIPO> v1 = this.getVertice(d1);
        Vertice<TIPO> v2 = this.getVertice(d2);
        Aresta<TIPO> aresta1 = new Aresta<TIPO>(v1, v2);
        v1.adicionarAresta(aresta1);
        v2.adicionarAresta(aresta1);
        this.arestas.add(aresta1);
    }
    
    public Vertice<TIPO> getVertice(Integer dado){
        int inicio = 0; int fim = vertices.size(); int meio = 0;
        Vertice<TIPO> verticeProcurado = null;
        while(inicio <= fim) {
            meio = (fim + inicio) / 2;
            op++;
            if(vertices.get(meio).getDado().equals(dado)) {
                verticeProcurado = vertices.get(meio);
                Grafo.op++;
                break;
            }
            if(vertices.get(meio).getDado() < dado) {
                inicio = meio + 1;
                Grafo.op++;
            } 
            else {
                fim = meio - 1;
                Grafo.op++;
            }
            Grafo.looping++;
        }
        return verticeProcurado;
    }
    /*public Vertice<TIPO> getVertice(Integer dado){
        Vertice<TIPO> vertice = null;int aux=0;
        for(int i=0; i < this.vertices.size(); i++){
            if (this.vertices.get(i).getDado().equals(dado)){
                vertice = this.vertices.get(i);
                aux = i;
                break;
            }
            Grafo.op++;
        }
        Grafo.looping=looping+aux;
        return vertice;
    }*/

    public void labirintoContaCasas(){
        ArrayList<Integer> players = new ArrayList<Integer>();
        for(int k = 0; k<vertices.size();k++){
            if(vertices.get(k).getValor().matches("[1-9]")){
                players.add(k);
            }
        }
        while(players.size()>0){
            ArrayList<Vertice<TIPO>> marcados = new ArrayList<Vertice<TIPO>>();
            ArrayList<Vertice<TIPO>> fila = new ArrayList<Vertice<TIPO>>();
            ArrayList<Vertice<TIPO>> chaves = new ArrayList<Vertice<TIPO>>();
            ArrayList<Vertice<TIPO>> portas = new ArrayList<Vertice<TIPO>>();
            Vertice<TIPO> atual = this.vertices.get(players.get(0));
        
            int contaCasas = 1;
            marcados.add(atual);
            fila.add(atual);
            while(fila.size() > 0){
                Vertice<TIPO> visitado = fila.get(0);
                for(int i=0; i < visitado.getArestas().size(); i++){
                    Vertice<TIPO> proximo = visitado.getArestas().get(i).getFim();
                    if(!proximo.getValor().equals("#")){
                        if(!marcados.contains(proximo)){
                            if(proximo.getValor().matches("[a-z]")){
                                chaves.add(proximo);
                                marcados.add(proximo);
                                fila.add(proximo);
                                contaCasas=contaCasas+1;
                            }
                            else if(proximo.getValor().matches("[A-Z]")){
                                if(!portas.contains(proximo)){
                                    portas.add(proximo);
                                    for(int n=0;n<chaves.size();n++){
                                        if(chaves.get(n).getValor().toUpperCase().equals(proximo.getValor())){
                                            marcados.add(proximo);
                                            fila.add(proximo);
                                            contaCasas=contaCasas+1;
                                        }
                                    }
                                }
                            }
                            else{
                                marcados.add(proximo);
                                fila.add(proximo);
                                contaCasas=contaCasas+1;
                            }
                        }
                    }
                }
                fila.remove(0);
                for(int m=0;m<portas.size();m++){
                    for(int n=0;n<chaves.size();n++){
                        if(portas.get(m).getValor().equals(chaves.get(n).getValor().toUpperCase()) && !marcados.contains(portas.get(m))){
                            marcados.add(portas.get(m));
                            fila.add(portas.get(m));
                            contaCasas=contaCasas+1;
                        }
                    }
                }
            }
            System.out.println("Numero de casas possíveis de percorrer player "+vertices.get(players.get(0)).getValor() +"= "+ contaCasas);
            players.remove(0);
        }
    }

    public static int contador(){
        return count = count +1;
    }
}