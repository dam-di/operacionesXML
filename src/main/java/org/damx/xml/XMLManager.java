package org.damx.xml;

import org.damx.models.FilmModel;
import org.damx.models.GenreModel;
import org.damx.service.XMLService;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.damx.service.XMLService.ROOT_NODE;

public class XMLManager {

    public static boolean createFilm(FilmModel film) throws Exception {

        Document document = XMLService.loadOrCreateXML();
        if(document != null){

            try{
                if(getGenreById(film.getGenre().getId()) == null){
                    System.out.println("El genero indicado no existe");
                    throw new Exception("1, El genero indicado no existe");
                }
            }catch (Exception e){
                System.out.println("Error al obtener el genero");
                throw new Exception("2, Error al obtener el genero");
            }

            try{
                if(getFilmByIdOrName(film.getId(), film.getName()) != null){
                    System.out.println("La pelicula ya existe");
                    return false;
                }
            }catch (Exception e){
                System.out.println("Error al obtener la pelicula");
                throw new Exception("3, Error al obtener la pelicula");
            }


            try{
                Element filmElement = document.createElement("film");
                filmElement.setAttribute("id", film.getId());
                filmElement.setAttribute("name", film.getName());
                filmElement.setAttribute("date", String.valueOf(film.getDate()));
                filmElement.setAttribute("adultMovie", String.valueOf(film.isAdultMovie()));
                filmElement.setAttribute("genre_id", film.getGenre().getId());

                NodeList nodeList = document.getElementsByTagName("films");

                if(nodeList.getLength() > 0){
                    Element filmsElement = (Element) nodeList.item(0);
                    filmsElement.appendChild(filmElement);
                    boolean okSave = XMLService.saveXML(document);
                    if(okSave){
                        return true;
                    }else{
                        throw new Exception("6, Error al guardar la pelicula");
                    }
                }

            }catch (Exception e){
                System.out.println("Error al agregar nodo");
                throw new Exception("4, Error al agregar la pelicula");

            }
        }

        throw new Exception("5, Error al cargar o crear el documento XML");


    }


    public static boolean createGenres(ArrayList<GenreModel> genreList){

        Document document = XMLService.loadOrCreateXML();
        if(document != null){
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
                    System.out.println("Error al agregar nodo");
                }
            }
            return XMLService.saveXML(document);
        }

        return false;
    }

    public static GenreModel getGenreById(String id) throws Exception {
        Document document = XMLService.loadOrCreateXML();
        if(document != null){
            try {
                NodeList nodeList = document.getElementsByTagName("genre");
                for(int i = 0; i < nodeList.getLength(); i++){
                    Element element = (Element) nodeList.item(i);
                    if(element.getAttribute("id").equals(id)){
                        return new GenreModel(id, element.getAttribute("name"));
                    }
                }
            }catch (Exception e){
                throw new Exception("1, Error al obtener el genero");
            }
        }else{
            throw new Exception("2, Error al obtener documento xml");
        }

        return null;
    }

    public static FilmModel getFilmByIdOrName(String id, String name) throws Exception {
        Document document = XMLService.loadOrCreateXML();
        if(document != null){
            try {
                NodeList nodeList = document.getElementsByTagName("film");
                for(int i = 0; i < nodeList.getLength(); i++){
                    Element element = (Element) nodeList.item(i);
                    if(element.getAttribute("id").equals(id) || element.getAttribute("name").equals(name) ){
                        GenreModel genreModel = getGenreById(element.getAttribute("genre_id"));
                        if(genreModel != null){
                            return new FilmModel(element.getAttribute("id"), element.getAttribute("name"),
                                    LocalDate.parse(element.getAttribute("date")),
                                    Boolean.valueOf(element.getAttribute("adultMovie")),genreModel);
                        }
                    }
                }
            }catch (Exception e){
                throw new Exception("1, Error al obtener el genero");
            }
        }else{
            throw new Exception("2, Error al obtener documento xml");
        }
        return null;
    }

    public static ArrayList<FilmModel> getFilms() throws Exception {
        Document document = XMLService.loadOrCreateXML();
        ArrayList<FilmModel> filmList = new ArrayList<>();
        if(document != null){
            try {
                NodeList nodeList = document.getElementsByTagName("film");
                for(int i = 0; i < nodeList.getLength(); i++){
                    Element element = (Element) nodeList.item(i);
                    GenreModel genreModel = getGenreById(element.getAttribute("genre_id"));
                    if(genreModel != null){
                        filmList.add(new FilmModel(element.getAttribute("id"), element.getAttribute("name"),
                                LocalDate.parse(element.getAttribute("date")),
                                Boolean.valueOf(element.getAttribute("adultMovie")),genreModel));
                    }
                }

            }catch (Exception e){
                throw new Exception("1, Error al obtener el genero");
            }
        }else{
            throw new Exception("2, Error al obtener documento xml");
        }
        return filmList;
    }


    public static boolean updateFilm(FilmModel film) throws Exception {
        Document document = XMLService.loadOrCreateXML();
        if(document != null){
            try {
                NodeList nodeList = document.getElementsByTagName("film");
                for(int i = 0; i < nodeList.getLength(); i++){
                    Element element = (Element) nodeList.item(i);
                    if(element.getAttribute("id").equals(film.getId())){
                        GenreModel genreModel = getGenreById(film.getGenre().getId());
                        if(genreModel != null){
                            element.setAttribute("name",film.getName());
                            element.setAttribute("adultMovie", String.valueOf(film.isAdultMovie()));
                            element.setAttribute("date", String.valueOf(film.getDate()));
                            element.setAttribute("genre_id", film.getGenre().getId());
                            boolean okSave = XMLService.saveXML(document);
                            if(okSave){
                                break;
                            }else{
                                return false;
                            }
                        }
                    }
                }
            }catch (Exception e){
                throw new Exception("1, Error al obtener el la pelicula");
            }
        }else{
            throw new Exception("2, Error al obtener documento xml");
        }

        return true;
    }


    public static boolean deleteFilm(String id) throws Exception {
        Document document = XMLService.loadOrCreateXML();
        if(document != null){
            try {
                NodeList nodeList = document.getElementsByTagName("film");
                for(int i = 0; i < nodeList.getLength(); i++){
                    Element element = (Element) nodeList.item(i);
                    if(element.getAttribute("id").equals(id)){
                        element.getParentNode().removeChild(element);
                        return XMLService.saveXML(document);
                    }
                }
            }catch (Exception e){
                throw new Exception("1, Error al obtener el la pelicula");
            }
        }else{
            throw new Exception("2, Error al obtener documento xml");
        }

        return true;
    }


}
