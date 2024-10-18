package org.damx;

import org.damx.models.FilmModel;
import org.damx.models.GenreModel;
import org.damx.service.XMLService;
import org.damx.xml.XMLManager;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "INICIANDO PROGRAMA" );

        try {
            if(XMLManager.deleteFilm("10")){
                JOptionPane.showMessageDialog(null,"Pelicula eliminada con éxito");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar");
        }


//        try {
//            if(XMLManager.updateFilm(new FilmModel("10","El Terminator 2",LocalDate.now(),false, new GenreModel("4", "")))){
//                JOptionPane.showMessageDialog(null,"Pelicula actualizada con éxito");
//            }else{
//                JOptionPane.showMessageDialog(null,"Error al actualizar");
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            JOptionPane.showMessageDialog(null,e.getMessage());
//        }


//        try {
//            if(XMLManager.createFilm(new FilmModel("2","Terminator2",LocalDate.now(),false, new GenreModel("2", "Ciencia Ficcion")))){
//                JOptionPane.showMessageDialog(null,"Pelicula creada con éxito");
//            }else{
//                JOptionPane.showMessageDialog(null,"La pelicula ya existe");
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            JOptionPane.showMessageDialog(null,e.getMessage());
//        }

//        try {
//            ArrayList<FilmModel> filmList = XMLManager.getFilms();
//            JOptionPane.showMessageDialog(null, filmList);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }


//        // Crear un ArrayList de GenreModel
//        ArrayList<GenreModel> genreList = new ArrayList<>();
//
//        // Añadir algunos géneros al ArrayList
//        genreList.add(new GenreModel("1", "Action"));
//        genreList.add(new GenreModel("2", "Comedy"));
//        genreList.add(new GenreModel("3", "Drama"));
//        genreList.add(new GenreModel("4", "Science Fiction"));
//        genreList.add(new GenreModel("5", "Horror"));
//
//        if(XMLManager.createGenres(genreList)){
//            JOptionPane.showMessageDialog(null,"Generos creados con éxito");
//        }else{
//            JOptionPane.showMessageDialog(null,"Error al crear generos");
//        }

    }
}
