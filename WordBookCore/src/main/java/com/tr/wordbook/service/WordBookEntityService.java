package com.tr.wordbook.service;

import com.tr.wordbook.dao.WordBookDao;
import com.tr.wordbook.domain.Word;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Koray PEKER
 * @since 0.0.1
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WordBookEntityService extends BaseEntityService<Word, WordBookDao> {

    private final static Logger logger = LoggerFactory.getLogger(WordBookEntityService.class);

    public WordBookEntityService() {
        super(WordBookDao.class);
    }

    public List<Word> findAllWordByText(String text){
        return getDao().findAllWordByText(text);
    }

}
