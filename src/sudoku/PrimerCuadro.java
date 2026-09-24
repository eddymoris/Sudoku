package sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 * Autor: Eddy Moris Matos
 */
public class PrimerCuadro {
    ArrayList<Integer> orden;
    ArrayList<Integer> comodin;
    ArrayList<Integer> lista        = new ArrayList<Integer>() {{ add(0); add(1); add(2); }};
    ArrayList<Integer> provisional  = new ArrayList<>();
    ArrayList<Integer> provisional2 = new ArrayList<>();
    
    
    /**
     *
     * Genera el primer cuadro del sudoku.
     */
    public int[][] generarPrimerCuadro(int[][] matriz) {
        ArrayList<Integer> lista = new ArrayList<>();
        lista                    = listaNumeros(9);
        lista                    = reordenarLista(lista);
        int indice               = 0;

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = lista.get(indice);
                indice++;
            }
        }   
        
        return matriz;
    }
    /**********************************************************************************/
    
    
    /**
     *
     * Mezcla los números de la lista y crea una lista desordenada.
     */
    public ArrayList<Integer> reordenarLista(ArrayList<Integer> listaDesordenada) {
        Collections.shuffle(listaDesordenada); 
        
        return listaDesordenada;
    }
    /**********************************************************************************/
    
    
    /**
     *
     * Genera una lista desordenada de dimensión específica y de números sin repetir.
     */
    public ArrayList<Integer> listaNumeros(int medida) {
        ArrayList<Integer> lista = new ArrayList<>();
        
        for (int i = 0; i < medida; i++) {
            lista.add(i + 1);
        } 
        
        return lista;
    }
    /**********************************************************************************/
    
    
    /**
     *
     * Presenta por pantalla una matriz de enteros.
     */
    public void presentarMatriz(int[][] matriz) {
        // Imprime la matriz para visualizar el resultado
        for (int i = 0; i < matriz.length; i++) {
            if (i%3==0)
                System.out.println("=============  ===========  =============");
            else
                System.out.println("-----------------------------------------");
            for (int j = 0; j < matriz.length; j++) {
                if (j%3==0) 
                    System.out.print("|");
                if (j == matriz.length - 1) 
                    System.out.print("| " + matriz[i][j] + " |");
                else 
                    System.out.print("| " + matriz[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("=============  ===========  =============\n");
    }
    /**********************************************************************************/
    
    
    /*
     **********************************************************************************
     * Determina la lista según la cual será desordenada una matriz.                  *
     **********************************************************************************
     */
    public void determinarOrden(int inicio, int fin) {
        if (inicio > fin) {
            orden   = new ArrayList<Integer>() {{ add(1); add(2); add(0); }};
            comodin = new ArrayList<Integer>() {{ add(2); add(0); add(1); }};
        } else { 
            orden   = new ArrayList<Integer>() {{ add(2); add(0); add(1); }};
            comodin = new ArrayList<Integer>() {{ add(1); add(2); add(0); }};
        }
    }
    /**********************************************************************************/
    
    
    /*
     **********************************************************************************
     * Establece un nuevo orden para las filas de una matriz de enteros.              *
     **********************************************************************************
     */
    public int[][] reordenarPrimerCuadro(int[][] matriz, int[][] nueva) {
        for (int i = 0; i < matriz.length; i++) {
            lista = reordenarLista(lista);
            for (int j = 0; j < matriz.length; j++) {
                nueva[i][lista.get(j)] = matriz[i][j];
            }
        }   
        return nueva;
    }
    /**********************************************************************************/
    
    
    /*
     **********************************************************************************
     * Determina tres números como comodines en una matriz de enteros.                *
     **********************************************************************************
     */
    public int[][] determinarComodinesH(int[][] matriz, int[][] nueva, int numero) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (j == 0) {
                    if (numero == 2) nueva[comodin.get(i)][j] = matriz[i][j];
                    if (numero == 3) nueva[orden.get(i)][j] = matriz[i][j];
                } else {
                    nueva[i][j] = matriz[i][j];
                }               
            }
        }        
        return nueva;
    }
    /**********************************************************************************/
    
    
    /*
     **********************************************************************************
     * Establece un nuevo orden para las filas de una matriz de enteros.              *
     **********************************************************************************
     */
    public int[][] reordenarMatrizH(int[][] matriz, int[][] nueva, int numero) {
        for (int i = 0; i < matriz.length; i++) {
            lista = reordenarLista(lista);
            for (int j = 0; j < matriz.length; j++) {
                if (numero == 2) nueva[comodin.get(i)][lista.get(j)] = matriz[i][j];
                if (numero == 3) nueva[orden.get(i)][lista.get(j)] = matriz[i][j];
            }
        }        
        return nueva;
    }
    /**********************************************************************************/
    
    
    /*
     **********************************************************************************
     * Determina tres números como comodines en una matriz de enteros.                *
     **********************************************************************************
     */
    public void determinarComodinesCuartoCuadro(int[][] matriz) {
        for (int j = 0; j < matriz.length; j++) {
            for (int i = 0; i < matriz.length; i++) {
                if (j == comodin.get(i)) provisional.add(matriz[j][i]);               
            }
        }
    }
    /**********************************************************************************/
    
    
    /*
     **********************************************************************************
     * Reordena los comodines con una nueva pareja para el cuarto cuadro.             *
     **********************************************************************************
     */
    public int[][] ordenarComodinesCuartoCuadro(int[][] matriz, int[][] nueva) {
        determinarComodinesCuartoCuadro(matriz);
        
        for (int j = 0; j < matriz.length; j++) {
            for (int i = 0; i < matriz.length; i++) {
                if (j == comodin.get(i)) {
                    nueva[j][i] = provisional.get(i);
                } else {
                    nueva[j][i] = matriz[j][i];
                }               
            }
        }
        return nueva;
    }
    /**********************************************************************************/
    
    
    /*
     **********************************************************************************
     * Reordena los comodines con una nueva pareja para el séptimo cuadro.            *
     **********************************************************************************
     */
    public int[][] ordenarComodinesSeptimoCuadro(int[][] matriz, int[][] nueva) {
        for (int j = 0; j < matriz.length; j++) {
            for (int i = 0; i < matriz.length; i++) {
                if (j == comodin.get(i)) {
                    nueva[j][i] = provisional.get(orden.get(i));
                } else {
                    nueva[j][i] = matriz[j][i];
                }               
            }
        }
        return nueva;
    }
    /**********************************************************************************/
    
    
    /*
     **********************************************************************************
     * Establece un nuevo orden para las filas de una matriz de enteros.              *
     **********************************************************************************
     */
    public int[][] reordenarMatrizV(int[][] matriz, int[][] nueva, int numero) {
        for (int j = 0; j < matriz.length; j++) {
            for (int i = 0; i < matriz.length; i++) {
                if (numero == 4) nueva[j][comodin.get(i)] = matriz[j][i];
                if (numero == 7) nueva[j][orden.get(i)] = matriz[j][i];
            }
        }       
        return nueva;
    }
    /**********************************************************************************/
    
    
    /*
     **********************************************************************************
     * Establece un nuevo orden para las columnas de una matriz de enteros.              *
     **********************************************************************************
     */
    public int[][] reordenarMatrizVF(int[][] matriz, int[][] nueva) {
        for (int j = 0; j < matriz.length; j++) {
            lista = reordenarLista(lista);
            for (int i = 0; i < matriz.length; i++) {
                nueva[lista.get(i)][j] = matriz[i][j];
            }
        }       
        return nueva;
    }
    /**********************************************************************************/
        
    
    /*
     **********************************************************************************
     *Determina los comodines y su posición en una matriz de enteros.                 *
     **********************************************************************************
     */
    public int determinarComodinesQuintoCuadro(int[][] matriz, int[][] nueva, int fila) {
        int count = 0;
        int col   = 0;
        int num   = 0;
        
        for (int rep = 0; rep < matriz.length; rep++) {
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz.length; j++) {
                    if (matriz[fila][i] == nueva[j][col]) {
                        count++;
                    }

                    if (count == 2) {
                        num = matriz[fila][i];
                        provisional2.add(i);
                        return num;
                    } else {
                        if (j == 2 && col == 2) {
                            num = matriz[fila][fila];
                            provisional2.add(fila);
                            return num;
                        }
                    }
                }
            }
            col++;
        }
        
        return num;   
    }
    /**********************************************************************************/
    
    
    /*
     **********************************************************************************
     * Reordena los comodines con una nueva pareja para el quinto cuadro.             *
     **********************************************************************************
     */
    public int[][] ordenarComodinesQuintoSextoCuadro(int[][] matriz, int[][] nueva, int cuadro) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (j == provisional2.get(i)) {
                    if (cuadro == 5) nueva[i][j] = provisional.get(comodin.get(i));
                    if (cuadro == 6) nueva[i][j] = provisional.get(orden.get(i));
                } else {
                    nueva[i][j] = matriz[i][j];
                }               
            }
        }
        return nueva;
    }
    /**********************************************************************************/
    
    
    /*
     **********************************************************************************
     * Reordena las filas que conforman el quinto cuadro.                             *
     **********************************************************************************
     */
    public int[][] reordenarFilasQuintoSextoCuadro(int[][] matriz, int[][] nueva, int cuadro) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (cuadro == 5) nueva[orden.get(i)][j] = matriz[i][j];
                if (cuadro == 6) nueva[comodin.get(i)][j] = matriz[i][j];            
            }
        }
        return nueva;
    }
    /**********************************************************************************/
    
    
    /*
     **********************************************************************************
     * Elimina las coincidencias 3x3 en columnas de una matriz de enteros.            *
     **********************************************************************************
     */
    public int[][] eliminarCoincidenciasColumnas(int[][] comparado, int[][] comparable, int fila) {
        int count = 0;
        int num   = 0;
        int val   = 0;
        int fil   = -1;
        
        for (int rep = 0; rep < comparado.length; rep++) {
            for (int i = 0; i < comparado.length; i++) {
                for (int j = 0; j < comparado.length; j++) {                    
                    if (comparado[fila][count] == comparable[j][rep]) {
                        num++;
                        if (count == rep) {
                            val = comparable[j][rep];
                            fil = j;
                        }
                    }
                }
                count++;                
            }
            
            if (num == 3) {
                if (rep < 2) {
                    comparable[fil][rep]   = comparable[fil][rep+1];
                    comparable[fil][rep+1] = val;
                } else {
                    comparable[fil][rep]   = comparable[fil][rep-1];
                    comparable[fil][rep-1] = val;   
                }
            }
            count = 0;
            num   = 0;
            val   = 0;
            fil   = 0;
        }
        return comparable;
    }
    /**********************************************************************************/
    
    
    /*
     **********************************************************************************
     * Elimina las coincidencias 3x3 en filas de una matriz de enteros.            *
     **********************************************************************************
     */
    public int[][] eliminarCoincidenciasFilas(int[][] comparado, int[][] comparable, int fila) {
        int count = 0;
        int num   = 0;
        int val   = 0;
        int fil   = -1;
        
        for (int rep = 0; rep < comparado.length; rep++) {
            for (int i = 0; i < comparado.length; i++) {
                for (int j = 0; j < comparado.length; j++) {                    
                    if (comparable[fila][count] == comparado[j][rep]) {
                        num++;
                        if (count == rep) {
                            val = comparable[fila][count];
                            fil = count;
                        }
                    }
                }
                count++;                
            }
            if (num == 3) {
                if (fila < 2) {
                    comparable[fila][fil]   = comparable[fila+1][fil];
                    comparable[fila+1][fil] = val;
                } else {
                    comparable[fila][fil]   = comparable[fila-1][fil];
                    comparable[fila-1][fil] = val;   
                }
            }
            count = 0;
            num   = 0;
            val   = 0;
            fil   = 0;
        }
        return comparable;
    }
    /**********************************************************************************/
    
    
    /*
     **********************************************************************************
     * Busca la posición en que no se repita un número de una fila en una columna.    *
     **********************************************************************************
     */
    public int[][] determinarNumerosQuintoSextoCuadro(int[][] matriz, int[][] comparado, int[][] comparable, int fila) {
        ArrayList<Integer> nuevoOrden   = new ArrayList<Integer>() {{ add(0); add(1); add(2); }};
        int count = 0;
        int col   = 0;
        int num   = 0;
        int pos   = 0;
        int posic = -1;
        
        for (int rep = 0; rep < matriz.length; rep++) {
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz.length; j++) {
                    if (matriz[fila][i] == comparado[j][col]) {
                        count++;
                    } else {
                        pos++;
                        if (pos == 3) {
                            posic = i;
                            num = matriz[fila][i];
                        }
                    }
                }
                pos = 0;
            }
            
            if (count == 2 && num > 0) {
                comparable[fila][col] = num;
                comparable[fila][posic] = matriz[fila][col];
                nuevoOrden.remove(new Integer(fila));
            }
            
            col++;
            count = 0;
            num = 0;
            posic = -1;
        }
        comparable = numerosFaltantesQuintoSextoCuadro(matriz, comparable);
        if (nuevoOrden.contains(fila)) {
            comparable = reordenarNumerosRepetidosQuintoSextoCuadro(comparado, comparable, fila);
        }
        return comparable;
    }
    /**********************************************************************************/
    
    
    /*
     **********************************************************************************
     * Completa números faltantes en una matriz de enteros.                           *
     **********************************************************************************
     */
    public int[][] numerosFaltantesQuintoSextoCuadro(int[][] matriz, int[][] nueva) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (nueva[i][j] == 0) { nueva[i][j] = matriz[i][j]; }
            }
        }
        return nueva;
    }
    /**********************************************************************************/
    
    
    /*
     **********************************************************************************
     * Reordena números para evitar coincidencias.                                    *
     **********************************************************************************
     */
    public int[][] reordenarNumerosRepetidosQuintoSextoCuadro(int[][] comparado, int[][] comparable, int fila) {
        int count = 0;
        int num   = 0;
        for (int i = 0; i < comparado.length; i++) {
            for (int j = 0; j < comparado.length; j++) {
                if (comparable[fila][count] == comparado[j][count]) {
                    if (count < 2) {
                        num = comparable[fila][count];
                        comparable[fila][count]   = comparable[fila][count+1];
                        comparable[fila][count+1] = num;
                    } else {
                        num = comparable[fila][count];
                        comparable[fila][count]   = comparable[fila][count-1];
                        comparable[fila][count-1] = num;   
                    }
                }
            }
            count++;
        }
        return comparable;
    }
    /**********************************************************************************/
    
    
    /*
     **********************************************************************************
     * Determina números faltantes en una columna o fila.                             *
     **********************************************************************************
     */
    public ArrayList<Integer> numerosFaltantesColFil(int[][] comparado, int[][] comparable, int fila) {
        ArrayList<Integer> numeros = new ArrayList<>();
        numeros = listaNumeros(9);
        for (int i = 0; i < comparado.length-1; i++) {
            for (int j = 0; j < comparado.length; j++) {
                if (i == 0) {
                    if (numeros.contains(comparado[j][fila])) {
                        numeros.remove(new Integer(comparado[j][fila]));
                    }
                } else {
                    if (numeros.contains(comparable[j][fila])) {
                        numeros.remove(new Integer(comparable[j][fila]));
                    }
                }
            }
        }
        return numeros;
    }
    /**********************************************************************************/
    
    
    /*
     **********************************************************************************
     * Rellena una matriz de enteros con los números faltantes de las dos precedentes.*
     **********************************************************************************
     */
    public int[][] rellenarCuadro(int[][] resultante, ArrayList<Integer> numeros, int fila) {
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < resultante.length; j++) {
                resultante[j][fila] = numeros.get(j);
            }
        }
        
        return resultante;
    }
    /**********************************************************************************/
    
    
    /*
     **********************************************************************************
     * Determina el número y su posición exacta en una matriz de enteros.             *
     **********************************************************************************
     */
    public int[][] numerosExactos(int[][] comparado, int[][] comparable, int[][] nueva, int fila) {
        int count = 0;
        int col   = 0;
        int num   = 0;
        int pos   = 0;
        
        for (int rep = 0; rep < comparado.length; rep++) {
            for (int i = 0; i < comparado.length; i++) {
                for (int j = 0; j < comparado.length; j++) {
                    if (comparable[fila][j] == comparado[i][col]) {
                        count++;
                    } else {
                        pos++;
                        if (pos == 3) {
                            num = comparado[i][col];
                        }
                    }
                }
                pos = 0;
            }
            if (count == 2 && num > 0) {
                nueva[fila][col] = num;
            }
            
            col++;
            count = 0;
            num   = 0;
        }
        
        return nueva;
    }
    /**********************************************************************************/
    
    
    /**
     *
     * Identifica si hay coincidencia 3x3 en columnas de dos matrices diferentes.
     */
    public int identificarColumnasIguales(int[][] matrizCol1, int[][] matrizCol2, int fila) {
        int igual   = 0;
        int columna = 0;
        int num     = -1;
        
        for (int rep = 0; rep < matrizCol1.length; rep++) {
            for (int i = 0; i < matrizCol1.length; i++) {
                for (int j = 0; j < matrizCol2.length; j++) {
                    if (matrizCol1[j][fila] == matrizCol2[i][columna]) {
                        igual++;
                    }
                    if (igual == 3) {
                        return fila;
                    }
                }
            }
            columna++;
            igual = 0;
        }
        
        return num;
    }
    /**********************************************************************************/
    
    
    /**
     *
     * Comprueba que un número dado no exista en una fila específica de otra matriz.
     * Retorna falso si lo encuentra y verdadero si no es así.
     */
    public boolean comprobarNumeroEnFila(int[][] matriz, int numero, int fila) {
        for (int j = 0; j < matriz.length; j++) {
            if (matriz[fila][j] == numero) {
                return false;
            }
        }
        
        return true;
    }
    /**********************************************************************************/
    
    
    /**
     *
     * Comprueba todas las filas de una matriz.
     * Retorna el número y la fila donde debe ser ubicado.
     */
    public ArrayList<Integer> obtenerNumeroYFila(int[][] matriz, int[][] matriz1, int[][] matriz2) {
        ArrayList<Integer> filaColumna = new ArrayList<>();
        ArrayList<Integer> numeroFila  = new ArrayList<>();
        ArrayList<Integer> resultado   = new ArrayList<>();
        int num = -1;

        for (int i = 0; i < matriz1.length; i++) {
            ArrayList<Integer> lista   = new ArrayList<Integer>() {{ add(0); add(1); add(2); }};
            numeroFila = encontrarNumeroFila(matriz1, i);
            
            if (!numeroFila.isEmpty()) {
                num = numeroFila.getFirst();
                
                filaColumna = encontrarFilaColumna(matriz, num);
                if (!filaColumna.isEmpty()) {
                    lista.remove(new Integer(filaColumna.getFirst()));
                    lista.remove(new Integer(i));
                    
                    if (comprobarNumeroEnFila(matriz2, num, lista.getFirst())) {
                        resultado.add(num);
                        resultado.add(lista.getFirst());
                    }
                }
            }
        }
        
        return resultado;
    }
    /**********************************************************************************/
    
    
    /**
     *
     * Comprueba todas las filas hasta encontrar un valor en la matriz.
     * Retorno una valor boolean para determinar la acción siguiente.
     */
    public boolean eliminarDobleCoincidenciaFilasSin3x3(int[][] matriz, int[][] matriz1, int[][] matriz2) {
        ArrayList<Integer> numeroFila = new ArrayList<>();
        ArrayList<Integer> filaColumna = new ArrayList<>();
        
        int num1 = 0;
        int num2 = 0;
        int val  = 0;
        
        for (int i = 0; i < matriz2.length; i++) {
            numeroFila = encontrarNumeroFila(matriz1, i);
            if (!numeroFila.isEmpty()) {
                num1 = numeroFila.getFirst();
            }
            
            numeroFila = encontrarNumeroFila(matriz2, i);
            if (!numeroFila.isEmpty()) {
                num2 = numeroFila.getFirst();
            }
            
            if (num1 == num2 && (num1 != 0 || num2 != 0)) {
                filaColumna = encontrarFilaColumna(matriz, num1);
                if (!filaColumna.isEmpty()) {
                    lista.remove(new Integer(filaColumna.getFirst()));
                    lista.remove(new Integer(i));

                    val = matriz[lista.getFirst()][filaColumna.getLast()];
                    matriz[lista.getFirst()][filaColumna.getLast()] = matriz[i][filaColumna.getLast()];
                    matriz[i][filaColumna.getLast()] = val;
                }
                return true;
            }
        }
        
        return false;
    }
    /**********************************************************************************/
    
    
    /**
     *
     * Comprueba toda la fila hasta encontrar un valor en la matriz.
     * Retorna el número y la fila donde se encontró.
     */
    public ArrayList<Integer> encontrarNumeroFila(int[][] matriz, int fila) {
        ArrayList<Integer> numeroFila = new ArrayList<>();
        
        for (int j = 0; j < matriz.length; j++) {
            if (matriz[fila][j] != 0) {
                numeroFila.add(matriz[fila][j]);
                numeroFila.add(fila);
                return numeroFila;
            }
        }
        
        return numeroFila;
    }
    /**********************************************************************************/
    
    
    /**
     *
     * Comprueba posición por posición hasta encontrar un valor determinado, en la matriz.
     * Retorna un ArrayList con la fila y la columna donde está el valor encontrado.
     */
    public ArrayList<Integer> encontrarFilaColumna(int[][] matriz, int numero) {
        ArrayList<Integer> filaColumna = new ArrayList<>();
        
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j] == numero) {
                    filaColumna.add(i);
                    filaColumna.add(j);
                }
            }
        }
        
        return filaColumna;
    }
    /**********************************************************************************/
    
    
    /**
     *
     * Elimina la doble coincidencia en una fila que afecta columnas 3x3.
     * Permuta dos números en una misma columna para romper la doble coincidencia.
     */
    public void eliminarDobleCoincidenciaFilasCon3x3(int[][] matrizCol, int[][] matrizFil, int fila) {
        ArrayList<Integer> numeros = encontrarRepetidos1Columna3Filas(matrizCol, matrizFil, fila);
        
        if (numeros.size() > 3) {
            int fil    = numeros.get(numeros.size()-2);
            int col    = numeros.getLast();
            int cambio = numeros.indexOf(Collections.min(numeros));
            int num    = matrizFil[fil][col];

            matrizFil[fil][col] = matrizFil[cambio][col];
            matrizFil[numeros.indexOf(Collections.min(numeros))][col] = num;
        }
    }
    /**********************************************************************************/
    
    
    /**
     *
     * Recorre una columna específica y la compara con las tres filas de otra matriz.
     * Retorna un ArrayList con el número de repeticiones por cada fila.                
     * Al final del mismo, se añade la posición para realizar la permuta de números.
     */
    public ArrayList<Integer> encontrarRepetidos1Columna3Filas(int[][] matrizCol, int[][] matrizFil, int fila) {
        ArrayList<Integer> numeros = new ArrayList<>();
        int igual   = 0;
        int columna = 0;
        int fil     = -1;
        int col     = -1;
        
        for (int rep = 0; rep < matrizCol.length; rep++) {
            for (int i = 0; i < matrizCol.length; i++) {
                for (int j = 0; j < matrizFil.length; j++) {   
                    if (matrizCol[columna][fila] == matrizFil[rep][j]) {
                        igual++;
                        if (igual == 2) {
                            fil = rep;
                            col = j;
                        }
                    } 
                }
                columna++;                
            }
            numeros.add(igual);
            igual   = 0;
            columna = 0;
        }
        // Añade al array, fila y columna sobre la que se realizará la permuta.
        if (fil >= 0 && col >=0) {
            numeros.add(fil);
            numeros.add(col);
        }
        
        return numeros;
    }
    /**********************************************************************************/
    
    
    /**
     *
     * Genera una matriz de enteros para almacenar los números en el orden definitivo.
     */
    public int[][] generarSudoku(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = 0;
            }
        }
        
        return matriz;
    }
    /**********************************************************************************/
    
    
    /**
     *
     * Asigna valores a una matriz de enteros, cuadro por cuadro.
     */
    public int[][] asignarValores(int[][] matriz, int[][] cuadro, int fila, int columna) {
        for (int i = 0; i < cuadro.length; i++) {
            for (int j = 0; j < cuadro.length; j++) {
                matriz[i + fila][j + columna] = cuadro[i][j];
            }
        }  
        
        return matriz;
    }
    /**********************************************************************************/
}
// En el octavo y noveno cuadros, puede ser que no haya ningún número después de ejecutar la función numerosExactos().

// Puede haber 3x3x3 en el octavo y noveno cuadro.