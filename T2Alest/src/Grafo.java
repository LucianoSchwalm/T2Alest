import java.util.ArrayList;

public class Grafo<TIPO> {
    private ArrayList<Vertice<TIPO>> vertices;
    private ArrayList<Aresta<TIPO>> arestas;
    public static int count=0;
    
    public Grafo(){
        this.vertices = new ArrayList<Vertice<TIPO>>();
        this.arestas = new ArrayList<Aresta<TIPO>>();
    }
    
    public void adicionarVertice(TIPO dado,String a){
        Vertice<TIPO> novoVertice = new Vertice<TIPO>(dado,a);
        this.vertices.add(novoVertice);
    }
    
    public void adicionarAresta(TIPO d1, TIPO d2){
        Vertice<TIPO> v1 = this.getVertice(d1);
        Vertice<TIPO> v2 = this.getVertice(d2);
        Aresta<TIPO> aresta1 = new Aresta<TIPO>(v1, v2);
        v1.adicionarAresta(aresta1);
        v2.adicionarAresta(aresta1);
        this.arestas.add(aresta1);
    }
    
    public Vertice<TIPO> getVertice(TIPO dado){
        Vertice<TIPO> vertice = null;
        for(int i=0; i < this.vertices.size(); i++){
            if (this.vertices.get(i).getDado().equals(dado)){
                vertice = this.vertices.get(i);
                break;
            }
        }
        return vertice;
    }

    public void buscaEmLargura(){
        ArrayList<Integer> players = new ArrayList<Integer>();
        for(int k = 0; k<vertices.size();k++){
            if(vertices.get(k).getValor().matches("[1-9]")){
                players.add(k);
            }
        }
        System.out.println(players);
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