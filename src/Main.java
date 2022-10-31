/**
 * Aluno: Bruno Yudi Mino Okada
 *
 * Sua  tarefa  será  imprimir  a  Pilha  do  DFS  e  a  Fila  do  BFS  quando  aplicadas  a  um  grafo  de  100
 * vértices cujos índices serão criados a partir de números aleatórias gerados entre 1 e 1000. Nesta tarefa
 * você pode usar todos os algoritmos que desenvolveu ao longo desta disciplina.
 */

import java.util.*;

// Classe dos vertices do grafo
class Vertice{
    public int valor;
    public boolean foiVisitado;

    // Construtor do vertice
    public Vertice(int val){
        valor = val;
        foiVisitado = false;
    }
}

class GrafoBFS{
    public final int MAX_VERTICES_BFS = 1000;
    public Vertice listaVerticesBFS[];
    public int matrizAdjBFS[][];
    public int nVertices;
    public Queue<Integer> q;

    // Construtor do grafo para BFS
    public GrafoBFS(){
        listaVerticesBFS = new Vertice[MAX_VERTICES_BFS];
        matrizAdjBFS = new int[MAX_VERTICES_BFS][MAX_VERTICES_BFS];
        nVertices = 0;
        q = new LinkedList<Integer>();
    }

    // Funcao para adicionar vertices na lista de vertices do grafo BFS
    public void adicionarVerticesBFS(int val){
        listaVerticesBFS[nVertices++] = new Vertice(val);
    }

    // Funcao para adicionar as arestas do grafo BFS
    public void adicionarArestasBFS(int comeco, int fim){
        // Posicoes da matriz com valor = 1 significa que ha uma conexao entre os vertices
        matrizAdjBFS[comeco][fim] = 1;
        matrizAdjBFS[fim][comeco] = 1;
    }

    // Funcao para imprimir o vertice da lista
    public void imprimirVerticeBFS(int vert){
        System.out.println(listaVerticesBFS[vert].valor);
    }

    // Funcao para adquirir o vertice adjacente ao indicado, que ainda nao foi visitado
    public int verticeAdjNaoVisitado(int vert){
        for(int j = 0; j < nVertices; j++){
            if(matrizAdjBFS[vert][j] == 1 && listaVerticesBFS[j].foiVisitado == false){
                return j;
            }
        }
        return -1;
    }

    // Funcao para realizar o BFS no grafo
    public void bfs(){
        listaVerticesBFS[0].foiVisitado = true;
        imprimirVerticeBFS(0);
        q.add(0);
        int v2;

        while(!q.isEmpty()){
            int v1 = q.remove();
            while((v2 = verticeAdjNaoVisitado(v1)) != -1){
                listaVerticesBFS[v2].foiVisitado = true;
                imprimirVerticeBFS(v2);
                q.add(v2);
            }
        }
    }
}

class GrafoDFS{
    public final int MAX_VERTICES_DFS = 1000;
    public Vertice listaVerticesDFS[];
    public int matrizAdjDFS[][];
    public int nVerticesDFS;
    public Stack<Integer> s;

    // Construtor do grafo para DFS
    public GrafoDFS(){
        listaVerticesDFS = new Vertice[MAX_VERTICES_DFS];
        matrizAdjDFS = new int[MAX_VERTICES_DFS][MAX_VERTICES_DFS];
        nVerticesDFS = 0;
        s = new Stack<Integer>();
    }

    // Funcao para adicionar o vertice na lista de vertices do grafo DFS
    public void adicionarVerticesDFS(int val){
        listaVerticesDFS[nVerticesDFS++] = new Vertice(val);
    }

    // Funcao para adicionar arestas no grafo DFS
    public void adicionarArestasDFS(int comeco, int fim){
        // Posicoes da matriz com valor = 1 significa que ha uma conexao entre os vertices
        matrizAdjDFS[comeco][fim] = 1;
        matrizAdjDFS[fim][comeco] = 1;
    }

    // Funcao para imprimir o valor do vertice
    public void imprimirVerticeDFS(int vert){
        System.out.println(listaVerticesDFS[vert].valor + " ");
    }

    // Funcao para adquirir o vertice adjacente ao indicado, que ainda nao foi visitado
    public int verticeAdjNaoVisitadoDFS(int vert){
        for(int j = 0; j < nVerticesDFS; j++){
            if(matrizAdjDFS[vert][j] == 1 && listaVerticesDFS[j].foiVisitado == false){
                return j;
            }
        }
        return -1;
    }

    // Funcao para realizar o DFS no grafo
    public void dfs(){
        listaVerticesDFS[0].foiVisitado = true;
        imprimirVerticeDFS(0);
        s.push(0);

        while(!s.isEmpty()){
            int v = verticeAdjNaoVisitadoDFS(s.peek());

            if(v == -1){
                s.pop();
            } else{
                listaVerticesDFS[v].foiVisitado = true;
                imprimirVerticeDFS(v);
                s.push(v);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        GrafoBFS grafoBFS = new GrafoBFS();
        GrafoDFS grafoDFS = new GrafoDFS();
        // Indicacao dos possiveis valores min e max dos vertices
        // para a geracao de valores aleatorios
        int valorMin = 1;
        int valorMax = 1000;

        // Geracao de 100 valores aleatorios entre 1 e 1000 para os vertices
        // E insercao destes no grafo BFS e DFS
        for(int i = 0; i < 100; i++){
            int vert = (int)Math.floor(Math.random()*(valorMax-valorMin+1)+valorMin);
            grafoBFS.adicionarVerticesBFS(vert);
            grafoDFS.adicionarVerticesDFS(vert);
        }

        // Geracao de arestas aleatorias
        for(int j = 5; j < 100; j+=5){
            grafoBFS.adicionarArestasBFS(j-5,j);
            grafoDFS.adicionarArestasDFS(j-5,j);
        }

        // Geracao de arestas aleatorias
        for(int x = 2; x < 100; x++){
            grafoBFS.adicionarArestasBFS(x-2,x);
            grafoDFS.adicionarArestasDFS(x-2,x);
        }

        // Impressao da ordem BFS do grafo
        System.out.println("BFS: ");
        grafoBFS.bfs();
        System.out.println();

        System.out.println("--------------------------------\n");

        // Impressao da ordem DFS do grafo
        System.out.println("DFS: ");
        grafoDFS.dfs();
        System.out.println();

    }
}
