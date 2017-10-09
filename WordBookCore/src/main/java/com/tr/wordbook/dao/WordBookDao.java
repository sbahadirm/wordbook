package com.tr.wordbook.dao;

import com.tr.wordbook.domain.Word;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Koray PEKER
 * @since 0.0.1
 */
@Repository
public class WordBookDao extends BaseDao<Word> {

    public WordBookDao() {
        super(Word.class);
    }

    public List<Word> findAllWordByText(String text){
        Criteria criteria = getCurrentSession().createCriteria(Word.class);
        criteria.add(Restrictions.like("text", text, MatchMode.ANYWHERE));
        return criteria.list();
    }

}
