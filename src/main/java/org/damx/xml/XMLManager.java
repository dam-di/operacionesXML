package org.damx.xml;

import org.damx.models.FilmModel;
import org.damx.models.GenreModel;
import org.damx.service.XMLService;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.damx.service.XMLService.ROOT_NODE;

public class XMLManager {

    public static boolean updatefilm(FilmModel film) throws Exception {
        Document document = XMLService.loadOrCreateXML();
        if(document == null) {
            throw new Exception("5. Error al cargar xml");
        }

        FilmModel filmToUpdate;
        try {
            filmToUpdate = findFilmByIdOrName(null,film.getName());

        } catch (Exception e) {
            throw new Exception("3. Error al obtener la pelicula");
        }

        // Comprobar si el nombre de la pelicula ya existe
        if (filmToUpdate != null) {
            // Si el nombre de la pelicula ya existe retornamos false
            return false;
        }

        NodeList nodeList = document.getElementsByTagName("film");
        if(nodeList.getLength() > 0){
            for(int i = 0; i < nodeList.getLength(); i++){
                Element filmElement = (Element) nodeList.item(i);
                if(filmElement.getAttribute("id").equals(film.getId())){
                    filmElement.setAttribute("name", film.getName());
                    filmElement.setAttribute("date", String.valueOf(film.getDate()));
                    filmElement.setAttribute("adultMovie", String.valueOf(film.isAdultMovie()));
                    filmElement.setAttribute("genre_id", film.getGenre().getId());
                    boolean okSave = XMLService.saveXML(document);
                    if(okSave){
                        return true;
                    }else{
                        throw new Exception("6. Error al guardar xml");
                    }
                }
            }
        }
        throw new Exception("6. Error al guardar xml");
    }

    public static FilmModel findFilmByIdOrName(String id, String name) throws Exception {
        Document document = XMLService.loadOrCreateXML();
        if(document != null) {
            try{
                NodeList nodeList = document.getElementsByTagName("film");
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element filmElement = (Element) nodeList.item(i);
                    if(filmElement.getAttribute("id").equals(id) ||
                            filmElement.getAttribute("name").equals(name)) {
                        GenreModel genreModel = getGenreById(filmElement.getAttribute("genre_id"));

                        return new FilmModel(filmElement.getAttribute("id"),
                                filmElement.getAttribute("name"),
                                LocalDate.parse(filmElement.getAttribute("date")),
                                Boolean.parseBoolean(filmElement.getAttribute("adultMovie")),
                                genreModel);
                    }
                }
            }catch (Exception e) {
                System.out.println("Error al obtener la pelicula");
                throw new Exception("1, Error al obtener la pelicula");
            }
        }else{
            throw new Exception("2, Error al obtener el documento xml");
        }
        return null;
    }


    public static boolean removeFilmByIdOrName(String id, String name) throws Exception {
        Document document = XMLService.loadOrCreateXML();
        if(document != null) {
            try{
                NodeList nodeList = document.getElementsByTagName("film");

                if(nodeList.getLength() == 0){
                    throw new Exception("La lista films no tiene elementos");
                }

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element filmElement = (Element) nodeList.item(i);
                    if(filmElement.getAttribute("id").equals(id) ||
                            filmElement.getAttribute("name").equals(name)) {
                        filmElement.getParentNode().removeChild(filmElement);
                        return XMLService.saveXML(document);
                    }
                }
            }catch (Exception e) {
                System.out.println("Error al obtener la pelicula");
                throw new Exception(e.getMessage());
            }
        }else{
            throw new Exception("2, Error al obtener el documento xml");
        }
        throw new Exception("La id o el nombre no existe");

    }


    public static boolean createFilm(FilmModel film) throws Exception {
        Document document = XMLService.loadOrCreateXML();
        if(document != null) {

            // Comprobar genero
            try {
                GenreModel genre = getGenreById(film.getGenre().getId());
                if(genre.getId().equals("0")) {
                    System.out.println("El genero indicao no existe");
                    throw new Exception("1. El genero indicao no existe");
                }
            }catch (Exception e) {
                throw new Exception("2. Error al obtener el genero");
            }

            // Comprobar id y nombre de pelicula
            try{
                if(findFilmByIdOrName(film.getId(), film.getName()) != null) {
                    System.out.println("El film ya existe");
                    return false; // Esto indica que la pelicula ya existe (la id o el nombre)
                }
            } catch (Exception e) {
                throw new Exception("3. Error al obtener la pelicula");
            }


            // Crear pelicula
            try{
                Element filmElement = document.createElement("film");
                filmElement.setAttribute("id", film.getId());
                filmElement.setAttribute("name", film.getName());
                filmElement.setAttribute("date", String.valueOf(film.getDate()));
                filmElement.setAttribute("adultMovie", String.valueOf(film.isAdultMovie()));
                filmElement.setAttribute("genre_id", film.getGenre().getId());

                NodeList nodeList = document.getElementsByTagName("films");
                if(nodeList.getLength() > 0) {
                    Element filmsElement = (Element) nodeList.item(0);
                    filmsElement.appendChild(filmElement);
                    boolean okSave = XMLService.saveXML(document);
                    if(okSave) {
                        return true;
                    }else{
                        throw new Exception("6. Error al guardar xml");
                    }
                }

            } catch (Exception e) {
                System.out.println("Error al crear la pelicula");
                throw new Exception("4. Error al crear la pelicula");
            }
        }
        throw new Exception("5. Error al cargar xml");
    }

    public static boolean createGenres(ArrayList<GenreModel> genreList) {
        Document document = XMLService.loadOrCreateXML();
        if(document != null) {
            for(GenreModel genre : genreList) {
                try {
                    Element genreElement = document.createElement("genre");
                    genreElement.setAttribute("id", genre.getId());
                    genreElement.setAttribute("name", genre.getName());

                    NodeList nodeList = document.getElementsByTagName("genres");
                    if (nodeList.getLength() > 0) {
                        Element genresElement = (Element) nodeList.item(0);
                        genresElement.appendChild(genreElement);
                    }
                } catch (Exception e) {
                    System.out.println("Error al crear la pelicula");
                }
            }
            return XMLService.saveXML(document);
        }
        return false;
    }

    public static GenreModel getGenreById(String id) throws Exception {
        Document document = XMLService.loadOrCreateXML();
        if(document != null) {
            try{
                NodeList nodeList = document.getElementsByTagName("genre");
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element genreElement = (Element) nodeList.item(i);
                    if(genreElement.getAttribute("id").equals(id)) {
                        return new GenreModel(id, genreElement.getAttribute("name"));
                    }
                }
            }catch (Exception e) {
                System.out.println("Error al obtener el genero");
                throw new Exception("1, Error al obtener el genero");
            }
        }else{
            throw new Exception("2, Error al obtener el documento xml");
        }
        return new GenreModel("0","No tiene");
    }
}
