package zatribune.spring.example.webservices.data.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import zatribune.spring.example.webservices.data.entities.Category;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CategoryRepositoryIT {

    @Autowired
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        Category c1=new Category();
        c1.setName("Fruits");
        Category c2=new Category();
        c2.setName("Drinks");
        Category c3=new Category();
        c3.setName("Books");
        List<Category>list= StreamSupport.stream(categoryRepository.saveAll(List.of(c1,c2,c3))
                .spliterator(),false)
                .collect(Collectors.toList());
        assertEquals(3,list.size());
    }

    @Test
    public void findAll(){
        List<Category>anotherList=StreamSupport.stream(categoryRepository.findAll()
                .spliterator(),false)
                .collect(Collectors.toList());
        assertEquals(3,anotherList.size());
    }

    @Test
    public void findCategoriesByNameStartingWith(){
        List<Category>anotherList=StreamSupport.stream(categoryRepository.findCategoriesByNameStartingWith("f")
                .spliterator(),false)
                .collect(Collectors.toList());
        assertEquals(1,anotherList.size());
    }
}