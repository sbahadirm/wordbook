package com.tr.wordbook;

import com.tr.wordbook.domain.Kelime;
import com.tr.wordbook.service.entityservice.KelimeEntityService;
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
    KelimeEntityService wordBookEntityService;

    @Test
    public void test_findAll(){
        List<Kelime> kelimeList = wordBookEntityService.findAll();
        Assert.assertFalse(kelimeList.isEmpty());
    }

}
