package org.damx;

import org.damx.models.FilmModel;
import org.damx.models.GenreModel;
import org.damx.service.XMLService;
import org.damx.xml.XMLManager;

import javax.swing.*;
import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "INICIANDO PROGRAMA" );

        System.out.println( "LLAMADAS A LOS MÉTODOS" );

//        try {
//            XMLManager.findFilmByIdOrName(null, "Joker");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

//        try {
//            boolean okCreate = XMLManager.createFilm(new FilmModel("1","Joker", LocalDate.now(),
//                    true, new GenreModel("3","")));
//            if( okCreate ){
//                JOptionPane.showMessageDialog(null,
//                        "Pelicula creada con éxito");
//            }else{
//                JOptionPane.showMessageDialog(null,
//                        "La pelicula ya existe");
//            }
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }

//        boolean okUpdate = false;
//        try {
//            okUpdate = XMLManager
//                    .updatefilm(new FilmModel("1","Joker 2",
//                            LocalDate.now(),false,new GenreModel("3","")));
//            if(okUpdate){
//                JOptionPane.showMessageDialog(null, "PELI ACTUALIZADA");
//            }else{
//                JOptionPane.showMessageDialog(null, "EL NOMBRE DE LA PELI YA EXISTE");
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

//        try {
//            boolean okRemove = XMLManager.removeFilmByIdOrName(null,"Una peli");
//            if( okRemove){
//                JOptionPane.showMessageDialog(null, "Pelicula eliminada!");
//            }else{
//                JOptionPane.showMessageDialog(null, "Error al eliminar la peli");
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null,e.getMessage(),
//                    "Error",JOptionPane.ERROR_MESSAGE);
//        }

//        ArrayList<GenreModel> genres = new ArrayList<>();
//        genres.add( new GenreModel("1", "Accion") );
//        genres.add( new GenreModel("2", "Comedia") );
//        genres.add( new GenreModel("3", "Drama") );
//        genres.add( new GenreModel("4", "Ciencia Ficcion") );
//        genres.add( new GenreModel("5", "Terror") );
//        XMLManager.createGenres(genres);


//        try {
//            GenreModel genre = XMLManager.getGenreById("50");
//
//            if(genre != null) {
//                JOptionPane.showMessageDialog(null,genre.getName());
//            }else{
//                JOptionPane.showMessageDialog(null,"El genero no existe");
//            }
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

    }
}
