package sudoku;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Autor: Eddy Moris Matos
 * Fecha: 20/05/2026
 */
public class Sudoku {
    public static void main(String[] args) {
        int[][] tablero                     = new int[9][9];
        int[][] cuadrante                   = new int[9][9];
        int[][] matriz                      = new int[3][3];
        int[][] nueva                       = new int[3][3];
        int[][] provisional                 = new int[3][3];
        int[][] octProvisional              = new int[3][3];
        int[][] novProvisional              = new int[3][3];
        
        int[][] primerCuadro                = new int[3][3];
        int[][] segundoCuadro               = new int[3][3];
        int[][] tercerCuadro                = new int[3][3];
        int[][] cuartoCuadro                = new int[3][3];
        int[][] quintoCuadro                = new int[3][3];
        int[][] sextoCuadro                 = new int[3][3];
        int[][] septimoCuadro               = new int[3][3];
        int[][] octavoCuadro                = new int[3][3];
        int[][] novenoCuadro                = new int[3][3];
        
        ArrayList<Integer> lista            = new ArrayList<>();
        ArrayList<Integer> listaDesordenada = new ArrayList<>();
        
        PrimerCuadro sudoku = new PrimerCuadro();
        
        /**********************************************************************
         * Primer cuadro.
         **********************************************************************/
//        lista            = sudoku.listaNumeros(9);
//        listaDesordenada = sudoku.reordenarLista(lista);
        matriz           = sudoku.generarPrimerCuadro(matriz);
        primerCuadro     = sudoku.reordenarPrimerCuadro(matriz, primerCuadro);
        /**********************************************************************/
        
        sudoku.determinarOrden(matriz[0][0], matriz[2][2]);
        
        /**********************************************************************
         * Segundo cuadro.
         **********************************************************************/
        nueva         = sudoku.determinarComodinesH(matriz, nueva, 2);
        segundoCuadro = sudoku.reordenarMatrizH(nueva, segundoCuadro, 2);
        /**********************************************************************/
        
        /**********************************************************************
         * Tercer cuadro.
         **********************************************************************/
        nueva        = sudoku.determinarComodinesH(matriz, nueva, 3);
        tercerCuadro = sudoku.reordenarMatrizH(nueva, tercerCuadro, 3);
        /**********************************************************************/
        
        sudoku.determinarOrden(primerCuadro[0][0], primerCuadro[2][2]);
        
        /**********************************************************************
         * Cuarto cuadro.
         **********************************************************************/
        nueva        = sudoku.ordenarComodinesCuartoCuadro(primerCuadro, nueva);
        provisional  = sudoku.reordenarMatrizV(nueva, provisional, 4);
        cuartoCuadro = sudoku.reordenarMatrizVF(provisional, cuartoCuadro);
        /**********************************************************************/
        
        /**********************************************************************
         * Séptimo cuadro.
         **********************************************************************/
        nueva         = sudoku.ordenarComodinesSeptimoCuadro(primerCuadro, nueva);
        provisional   = sudoku.reordenarMatrizV(nueva, provisional, 7);
        septimoCuadro = sudoku.reordenarMatrizVF(provisional, septimoCuadro);
        /**********************************************************************/
        
        /**********************************************************************
         * Quinto cuadro.
         **********************************************************************/
        sudoku.provisional.clear();
        sudoku.provisional.add(sudoku.determinarComodinesQuintoCuadro(cuartoCuadro, segundoCuadro, 0));
        sudoku.provisional.add(sudoku.determinarComodinesQuintoCuadro(cuartoCuadro, segundoCuadro, 1));
        sudoku.provisional.add(sudoku.determinarComodinesQuintoCuadro(cuartoCuadro, segundoCuadro, 2));
        
        nueva        = sudoku.ordenarComodinesQuintoSextoCuadro(cuartoCuadro, nueva, 5);
        provisional  = sudoku.reordenarFilasQuintoSextoCuadro(nueva, provisional, 5);
        
        quintoCuadro = sudoku.determinarNumerosQuintoSextoCuadro(provisional, segundoCuadro, quintoCuadro, 0);
        quintoCuadro = sudoku.determinarNumerosQuintoSextoCuadro(provisional, segundoCuadro, quintoCuadro, 1);
        quintoCuadro = sudoku.determinarNumerosQuintoSextoCuadro(provisional, segundoCuadro, quintoCuadro, 2);
        /**********************************************************************/
                
        /**********************************************************************
         * Sexto cuadro.
         **********************************************************************/
        nueva        = sudoku.ordenarComodinesQuintoSextoCuadro(cuartoCuadro, nueva, 6);
        provisional  = sudoku.reordenarFilasQuintoSextoCuadro(nueva, provisional, 6);
        
        tercerCuadro = sudoku.eliminarCoincidenciasColumnas(provisional, tercerCuadro, 0);
        tercerCuadro = sudoku.eliminarCoincidenciasColumnas(provisional, tercerCuadro, 1);
        tercerCuadro = sudoku.eliminarCoincidenciasColumnas(provisional, tercerCuadro, 2);
        
        sextoCuadro  = sudoku.determinarNumerosQuintoSextoCuadro(provisional, tercerCuadro, sextoCuadro, 0);
        sextoCuadro  = sudoku.determinarNumerosQuintoSextoCuadro(provisional, tercerCuadro, sextoCuadro, 1);
        sextoCuadro  = sudoku.determinarNumerosQuintoSextoCuadro(provisional, tercerCuadro, sextoCuadro, 2);
        /**********************************************************************/
        
        /**********************************************************************
         * Octavo cuadro.
         **********************************************************************/
        lista          = sudoku.numerosFaltantesColFil(segundoCuadro, quintoCuadro, 0);
        octProvisional = sudoku.rellenarCuadro(octProvisional, lista, 0);
        lista          = sudoku.numerosFaltantesColFil(segundoCuadro, quintoCuadro, 1);
        octProvisional = sudoku.rellenarCuadro(octProvisional, lista, 1);
        lista          = sudoku.numerosFaltantesColFil(segundoCuadro, quintoCuadro, 2);
        octProvisional = sudoku.rellenarCuadro(octProvisional, lista, 2);
        
        septimoCuadro  = sudoku.eliminarCoincidenciasFilas(octProvisional, septimoCuadro, 0);
        septimoCuadro  = sudoku.eliminarCoincidenciasFilas(octProvisional, septimoCuadro, 1);
        septimoCuadro  = sudoku.eliminarCoincidenciasFilas(octProvisional, septimoCuadro, 2);
        /**********************************************************************/
        
        /**********************************************************************
         * Noveno cuadro.
         **********************************************************************/
        lista          = sudoku.numerosFaltantesColFil(tercerCuadro, sextoCuadro, 0);
        novProvisional = sudoku.rellenarCuadro(novProvisional, lista, 0);
        lista          = sudoku.numerosFaltantesColFil(tercerCuadro, sextoCuadro, 1);
        novProvisional = sudoku.rellenarCuadro(novProvisional, lista, 1);
        lista          = sudoku.numerosFaltantesColFil(tercerCuadro, sextoCuadro, 2);
        novProvisional = sudoku.rellenarCuadro(novProvisional, lista, 2);
        
        septimoCuadro  = sudoku.eliminarCoincidenciasFilas(novProvisional, septimoCuadro, 0);
        septimoCuadro  = sudoku.eliminarCoincidenciasFilas(novProvisional, septimoCuadro, 1);
        septimoCuadro  = sudoku.eliminarCoincidenciasFilas(novProvisional, septimoCuadro, 2);
        /**********************************************************************/
        
//        sudoku.presentarMatriz(octProvisional);
//        sudoku.presentarMatriz(novProvisional);
//        sudoku.presentarMatriz(septimoCuadro);
        
        int col = sudoku.identificarColumnasIguales(octProvisional, novProvisional, 0);
        if (col < 0) {
            col = sudoku.identificarColumnasIguales(octProvisional, novProvisional, 1);
            if (col < 0) {
                col = sudoku.identificarColumnasIguales(octProvisional, novProvisional, 2);
            }
        }
        
        if (col >= 0) {
            sudoku.eliminarDobleCoincidenciaFilasCon3x3(octProvisional, septimoCuadro, col); 
        }
        
        octavoCuadro   = sudoku.numerosExactos(octProvisional, septimoCuadro, octavoCuadro, 0);
        octavoCuadro   = sudoku.numerosExactos(octProvisional, septimoCuadro, octavoCuadro, 1);
        octavoCuadro   = sudoku.numerosExactos(octProvisional, septimoCuadro, octavoCuadro, 2);
        
        novenoCuadro   = sudoku.numerosExactos(novProvisional, septimoCuadro, novenoCuadro, 0);
        novenoCuadro   = sudoku.numerosExactos(novProvisional, septimoCuadro, novenoCuadro, 1);
        novenoCuadro   = sudoku.numerosExactos(novProvisional, septimoCuadro, novenoCuadro, 2);
        
        if (sudoku.eliminarDobleCoincidenciaFilasSin3x3(septimoCuadro, octavoCuadro, novenoCuadro)) {
//            sudoku.presentarMatriz(octavoCuadro);
//            sudoku.presentarMatriz(novenoCuadro);
//            sudoku.presentarMatriz(septimoCuadro);
            octavoCuadro   = sudoku.generarSudoku(octavoCuadro);
            octavoCuadro   = sudoku.numerosExactos(octProvisional, septimoCuadro, octavoCuadro, 0);
            octavoCuadro   = sudoku.numerosExactos(octProvisional, septimoCuadro, octavoCuadro, 1);
            octavoCuadro   = sudoku.numerosExactos(octProvisional, septimoCuadro, octavoCuadro, 2);

            novenoCuadro   = sudoku.generarSudoku(novenoCuadro);
            novenoCuadro   = sudoku.numerosExactos(novProvisional, septimoCuadro, novenoCuadro, 0);
            novenoCuadro   = sudoku.numerosExactos(novProvisional, septimoCuadro, novenoCuadro, 1);
            novenoCuadro   = sudoku.numerosExactos(novProvisional, septimoCuadro, novenoCuadro, 2);
        }
        
        System.out.print("-- " + sudoku.obtenerNumeroYFila(septimoCuadro, octavoCuadro, novenoCuadro));
        System.out.println("\t-- " + sudoku.obtenerNumeroYFila(septimoCuadro, novenoCuadro, octavoCuadro));
        
        /**********************************************************************
         * Sudoku.
         **********************************************************************/
        tablero   = sudoku.generarSudoku(tablero);
        cuadrante = sudoku.asignarValores(tablero, primerCuadro,  0, 0);
        cuadrante = sudoku.asignarValores(tablero, segundoCuadro, 0, 3);
        cuadrante = sudoku.asignarValores(tablero, tercerCuadro,  0, 6);
        cuadrante = sudoku.asignarValores(tablero, cuartoCuadro,  3, 0);
        cuadrante = sudoku.asignarValores(tablero, septimoCuadro, 6, 0);
        cuadrante = sudoku.asignarValores(tablero, quintoCuadro,  3, 3);
        cuadrante = sudoku.asignarValores(tablero, sextoCuadro,   3, 6);
        
        cuadrante = sudoku.asignarValores(tablero, octavoCuadro,  6, 3);
        cuadrante = sudoku.asignarValores(tablero, novenoCuadro,  6, 6);
        
        sudoku.presentarMatriz(cuadrante);
        /**********************************************************************/
    }   
}
