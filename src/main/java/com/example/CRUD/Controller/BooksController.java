package com.example.CRUD.Controller;

import com.example.CRUD.Entity.Books;
import com.example.CRUD.Repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping
@RestController
public class BooksController {

    @Autowired
    BooksRepository booksRepository;

    @GetMapping
    public ResponseEntity<List<Books>> getAllBooks(){

            try{
                List<Books> booklList = new ArrayList<>();
                booksRepository.findAll().forEach(booklList::add);

                if(booklList.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(booklList,HttpStatus.OK);

            }catch(Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }


    @GetMapping ("/getBooksById/{id}")
            public ResponseEntity<Books> getBooksById(@PathVariable Long id){
        Optional<Books> bookData = booksRepository.findById(id);

        if(bookData.isPresent()){
            return new ResponseEntity<>(bookData.get(),HttpStatus.OK);
        }
                  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           }



    @PostMapping("/addBooks")
    public ResponseEntity<Books> addBooks(@RequestBody Books books){
        Books bookObj = booksRepository.save(books);

        return new ResponseEntity<>(bookObj,HttpStatus.OK);


    }
    @PostMapping("/updateBooksById/{id}")
    public ResponseEntity<Books> updateBooksById(@PathVariable Long id,@RequestBody Books newBookData){
        Optional<Books> oldbookData = booksRepository.findById(id);

         if(oldbookData.isPresent()){
             Books updateBookData = oldbookData.get();
             updateBookData.setName(newBookData.getName());
             updateBookData.setAuthor(newBookData.getAuthor());

             Books bookObj=booksRepository.save(updateBookData);
             return new ResponseEntity<>(bookObj,HttpStatus.OK);

         }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<HttpStatus> deleteBooks(@PathVariable Long id){
        booksRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
