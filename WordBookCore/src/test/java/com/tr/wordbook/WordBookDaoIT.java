package com.tr.wordbook;

import com.tr.wordbook.domain.Word;
import com.tr.wordbook.service.entityservice.WordBookEntityService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Koray PEKER
 * @since 0.0.1
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class WordBookDaoIT {

    @Autowired
    WordBookEntityService wordBookEntityService;

    @Test
    public void test_findAll(){
        List<Word> wordList = wordBookEntityService.findAll();
        Assert.assertFalse(wordList.isEmpty());
    }

}
